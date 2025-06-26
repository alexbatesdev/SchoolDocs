// This code was written by an AI assistant

using System;
using System.IO;
using System.Security.Cryptography;
using System.Text;

Console.WriteLine("The AES-itron 9000! Encrypting and Decrypting data with the power of AES!");

while (true)
{
    bool encryptMode = GetBoolInput("Are we Encrypting or Decrypting?", "E", "D");

    if (encryptMode)
    {
        string plainText = GetStringInput("Input string to be encrypted: ");
        string keyInput = GetStringInput("Input key: ");
        string encryptedData = EncryptString(plainText, keyInput);
        Console.WriteLine("Encrypted data: " + encryptedData);
    }
    else
    {
        string encryptedText = GetStringInput("Input base64-encoded encrypted string: ");
        string keyInput = GetStringInput("Input key: ");
        string decryptedData = DecryptString(encryptedText, keyInput);
        Console.WriteLine("Decrypted text: " + decryptedData);
    }
}

string GetStringInput(string displayText)
{
    Console.Write(displayText + "\n> ");
    return Console.ReadLine().Trim();
}

bool GetBoolInput(string displayText, string trueVal, string falseVal)
{
    while (true)
    {
        string userInput = GetStringInput(displayText + " (" + trueVal + "/" + falseVal + ")").ToLower();
        if (userInput == trueVal.ToLower())
        {
            return true;
        }
        else if (userInput == falseVal.ToLower())
        {
            return false;
        }
        else
        {
            Console.WriteLine("Invalid input, please input " + trueVal + " or " + falseVal);
        }
    }
}

string EncryptString(string plainText, string key)
{
    byte[] keyBytes = Encoding.UTF8.GetBytes(sha1Digest(key)).AsSpan(0, 16).ToArray();
    byte[] plainBytes = Encoding.UTF8.GetBytes(plainText);

    using (Aes aes = Aes.Create())
    {
        aes.Key = keyBytes;
        aes.Mode = CipherMode.ECB;
        aes.Padding = PaddingMode.PKCS7;

        using (ICryptoTransform encryptor = aes.CreateEncryptor())
        {
            byte[] encryptedBytes = encryptor.TransformFinalBlock(plainBytes, 0, plainBytes.Length);
            return Convert.ToBase64String(encryptedBytes);
        }
    }
}

string DecryptString(string encryptedText, string key)
{
    byte[] keyBytes = Encoding.UTF8.GetBytes(sha1Digest(key)).AsSpan(0, 16).ToArray();
    byte[] encryptedBytes = Convert.FromBase64String(encryptedText);

    using (Aes aes = Aes.Create())
    {
        aes.Key = keyBytes;
        aes.Mode = CipherMode.ECB;
        aes.Padding = PaddingMode.PKCS7;

        using (ICryptoTransform decryptor = aes.CreateDecryptor())
        {
            byte[] decryptedBytes = decryptor.TransformFinalBlock(encryptedBytes, 0, encryptedBytes.Length);
            return Encoding.UTF8.GetString(decryptedBytes);
        }
    }
}

string sha1Digest(string data)
{
    using (SHA1 sha1 = SHA1.Create())
    {
        byte[] hashBytes = sha1.ComputeHash(Encoding.UTF8.GetBytes(data));
        StringBuilder sb = new StringBuilder();
        foreach (byte b in hashBytes)
        {
            sb.Append(b.ToString("x2"));
        }
        return sb.ToString();
    }
}

// This code was written by an AI assistant
