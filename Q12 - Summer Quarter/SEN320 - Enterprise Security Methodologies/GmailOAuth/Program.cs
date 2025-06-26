using Google.Apis.Auth.OAuth2;
using Google.Apis.Gmail.v1;
using Google.Apis.Gmail.v1.Data;
using Google.Apis.Services;
using Google.Apis.Util.Store;
using System.Text;
using System;
using System.IO;
using Newtonsoft.Json.Linq;
using Google.Apis.Auth.OAuth2.Responses;
using Google.Apis.Auth.OAuth2.Flows;
using Twilio;
using Twilio.Rest.Verify.V2.Service;

string accountSid = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Your Account SID from www.twilio.com/console
string authToken = ""; // Your Auth Token from www.twilio.com/console


string pathServiceSid = "VAXXXXXXXXXXXXXXXX"; // Your Verify Service SID from www.twilio.com/console/verify/services

TwilioClient.Init(accountSid, authToken);

string GetPhoneNumberInput()
{
    bool validOption = false;
    string phone = "";
    while (!validOption)
    {
        Console.WriteLine("Enter phone number: (This service is only available in the US)");
        phone = Console.ReadLine();
        phone = phone.Replace(" ", "").Replace("-", "").Replace("(", "").Replace(")", "");
        // Validate phone number
        if (phone.Length != 10)
        {
            Console.WriteLine("Invalid phone number");
            continue;
        }

        try
        {
            Int64.Parse(phone);
            validOption = true;
        }
        catch (Exception e)
        {
            Console.WriteLine("Invalid phone number");
            Console.WriteLine("This next bit is for the nerds:");
            Console.WriteLine(e);
            continue;
        }
    }
    phone = "+1" + phone;

    return phone;
}

bool ValidateTwilioCode(string phone, string code)
{
    var vCheck = VerificationCheckResource.Create(
        to: phone,
        code: code,
        pathServiceSid: pathServiceSid
    );

    return vCheck.Status == "approved";
}

void SendTwilioCode(string phone)
{
    VerificationResource.Create(
        to: phone,
        channel: "sms",
        pathServiceSid: pathServiceSid
    );
}

void SavePhoneNumber(string phone, string accountName)
{
    string directoryPath = $"{accountName}.token.json";
    if (!Directory.Exists(directoryPath))
    {
        Directory.CreateDirectory(directoryPath);
    }
    string path = $"{directoryPath}/phone.txt";
    
    string base64Phone = Convert.ToBase64String(Encoding.UTF8.GetBytes(phone));

    File.WriteAllText(path, base64Phone);
}

void DeletePhoneNumber(string accountName)
{
    string directoryPath = $"{accountName}.token.json";
    string path = $"{directoryPath}/phone.txt";
    if (File.Exists(path))
    {
        File.Delete(path);
    }

    if (!File.Exists(directoryPath + "/Google.Apis.Auth.OAuth2.Responses.TokenResponse-user"))
    {
        Directory.Delete($"{accountName}.token.json", true);
    }
}

string GetPhoneNumber(string accountName)
{
    string path = $"{accountName}.token.json/phone.txt";
    if (File.Exists(path))
    {
        string phone = File.ReadAllText(path);
        phone = Encoding.UTF8.GetString(Convert.FromBase64String(phone));
        return phone;
    }
    return "";
}

bool GetCodeValidationInput(string phone, string accountName)
{
    bool validCode = false;
    while (!validCode)
    {
        Console.WriteLine("Enter code from Twilio: ");
        string code = Console.ReadLine();

        if (code.Length != 6)
        {
            Console.WriteLine("Invalid code");
            continue;
        }

        validCode = ValidateTwilioCode(phone, code);
        if (!validCode)
        {
            Console.WriteLine("Invalid code. Try again?");
            Console.WriteLine("1. Yes");
            Console.WriteLine("2. No");
            string option = Console.ReadLine();
            if (option == "2")
            {
                DeletePhoneNumber(accountName);
                return false;
            }
        }
        else
        {
            Console.WriteLine("Code validated!");
        }
    }
    return validCode;
}

Main();

void Main()
{
    while (true)
    {
        bool validOption = false;

        while (!validOption)
        {
            Console.WriteLine("What do you want to do?");
            Console.WriteLine("1. Add Gmail Account");
            Console.WriteLine("2. Search Gmail Messages");
            Console.WriteLine("3. Exit");
            string option = Console.ReadLine();

            switch (option)
            {
                case "1":
                    AddAccount();
                    validOption = true;
                    break;
                case "2":
                    string accountName = SelectAccount();
                    GmailService gmailAPIService = LoadGmailServiceFromFile(accountName);
                    Console.WriteLine("Input your search query:");
                    string query = Console.ReadLine();
                    var messages = SearchMessages(gmailAPIService, query);
                    PrintMessages(gmailAPIService, messages, query, 10, 1);
                    validOption = true;
                    break;
                case "3":
                    Console.WriteLine("Goodbye!");
                    return;
                default:
                    Console.WriteLine("Invalid option");
                    break;
            }
        }

    }
}

void AddAccount()
{
    bool validOption = false;
    string accountName = "";
    while (!validOption)
    {
        Console.WriteLine("Input your Gmail:");
        accountName = Console.ReadLine();
        if (accountName.Length == 0 || !accountName.Contains("@") || !accountName.Contains("gmail.com"))
        {
            Console.WriteLine("Invalid email");
            continue;
        }
        else
        {
            validOption = true;
        }
    }

    bool validCode = false;
    // Check if we have the token file
    if (!File.Exists($"{accountName}.token.json/Google.Apis.Auth.OAuth2.Responses.TokenResponse-user"))
    {
        Console.WriteLine("Welcome new user!");
        string phone = GetPhoneNumberInput();
        SavePhoneNumber(phone, accountName);
        SendTwilioCode(phone);
        validCode = GetCodeValidationInput(phone, accountName);
    }
    else
    {
        Console.WriteLine("Welcome back old friend!");
        string phone = GetPhoneNumber(accountName);
        SendTwilioCode(phone);
        validCode = GetCodeValidationInput(phone, accountName);

    }

    if (!validCode)
    {
        Console.WriteLine("Failed to validate code. Please try again later.");
        return;
    }
    Console.WriteLine("Opening OAuth consent screen...");
    GetGmailAPIService(accountName);
}

string SelectAccount()
{
    Console.WriteLine("Select an account's inbox to search:");
    Dictionary<int, string> accounts = ListAccounts();
    bool validOption = false;
    string accountName = "";

    while (!validOption)
    {
        string option = Console.ReadLine();
        try
        {
            int.Parse(option);
        }
        catch (Exception e)
        {
            Console.WriteLine("Invalid option");
            continue;
        }


        if (accounts.ContainsKey(int.Parse(option)))
        {
            accountName = accounts[int.Parse(option)];
            validOption = true;
        }
        else
        {
            Console.WriteLine("Invalid option");
        }
    }

    return accountName;
}

void PrintMessages(GmailService gmailAPIService, IList<Message> messages, string query, int pageSize, int pageNumber_in)
{
    if (messages == null || messages.Count == 0)
    {
        Console.WriteLine("No messages found.");
        return;
    }

    bool userWantsToLeave = false;
    int pageNumber = pageNumber_in;

    while (!userWantsToLeave)
    {
        // Calculate the starting index
        int startIndex = (pageNumber - 1) * pageSize;

        // Get the messages for the current page
        var pagedMessages = messages.Skip(startIndex).Take(pageSize);

        // Print the message fromEmail, subject, and a count of the number of times the query appears in the message
        foreach (var message in pagedMessages)
        {
            Console.WriteLine("-------------");
            var specificMessage = gmailAPIService.Users.Messages.Get("me", message.Id).Execute();
            string fromEmail = "";
            string subject = "";
            string body = "";

            foreach (var header in specificMessage.Payload.Headers)
            {
                if (header.Name == "From")
                {
                    fromEmail = header.Value;
                }
                else if (header.Name == "Subject")
                {
                    subject = header.Value;
                }
            }

            if (specificMessage.Payload.Parts != null)
            {
                foreach (var part in specificMessage.Payload.Parts)
                {
                    if (part.MimeType == "text/plain")
                    {
                        string base64String = part.Body.Data.Trim();

                        // Ensure the base64 string is properly padded
                        base64String = base64String.Replace('-', '+').Replace('_', '/');

                        try
                        {
                            byte[] data = Convert.FromBase64String(base64String);
                            body = Encoding.UTF8.GetString(data);
                        }
                        catch (FormatException ex)
                        {
                            Console.WriteLine($"Failed to decode base64 string: {ex.Message}");
                            return;
                        }
                    }
                }
            }
            else if (specificMessage.Payload.Body != null)
            {
                string base64String = specificMessage.Payload.Body.Data.Trim();

                // Ensure the base64 string is properly padded
                base64String = base64String.Replace('-', '+').Replace('_', '/');

                try
                {
                    byte[] data = Convert.FromBase64String(base64String);
                    body = Encoding.UTF8.GetString(data);

                }
                catch (FormatException ex)
                {
                    Console.WriteLine($"Failed to decode base64 string: {ex.Message}");
                    return;
                }
            }

            // Count the number of times the query appears in the message body
            string lower_body = body.ToLower();
            int queryCount = lower_body.Split(new string[] { query.ToLower() }, StringSplitOptions.None).Length - 1;

            // Print the details
            Console.WriteLine($"Body: {body}");
            // save message body to file
            Console.WriteLine("-----------------");
            Console.WriteLine($"From: {fromEmail}");
            Console.WriteLine($"Subject: {subject}");
            Console.WriteLine($"Query '{query}' appears {queryCount} times in the message body. {(queryCount == 0 ? "(The query may exist in the html version of this message if it's not already in the subject or sender email)" : "")}");
            Console.WriteLine("Press any key to continue...");
            Console.ReadKey();
        }

        Console.WriteLine("What do you want to do?");
        Console.WriteLine("1. Next Page");
        Console.WriteLine("2. Back");

        bool validOption = false;

        while (!validOption)
        {
            string option = Console.ReadLine();

            switch (option)
            {
                case "1":
                    pageNumber++;
                    validOption = true;
                    break;
                case "2":
                    userWantsToLeave = true;
                    validOption = true;
                    break;
                default:
                    Console.WriteLine("Invalid option");
                    break;
            }
        }
    }
}

Dictionary<int, string> ListAccounts()
{
    int index = 0;
    string[] directories = Directory.GetDirectories(Directory.GetCurrentDirectory());
    Dictionary<int, string> accounts = new Dictionary<int, string>();
    foreach (var directory in directories)
    {
        if (directory.EndsWith(".token.json"))
        {
            string[] splitDirectory = directory.Split("\\");
            string accountName = splitDirectory[splitDirectory.Length - 1].Replace(".token.json", "");
            Console.WriteLine($"{index}. {accountName}");
            accounts.Add(index, accountName);
            index++;
        }
    }
    return accounts;
}

GmailService LoadGmailServiceFromFile(string accountName)
{
    UserCredential credential = LoadUserCredentialFromFile(accountName);
    if (credential == null)
    {
        Console.WriteLine("Failed to load user credentials.");
        return null;
    }

    string ApplicationName = "Funny Oauth Gmail App";
    var gmailAPIService = new GmailService(new BaseClientService.Initializer
    {
        HttpClientInitializer = credential,
        ApplicationName = ApplicationName
    });

    return gmailAPIService;
}

UserCredential LoadUserCredentialFromFile(string accountName)
{
    string GmailAPICredentialsFile = "client_secret_347006066390-onfcqe48pcgtqmb2cqgbpagq6qh6dinh.apps.googleusercontent.com.json";
    string credentialsDirectoryPath = $"{accountName}.token.json";
    string credentialsFilePath = $"{credentialsDirectoryPath}/Google.Apis.Auth.OAuth2.Responses.TokenResponse-user";
    if (!File.Exists(credentialsFilePath))
    {
        Console.WriteLine("Credentials file not found.");
        return null;
    }

    try
    {
        // Load the credentials from the file
        var json = File.ReadAllText(credentialsFilePath);
        var tokenData = JObject.Parse(json);

        // Extract the tokens and expiration information from the JSON
        var accessToken = (string)tokenData["access_token"];
        var refreshToken = (string)tokenData["refresh_token"];
        var tokenType = (string)tokenData["token_type"];
        var expiresIn = (int?)tokenData["expires_in"];

        if (accessToken == null || refreshToken == null || tokenType == null || expiresIn == null)
        {
            Console.WriteLine("Invalid credentials data.");
            return null;
        }

        // Create a DateTime representing when the token will expire
        var expiry = DateTime.UtcNow.AddSeconds(expiresIn.Value);

        // Create and return a UserCredential object
        var tokenResponse = new TokenResponse
        {
            AccessToken = accessToken,
            RefreshToken = refreshToken,
            TokenType = tokenType,
            ExpiresInSeconds = expiresIn
        };

        var credential = new UserCredential(
            new GoogleAuthorizationCodeFlow(new GoogleAuthorizationCodeFlow.Initializer
            {
                ClientSecrets = GoogleClientSecrets.FromFile(GmailAPICredentialsFile).Secrets,
                Scopes = new[] { GmailService.Scope.GmailReadonly },
                DataStore = new FileDataStore(credentialsDirectoryPath, true)
            }),
            "user",
            tokenResponse
        );

        return credential;
    }
    catch (Exception e)
    {
        Console.WriteLine($"Error loading credentials: {e.Message}");
        return null;
    }
}

UserCredential GetUserCredential(string accountName)
{
    string[] Scopes = { GmailService.Scope.GmailReadonly };
    string GmailAPICredentialsFile = "client_secret_347006066390-onfcqe48pcgtqmb2cqgbpagq6qh6dinh.apps.googleusercontent.com.json";
    UserCredential userCredentials;

    try
    {
        using (var stream = new FileStream(GmailAPICredentialsFile, FileMode.Open, FileAccess.Read))
        {
            string credentialsFolderPath = $"{accountName}.token.json";
            userCredentials = GoogleWebAuthorizationBroker.AuthorizeAsync(
                GoogleClientSecrets.FromStream(stream).Secrets,
                Scopes,
                "user",
                CancellationToken.None,
                new FileDataStore(credentialsFolderPath, true)).Result;
        }

        return userCredentials;
    }
    catch (Exception e)
    {
        Console.WriteLine("Failed to authenticate. Please try again.");
        Console.WriteLine("This next bit is for the nerds:");
        Console.WriteLine(e);
        // Delete the folder if it exists
        if (Directory.Exists($"{accountName}.token.json"))
        {
            Directory.Delete($"{accountName}.token.json", true);
        }
        return null;
    }
}

GmailService GetGmailAPIService(string accountName)
{
    string ApplicationName = "Funny Oauth Gmail App";
    UserCredential userCredentials = GetUserCredential(accountName);

    if (userCredentials == null)
    {
        Console.WriteLine("Failed to authenticate.");
        return null;
    }

    var gmailAPIService = new GmailService(new BaseClientService.Initializer
    {
        HttpClientInitializer = userCredentials,
        ApplicationName = ApplicationName
    });

    return gmailAPIService;
}

IList<Message> SearchMessages(GmailService gmailAPIService, string query)
{
    UsersResource.MessagesResource.ListRequest request = gmailAPIService.Users.Messages.List("me");
    request.Q = $"subject:{query} OR from:{query} OR {query}";
    var response = request.Execute();
    return response.Messages;
}