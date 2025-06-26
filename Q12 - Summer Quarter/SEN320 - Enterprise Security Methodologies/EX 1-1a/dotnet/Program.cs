using System.Security.Cryptography;
using System.Text;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");

String cleartext = "Hello from SEN320";

using (var md5Hash = MD5.Create()) {
    var sourceBytes = Encoding.UTF8.GetBytes(cleartext);
    var hashBytes = md5Hash.ComputeHash(sourceBytes);
    var hash = BitConverter.ToString(hashBytes).Replace("-", String.Empty);
    Console.WriteLine($"The MD5 hash of '{cleartext}' is: {hash}.");
}

// vvv - Cool sexy way to do it (Mathew's Code) - vvv

// using System.Security.Cryptography;
// using System.Text;

// string plaintext = "Hello from SEN320";

// byte[] sourceBytes = Encoding.UTF8.GetBytes(plaintext);
// byte[] hashBytes = MD5.HashData(sourceBytes);
// string hash = BitConverter.ToString(hashBytes).Replace("-", string.Empty);

// Console.WriteLine($"The MD5 hash of '{plaintext}' is: {hash}");
