package main

import (
	"context"
	"crypto/sha256"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"
	"unicode"

	"github.com/google/uuid"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

// Account represents a user account with JSON tags for API communication.
type Account struct {
	ID       uuid.UUID `json:"id" bson:"id"`
	Username string    `json:"username" bson:"username"`
	Password string    `json:"password" bson:"password"`
	Salt     uuid.UUID `json:"salt" bson:"salt"`
}

type AccountUpdate struct {
	Username    string `json:"username"`
	OldPassword string `json:"old_password"`
	NewPassword string `json:"new_password"`
}

// Global variable to store accounts
var (
	accounts_collection *mongo.Collection
)

// Endpoint Methods

func index(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Hello World!")
}

func register(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var account Account
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	if !checkUsernameUnique(account.Username) {
		http.Error(w, "Username not unique", http.StatusForbidden)
		return
	}

	if !isValidPassword(account.Password) {
		http.Error(w, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number", http.StatusBadRequest)
		return
	}

	account.ID, _ = uuid.NewRandom()
	account.Salt, _ = uuid.NewRandom()
	account.Password = hashLineWithSalt(account.Password, account.Salt)

	insertAccount(account)

	response, err := json.Marshal(account)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func listAccounts(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodGet {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	result, err := accounts_collection.Find(context.Background(), bson.D{})
	if err != nil {
		panic(err)
	}

	var accounts []Account
	for result.Next(context.Background()) {
		var account Account
		err := result.Decode(&account)
		if err != nil {
			panic(err)
		}
		accounts = append(accounts, account)
	}

	response, err := json.Marshal(accounts)

	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func login(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var account Account
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount := getAccountByUsername(account.Username)

	if storedAccount.ID == uuid.Nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	if storedAccount.Password != hashLineWithSalt(account.Password, storedAccount.Salt) {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}

	response, err := json.Marshal(storedAccount)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func updatePassword(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPatch {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var account AccountUpdate
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount := getAccountByUsername(account.Username)

	if storedAccount.ID == uuid.Nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	if storedAccount.Password != hashLineWithSalt(account.OldPassword, storedAccount.Salt) {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}

	if !isValidPassword(account.NewPassword) {
		http.Error(w, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number", http.StatusBadRequest)
		return
	}

	storedAccount.Password = hashLineWithSalt(account.NewPassword, storedAccount.Salt)

	updateAccountByUsername(account.Username, storedAccount)

	response, err := json.Marshal(storedAccount)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

// Endpoint Router

func handleRequests() {
	http.HandleFunc("/", index)
	http.HandleFunc("/register", register)
	http.HandleFunc("/login", login)
	http.HandleFunc("/update", updatePassword)
	http.HandleFunc("/accounts", listAccounts)
	log.Fatal(http.ListenAndServe(":8080", nil))
}

// Main, where the magic happens

func main() {
	mongoUsername := os.Getenv("MONGO_USERNAME")
	mongoPassword := os.Getenv("MONGO_PASSWORD")
	mongoHost := os.Getenv("MONGO_HOST")
	connection_string := fmt.Sprintf("mongodb://%s:%s@%s:27017", mongoUsername, mongoPassword, mongoHost)
	// Use the SetServerAPIOptions() method to set the Stable API version to 1
	serverAPI := options.ServerAPI(options.ServerAPIVersion1)
	opts := options.Client().ApplyURI(connection_string).SetServerAPIOptions(serverAPI)
	// Create a new client and connect to the server
	client, err := mongo.Connect(context.Background(), opts)

	if err != nil {
		panic(err)
	}
	defer func() {
		if err = client.Disconnect(context.TODO()); err != nil {
			panic(err)
		}
	}()

	coll := client.Database("Lab1").Collection("Accounts")
	accounts_collection = coll
	handleRequests()
}

// Utility Functions

func hashLineWithSalt(line string, salt uuid.UUID) string {
	newLine := line + salt.String()
	return hashLine(newLine)
}

func hashLine(line string) string {
	data := []byte(line)
	hash := sha256.Sum256(data)
	hashString := fmt.Sprintf("%x", hash)
	return hashString
}

func checkUsernameUnique(username string) bool {
	result := getAccountByUsername(username)
	return result.ID == uuid.Nil
}

func getAccountByUsername(username string) Account {
	filter := bson.M{"username": username}
	var account Account
	err := accounts_collection.FindOne(context.TODO(), filter).Decode(&account)
	if err != nil {
		if err == mongo.ErrNoDocuments {
			// No document was found
			return Account{}
		}
		panic(err)
	}
	return account
}

func updateAccountByUsername(username string, account Account) {
	filter := bson.M{"username": username}
	update := bson.M{
		"$set": bson.M{
			"id":       account.ID,
			"username": account.Username,
			"password": account.Password,
			"salt":     account.Salt,
		},
	}
	_, err := accounts_collection.UpdateOne(context.TODO(), filter, update)
	if err != nil {
		panic(err)
	}
}

func insertAccount(account Account) {
	_, err := accounts_collection.InsertOne(context.TODO(), account)
	if err != nil {
		panic(err)
	}
}

func isValidPassword(password string) bool {
	var hasMinLen, hasUpper, hasLower, hasNumber bool
	hasMinLen = len(password) >= 8

	for _, char := range password {
		switch {
		case unicode.IsUpper(char):
			hasUpper = true
		case unicode.IsLower(char):
			hasLower = true
		case unicode.IsDigit(char):
			hasNumber = true
		}
	}

	return hasMinLen && hasUpper && hasLower && hasNumber
}
