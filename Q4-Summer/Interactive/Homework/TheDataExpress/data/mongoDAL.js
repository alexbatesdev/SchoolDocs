// DAL = Data Access Layer
// CRUD = Create, Read, Update, Delete

// Connect to database
// npm i --save mongodb
const { MongoClient, ObjectId } = require('mongodb');

const uri = "mongodb://localhost:27017";
const dbName = "TestDB";
const collectionName = "Users";

const createUser = async (username, password, email, age, questions) => {
    const client = await MongoClient.connect(uri);

    try {
        const db = client.db(dbName);

        const collection = db.collection("Users");

        var newUser = {
            Username: username,
            Password: password,
            Email: email,
            Age: age,
            Questions: questions
        };

        var results = await collection.insertOne(newUser);

        return results;

    } catch (err) {
        console.log(err);

    } finally {
        client.close();
    }
}

const getUser = async (username) => {
    const client = await MongoClient.connect(uri);
    try {
        const db = client.db(dbName);
        const collection = db.collection("Users");
        var results = await collection.findOne({ Username: username });
        return results;
    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}

const updateUser = async (id, password, email, age, questions) => {
    const client = await MongoClient.connect(uri);

    try {
        const db = client.db(dbName);

        const collection = db.collection("Users");

        var results = await collection.updateOne({ _id: new ObjectId(id)}, { $set: { Password: password, Email: email, Age: age, Questions: questions } });

        return results;

    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}


const deleteUserByID = async (id) => {
    const client = await MongoClient.connect(uri);

    try {
        const db = client.db(dbName);
        const collection = db.collection("Users");
        var results = await collection.deleteOne({ _id: new ObjectId(id) });
    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}

const deleteUserByUsername = async (username) => {
    const client = await MongoClient.connect(uri);

    try {
        const db = client.db(dbName);
        const collection = db.collection("Users");
        var results = await collection.deleteOne({ Username: username });
    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}

const promoteUser = async (id) => {
    const client = await MongoClient.connect(uri);
    try {
        const db = client.db(dbName);
        const collection = db.collection("Users");
        var results = await collection.updateOne({ _id: new ObjectId(id) }, { $set: { isAdmin: true } });
    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}

const demoteUser = async (id) => {
    const client = await MongoClient.connect(uri);
    try {
        const db = client.db(dbName);
        const collection = db.collection("Users");
        var results = await collection.updateOne({ _id: new ObjectId(id) }, { $set: { isAdmin: false } });
    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}


const getAllUsers = async () => {
    const client = await MongoClient.connect(uri);

    try {
        const db = client.db(dbName);
        const collection = db.collection("Users");
        var results = await collection.find({}).toArray();
        return results;
    } catch (err) {
        console.log(err);
    } finally {
        client.close();
    }
}

const getQuestions = async (username) => {
    let results = await getAllUsers();
    let questions = [[0,0,0], [0,0,0], [0,0,0]];
    results.forEach(user => {
        try {
            if (user.Questions[0] == "Red") {
                questions[0][0] += 1;
            } else if (user.Questions[0] == "Green") {
                questions[0][1] += 1;
            } else if (user.Questions[0] == "Blue") {
                questions[0][2] += 1;
            }
            
            if (user.Questions[1] == "Dog") {
                questions[1][0] += 1;
            } else if (user.Questions[1] == "Cat") {
                questions[1][1] += 1;
            } else if (user.Questions[1] == "Bird") {
                questions[1][2] += 1;
            }
    
            if (user.Questions[2] == "Pizza") {
                questions[2][0] += 1;
            } else if (user.Questions[2] == "Pasta") {
                questions[2][1] += 1;
            } else if (user.Questions[2] == "Cake") {
                questions[2][2] += 1;
            }
        } catch (err) {
            console.log("User has no questions");
        }
    });
    return questions;
}

exports.getQuestions = getQuestions;
exports.createUser = createUser;
exports.getUser = getUser;
exports.updateUser = updateUser;
exports.getAllUsers = getAllUsers;
exports.deleteUserByID = deleteUserByID;
exports.deleteUserByUsername = deleteUserByUsername;
exports.promoteUser = promoteUser;
exports.demoteUser = demoteUser;