package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type ToDo struct {
	Priority    string
	TaskName    string
	Description string
}

func main() {
	var running bool = true
	var user_input string
	var list []ToDo

	fmt.Println("Welcome to my Go ToDo list Script!\na) Read ToDo List\nb) Add to List\nc) Remove from List\nd) Clear List\nx) Exit\nh) Display this message")
	for running {
		user_input = getMultipleChoiceInput("Input Here", []string{"a", "b", "c", "d", "x", "h"})
		fmt.Println(user_input)
		switch user_input {
		case "a":
			fmt.Println("Display List")
			print_ToDo_list(list)
		case "b":
			task_name := getStringInput("Add to List Selected\nEnter Task Name: ")
			task_description := getStringInput("Enter Task Description: ")
			task_priority := getStringInput("Enter Task Priority")
			list = append(list, ToDo{
				Priority:    task_priority,
				TaskName:    task_name,
				Description: task_description,
			})

		case "c":
			fmt.Println("Remove From List")
			print_ToDo_list(list)
			removal_target_id := getIntegerInput("Enter Task ID: ")
			list = remove_at_index(list, int(removal_target_id))
			print_ToDo_list(list)
		case "d":
			fmt.Println("Clearing List...")
			list = []ToDo{}
		case "x":
			fmt.Println("Exiting...")
			running = false
			break
		case "h":
			fmt.Println("a) Read ToDo List\nb) Add to List\nc) Remove from List\nd) Clear List\nx) Exit\nh) Display this message")
		}

	}
}

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

func getFloatInput(display_text string) float64 {
	var valid_input bool = false
	var output float64
	var err error
	for !valid_input {
		output, err = strconv.ParseFloat(getStringInput(display_text), 64)

		if err == nil {
			valid_input = true
		} else {
			fmt.Println("Invalid input, please input a float")
		}
	}

	return output
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

func getMultipleChoiceInput(display_text string, options []string) string {
	for true {
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
	return "This string should never get returned"
}

func getStringInput(display_text string) string {
	var reader = bufio.NewReader(os.Stdin)
	var user_input string
	fmt.Print(display_text + "\n>")
	user_input, _ = reader.ReadString('\n')
	user_input = strings.TrimSpace(user_input)

	return user_input
}

func contains(list []string, item string) bool {
	for _, s := range list {
		if s == item {
			return true
		}
	}
	return false
}

func remove_at_index[T any](list []T, index int) []T {
	var new_list []T
	for iter, item := range list {
		if iter != index {
			new_list = append(new_list, item)
		}
	}
	return new_list
}

func print_ToDo_list(list []ToDo) {
	for index, item := range list {
		formattedString := fmt.Sprintf(`%d: 
  Name: %s
  Priority: %s
  Description: 
    %s
---------------------`,
			index,
			item.TaskName,
			item.Priority,
			item.Description)
		fmt.Println(formattedString)
	}
}
