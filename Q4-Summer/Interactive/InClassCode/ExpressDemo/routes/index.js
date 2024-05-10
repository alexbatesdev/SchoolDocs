const express = require("express");
const router = express.Router();

const dal = require("../data/mongoDAL");

router.get( // .get means this will handle a get request
    "/", // This defines a route/endpoint for the root of the site
    (req, res) => { //reg = reguest, res = response
        res.render("home");
    }
)

router.get("/features", (req, res) => {
    console.log(req.session);

    req.session.count = req.session.count + 1;

    console.log(req.session);

    req.session.hasBeenToFeatures = true;


    // Features page
        res.render("features")
    }
)

router.get("/order", (req, res) => { 
    // Order page
    res.render("order")
    }
)

router.get("/no", (req, res) => { 
    // About Us page
    res.render("no")
    }
)

router.get("/bulldogs", async (req, res) => { 
    // Bulldogs page

    // Use DAL to get bulldogs
    let bulldogs = await dal.getAllBulldogs();

    console.log('bulldogs');
    console.log(bulldogs);

    let bulldogs2 = dal.commands.getdogs();
    // Pass Bulldogs to the view
    let model = {
        bulldogs: bulldogs
    };

    res.render("bulldogs", model);
    }
)

module.exports = router; // This exports the router so it can be used in app.js