const express = require("express");
const bodyParser = require("body-parser");

const fs = require("fs");

const app = express();
const port = 3000;

//setup our session and tell express to use it
const session = require("express-session");
const sessionConfig = {
    secret: "this is a secret",
    cookie: {}
};
app.use(session(sessionConfig));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use(express.static("public"));

// Telling express where our views are
// and Telling express that we want to use pug
app.set("views", "./views");
app.set("view engine", "pug");

app.post("/order", (req, res) => { 
    console.log("POST:ORDER");
    let object = {
        "name": req.body.name,
        "email": req.body.email,
        "phone": req.body.phone,
        "address": req.body.address
    }
    let myJSON = JSON.stringify(object);

    try {
        fs.appendFile(`./public/orders/orders.json`, myJSON, err => console.log(err));
        //fs.writeFileSync();
    } catch (err) {
        console.error(err);
    }
    // Get order details from request
    // Save order details to file
    res.render("order");
}
)
const indexRouter = require("./routes/index");
app.use("", indexRouter);

const usersRouter = require("./routes/users");
app.use("/u", usersRouter);

app.listen(port, () => {
    console.log(`Listening on port ${port}`)
});