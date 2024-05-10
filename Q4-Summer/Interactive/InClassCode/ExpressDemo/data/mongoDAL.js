// DAL = Data Access Layer
// CRUD = Create, Read, Update, Delete

// Connect to database
// npm i --save mongodb
const { MongoClient } = require('mongodb');

const uri = "mongodb://localhost:27017";
const dbName = "TestDB";
const collectionName = "Dogs";

// Gets all the bulldogs from MongoDB
// Returns an array of bulldog objects

const getAllBulldogs = async () => {
    const client = await MongoClient.connect(uri);

    try {
        const db = client.db(dbName);
        const collection = db.collection(collectionName);

        var results = await collection.find({}, {_id: 0, name: 1}).toArray();
        console.log("RESULTS");
        console.log(results);

        return results;
    } catch (err) {
        console.log("GetAllBulldogs: OOPS");
        console.log(err);

    } finally {
        //MongoClient.close();
    }
}



const getAllBulldogsStatic = () => {
    let bulldogs = []

    // TODO: Get bulldogs

    bulldogs.push({
        id: 1,
        name: "Joonke",
        age: 11,
        colors: ["Black", "White"]
    },
    {
        id: 1,
        name: "Billford",
        age: 8,
        colors: ["Black", "White"]
    });

    return bulldogs;
}

const addBulldog = async (name, age, colors) => {
    try {
        const client = await MongoClient.connect(uri);
        const db = client.db(dbName);
        const collection = db.collection(collectionName);

        var newBulldog = {
            Name: name,
            Age: age,
            IsOwnedBy: null,
            Colors: colors
        };

        var results = await collection.insertOne(newBulldog);
        console.log("AddBulldog: RESULTS");
        console.log(results);

        return results;
    } catch (err) {
        console.log("AddBulldog: OOPS");
        console.log(err);

    } finally {
        //MongoClient.close();
    }
}

const removeBulldog = (id) => {

}

exports.getAllBulldogs = getAllBulldogs;
exports.addBulldog = addBulldog;
exports.removeBulldog = removeBulldog;

// Grouping all the functions into an object and then exporting that

let commands = {
    getdogs: getAllBulldogs,
    adddog: addBulldog,
    rmvdog: removeBulldog
}

exports.commands = commands;

// CoPilot class lmao
// an entire class that can add, get, and remove bulldogs from our mongoDB database
class MongoDAL {
    constructor() {
        this.client = new MongoClient(uri, { useNewUrlParser: true });
    }
    async getAllBulldogs() {
        try {
            await this.client.connect();
            const db = this.client.db(dbName);
            const collection = db.collection(collectionName);

            var results = await collection.find({}, {_id: 0, name: 1}).toArray();
            console.log("RESULTS");
            console.log(results);

            return results;
        } catch (err) {
            console.log("GetAllBulldogs: OOPS");
            console.log(err);

        } finally {
            this.client.close();
        }
    }

    async addBulldog(name, age, colors) {
        try {
            await this.client.connect();
            const db = this.client.db(dbName);
            const collection = db.collection(collectionName);

            var results = await collection.insertOne({name: name, age: age, colors: colors});
            console.log("RESULTS");
            console.log(results);

            return results;
        } catch (err) {
            console.log("AddBulldog: OOPS");
            console.log(err);

        } finally {
            this.client.close();
        }
    }

    async removeBulldog(id) {
        try {
            await this.client.connect();
            const db = this.client.db(dbName);
            const collection = db.collection(collectionName);

            var results = await collection.deleteOne({id: id});
            console.log("RESULTS");
            console.log(results);

            return results;
        } catch (err) {
            console.log("RemoveBulldog: OOPS");
            console.log(err);

        } finally {
            this.client.close();
        }
    }
}

exports.MongoDAL = MongoDAL;