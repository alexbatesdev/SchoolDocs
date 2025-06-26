package main

import (
	"encoding/json"
	"fmt"
	"os"
)

type Contact struct {
	Email  string `json:"email"`
	PubKey string `json:"pubKey"`
}

type ContactBook struct {
	Contacts []Contact `json:"contacts"`
}

// Initialize the json file for contacts, encrypt it
func newContactFileIfNotExists(fileName string, publicKeyPath string) {

	// Check if the file exists
	if _, err := os.Stat(fileName); os.IsNotExist(err) {
		// File does not exist, create it
		file, err := os.Create(fileName)
		if err != nil {
			fmt.Println("Error creating file:", err)
			return
		}
		defer file.Close()

		contactBook := ContactBook{
			Contacts: []Contact{},
		}

		jsonData, err := json.Marshal(contactBook)
		if err != nil {
			fmt.Println("Error marshalling JSON:", err)
			return
		}

		// encryptedJsonData, err := rsaEncryptBytes(jsonData, publicKeyPath)
		// if err != nil {
		// 	fmt.Println("Error encrypting JSON:", err)
		// 	return
		// }

		_, err = file.Write(jsonData) // Writing an empty JSON object
		if err != nil {
			fmt.Println("Error writing to file:", err)
			return
		}

		fmt.Println("File created successfully:", fileName)
	} else {
		fmt.Println("File already exists:", fileName)
	}
}

// Decrypt and parse the json file into a useable variable format
func readContactFile(fileName string, privateKeyPath string) (*ContactBook, error) {
	// Open the file
	file, err := os.Open(fileName)
	if err != nil {
		return nil, fmt.Errorf("error opening file: %v", err)
	}
	defer file.Close()

	// Read the file data
	fileData, err := os.ReadFile(fileName)
	if err != nil {
		return nil, fmt.Errorf("error reading file: %v", err)
	}

	// // Decrypt the file
	// decryptedFileData, err := rsaDecryptBytes(fileData, privateKeyPath)

	// Unmarshal the JSON data into a ContactBook struct
	var contactBook ContactBook
	err = json.Unmarshal(fileData, &contactBook)
	if err != nil {
		return nil, fmt.Errorf("error unmarshalling JSON: %v", err)
	}

	return &contactBook, nil
}

// Add a contact to the contactbook file
func addContact(contactBook *ContactBook, contact Contact) {
	contactBook.Contacts = append(contactBook.Contacts, contact)
}

// Remove a contact from the contactbook file
func removeContact(contactBook *ContactBook, email string) {
	for i, contact := range contactBook.Contacts {
		if contact.Email == email {
			contactBook.Contacts = append(contactBook.Contacts[:i], contactBook.Contacts[i+1:]...)
			return
		}
	}
}

// Save ContactBook to json file
func saveContactFile(contactBook ContactBook, fileName string, publicKeyPath string) error {
	// Marshal the ContactBook struct into JSON
	jsonData, err := json.Marshal(contactBook)
	if err != nil {
		return fmt.Errorf("error marshalling JSON: %v", err)
	}

	// Open the file
	file, err := os.OpenFile(fileName, os.O_RDWR|os.O_CREATE, 0644)
	if err != nil {
		return fmt.Errorf("error opening file: %v", err)
	}
	defer file.Close()

	// Write the JSON data to the file
	_, err = file.Write(jsonData)
	if err != nil {
		return fmt.Errorf("error writing to file: %v", err)
	}

	// // Encrypt the file
	// _, err = rsaEncrypt(fileName, publicKeyPath, fileName)
	// if err != nil {
	// 	return fmt.Errorf("error encrypting file: %v", err)
	// }

	return nil
}

func displayContacts(contactBook *ContactBook) {
	fmt.Println("Contacts:")
	for i, contact := range contactBook.Contacts {
		fmt.Println(i, "Email:", contact.Email)
	}
}

func getContactSelection(contactBook *ContactBook) (Contact, error) {
	fmt.Println("Select a contact:")
	displayContacts(contactBook)
	selection := int(getIntegerInputInRange("Enter the number of the contact", 0, len(contactBook.Contacts)-1))
	return contactBook.Contacts[selection], nil
}
