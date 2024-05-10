// Require express
const express = require("express");
// Express setup
const app = express();
const port = 3000
;

//setup our session and tell express to use it
const session = require("express-session");
const sessionConfig = {
    secret: "this is a secret",
    cookie: {}
};
app.use(session(sessionConfig));

// Tell express to use the public folder for static files
app.use(express.static("public"));
// Tell express to use the views folder for views
app.set("views", "./views");
// Tell express to use the pug view engine
app.set("view engine", "pug");

// Require FileSystem module
const fs = require("fs");

// Require body-parser module
const bodyParser = require("body-parser");
// Tell express to use body-parser
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

const cookieParser = require("cookie-parser");
app.use(cookieParser());

// ////////////////////////////////////////////////////////////////

// Main router
const indexRouter = require("./routes/index");
app.use("/", indexRouter);
// User router
const usersRouter = require("./routes/users");
app.use("/u", usersRouter);
// Api router
const apiRouter = require("./routes/api");
app.use("/api", apiRouter);

app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.listen(port, () => {
    console.log(`Listening on port ${port}`)
});