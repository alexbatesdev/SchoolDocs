package main

import (
	"fmt"

	"github.com/emersion/go-imap"
	"github.com/emersion/go-imap/client"
)

func main() {
	messages, _ := FetchRecentEmails("mcbuzzer@gmail.com", "sdrs bjty hzxe qzaz", 1)
	printEmails(messages)
}

func printEmails(messages []*imap.Message) {
	for index, _ := range messages {
		msg := messages[len(messages)-index-1]
		fmt.Println("Subject:", msg.Envelope.Subject)
		fmt.Println("From:", msg.Envelope.From[0].Address)
		fmt.Println("Date:", msg.Envelope.Date)
		fmt.Println()
	}
}

func FetchRecentEmails(username string, password string, pageNumber int) ([]*imap.Message, error) {
	// Connect to the server
	c, err := client.DialTLS("imap.gmail.com:993", nil)
	if err != nil {
		return nil, err
	}
	defer c.Logout()

	// Login
	if err := c.Login(username, password); err != nil {
		return nil, err
	}
	fmt.Println("Logged in")

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
