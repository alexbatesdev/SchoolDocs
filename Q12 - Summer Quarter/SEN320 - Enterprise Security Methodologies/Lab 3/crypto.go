package main

import (
	"crypto"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha1"
	"crypto/sha256"
	"crypto/x509"
	"encoding/base64"
	"encoding/pem"
	"fmt"
	"io"
	"os"
)

func readPEMFile(filename string) (*pem.Block, error) {
	data, err := os.ReadFile(filename)
	if err != nil {
		return nil, err
	}
	block, _ := pem.Decode(data)
	if block == nil {
		return nil, fmt.Errorf("failed to decode PEM block from file %s", filename)
	}
	return block, nil
}

func getPrivateKey(privateKeyFile string) (*rsa.PrivateKey, error) {
	block, err := readPEMFile(privateKeyFile)
	if err != nil {
		return nil, err
	}
	privKey, err := x509.ParsePKCS8PrivateKey(block.Bytes)
	if err != nil {
		return nil, err
	}
	rsaPrivKey := privKey.(*rsa.PrivateKey)
	return rsaPrivKey, nil
}

func getPublicKey(publicKeyFile string) (*rsa.PublicKey, error) {
	block, err := readPEMFile(publicKeyFile)
	if err != nil {
		return nil, err
	}
	pubKey, err := x509.ParsePKIXPublicKey(block.Bytes)
	if err != nil {
		return nil, err
	}
	rsaPubKey := pubKey.(*rsa.PublicKey)
	return rsaPubKey, nil
}

func rsaEncrypt(cleartextFile, publicKeyFile, encryptedFileName string) (string, error) {
	message, err := os.ReadFile(cleartextFile)
	if err != nil {
		return "", err
	}

	rsaPubKey, err := getPublicKey(publicKeyFile)

	encryptedBytes, err := rsa.EncryptPKCS1v15(rand.Reader, rsaPubKey, message)
	if err != nil {
		return "", err
	}

	base64Encrypted := base64.StdEncoding.EncodeToString(encryptedBytes)
	if encryptedFileName != "" {
		err = os.WriteFile(encryptedFileName, []byte(base64Encrypted), 0644)
		if err != nil {
			return "", err
		}
	}

	return base64Encrypted, nil
}

func rsaEncryptBytes(bytes []byte, publicKeyFile string) ([]byte, error) {
	rsaPubKey, err := getPublicKey(publicKeyFile)
	if err != nil {
		fmt.Println("Error getting public key:")
		return nil, err
	}

	encryptedBytes, err := rsa.EncryptPKCS1v15(rand.Reader, rsaPubKey, bytes)
	if err != nil {
		fmt.Println("Error encrypting bytes:")
		return nil, err
	}

	return encryptedBytes, nil
}

func rsaDecrypt(filename, privateKeyFile, decryptedFileName string) (string, error) {
	encryptedData, err := os.ReadFile(filename)
	if err != nil {
		return "", err
	}
	encryptedBytes, err := base64.StdEncoding.DecodeString(string(encryptedData))
	if err != nil {
		return "", err
	}

	rsaPrivKey, err := getPrivateKey(privateKeyFile)

	decryptedBytes, err := rsa.DecryptPKCS1v15(rand.Reader, rsaPrivKey, encryptedBytes)
	if err != nil {
		return "", err
	}
	message := string(decryptedBytes)

	if decryptedFileName != "" {
		err = os.WriteFile(decryptedFileName, []byte(message), 0644)
		if err != nil {
			return "", err
		}
	}

	return message, nil
}

func rsaDecryptBytes(encryptedBytes []byte, privateKeyFile string) ([]byte, error) {
	rsaPrivKey, err := getPrivateKey(privateKeyFile)
	if err != nil {
		return nil, err
	}

	decryptedBytes, err := rsa.DecryptPKCS1v15(rand.Reader, rsaPrivKey, encryptedBytes)
	if err != nil {
		return nil, err
	}

	return decryptedBytes, nil
}

func signData(filename, privateKeyFile string) (string, error) {
	data, err := os.ReadFile(filename)
	if err != nil {
		return "", err
	}
	hashed := sha256.Sum256(data)

	rsaPrivKey, err := getPrivateKey(privateKeyFile)

	signature, err := rsa.SignPKCS1v15(rand.Reader, rsaPrivKey, crypto.SHA256, hashed[:])
	if err != nil {
		return "", err
	}
	return base64.StdEncoding.EncodeToString(signature), nil
}

func signString(data string, privateKeyFile string) (string, error) {
	hashed := sha256.Sum256([]byte(data))

	rsaPrivKey, err := getPrivateKey(privateKeyFile)
	if err != nil {
		return "", err
	}

	signature, err := rsa.SignPKCS1v15(rand.Reader, rsaPrivKey, crypto.SHA256, hashed[:])
	if err != nil {
		return "", err
	}

	return base64.StdEncoding.EncodeToString(signature), nil
}

func verifyData(filename, signature, publicKeyFile string) (bool, error) {
	data, err := os.ReadFile(filename)
	if err != nil {
		return false, err
	}
	hashed := sha256.Sum256(data)

	rsaPubKey, err := getPublicKey(publicKeyFile)

	signatureBytes, err := base64.StdEncoding.DecodeString(signature)
	if err != nil {
		return false, err
	}
	err = rsa.VerifyPKCS1v15(rsaPubKey, crypto.SHA256, hashed[:], signatureBytes)

	return err == nil, err
}

func verifyString(data, signature, publicKeyFile string) (bool, error) {
	hashed := sha256.Sum256([]byte(data))

	rsaPubKey, err := getPublicKey(publicKeyFile)
	if err != nil {
		fmt.Println("Error getting public key:")
		return false, err
	}

	signatureBytes, err := base64.StdEncoding.DecodeString(signature)
	if err != nil {
		fmt.Println("Error decoding signature:")
		return false, err
	}

	err = rsa.VerifyPKCS1v15(rsaPubKey, crypto.SHA256, hashed[:], signatureBytes)
	return err == nil, err
}

func base64Encode(data []byte) string {
	return base64.StdEncoding.EncodeToString(data)
}

func base64Decode(data string) ([]byte, error) {
	return base64.StdEncoding.DecodeString(data)
}

func aesEncrypt(data string, key string) string {
	// Convert the data to a byte array
	data_bytes := []byte(data)
	// Convert the key to a byte array
	key_bytes := []byte(key)
	c, err := aes.NewCipher(key_bytes)

	if err != nil {
		fmt.Println("Error creating AES cipher:", err)
		return ""
	}

	gcm, err := cipher.NewGCM(c)

	if err != nil {
		fmt.Println("Error creating GCM:", err)
		return ""
	}

	nonce := make([]byte, gcm.NonceSize())

	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		fmt.Println("Error creating nonce:", err)
		return ""
	}

	encrypted_data := gcm.Seal(nonce, nonce, data_bytes, nil)

	return base64Encode(encrypted_data)
}

func aesDecrypt(data string, key string) string {
	key_bytes := []byte(key)
	data_bytes, err := base64.StdEncoding.DecodeString(data)

	if err != nil {
		fmt.Println("Error decoding base64 string:", err)
		return ""
	}

	c, err := aes.NewCipher(key_bytes)
	if err != nil {
		fmt.Println("Error creating AES cipher:", err)
		return ""
	}

	gcm, err := cipher.NewGCM(c)
	if err != nil {
		fmt.Println("Error creating GCM:", err)
		return ""
	}

	nonceSize := gcm.NonceSize()
	nonce, cypherText := data_bytes[:nonceSize], data_bytes[nonceSize:]

	decryptedData, err := gcm.Open(nil, nonce, cypherText, nil)
	if err != nil {
		fmt.Println("Error decrypting data:", err)
		return ""
	}

	return string(decryptedData)
}

func sha1Digest(data string) string {
	hash := sha1.New()
	hash.Write([]byte(data))
	return fmt.Sprintf("%x", hash.Sum(nil))
}

func genUUID() string {
	uuid := make([]byte, 16)
	_, err := rand.Read(uuid)
	if err != nil {
		fmt.Println("Error generating UUID:", err)
		return ""
	}
	uuid[8] = 0x80
	uuid[4] = 0x40
	return fmt.Sprintf("%x-%x-%x-%x-%x", uuid[0:4], uuid[4:6], uuid[6:8], uuid[8:10], uuid[10:])
}
