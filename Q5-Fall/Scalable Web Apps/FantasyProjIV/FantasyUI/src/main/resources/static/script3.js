var authHeaderValue = null;
var username = null;
var password = null;

const login = () => {
    username = document.getElementById("username").value;
    password = document.getElementById("password").value;
    authHeaderValue = "Basic " + btoa(username + ":" + password);
    let loginForm = document.getElementById("login-form");
    let navLeft = document.getElementsByClassName("nav-left")[0];


    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/MONGO/login", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            console.log("logged in");
            loginForm.style.display = "none";
            let divEl = document.createElement("div");
            divEl.id = "logout-menu";
            divEl.innerHTML = "Logged in as " + username + "<button onclick='logout()' id='logout-button'>Logout</button>";
            navLeft.appendChild(divEl);
            // let logoutButton = document.createElement("button");
            // logoutButton.innerHTML = "Logout";
            // navLeft.appendChild(logoutButton);
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) {
            console.log("not logged in");
            loginForm.style.display = "block";
            alert("Invalid Login Credentials");
        }
    }
    xhr.send();
}

const logout = () => {
    username = null;
    password = null;
    authHeaderValue = null;
    let loginForm = document.getElementById("login-form");
    loginForm.style.display = "block";
    let logoutMenu = document.getElementById("logout-menu");
    logoutMenu.remove();
    // let navLeft = document.getElementsByClassName("nav-left")[0];
    // navLeft.innerHTML = "";
    // let loginButton = document.createElement("button");
    // loginButton.innerHTML = "Login";
    // loginButton.onclick = login;
    // navLeft.appendChild(loginButton);
}

const createUser = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var user = {
        userId: null,
        username: document.getElementById("create-username").value,
        password: document.getElementById("create-password").value,
        isAdmin: document.getElementById("create-admin").checked
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/MONGO/user", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            alert("User created");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a user");
        }
    }
    xhr.send(JSON.stringify(user));
}

//CRUDSS
const createPerson = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var person = {
        personId: document.getElementById("person_id").value,
        name: document.getElementById("person_name").value,
        isDeceased: document.getElementById("person_is_deceased").checked,
        description: document.getElementById("person_description").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/MONGO/person", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getPeople();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send(JSON.stringify(person));
}

const updatePerson = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var person = {
        personId: document.getElementById("person_id-update").value,
        name: document.getElementById("person_name-update").value,
        isDeceased: document.getElementById("person_is_deceased-update").checked,
        description: document.getElementById("person_description-update").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("PUT", "http://localhost:8080/MONGO/person", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getPeople();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send(JSON.stringify(person));
}

const getPeople = () => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var people = JSON.parse(this.responseText);
            console.log(people);
            renderPeople(people);
        }
    }
    xhr.open("GET", "http://localhost:8080/MONGO/person", true);
    xhr.send();
}

const renderPeople = (people) => {
    var person_list = document.getElementById("person_list");
    person_list.innerHTML = "";
    //Loop through entries
    for (var person of people) {
        console.log(person);
        var person_html = "<p>" + person.personId + ". </p>" + "<h2>" + person.name + "</h2>" + "<br/>" + "<h3>" +  person.isDeceased + "</h3>" + "<br/>" + "<p>" + person.description + "</p>" + "<br/>";
        person_list.innerHTML += person_html;
    }
}

const deletePerson = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var id = document.getElementById("person_id-delete").value;

    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/MONGO/person/" + id, true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getPeople();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send();
}





const createDevil = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var devil = {
        devilId: document.getElementById("devil_id").value,
        name: document.getElementById("devil_name").value,
        type: document.getElementById("devil_type").value,
        description: document.getElementById("devil_description").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/MONGO/devil", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getDevils();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send(JSON.stringify(devil));
}

const updateDevil = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var devil = {
        devilId: document.getElementById("devil_id-update").value,
        name: document.getElementById("devil_name-update").value,
        type: document.getElementById("devil_type-update").value,
        description: document.getElementById("devil_description-update").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("PUT", "http://localhost:8080/MONGO/devil", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getDevils();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send(JSON.stringify(devil));
}

const getDevils = () => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var devils = JSON.parse(this.responseText);
            renderDevils(devils);
        }
    }
    xhr.open("GET", "http://localhost:8080/MONGO/devil", true);
    xhr.send();
}

const renderDevils = (devils) => {
    var devil_list = document.getElementById("devil_list");
    devil_list.innerHTML = "";
    //Loop through entries
    for (var devil of devils) {
        console.log(devil);
        var devil_html = "<p>" + devil.devilId + ". </p>" + "<h2>" + devil.name + "</h2>" + "<br/>" + "<h3>" +  devil.type + "</h3>" + "<br/>" + "<p>" + devil.description + "</p>" + "<br/>";
        devil_list.innerHTML += devil_html;
    }
}

const deleteDevil = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var id = document.getElementById("devil_id-delete").value;

    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/MONGO/devil/" + id, true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getDevils();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send();
}




const createLocation = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var location = {
        locationId: document.getElementById("location_id").value,
        name: document.getElementById("location_name").value,
        description: document.getElementById("location_description").value
    }
    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/MONGO/location", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getLocations();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send(JSON.stringify(location));
}

const updateLocation = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var location = {
        locationId: document.getElementById("location_id-update").value,
        name: document.getElementById("location_name-update").value,
        description: document.getElementById("location_description-update").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("PUT", "http://localhost:8080/MONGO/location", true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getLocations();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send(JSON.stringify(location));
}

const getLocations = () => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var locations = JSON.parse(this.responseText);
            renderLocations(locations);
        }
    }
    xhr.open("GET", "http://localhost:8080/MONGO/location", true);
    xhr.send();
}

const renderLocations = (locations) => {
    var location_list = document.getElementById("location_list");
    location_list.innerHTML = "";
    //Loop through entries
    for (var location of locations) {
        console.log(location);
        var location_html = "<p>" + location.locationId + ". </p>" + "<h2>" + location.name + "</h2>" + "<br/>" + "<p>" + location.description + "</p>" + "<br/>";
        location_list.innerHTML += location_html;
    }
}

const deleteLocation = () => {
    authHeaderValue = "Basic " + btoa(username + ":" + password);

    var id = document.getElementById("location_id-delete").value;

    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/MONGO/location/" + id, true);
    xhr.setRequestHeader("Authorization", authHeaderValue);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getLocations();
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 401) { //not logged in
            alert("401 - You are not logged in");
        } else if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 403) { //logged in but not admin
            alert("403 - You are not authorized to create a person");
        }
    }
    xhr.send();
}

window.onload = function() {
    getPeople();
    getDevils();
    getLocations();
}