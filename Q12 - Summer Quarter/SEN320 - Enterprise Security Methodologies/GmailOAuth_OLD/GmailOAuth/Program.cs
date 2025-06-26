using Google.Apis.Auth.OAuth2;
using Google.Apis.Gmail.v1;
using Google.Apis.Gmail.v1.Data;
using Google.Apis.Services;
using Google.Apis.Util.Store;
using System.Text;
 
 
string accountName = "mcbuzzer@gmail.com";
GmailService gmailAPIService = GetGmailAPIService(accountName);
Console.WriteLine(gmailAPIService.ApiKey);

GetGmailLabels(gmailAPIService);
GetGmailMessages1(gmailAPIService);

static GmailService GetGmailAPIService(string accountName)
{
    string[] Scopes = { GmailService.Scope.GmailReadonly };
    string ApplicationName = "SEN320 2023 Q3 - Oauth Gmail";
 
    string GmailAPICredentialsFile = "client_secret_347006066390-onfcqe48pcgtqmb2cqgbpagq6qh6dinh.apps.googleusercontent.com.json";
    UserCredential userCredentials;
    try
    {
        using (var stream = new FileStream(GmailAPICredentialsFile, FileMode.Open, FileAccess.Read))
        {
            string crendentialsFolderPath = $"{accountName}.token.json";
            userCredentials = GoogleWebAuthorizationBroker.AuthorizeAsync(
                GoogleClientSecrets.FromStream(stream).Secrets,
                Scopes,
                "user",
                CancellationToken.None,
                new FileDataStore(crendentialsFolderPath, true)).Result;
        }
 
        var gmailAPIService = new GmailService(new BaseClientService.Initializer
        {
            HttpClientInitializer = userCredentials,
            ApplicationName = ApplicationName
        });
 
        return gmailAPIService;
 
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
 
    return null;
}

static void GetGmailLabels(GmailService gmailAPIService)
{
    try
    {
        // Define parameters of request.
        UsersResource.LabelsResource.ListRequest request = gmailAPIService.Users.Labels.List("me");
        IList<Label> labels = request.Execute().Labels;
 
        Console.WriteLine("Labels:");
        if (labels == null || labels.Count == 0)
        {
            Console.WriteLine("No labels found.");
            return;
        }
        foreach (var labelItem in labels)
        {
            Console.WriteLine("{0}", labelItem.Name);
        }
    }
    catch (Exception e)
    {
        Console.WriteLine(e);
    }
}

static IList<Message> GetGmailMessages1(GmailService gmailAPIService)
{
    UsersResource.LabelsResource.ListRequest request2 = gmailAPIService.Users.Labels.List("me");
    var msg2 = gmailAPIService.Users.Messages.List(request2.UserId);
 
    var messages2 = msg2.Execute();
 
    foreach (var m in messages2.Messages)
    {
        // var specificMessage = gmailAPIService.Users.Messages.Get(request.UserId, m.Id).Execute();
        var specificMessage = gmailAPIService.Users.Messages.Get("me", m.Id).Execute();
 
        Console.WriteLine($"\n--- Msg ID: {specificMessage.Id}, Headers:");
 
        foreach (var header in specificMessage.Payload.Headers)
        {
            Console.WriteLine($"Name: {header.Name}, Value: {header.Value}");
        }
 
        Console.WriteLine("\nBody:");
        //Console.WriteLine(specificMessage.Payload.Body.Data.;
    }

    return null;
}