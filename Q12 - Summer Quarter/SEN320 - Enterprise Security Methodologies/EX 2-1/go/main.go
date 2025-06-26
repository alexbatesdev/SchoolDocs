package main

import (
	"bufio"
	"encoding/base64"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	var running bool = true
	fmt.Println("The XOR-inator 3000! Encrypting and Decrypting data with the power of XOR!")
	for running {
		user_bool := getBoolInput("Are we Encryptying or Decrypting?", "E", "D")
		if user_bool {
			user_input := getStringInput("Input string to be encrypted: ")
			data := []byte(user_input)
			binary_data := fmt.Sprintf("%08b", data)
			fmt.Println(binary_data)
			user_input = getStringInput("Input key: ")
			key := []byte(user_input)
			binary_key := fmt.Sprintf("%08b", key)
			// Replace space with empty string
			binary_data = strings.Replace(binary_data, "[", "", -1)
			binary_data = strings.Replace(binary_data, "]", "", -1)
			binary_key = strings.Replace(binary_key, "[", "", -1)
			binary_key = strings.Replace(binary_key, "]", "", -1)
			binary_data = strings.Replace(binary_data, " ", "", -1)
			binary_key = strings.Replace(binary_key, " ", "", -1)

			binary_key = matchKeyLengthToDataLength(binary_data, binary_key)

			encrypted_data := applyXOR(binary_data, binary_key)

			fmt.Println(encrypted_data)

			bas64_encrypted_data := binaryStringToBase64Binary(encrypted_data)
			fmt.Println(bas64_encrypted_data)

			if user_input == "exit" {
				running = false
				break
			}
		} else {
			user_input := getStringInput("Input binary string to be decrypted: ")
			user_input = base64BinaryToBinaryString(user_input)
			fmt.Println(user_input)
			key_input := getStringInput("Input key: ")
			key := []byte(key_input)
			binary_key := fmt.Sprintf("%08b", key)
			binary_key = strings.Replace(binary_key, "[", "", -1)
			binary_key = strings.Replace(binary_key, "]", "", -1)
			binary_key = strings.Replace(binary_key, " ", "", -1)
			binary_key = matchKeyLengthToDataLength(user_input, binary_key)

			decrypted_data := applyXOR(user_input, binary_key)
			fmt.Println(decrypted_data)
			decrypted_text := binaryToText(decrypted_data)
			fmt.Println(decrypted_text)

		}

	}
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
