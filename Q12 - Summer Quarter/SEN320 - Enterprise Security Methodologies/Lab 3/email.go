package main

import (
	"bytes"
	"fmt"
	"net/smtp"
	"strings"

	"github.com/emersion/go-imap"
	"github.com/emersion/go-imap/client"
)

func SelectEmail(messages []*imap.Message, client *client.Client, privateKeyPath string, publicKeyPath string) {

	for index := range messages {
		msg := messages[len(messages)-index-1]
		fmt.Println("---[", index, "]---")
		fmt.Println("Subject:", msg.Envelope.Subject)
		fmt.Println("From:", msg.Envelope.From[0].Address)
		fmt.Println("Date:", msg.Envelope.Date)
		fmt.Println("---[", index, "]---")
		fmt.Println()
	}

	// Select an email
	email_index := getIntegerInputInRange("Select an email to view", 0, len(messages)-1)
	selected_email := messages[len(messages)-int(email_index)-1]
	fmt.Println("Subject:", selected_email.Envelope.Subject)
	fmt.Println("From:", selected_email.Envelope.From[0].Address)
	fmt.Println("Date:", selected_email.Envelope.Date)
	// Fetch the body of the selected email
	seqset := new(imap.SeqSet)
	seqset.AddNum(selected_email.SeqNum)

	section := &imap.BodySectionName{}
	items := []imap.FetchItem{section.FetchItem()}
	msgs := make(chan *imap.Message, 1)
	go func() {
		if err := client.Fetch(seqset, items, msgs); err != nil {
			fmt.Println("Error fetching email body:", err)
		}
	}()

	msg := <-msgs
	r := msg.GetBody(section)
	if r == nil {
		fmt.Println("Server didn't return message body")
		return
	}

	buf := new(bytes.Buffer)
	if _, err := buf.ReadFrom(r); err != nil {
		fmt.Println("Error reading body:", err)
		return
	}

	body := buf.String()
	fmt.Println("First Body:", body)
	if strings.Contains(body, "START_42") {
		fmt.Println("EMAIL SENT FROM THIS CLIENT")
		index_0 := strings.Index(body, "START_42")
		body = body[index_0+len("START_42"):]
		body = strings.TrimSpace(body)
		fmt.Println("Bodyyyy:", body)
	}

	signature := ""
	if strings.Contains(body, "SIGNATURE_0112358") {
		index := strings.Index(body, "SIGNATURE_0112358")
		signature = body[index+len("SIGNATURE_0112358"):]
		signature = strings.Replace(strings.TrimSpace(signature), "\n", "", -1)
		fmt.Println("Signature:", signature)
		body = body[:index]
	}

	if strings.Contains(body, "ENCRYPTED_CONTENTS_5318008") {
		index_1 := strings.Index(body, "ENCRYPTED_CONTENTS_5318008")
		index_2 := strings.Index(body, "ENCRYPTED_CONTENTS_80085")

		key := body[index_2+len("ENCRYPTED_CONTENTS_80085"):]
		body = body[index_1+len("ENCRYPTED_CONTENTS_5318008") : index_2]

		decoded_body, err := base64Decode(body)
		if err != nil {
			fmt.Println("Error decoding email body:", err)
			return
		}

		decoded_key, err := base64Decode(key)
		if err != nil {
			fmt.Println("Error decoding email key:", err)
			return
		}

		decrypted_key, err := rsaDecryptBytes(decoded_key, privateKeyPath)
		if err != nil {
			fmt.Println("Error decrypting email key:", err)
			return
		}

		decrypted_body := aesDecrypt(string(decoded_body), string(decrypted_key))
		body = decrypted_body
	}

	fmt.Println("Body:", body)

	if signature != "" {
		// Verify the signature
		body = strings.TrimSpace(body)
		body = strings.Replace(body, "\n", "", -1)
		verified, err := verifyString(body, signature, publicKeyPath)
		if err != nil {
			fmt.Println("Error verifying signature:", err)
		} else {
			fmt.Println("Signature verified:", verified)
		}

	}

	getStringInput("Press enter to continue")
	fmt.Println()
}

func printEmails(messages []*imap.Message) {
	for index := range messages {
		msg := messages[len(messages)-index-1]
		fmt.Println("Subject:", msg.Envelope.Subject)
		fmt.Println("From:", msg.Envelope.From[0].Address)
		fmt.Println("Date:", msg.Envelope.Date)
		fmt.Println()
	}
}

func getEmailClient(username string, password string) (*client.Client, error) {
	// Connect to the server
	c, err := client.DialTLS("imap.gmail.com:993", nil)
	if err != nil {
		return nil, err
	}

	// Login
	if err := c.Login(username, password); err != nil {
		return nil, err
	}
	fmt.Println("Logged in")
	return c, nil
}

func FetchRecentEmails(c *client.Client, pageNumber int) ([]*imap.Message, error) {
	// Select the INBOX
	mbox, err := c.Select("INBOX", false)
	if err != nil {
		return nil, err
	}
	fmt.Println("Number of messages in INBOX:", mbox.Messages)

	// Set the criteria to search for all emails
	criteria := imap.NewSearchCriteria()
	criteria.WithoutFlags = nil // Remove the filter for unseen emails

	uids, err := c.Search(criteria)
	if err != nil {
		return nil, err
	}
	if len(uids) == 0 {
		fmt.Println("No emails found.")
		return nil, nil
	}

	pageSize := 10

	startIndex := len(uids) - (pageNumber * pageSize)
	endIndex := startIndex + pageSize

	if endIndex > len(uids) {
		endIndex = len(uids)
	}

	// Sort UIDs in descending order and take the 10 most recent
	if len(uids) > 10 {
		uids = uids[startIndex:endIndex]
	}

	// Fetch the emails
	seqset := new(imap.SeqSet)
	seqset.AddNum(uids...)

	messages := make(chan *imap.Message, 10)
	done := make(chan error, 1)
	go func() {
		done <- c.Fetch(seqset, []imap.FetchItem{imap.FetchEnvelope}, messages)
	}()

	var fetchedMessages []*imap.Message
	for msg := range messages {
		fetchedMessages = append(fetchedMessages, msg)
	}

	if err := <-done; err != nil {
		return nil, err
	}

	return fetchedMessages, nil
}

func sendEmail(email string, password string, to []string, subject string, body string) error {
	// Set up authentication information.
	auth := smtp.PlainAuth("", email, password, "smtp.gmail.com")

	// Set up email details.
	from := email
	toHeader := strings.Join(to, ",")
	msg := []byte("From: " + from + "\n" +
		"To: " + toHeader + "\n" +
		"Subject: " + subject + "\n\n" +
		body)

	// Send the email.
	err := smtp.SendMail("smtp.gmail.com:587", auth, from, to, msg)
	if err != nil {
		return err
	}
	fmt.Println("Email sent")
	return nil
}
