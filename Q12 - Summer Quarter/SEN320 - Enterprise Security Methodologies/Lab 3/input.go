package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func getIntegerInputInRange(display_text string, min int, max int) int64 {
	var valid_input bool = false
	var output int64
	var err error
	for !valid_input {
		output, err = strconv.ParseInt(getStringInput(display_text), 10, 64)

		if err == nil && int(output) >= min && int(output) <= max {
			valid_input = true
		} else {
			fmt.Println("Invalid input, please input an integer between", min, "and", max)
		}
	}

	return output
}

// Make clamped version
func getIntegerInput(display_text string) int64 {
	var valid_input bool = false
	var output int64
	var err error
	for !valid_input {
		output, err = strconv.ParseInt(getStringInput(display_text), 10, 64)

		if err == nil {
			valid_input = true
		} else {
			fmt.Println("Invalid input, please input an integer")
		}
	}

	return output
}

func getStringInput(display_text string) string {
	var reader = bufio.NewReader(os.Stdin)
	var user_input string
	fmt.Print(display_text + "\n>")
	user_input, _ = reader.ReadString('\n')
	user_input = strings.TrimSpace(user_input)

	return user_input
}

func getMultipleChoiceInput(display_text string, options []string) string {
	for {
		options_suffix := " ("
		for _, option := range options {
			options_suffix = options_suffix + "/" + option
		}
		options_suffix = options_suffix + ")"
		options_suffix = strings.Replace(options_suffix, "/)", ")", 1)
		options_suffix = strings.Replace(options_suffix, "(/", "(", 1)
		user_input := getStringInput(display_text + options_suffix)

		if contains(options, user_input) {
			return user_input
		} else {
			fmt.Println("Invalid input, please input one of the provided options:" + options_suffix)
		}
	}
}

func getBoolInput(display_text string, true_val string, false_val string) bool {
	for {
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
}
