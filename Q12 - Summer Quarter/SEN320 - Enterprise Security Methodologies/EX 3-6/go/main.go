package main

import (
	"fmt"
	"net/smtp"
)

func main() {
	// Set up authentication information.
	auth := smtp.PlainAuth("", "mcbuzzer@gmail.com", "sdrs bjty hzxe qzaz", "smtp.gmail.com")

	// Set up email details.
	from := "mcbuzzer@gmail.com"
	to := []string{"mcbuzzer@gmail.com"}
	subject := "Subject: Test Email\n"
	body := "This is a test email sent from a Go program!"

	msg := []byte(subject + "\n" + body)

	// Send the email.
	err := smtp.SendMail("smtp.gmail.com:587", auth, from, to, msg)
	if err != nil {
		fmt.Println("Error sending email:", err)
		return
	}
	fmt.Println("Email sent successfully!")
}

func sendEmail(email string, password string, to []string, subject string, body string) error {
	// Set up authentication information.
	auth := smtp.PlainAuth("", email, password, "smtp.gmail.com")

	// Set up email details.
	from := email
	msg := []byte(subject + "\n" + body)

	// Send the email.
	err := smtp.SendMail("smtp.gmail.com:587", auth, from, to, msg)
	if err != nil {
		return err
	}
	return nil

}
