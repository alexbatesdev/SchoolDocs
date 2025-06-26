package main

import (
	"context"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha1"
	"crypto/sha256"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
	"os"
	"strconv"
	"unicode"

	"github.com/google/uuid"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type AccountAuth struct {
	Username string `json:"username" bson:"username"`
	Password string `json:"password" bson:"password"`
}

type Account struct {
	AccountAuth
	ID    uuid.UUID  `json:"id" bson:"id"`
	Vault []Password `json:"vault" bson:"vault"`
}

type Password struct {
	AccountAuth
	ID      uuid.UUID `json:"id" bson:"id"`
	Comment string    `json:"comment" bson:"comment"`
}

type AccountUpdatePassword struct {
	AccountAuth
	NewPassword string `json:"new_password"`
}

type AccountUpdateVault struct {
	AccountAuth
	NewPasswords []Password `json:"new_passwords"`
}

// Global variable to store accounts
var (
	accounts_collection *mongo.Collection
)

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

	coll := client.Database("Lab2").Collection("Accounts")
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
	result, err := getAccountByUsername(username)
	return result == nil && err != nil
}

func getAccountByUsername(username string) (*Account, error) {
	filter := bson.M{"accountauth.username": username}
	var account Account
	err := accounts_collection.FindOne(context.TODO(), filter).Decode(&account)

	if err != nil {
		if err == mongo.ErrNoDocuments {
			// No document was found
			return nil, fmt.Errorf("no account found with username: %s", username)
		}
		return nil, err // Returning the error for further handling
	}
	return &account, nil
}

func updateAccountByUsername(username string, account Account) {
	filter := bson.M{"accountauth.username": username}
	update := bson.M{
		"$set": bson.M{
			"accountauth.username": account.Username,
			"accountauth.password": account.Password,
			"vault":                account.Vault,
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

func encryptAES(data string, key string) string {
	// Convert the data to a byte array
	data_bytes := []byte(data)
	// Convert the key to a byte array
	key_bytes := []byte(key)
	c, err := aes.NewCipher(key_bytes)

	if err != nil {
		fmt.Println("Error creating AES cipher:", err)
		return ""
	}

	gcm, err := cipher.NewGCM(c)

	if err != nil {
		fmt.Println("Error creating GCM:", err)
		return ""
	}

	nonce := make([]byte, gcm.NonceSize())

	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		fmt.Println("Error creating nonce:", err)
		return ""
	}

	encrypted_data := gcm.Seal(nonce, nonce, data_bytes, nil)

	return binaryStringToBase64Binary(BytesToBinaryString(encrypted_data))
}

func decryptAES(data string, key string) string {
	key_bytes := []byte(key)
	data_bytes, err := base64.StdEncoding.DecodeString(data)

	if err != nil {
		fmt.Println("Error decoding base64 string:", err)
		return ""
	}

	c, err := aes.NewCipher(key_bytes)
	if err != nil {
		fmt.Println("Error creating AES cipher:", err)
		return ""
	}

	gcm, err := cipher.NewGCM(c)
	if err != nil {
		fmt.Println("Error creating GCM:", err)
		return ""
	}

	nonceSize := gcm.NonceSize()
	nonce, cypherText := data_bytes[:nonceSize], data_bytes[nonceSize:]

	decryptedData, err := gcm.Open(nil, nonce, cypherText, nil)
	if err != nil {
		fmt.Println("Error decrypting data:", err)
		return ""
	}

	decryptedText := BytesToString(decryptedData)
	return decryptedText
}

func binaryStringToBase64Binary(binary string) string {
	// Convert the binary string to a byte array
	var bytes []byte
	for i := 0; i < len(binary); i += 8 {
		byteStr := binary[i : i+8]
		byteVal, err := strconv.ParseUint(byteStr, 2, 8)
		if err != nil {
			fmt.Println("Error parsing binary string:", err)
			return ""
		}
		bytes = append(bytes, byte(byteVal))
	}

	// Convert the byte array to a base64 string
	base64Str := base64.StdEncoding.EncodeToString(bytes)
	return base64Str
}

func base64BinaryToBinaryString(base64Str string) string {
	// Decode the base64 string to a byte array
	bytes, err := base64.StdEncoding.DecodeString(base64Str)
	if err != nil {
		fmt.Println("Error decoding base64 string:", err)
		return ""
	}

	// Convert the byte array to a binary string
	var binaryStr string
	for _, b := range bytes {
		binaryStr += fmt.Sprintf("%08b", b)
	}

	return binaryStr
}

func sha1Digest(data string) string {
	// Convert the data to a byte array
	data_bytes := []byte(data)
	// Create the SHA-1 hash
	hash := sha1.Sum(data_bytes)
	// Convert the hash to a string
	hash_string := fmt.Sprintf("%x", hash)
	return hash_string
}

func BytesToString(b []byte) string {
	return string(b)
}

func BytesToBinaryString(b []byte) string {
	var result string
	for _, byteValue := range b {
		result += fmt.Sprintf("%08b", byteValue)
	}
	return result
}

// Endpoints ------------------------------------------------------------------------------------------------------------

// Endpoint Router

func handleRequests() {
	http.HandleFunc("/", index)
	http.HandleFunc("/register", register)
	http.HandleFunc("/login", login)
	http.HandleFunc("/update", updatePassword)
	http.HandleFunc("/accounts", listAccounts)
	http.HandleFunc("/vault", getVault)
	http.HandleFunc("/vault/add", addPasswordsToVault)
	http.HandleFunc("/vault/remove", removePasswordsFromVault)
	http.HandleFunc("/vault/update", updatePasswordsInVault)
	http.HandleFunc("/encrypt", encryptDataEndpoint)
	http.HandleFunc("/decrypt", decryptDataEndpoint)

	log.Fatal(http.ListenAndServe(":8080", nil))
}

func encryptDataEndpoint(w http.ResponseWriter, r *http.Request) {
	// Get the data from the request
	data := r.URL.Query().Get("data")
	key := r.URL.Query().Get("key")
	// Encrypt the data
	encrypted_data := encryptAES(data, sha1Digest(key)[:16])
	// Send the encrypted data in the response
	fmt.Fprint(w, encrypted_data)
}

func decryptDataEndpoint(w http.ResponseWriter, r *http.Request) {
	// Get the data from the request
	data := r.URL.Query().Get("data")
	key := r.URL.Query().Get("key")
	fmt.Println(data)
	// Decrypt the data
	decrypted_data := decryptAES(data, sha1Digest(key)[:16])
	// Send the decrypted data in the response
	fmt.Fprint(w, decrypted_data)
}

// Endpoint Methods ----------------------------------------------------

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
	// AES encrypt the password
	new_password := encryptAES(account.Password, account.ID.String()[:16])
	fmt.Println(new_password)
	account.Password = new_password
	account.Vault = []Password{}

	insertAccount(account)
	fmt.Println(account)

	response, err := json.Marshal(account)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	fmt.Println(response)
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

	var account AccountAuth
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount, err := getAccountByUsername(account.Username)

	if storedAccount == nil || err != nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}
	fmt.Println("Login: Decryption")
	fmt.Println(storedAccount)
	fmt.Println(storedAccount.Password)
	// AES decrypt the password
	stored_password := decryptAES(storedAccount.Password, storedAccount.ID.String()[:16])

	// Check that decryption matches the stored password
	fmt.Println(stored_password)
	fmt.Println(account.Password)
	if stored_password != account.Password {
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

	var account AccountUpdatePassword
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount, err := getAccountByUsername(account.Username)

	if storedAccount == nil || err != nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	// AES decrypt the password
	// Use the GUUID as the key
	// Check that decryption matches the stored password
	stored_password := decryptAES(storedAccount.Password, storedAccount.ID.String()[:16])

	// Check that decryption matches the stored password
	fmt.Println(stored_password)
	fmt.Println(account.Password)
	if stored_password != account.Password {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}

	if !isValidPassword(account.NewPassword) {
		http.Error(w, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number", http.StatusBadRequest)
		return
	}

	// AES encrypt the password
	// Use the GUUID as the key
	storedAccount.Password = encryptAES(account.NewPassword, storedAccount.ID.String()[:16])

	updateAccountByUsername(account.Username, *storedAccount)

	response, err := json.Marshal(storedAccount)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func addPasswordsToVault(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var account AccountUpdateVault
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount, err := getAccountByUsername(account.Username)

	if storedAccount == nil || err != nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	// AES decrypt the password
	// Use the GUUID as the key
	// Check that decryption matches the stored password
	stored_password := decryptAES(storedAccount.Password, storedAccount.ID.String()[:16])
	// Check that decryption matches the stored password
	fmt.Println(stored_password)
	fmt.Println(account.Password)
	if stored_password != account.Password {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}

	// Iterate over new passwords and add them to the vault
	// AES encrypt the password, use the GUUID as the key
	for _, password := range account.NewPasswords {
		password.ID, _ = uuid.NewRandom()
		password.Password = encryptAES(password.Password, storedAccount.ID.String()[:16])
		storedAccount.Vault = append(storedAccount.Vault, password)
	}

	updateAccountByUsername(account.Username, *storedAccount)

	response, err := json.Marshal(storedAccount)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func removePasswordsFromVault(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodDelete {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var account AccountUpdateVault
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount, err := getAccountByUsername(account.Username)

	if storedAccount == nil || err != nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	// AES decrypt the password
	// Use the GUUID as the key
	// Check that decryption matches the stored password
	stored_password := decryptAES(storedAccount.Password, storedAccount.ID.String()[:16])
	// Check that decryption matches the stored password
	fmt.Println(stored_password)
	fmt.Println(account.Password)
	if stored_password != account.Password {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}

	// Iterate over new passwords and remove them from the vault
	// AES encrypt the password, use the GUUID as the key
	// Skip non-existent passwords

	for _, password := range account.NewPasswords {
		for i, storedPassword := range storedAccount.Vault {
			if storedPassword.ID == password.ID {
				storedAccount.Vault = append(storedAccount.Vault[:i], storedAccount.Vault[i+1:]...)
			}
		}
	}

	updateAccountByUsername(account.Username, *storedAccount)

	response, err := json.Marshal(storedAccount)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func updatePasswordsInVault(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPatch {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	var account AccountUpdateVault
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount, err := getAccountByUsername(account.Username)

	if storedAccount == nil || err != nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	// AES decrypt the password
	// Use the GUUID as the key
	// Check that decryption matches the stored password
	stored_password := decryptAES(storedAccount.Password, storedAccount.ID.String()[:16])
	// Check that decryption matches the stored password
	fmt.Println(stored_password)
	fmt.Println(account.Password)
	if stored_password != account.Password {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}

	// Iterate over new passwords and update them in the vault
	// AES encrypt the password, use the GUUID as the key
	// Skip non-existent passwords

	for _, password := range account.NewPasswords {
		for i, storedPassword := range storedAccount.Vault {
			fmt.Println(storedPassword.ID)
			fmt.Println(password.ID)
			if storedPassword.ID == password.ID {
				password.Password = encryptAES(password.Password, storedAccount.ID.String()[:16])
				storedAccount.Vault[i] = password
			}
		}
	}

	fmt.Println(storedAccount.Vault)

	updateAccountByUsername(account.Username, *storedAccount)

	response, err := json.Marshal(storedAccount)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}

func getVault(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodGet {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	// Validate the user
	var account AccountAuth
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&account); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	storedAccount, err := getAccountByUsername(account.Username)

	if storedAccount == nil || err != nil {
		http.Error(w, "Account not found", http.StatusNotFound)
		return
	}

	// AES decrypt the password
	// Use the GUUID as the key
	// Check that decryption matches the stored password
	stored_password := decryptAES(storedAccount.Password, storedAccount.ID.String()[:16])

	// Check that decryption matches the stored password
	fmt.Println(stored_password)
	fmt.Println(account.Password)
	if stored_password != account.Password {
		http.Error(w, "Invalid password", http.StatusUnauthorized)
		return
	}
	output_vault := []Password{}
	// Iterate over the vault and decrypt the passwords
	for _, password := range storedAccount.Vault {
		decrypted_password := decryptAES(password.Password, storedAccount.ID.String()[:16])
		password.Password = decrypted_password
		output_vault = append(output_vault, password)
	}

	response, err := json.Marshal(output_vault)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(response)
}
