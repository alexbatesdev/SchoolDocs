package main

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
	"crypto/x509"
	"encoding/base64"
	"encoding/pem"
	"fmt"
	"os"
)

func main() {
	runTests()
}

func runTests() {
	path := `.\`
	privateKey01 := path + "private_key.pem"
	publicKey01 := path + "public_key.pem"
	fmt.Println("Private Key 01:", privateKey01)
	fmt.Println("Public Key 01:", publicKey01)

	file := path + "test.txt"
	encryptedFile := path + "base64_test.txt"
	decryptedFile := path + "decrypted_test.txt"

	message, err := os.ReadFile(file)
	if err != nil {
		fmt.Println("Error reading file:", err)
		return
	}
	fmt.Println("File:", string(message))

	base64Encrypted, err := rsaEncrypt(file, publicKey01, encryptedFile)
	if err != nil {
		fmt.Println("Error encrypting file:", err)
		return
	}
	fmt.Println("Encrypted:", base64Encrypted)

	decrypted, err := rsaDecrypt(encryptedFile, privateKey01, decryptedFile)
	if err != nil {
		fmt.Println("Error decrypting file:", err)
		return
	}
	fmt.Println("Decrypted:", decrypted)

	signature, err := signData(file, privateKey01)
	if err != nil {
		fmt.Println("Error signing data:", err)
		return
	}
	fmt.Println("Signature:", signature)

	valid, err := verifyData(file, signature, publicKey01)
	if err != nil {
		fmt.Println("Error verifying signature:", err)
		return
	}
	fmt.Println("Valid:", valid)
}

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
	err = os.WriteFile(encryptedFileName, []byte(base64Encrypted), 0644)
	if err != nil {
		return "", err
	}

	return base64Encrypted, nil
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
	err = os.WriteFile(decryptedFileName, []byte(message), 0644)
	if err != nil {
		return "", err
	}

	return message, nil
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
