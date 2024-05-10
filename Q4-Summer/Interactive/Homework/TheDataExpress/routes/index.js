const express = require("express");
const router = express.Router();

const dal = require("../data/mongoDAL");
const api = require("./api");

router.get( // .get means this will handle a get request
    "/", // This defines a route/endpoint for the root of the site
    (req, res) => { //reg = reguest, res = response
        let model = {
            loggedInUser: req.session.user,
            data: api.get("/") //api.internalAPIFunction()
        }

        // console.log(req.session.user);
        // res.cookie("woot", "woot");
        // console.log("COOKIES");
        // console.log(req.cookies);


        res.render("home", model);
    }
)

router.get("/page", (req, res) => { 
        let model = {
            loggedInUser: req.session.user
        }
        res.render("page", model)
    }
)


module.exports = router; // This exports the router so it can be used in app.js