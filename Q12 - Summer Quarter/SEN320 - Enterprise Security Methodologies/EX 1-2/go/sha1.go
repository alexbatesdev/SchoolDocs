package main

import (
	"bufio"
	"crypto/sha1"
	"fmt"
	"os"
	"strings"
)

func main() {
	var running bool = true
	fmt.Println("Welcome to the Sha1 Shop where we specialize in makin' and breakin' Sha1 Hashbrowns!")
	for running {
		user_bool := getBoolInput("Are we Makin' or Breakin' Today?", "M", "B")
		if user_bool {
			user_input := getStringInput("Input string to be hashed: ")
			data := []byte(user_input)
			fmt.Printf("%x\n", sha1.Sum(data))
			if user_input == "exit" {
				running = false
				break
			}
		} else {
			user_input := getStringInput("Input Hash to be broke: ")
			// Open the file
			file, err := os.Open("realhuman_phill.txt")
			if err != nil {
				fmt.Println("Oops ", err)
			}
			defer file.Close()

			// Create a new scanner for the file
			scanner := bufio.NewScanner(file)

			// Iterate over each line in the file
			for scanner.Scan() {
				line := scanner.Text()
				data := []byte(line)
				hash := sha1.Sum(data)
				hashString := fmt.Sprintf("%x", hash)
				if hashString == user_input {
					fmt.Println("They Match! The answer is", line)
					break
				}
			}
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
