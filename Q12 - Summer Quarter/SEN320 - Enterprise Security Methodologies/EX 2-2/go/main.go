package main

import (
	"bufio"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha1"
	"encoding/base64"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

func main() {
	var running bool = true
	fmt.Println("The AES-itron 9000! Encrypting and Decrypting data with the power of AES!")
	for running {
		user_bool := getBoolInput("Are we Encryptying or Decrypting?", "E", "D")
		if user_bool {
			user_input := getStringInput("Input string to be encrypted: ")
			data := user_input
			user_input = getStringInput("Input key: ")
			key := sha1Digest(user_input)[:16]
			encrypted_data := encrypt(data, key)
			fmt.Println("Encrypted data:", encrypted_data)
		} else {
			encodedData := getStringInput("Input base64-encoded encrypted string: ")

			key_input := getStringInput("Input key: ")
			key := sha1Digest(key_input)
			key = key[:16]

			decryptedText := decrypt(encodedData, key)
			fmt.Println("Decrypted text:", decryptedText)

		}
	}
}

func encrypt(data string, key string) string {
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

	return binaryStringToBase64Binary(BytesToBinaryString(encrypted_data))
}

func decrypt(data string, key string) string {
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

	decryptedText := BytesToString(decryptedData)
	return decryptedText
}

func getStringInput(display_text string) string {
	var reader = bufio.NewReader(os.Stdin)
	var user_input string
	fmt.Print(display_text + "\n>")
	user_input, _ = reader.ReadString('\n')
	user_input = strings.TrimSpace(user_input)

	return user_input
}

func getBoolInput(display_text string, true_val string, false_val string) bool {
	for true {
		user_input := getStringInput(display_text + " (" + true_val + "/" + false_val + ")")
		user_input = strings.ToLower(user_input)
		if user_input == strings.ToLower(true_val) {
			return true
		} else if user_input == strings.ToLower(false_val) {
			return false
		} else {
			fmt.Println("Invalid input, please input " + true_val + " or " + false_val)
		}
	}
	// This return will never get executed
	return false
}

func matchKeyLengthToDataLength(data string, key string) string {
	// Compare the length of the key and the data
	ratio := float64(len(data)) / float64((len(key)))
	if ratio > 1 {
		// If the key is shorter than the data, repeat the key until it is the same length as the data
		for len(key) < len(data) {
			key += key
		}
		// If the key is longer than the data, cut the key to the same length as the data
		key = key[:len(data)]
	} else {
		// If the key is longer than the data, cut the key to the same length as the data
		key = key[:len(data)]
	}
	return key
}

func binaryToText(binary string) string {
	// Convert binary to text
	text := ""
	for i := 0; i < len(binary); i += 8 {
		// Convert the binary to a byte
		b := binary[i : i+8]
		// Parse the byte as an integer
		byteValue, err := strconv.ParseUint(b, 2, 8)
		if err != nil {
			fmt.Println("Error parsing binary:", err)
			continue
		}
		// Convert the integer to a rune
		r := rune(byteValue)
		// Add the rune to the text
		text += string(r)
	}
	return text
}

func applyXOR(binary_data string, binary_key string) string {
	modified_data := ""
	for i := 0; i < len(binary_data); i++ {
		if binary_data[i] == binary_key[i] {
			modified_data += "0"
		} else {
			modified_data += "1"
		}
	}
	return modified_data
}

func binaryStringToBase64Binary(binary string) string {
	// Convert the binary string to a byte array
	var bytes []byte
	for i := 0; i < len(binary); i += 8 {
		byteStr := binary[i : i+8]
		byteVal, err := strconv.ParseUint(byteStr, 2, 8)
		if err != nil {
			fmt.Println("Error parsing binary string:", err)
			return ""
		}
		bytes = append(bytes, byte(byteVal))
	}

	// Convert the byte array to a base64 string
	base64Str := base64.StdEncoding.EncodeToString(bytes)
	return base64Str
}

func base64BinaryToBinaryString(base64Str string) string {
	// Decode the base64 string to a byte array
	bytes, err := base64.StdEncoding.DecodeString(base64Str)
	if err != nil {
		fmt.Println("Error decoding base64 string:", err)
		return ""
	}

	// Convert the byte array to a binary string
	var binaryStr string
	for _, b := range bytes {
		binaryStr += fmt.Sprintf("%08b", b)
	}

	return binaryStr
}

func sha1Digest(data string) string {
	// Convert the data to a byte array
	data_bytes := []byte(data)
	// Create the SHA-1 hash
	hash := sha1.Sum(data_bytes)
	// Convert the hash to a string
	hash_string := fmt.Sprintf("%x", hash)
	return hash_string
}

func BytesToString(b []byte) string {
	return string(b)
}

func BytesToBinaryString(b []byte) string {
	var result string
	for _, byteValue := range b {
		result += fmt.Sprintf("%08b", byteValue)
	}
	return result
}
