using Twilio;
using Twilio.Rest.Verify.V2.Service;
 
 
string accountSid = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Your Account SID from www.twilio.com/console
string authToken = ""; // Your Auth Token from www.twilio.com/console
 
// you must go here: https://console.twilio.com/us1/develop/verify/services to find your Service SID aka pathServicesID below:
// TWILIO_PATH_SERVICE_ID (if you want to use environment variables)
string pathServiceSid = "VAXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Your Verify Service SID from www.twilio.com/console/verify/services
string toPhone = "+15555555555"; // Your phone number to send the verification code to, in E.164 format (e.g., +15555555555)

TwilioClient.Init(accountSid, authToken);
 
var vResource = VerificationResource.Create(
    to: toPhone,
    channel: "sms",
    pathServiceSid: pathServiceSid
);
 
 
Console.WriteLine(vResource.Lookup);
Console.WriteLine(vResource.To);
Console.WriteLine(vResource.Sid);
Console.WriteLine(vResource.Status);
 
 
Console.WriteLine("Enter code from Twilio: ");
string code = Console.ReadLine();
 
var vCheck = VerificationCheckResource.Create(
    to: toPhone,
    code: code,
    pathServiceSid: pathServiceSid
);
 
Console.WriteLine(vCheck.Status);