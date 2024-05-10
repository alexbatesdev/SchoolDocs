const express = require("express");
const router = express.Router();

const bcrypt = require('bcrypt');

const dal = require("../data/mongoDAL");


router.get("/", (req, res) => {
    let html = `<h1>User Homepage</h1>`;
    let model = {
        loggedInUser: req.session.user
    }
    res.send(html, model);
});

router.post("/register", async (req, res) => {
    let username = req.body.username;
    let password = req.body.password;
    let confirmPassword = req.body.confirmPassword;
    let email = req.body.email;
    let age = req.body.age;
    let questions = [req.body.question1, req.body.question2, req.body.question3];

    let model = {
        loggedInUser: req.session.user
    }

    if (password == confirmPassword && password.length > 0) {
        let hashedPass = await bcrypt.hash(password, 10);

        let result = dal.createUser(username, hashedPass, email, age, questions);
        res.redirect("/u/login");
    } else {
        res.render("register", model);
    }
});

router.get("/register", (req, res) => {
    res.render("register");
});

router.get("/logout", (req, res) => {
    req.session.user = null; // destroy the session

    res.redirect('/');
});

router.get("/login", (req, res) => {
    let model = {
        loggedInUser: req.session.user
    }
    res.render("login", model);
});

router.post("/login", async (req, res) => {
    // Get values from posted from
    // (body parser)
    let username = req.body.username;
    let password = req.body.password;

    console.log('Login attempt - username: ' + username);

    let results = await dal.getUser(username);
    
    if (results) {
    
        let passwordsMatch = await bcrypt.compare(password, results.Password);

        if (passwordsMatch) {
            // Set the session
            // Don't put anything secure in the session because it ends up in the browser's cookies/local storage
            console.log(`Logging in ${username}`);

            var user = {};
            if (!results.Questions && !results.Age) {
                user = {
                    username: username,
                    userID: results._id.toString(), //Will come from database
                    age: null,
                    email: results.Email,
                    questions: [
                        ["What is your favorite color?", null],
                        ["What is your favorite animal?", null],
                        ["What is your favorite food?", null]
                    ],
                    isAdmin: false,
                    lastVisit: new Date()
                };
            } else if (!results.Questions) {
                user = {
                    username: username,
                    userID: results._id.toString(), //Will come from database
                    age: results.Age,
                    email: results.Email,
                    questions: [
                        ["What is your favorite color?", null],
                        ["What is your favorite animal?", null],
                        ["What is your favorite food?", null]
                    ],
                    isAdmin: false,
                    lastVisit: new Date()
                };
            } else if (!results.Age) {
                user = {
                    username: username,
                    userID: results._id.toString(), //Will come from database
                    age: null,
                    email: results.Email,
                    questions: [
                        ["What is your favorite color?", results.Questions[0]],
                        ["What is your favorite animal?", results.Questions[1]],
                        ["What is your favorite food?", results.Questions[2]]
                    ],
                    isAdmin: false,
                    lastVisit: new Date()
                };
            } else {
                var user = {
                    username: username,
                    userID: results._id.toString(), //Will come from database
                    age: results.Age,
                    email: results.Email,
                    questions: [
                        ["What is your favorite color?", results.Questions[0]],
                        ["What is your favorite animal?", results.Questions[1]],
                        ["What is your favorite food?", results.Questions[2]]
                    ],
                    isAdmin: false,
                    lastVisit: new Date()
                };
            }

            if (results.hasOwnProperty('isAdmin')) {
                if (results.isAdmin) user.isAdmin = true;
                if (!results.isAdmin) user.isAdmin = false;
            } 

            req.session.user = user;
            res.cookie("LastLogin", user.lastVisit);

            res.redirect("/");
            // res.render("Profile");
        } else {
            let model = {
                errorMsg: "Invalid username or password",
                username: username,
                password: password
            }
            console.log("invalid login");
            res.render("login", model);
        }
    } else {
        let model = {
            errorMsg: "Invalid username or password",
            username: username,
            password: password
        }
        console.log("invalid login");
        res.render("login", model);
    }
});

router.get("/account", (req, res) => {
    let model = {
        loggedInUser: req.session.user
    }
    res.render("account", model);
});

router.post("/account", async (req, res) => {
    let username = req.body.username;
    let password = req.body.password;
    let confirmPassword = req.body.confirmPassword;
    let email = req.body.email;
    let age = req.body.age;
    let questions = [req.body.question1, req.body.question2, req.body.question3];
    let userID = req.session.user.userID;

    req.session.user.questions[0][1] = questions[0];
    req.session.user.questions[1][1] = questions[1];
    req.session.user.questions[2][1] = questions[2];
    let model = {
        loggedInUser: req.session.user
    }

    if (password == confirmPassword && password.length > 0) {
        let hashedPass = await bcrypt.hash(password, 10);
        
        let result = dal.updateUser(userID, hashedPass, email, age, questions);
        res.redirect("/u/account");
    } else {
        res.render("account", model);
    }
});

router.get("/admin", async (req, res) => {
    if (req.session.user) {
        if (req.session.user.isAdmin) {
            let model = {
                loggedInUser: req.session.user,
                users: []
            }
            let users = await dal.getAllUsers();

            users.forEach(user => {
                let userID = user._id.toString();
                let newUser = {
                    username: user.Username,
                    userID: userID,
                    age: user.Age,
                    email: user.Email,
                    isAdmin: false
                }
                if (user.hasOwnProperty('isAdmin')) {
                    if (user.isAdmin) newUser.isAdmin = true;
                    if (!user.isAdmin) newUser.isAdmin = false;
                }
                model.users.push(newUser);
            });
            
            res.render("admin", model);
        } else {
            res.redirect("/");
        }
    } else {
        res.redirect("/");
    }
});

router.get("/delete", (req, res) => {
    if (req.session.user.isAdmin) {
        dal.deleteUserByID(req.query.user);
        res.redirect("/u/admin");
    } else if (req.session.user.userID == req.query.user) {
        dal.deleteUserByID(req.query.user);
        req.session.user = null;
        res.redirect("/");
    } else {
        res.redirect("/");
    }
});

router.get("/promote", (req, res) => {
    if (req.session.user.isAdmin) {
        dal.promoteUser(req.query.user);
        res.redirect("/u/admin");
    } else {
        res.redirect("/");
    }
});

router.get("/demote", (req, res) => {
    if (req.session.user.isAdmin) {
        dal.demoteUser(req.query.user);
        res.redirect("/u/admin");
    } else {
        res.redirect("/");
    }
});

module.exports = router;