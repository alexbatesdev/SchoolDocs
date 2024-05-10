var authHeaderValue = null;
var username = null;
var password = null;

const login = () => {
    username = document.getElementById("username").value;
    password = document.getElementById("password").value;
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/agent/logincheck", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            console.log("Login successful");
            document.getElementById("labelMessage").innerHTML = "Login successful. Welcome " + username;
            showLoginForm(false);
        } else if(this.readyState == XMLHttpRequest.DONE && this.status == 401) {
            console.log("Login failed");
            document.getElementById("labelMessage").innerHTML = "Login failed. Please try again.";
            showLoginForm(true);
        }
    }
    xhr.send();
}

const showLoginForm = (boolDisplay) => {
    if (boolDisplay) {
        document.getElementById("formLogin").style.visibility = "visible";
        document.getElementById("formMain").style.visibility = "visible";
    } else {
        document.getElementById("formLogin").style.visibility = "hidden";
        document.getElementById("formMain").style.visibility = "visible";
    }

}

const getSecret = () => {
    console.log("getSecret");
    var xhr = new XMLHttpRequest();;
    xhr.open("GET", "/agent/secret", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            let response = this.responseText;
            console.log(this.responseText);
            document.getElementById("labelMessage").innerHTML = response;
        } else if(this.readyState == XMLHttpRequest.DONE && this.status == 401) {
            document.getElementById("labelMessage").innerHTML = "You do not have access to this secret";
            showLoginForm(true);
        }

    }
    xhr.send();
}

const getNotSecret = () => {
    console.log("getNotSecret");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/agent/notsecret", true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            let response = this.responseText;
            console.log(this.responseText);
            document.getElementById("labelMessage").innerHTML = response;
        }

    }
}

//Login persistence
const storage = () => {
    //local storage
    localStorage.setItem("username", Document.getElementById("username").value);
    localStorage.setItem("password", Document.getElementById("password").value);

    //session storage
    sessionStorage.stuff = "isLoggedin"; //This is storing a string, but we can store whether or not the user is logged in

}

window.onload = () => {
    console.log("Page loading");
    showLoginForm(true);
}