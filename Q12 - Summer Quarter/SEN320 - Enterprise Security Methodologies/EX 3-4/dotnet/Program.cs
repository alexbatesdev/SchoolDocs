// See https://aka.ms/new-console-template for more information
using System.Security.Cryptography;
using System.Text;

RunTests();

void RunTests() {
    string path = @"C:\Users\Alex\Documents\My_Documents__\School Docs\Q12 - Summer Quarter\SEN320 - Enterprise Security Methodologies\EX 3-4\dotnet\";
    string privateKey01 = path + "alice_private.pem";
    string publicKey01 = path + "alice_public.pem";
    string privateKey02 = path + "bob_private.pem";
    string publicKey02 = path + "bob_public.pem";
    
    
    string file = path + "test.txt";
    string encryptedFile = path + "base64_test.txt";
    string decryptedFile = path + "decrypted_test.txt";
    Console.WriteLine("File: " + File.ReadAllText(file).Trim());

    string base64 = RSAEncrypt(file, publicKey01, encryptedFile);
    Console.WriteLine("Encrypted: " + base64);

    string decrypted = RSADecrypt(encryptedFile, privateKey01, decryptedFile);
    Console.WriteLine("Decrypted: " + decrypted);

    string signature = SignData(file, privateKey01);
    Console.WriteLine("Signature: " + signature);

    bool valid = VerifyData(file, signature, publicKey01);
    Console.WriteLine("Valid: " + valid);

}

string RSAEncrypt(string cleartextFile, string publicKeyFile, string encryptedFileName) {
    string message = File.ReadAllText(cleartextFile).Trim();
    byte[] bytesToEncrypt = Encoding.UTF8.GetBytes(message);
    byte[] encryptedBytes;
    string base64;

    using (var rsa = RSA.Create()) {
        rsa.ImportFromPem(File.ReadAllText(publicKeyFile).Trim());
        encryptedBytes = rsa.Encrypt(bytesToEncrypt, RSAEncryptionPadding.Pkcs1);
        base64 = Convert.ToBase64String(encryptedBytes);
    }

    File.WriteAllText(encryptedFileName, base64);

    return base64;
}

string RSADecrypt(string filename, string privateKeyFile, string decryptedFileName) {
    string base64 = File.ReadAllText(filename).Trim();
    byte[] encryptedBytes = Convert.FromBase64String(base64);
    byte[] decryptedBytes;
    string message;

    using (var rsa = RSA.Create()) {
        rsa.ImportFromEncryptedPem(File.ReadAllText(privateKeyFile).Trim(), "pass");
        decryptedBytes = rsa.Decrypt(encryptedBytes, RSAEncryptionPadding.Pkcs1);
        message = Encoding.UTF8.GetString(decryptedBytes);
    }

    File.WriteAllText(decryptedFileName, message);

    return message;
}

string SignData(string filename, string privateKeyFile) {
    string data = File.ReadAllText(filename).Trim();
    byte[] bytesToSign = Encoding.Unicode.GetBytes(data);
    byte[] signature;

    using (var rsa = RSA.Create()) {
        rsa.ImportFromEncryptedPem(File.ReadAllText(privateKeyFile).Trim(), "pass");
        signature = rsa.SignData(bytesToSign, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);
    }

    return Convert.ToBase64String(signature);
}

bool VerifyData(string filename, string signature, string publicKeyFile)
{
    string data = File.ReadAllText(filename).Trim();
    bool valid;
 
    using (var rsa = RSA.Create())
    {
        rsa.ImportFromPem(File.ReadAllText(publicKeyFile).Trim());
        valid = rsa.VerifyData(Encoding.Unicode.GetBytes(data), Convert.FromBase64String(signature), HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);
    }
 
    return valid;
}