let authHeaderValue = "null";
let username = null;
let password = null;

const login = () => {
    username = document.getElementById("username").value;
    password = document.getElementById("password").value;
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/cia/login", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            console.log("Login successful");
            document.getElementById("labelMessage").innerHTML = "Login successful. Welcome " + username;
            setFormVisibility(false);
        } else if(this.readyState == XMLHttpRequest.DONE && this.status == 401) {
            console.log("Login failed");
            document.getElementById("labelMessage").innerHTML = "Login failed. Please try again.";
            setFormVisibility(true);
        }
    }
    xhr.send();
}

const setFormVisibility = (visible) => {
    if (visible) {
        document.getElementById("formLogin").style.visibility = "visible";
        document.getElementById("formMain").style.visibility = "visible";
    } else {
        document.getElementById("formLogin").style.visibility = "hidden";
        document.getElementById("formMain").style.visibility = "visible";
    }
}

const getAnonymous = () => {
    console.log("getAnonymous");
    var xhr = new XMLHttpRequest();;
    xhr.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            let response = this.responseText;
            console.log(this.responseText);
            document.getElementById("labelMessage").innerHTML = response;
        }

    }
    xhr.open("GET", "/cia/anonymous", true);
    xhr.send();
}

const getSecretStuff = (strType, strURL) => {
    console.log("getSecret");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            let response = this.responseText;
            console.log(this.responseText);
            document.getElementById("labelMessage").innerHTML = response;
        } else if(this.readyState == XMLHttpRequest.DONE && this.status == 401) {
            document.getElementById("labelMessage").innerHTML = "You are not logged in and do not have access to " + strType;
            setFormVisibility(true);
        } else if(this.readyState == XMLHttpRequest.DONE && this.status == 403) {
            document.getElementById("labelMessage").innerHTML = "You do not have access to " + strType;
            setFormVisibility(true);
        }

    }
    xhr.open("GET", strURL);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.send();
}