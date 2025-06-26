// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");

Encrypt();

static void Encrypt(){
    var plainText = Console.ReadLine().Trim();
    var secretKey = Console.ReadLine().Trim();

    string encryptedText = EncryptDecryptXOR(plainText, secretKey);
    Console.WriteLine(encryptedText);
}

// static void Decrypt(){

// }

static byte EncryptDecryptXOR(string data, string key){
    var textArray = data.ToCharArray();
    var keyArray = new char[data.Length];
    int key_index = 0;

    for(int i = 0; i < data.Length; i++){
        if(key_index >= key.Length){
            key_index = 0;
        }
        keyArray[i] = key.ToCharArray()[key_index];
        key_index++;
    }

    var result = new byte[data.Length];

    for(int i = 0; i < data.Length; i++){
        result[i] = (byte)(textArray[i] ^ keyArray[i]);
    }

    return result;
}

