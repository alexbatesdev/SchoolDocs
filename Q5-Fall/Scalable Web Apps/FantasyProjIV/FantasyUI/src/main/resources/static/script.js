function createPerson() {
    var person = {
        id: document.getElementById("person_id").value,
        name: document.getElementById("person_name").value,
        isDeceased: document.getElementById("person_is_deceased").checked,
        description: document.getElementById("person_description").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/person", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getPeople();
        }
    }
    xhr.send(JSON.stringify(person));
}

function getPeople() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var people = JSON.parse(this.responseText);
            renderPeople(people);
        }
    }
    xhr.open("GET", "http://localhost:8080/person", true);
    xhr.send();
}

function renderPeople(people) {
    var person_list = document.getElementById("person_list");
    person_list.innerHTML = "";
    //Loop through entries
    for (var person of people) {
        var person_html = "<p>" + person.id + ". </p>" + "<h2>" + person.name + "</h2>" + "<br/>" + "<h3>" +  person.isDeceased + "</h3>" + "<br/>" + "<p>" + person.description + "</p>" + "<br/>";
        person_list.innerHTML += person_html;
    }
}

function deletePerson() {
    var id = document.getElementById("person_id").value;

    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/person/" + id, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getPeople();
        }
    }
    xhr.send();
}





function createDevil() {
    var devil = {
        id: document.getElementById("devil_id").value,
        name: document.getElementById("devil_name").value,
        type: document.getElementById("devil_type").value,
        description: document.getElementById("devil_description").value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/devil", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getDevils();
        }
    }
    xhr.send(JSON.stringify(devil));
}

function getDevils() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var devils = JSON.parse(this.responseText);
            renderDevils(devils);
        }
    }
    xhr.open("GET", "http://localhost:8080/devil", true);
    xhr.send();
}

function renderDevils(devils) {
    var devil_list = document.getElementById("devil_list");
    devil_list.innerHTML = "";
    //Loop through entries
    for (var devil of devils) {
        var devil_html = "<p>" + devil.id + ". </p>" + "<h2>" + devil.name + "</h2>" + "<br/>" + "<h3>" +  devil.type + "</h3>" + "<br/>" + "<p>" + devil.description + "</p>" + "<br/>";
        devil_list.innerHTML += devil_html;
    }
}

function deleteDevil() {
    var id = document.getElementById("devil_id").value;

    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/devil/" + id, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getDevils();
        }
    }
    xhr.send();
}




function createLocation() {
    var location = {
        id: document.getElementById("location_id").value,
        name: document.getElementById("location_name").value,
        description: document.getElementById("location_description").value
    }
    alert(location);
    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/location", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getLocations();
        }
    }
    xhr.send(JSON.stringify(location));
}

function getLocations() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var locations = JSON.parse(this.responseText);
            renderLocations(locations);
        }
    }
    xhr.open("GET", "http://localhost:8080/location", true);
    xhr.send();
}

function renderLocations(locations) {
    var location_list = document.getElementById("location_list");
    location_list.innerHTML = "";
    //Loop through entries
    for (var location of locations) {
        var location_html = "<p>" + location.id + ". </p>" + "<h2>" + location.name + "</h2>" + "<br/>" + "<p>" + location.description + "</p>" + "<br/>";
        location_list.innerHTML += location_html;
    }
}

function deleteLocation() {
    var id = document.getElementById("location_id").value;

    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/location/" + id, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            getLocations();
        }
    }
    xhr.send();
}



window.onload = function() {
    getPeople();
    getDevils();
    getLocations();
}