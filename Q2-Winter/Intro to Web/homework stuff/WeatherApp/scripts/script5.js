const content = document.getElementById('content');

const API = 'bd9c77d7032a0813ffd29e8c529bc6f6';
let city = "";

let url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`;

let submit_btn = document.getElementById('submit_btn');
let my_textbox = document.getElementById('my_textbox');

const days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
const date = new Date();

submit_btn.addEventListener('click', evt => {
    let stuff_in_box = my_textbox.value;
    localStorage.setItem('city', stuff_in_box);
    my_textbox.value = "";
    city = localStorage.getItem('city');
    url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`
    call();
})

const main = (data, day) => {
    console.log(data);
    console.log(day);
    let today = `
    <h1>${days[day]}'s weather</h1>
    <h3>${city}</h3>
    <p>
    <span id="temp1">Temp: ${data.daily[day].temp.day}&degF<br /></span>
    Weather: ${data.daily[day].weather[0].description}
    </p>
    <img id="icon" alt="${data.daily[day].weather[0].description}" title="${data.daily[day].weather[0].description}" src="http://openweathermap.org/img/wn/${data.daily[day].weather[0].icon}@2x.png" />
    `;
    content.innerHTML = today;
}

const call = day => {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let lat = data.city.coord.lat;
            let lon = data.city.coord.lon;
            console.log(lat, lon);
            console.log(city);
            let new_url = `https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&units=imperial&appid=${API}`;
            console.log(new_url)
            fetch(new_url)
                .then(response => response.json())
                .then(data => {
                        console.log(day);
                        main(data, day);
                })
        })
}

if (localStorage.getItem('city') != null) {
    city = localStorage.getItem('city')
    url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`
    call(0);
}

const tabs = document.getElementById('window_tabs');
for (let i = 0; i < tabs.childElementCount; i++) {
    let weekday = date.getDay() + i;
    if (weekday > 6) {
        weekday -= 7;
    }
    tabs.children[i].innerHTML = days[weekday];
    tabs.children[i].addEventListener('click', evt => {
        for (let j = 0; j < tabs.childElementCount; j++) {
            if (j == i) {
                document.getElementById(`tab${j+1}`).classList.add('active_window_tab');
            } else {
                document.getElementById(`tab${j+1}`).classList.remove('active_window_tab');
            }
            
        }
        console.log(i)
        call(i);
    })
    
}









//Updates the notification tray to have today's date and the current time
const notif_tray = document.getElementById('notif_tray');

notif_tray_update();
setInterval(notif_tray_update, 15000);

function notif_tray_update() {
    const d = new Date();
    let date = d.toLocaleDateString();
    let hours = d.getHours();
    let am_pm = "AM";

    if (hours > 12) {
        hours -= 12;
        am_pm = "PM";
    }
    let minutes = d.getMinutes();
    if (minutes < 10) {
        minutes = `${0}${minutes}`;
    }
    let time = `${hours}:${minutes} ${am_pm}`;
    let info = `
    <p id="date_time">
    ${date}<br />
    ${time}
    </p>
    `
    notif_tray.innerHTML = info;
}


// Makes the X button actually close the window, reopen by clicking its tab 
const exit = document.getElementById('exit');
const five_window = document.getElementById('five_window')
const five_tab = document.getElementById('five_tab');

exit.addEventListener('click', evt => {
    five_window.style.visibility = "hidden";
    five_tab.classList.remove('active');
})

five_tab.addEventListener('click', evt => {
    five_window.style.visibility = "visible";
    five_tab.classList.add('active');
})


//Lets the main window be dragged around
draggyBoi(document.getElementById("five_window"));

function draggyBoi(elmnt) {
    document.getElementById(elmnt.id + "_header").onmousedown = startDrag;
    let xDiff = 0;
    let yDiff = 0;
    let xPos = 0;
    let yPos = 0;

    function startDrag(evt) {
        evt = evt || window.event;
        evt.preventDefault();

        xPos = evt.clientX;
        yPos = evt.clientY;

        document.onmouseup = end;

        document.onmousemove = move;
    }

    function move(evt) {
        evt = evt || window.event;
        evt.preventDefault();

        xDiff = xPos - evt.clientX;
        yDiff = yPos - evt.clientY;
        xPos = evt.clientX;
        yPos = evt.clientY;

        elmnt.style.top = (elmnt.offsetTop - yDiff) + "px";
        elmnt.style.left = (elmnt.offsetLeft - xDiff) + "px";
    }

    function end() {
        document.onmouseup = null;
        document.onmousemove = null;
    }
}


//lets the main window be resized
resizeElement(document.getElementById("five_window"));
function resizeElement(elmnt) {
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0, pos5 = 0, pos6 = 0;
    var elmntBox = elmnt.getBoundingClientRect();

    document.getElementById(elmnt.id + "_resizer").onmousedown = dragMouseDown;

    function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();
        // get element bounds
        elmntBox = elmnt.getBoundingClientRect();
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        // get element x and y
        pos5 = (elmntBox.left + window.scrollX);
        pos6 = (elmntBox.top + window.scrollY);
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        elmnt.style.width = (pos3 - pos5) + "px";
        elmnt.style.height = (pos4 - pos6) + "px";
        elmnt.childNodes[5].style.width = (pos3 - pos5 - 43) + "px";
        elmnt.childNodes[5].style.height = (pos4 - pos6 - 93) + "px";

        elmnt.childNodes[3].style.width = (pos3 - pos5 - 33) + "px";
    }

    function closeDragElement() {
        // stop moving when mouse button is released:
        document.onmouseup = null;
        document.onmousemove = null;
    }
}


dragbox(document.getElementById("icons"));

function dragbox(canvas) {
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0, pos5 = 0, pos6 = 0;
    canvas.onmousedown = dragMouseDown;
    const div = document.createElement('div');
    div.style.visibility = "hidden";
    const script = document.getElementById('script');

    function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();


        body.insertBefore(div, script);
        div.style.visibility = "visible";
        div.style.backgroundColor = "#005eff73";
        div.style.border = "1px solid #005eff";
        div.style.position = "fixed";
        div.style.zIndex = 0;
        div.style.left = e.clientX + "px";
        div.style.top = e.clientY + "px";
        // get element bounds
        elmntBox = div.getBoundingClientRect();
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        // get element x and y
        pos5 = (elmntBox.left + window.scrollX);
        pos6 = (elmntBox.top + window.scrollY);
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        div.style.width = (pos3 - pos5) + "px";
        div.style.height = (pos4 - pos6) + "px";
    }

    function closeDragElement() {
        // stop moving when mouse button is released:
        div.style.visibility = "hidden";
        div.style.width = "0px";
        div.style.height = "0px";
        document.onmouseup = null;
        document.onmousemove = null;
    }
}