package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	var valid_input bool = false
	var num1, num2 float64
	var user_input string
	var operator string
	var running bool = true
	var reader = bufio.NewReader(os.Stdin)
	var previous_result float64 = 0

	fmt.Println("Welcome to the basic calculator!")
	fmt.Println("Enter a number, an operator (+, -, *, /), and another number.")
	fmt.Println("Example: 5 + 5")
	fmt.Println("Type 'exit' to quit.")

	for running {
		for !valid_input {
			fmt.Print(("> "))
			user_input, _ = reader.ReadString('\n')
			user_input = strings.TrimSpace(user_input)
			if user_input == "exit" {
				running = false
				break
			}
			scanned_items, error_msg := fmt.Sscanf(user_input, "%f %s %f", &num1, &operator, &num2)
			if error_msg != nil {
				scanned_items, error_msg_2 := fmt.Sscanf(user_input, "%s %f", &operator, &num2)
				if error_msg_2 == nil && scanned_items == 2 {
					previous_result = calculate(operator, previous_result, num2)
					valid_input = true
					continue
				} else {
					fmt.Println(error_msg_2)
				}
				fmt.Println(error_msg)
				fmt.Println("Invalid input. Please try again.")
				continue
			} else if scanned_items != 3 {
				fmt.Println(scanned_items)
				fmt.Println("Invalid input. Please try again.")
				continue
			} else if operator != "+" && operator != "-" && operator != "*" && operator != "/" {
				fmt.Println("Invalid operator. Please try again.")
				continue
			} else {
				previous_result = calculate(operator, num1, num2)
				valid_input = true
			}
		}

		if running {
			valid_input = false
		} else {
			break
		}
	}
}

func calculate(operator string, num1 float64, num2 float64) float64 {
	var output float64 = 0
	switch operator {
	case "+":
		output = num1 + num2
	case "-":
		output = num1 - num2
	case "*":
		output = num1 * num2
	case "/":
		output = num1 / num2
	}
	fmt.Println("Result: ", output)
	return output
}
