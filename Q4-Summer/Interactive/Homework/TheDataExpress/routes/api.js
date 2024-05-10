const express = require("express");
const router = express.Router();

const dal = require("../data/mongoDAL");

router.get("/", async (req, res) => {
    let stats = await dal.getQuestions();
    let json = {
        questions: ["What is your favorite color?", "What is your favorite animal?", "What is your favorite food?"],
        labels: ["Red", "Green", "Blue", "Dog", "Cat", "Bird", "Pizza", "Pasta", "Cake"],
        data: [stats[0][0], stats[0][1], stats[0][2], stats[1][0], stats[1][1], stats[1][2], stats[2][0], stats[2][1], stats[2][2]]
    }
    res.send(json);
});

router.get("/getChartData", async (req, res) => {
    let stats = await dal.getQuestions();
    let json = {
        questions: [["What is your favorite color?", {
                        labels: ["Red", "Green", "Blue"],
                        data: [stats[0][0], stats[0][1], stats[0][2]]
                    }], 
                    ["What is your favorite animal?", {
                        labels: ["Dog", "Cat", "Bird"],
                        data: [stats[1][0], stats[1][1], stats[1][2]]
                    }], 
                    ["What is your favorite food?", {
                        labels: ["Pizza", "Pasta", "Cake"],
                        data: [stats[2][0], stats[2][1], stats[2][2]]
                    }]],
    }
    res.send(json);
});

// const internalAPIFunction = async (req, res) => {
//     let stats = await dal.getQuestions();
//     let json = {
//         questions: ["What is your favorite color?", "What is your favorite animal?", "What is your favorite food?"],
//         labels: ["Red", "Green", "Blue", "Dog", "Cat", "Bird", "Pizza", "Pasta", "Cake"],
//         data: [stats[0][0], stats[0][1], stats[0][2], stats[1][0], stats[1][1], stats[1][2], stats[2][0], stats[2][1], stats[2][2]]
//     }
//     return json;
// }

// exports.internalAPIFunction = internalAPIFunction;
module.exports = router;