package main

import (
	"bufio"
	"crypto/md5"
	"fmt"
	"os"
	"strings"
)

func main() {
	var running bool = true
	for running {
		user_input := getStringInput("Input string to be hashed: ")
		data := []byte(user_input)
		fmt.Printf("%x\n", md5.Sum(data))
		if user_input == "exit" {
			running = false
			break
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
