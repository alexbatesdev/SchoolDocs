const express = require("express");
const router = express.Router();

const dal = require("../data/mongoDAL");

router.get("/", (req, res) => {
    let html = `<h1>User Homepage</h1>`;
    res.send(html);
});

router.get("/register", (req, res) => {
    res.send("You are in the register page");
});

router.post("/register", (req, res) => {
    res.send("You submit the form");
});


module.exports = router;