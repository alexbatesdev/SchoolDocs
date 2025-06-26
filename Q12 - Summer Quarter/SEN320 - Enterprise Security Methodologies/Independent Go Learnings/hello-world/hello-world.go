package main

import "fmt"

func main() {
	var input string
	fmt.Println("Hello, World! \nWait, that's not right. What's your name?")
	fmt.Scanln(&input)
	fmt.Println("Hello, " + input + "!")
}
