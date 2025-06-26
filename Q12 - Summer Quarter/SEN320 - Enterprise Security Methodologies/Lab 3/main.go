package main

import (
	"fmt"
	"log"
	"os"
)

func main() {
	username := getStringInput("Enter your email address:")
	password := getStringInput("Enter your email/app password:")
	private_key_path := getStringInput("Enter the path to your private key:")
	if private_key_path == "" {
		private_key_path = "private_key.pem"
	}

	public_key_path := getStringInput("Enter the path to your public key:")
	if public_key_path == "" {
		public_key_path = "public_key.pem"
	}

	fmt.Println("Initializing contact book...")
	// Create a json file to store contacts of the user who is logged in
	contactBookName := username + "_contactBook.json"
	newContactFileIfNotExists(contactBookName, public_key_path)

	email_client, err := getEmailClient(username, password)
	defer email_client.Logout()
	if err != nil {
		log.Fatal(err)
	}

	for {

		user_choice := getMultipleChoiceInput("Select an option:", []string{"send email", "view inbox", "manage contacts", "exit"})

		if user_choice == "send email" {
			// Send email
			// Ask for subject
			subject := getStringInput("Enter the subject of the email:")
			fmt.Println("Subject:", subject)
			// Ask for body
			body := getStringInput("Enter the body of the email:")
			fmt.Println("Body:", body)
			os.WriteFile("body.txt", []byte(body), 0644)

			doEncrypt := getBoolInput("Would you like to encrypt the body of the email?", "yes", "no")
			fmt.Println("Encrypt:", doEncrypt)

			doSign := getBoolInput("Would you like to sign the body of the email?", "yes", "no")
			fmt.Println("Sign:", doSign)
			signature, err := signString(body, private_key_path)
			os.WriteFile("signature.txt", []byte(signature), 0644)
			if err != nil {
				log.Fatal(err)
			}

			// Fetch contacts from json and display them
			// 		Ask for recipient(s) from list of contacts
			contactBook, err := readContactFile(contactBookName, private_key_path)
			if err != nil {
				log.Fatal(err)
			}
			contact, err := getContactSelection(contactBook)
			if err != nil {
				log.Fatal(err)
			}
			recipient := contact.Email
			fmt.Println("Recipient:", recipient)

			if doEncrypt {
				key := sha1Digest(genUUID())[:16]
				// Encrypt key
				key_bytes := []byte(key)
				key_encrypted, err := rsaEncryptBytes(key_bytes, contact.PubKey)
				if err != nil {
					log.Fatal(err)
				}
				body = "ENCRYPTED_CONTENTS_5318008\n" + base64Encode([]byte(aesEncrypt(body, key))) + "\nENCRYPTED_CONTENTS_80085\n" + base64Encode(key_encrypted)
				fmt.Println("Encrypted body:", body)
			}
			body = "START_42\n" + body

			if doSign {
				body = body + "\nSIGNATURE_0112358\n" + signature
			}

			err = sendEmail(username, password, []string{recipient}, subject, body)
			if err != nil {
				log.Fatal(err)
			}
		} else if user_choice == "view inbox" {

			pageIndex := 1
			for {
				fmt.Println("Page:", pageIndex)
				messages, err := FetchRecentEmails(email_client, pageIndex)
				if err != nil {
					log.Fatal(err)
				}

				printEmails(messages)
				if len(messages) < 10 {
					if pageIndex > 1 {
						userInput := getMultipleChoiceInput("Select an option:", []string{"view email", "previous page", "back"})
						if userInput == "previous page" {
							pageIndex--
						} else if userInput == "back" {
							break
						} else if userInput == "view email" {
							SelectEmail(messages, email_client, private_key_path, public_key_path)
						}
					} else {
						userInput := getMultipleChoiceInput("Select an option:", []string{"view email", "back"})
						if userInput == "back" {
							break
						} else if userInput == "view email" {
							SelectEmail(messages, email_client, private_key_path, public_key_path)
						}
					}
				} else {
					if pageIndex > 1 {
						userInput := getMultipleChoiceInput("Select an option:", []string{"view email", "next page", "previous page", "back"})
						if userInput == "next page" {
							pageIndex++
						} else if userInput == "previous page" {
							pageIndex--
						} else if userInput == "back" {
							break
						} else if userInput == "view email" {
							SelectEmail(messages, email_client, private_key_path, public_key_path)
						}
					} else {
						userInput := getMultipleChoiceInput("Select an option:", []string{"view email", "next page", "back"})
						if userInput == "next page" {
							pageIndex++
						} else if userInput == "back" {
							break
						} else if userInput == "view email" {
							SelectEmail(messages, email_client, private_key_path, public_key_path)
						}
					}
				}
			}
		} else if user_choice == "manage contacts" {

			contactBook, err := readContactFile(contactBookName, private_key_path)
			if err != nil {
				log.Fatal(err)
			}
			for {

				displayContacts(contactBook)

				userInput := getMultipleChoiceInput("Select an option:", []string{"add contact", "remove contact", "back"})
				if userInput == "add contact" {
					email := getStringInput("Enter the email of the contact:")
					pubKeyPath := getStringInput("Enter the path to the public key of the contact:")
					contact := Contact{Email: email, PubKey: pubKeyPath}
					addContact(contactBook, contact)
					err = saveContactFile(*contactBook, contactBookName, public_key_path)
					if err != nil {
						log.Fatal(err)
					}
				} else if userInput == "remove contact" {
					email := getStringInput("Enter the email of the contact:")
					removeContact(contactBook, email)
					err = saveContactFile(*contactBook, contactBookName, public_key_path)
					if err != nil {
						log.Fatal(err)
					}
				} else if userInput == "back" {
					break
				}
			}
		} else if user_choice == "exit" {
			os.Exit(0)
		}

	}
}
