const urlParams = new URLSearchParams(document.location.search);
const s = urlParams.get('s');
if (s == "y") {
    document.body.onload = createElement;

    function createElement () {
        const div = document.createElement("div");
        div.setAttribute('id', 'startup_screen');

        const btn = document.createElement("button");
        btn.setAttribute('id', 'startup_btn');
        btn.innerHTML = "Start"
        div.appendChild(btn);
        btn.addEventListener('click', evt => {
            div.style.backgroundImage = "url(img/startup.gif)";
            btn.style.display = "none";
            setTimeout(() => {
                div.style.opacity = 1;
                let fade = setInterval( () => {
                    if (div.style.opacity > 0) {
                        div.style.opacity -= 0.1;
                    } else {
                        div.style.display = "none";
                        let startup_snd = new Audio("sound/startup.mp3");
                        startup_snd.play();
                        clearInterval(fade);
                    }
                }, 50)
            }, 4000);
        })

        const script = document.getElementById('script');
        document.body.insertBefore(div, script);
    }
}

const content = document.getElementById('content');

const API = 'bd9c77d7032a0813ffd29e8c529bc6f6';
let city = "";

let url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`;

let submit_btn = document.getElementById('submit_btn');
let my_textbox = document.getElementById('my_textbox');

const tabs = document.getElementById('window_tabs')
let tabSelect = "";

submit_btn.addEventListener('click', evt => {
    let stuff_in_box = my_textbox.value;
    localStorage.setItem('city', stuff_in_box);
    my_textbox.value = "";
    city = localStorage.getItem('city');
    url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`
    call();
})

const main = data => {
    console.log(data);
    let today = `
    <h1>Today's weather</h1>
    <h3>${city}</h3>
    <p>
    <span id="temp1" title="min: ${data.daily[0].temp.min}F max: ${data.daily[0].temp.max}F">Temp: ${data.daily[0].temp.day}&degF<br /></span>
    Weather: ${data.daily[0].weather[0].description}
    </p>
    <img id="icon" alt="${data.daily[0].weather[0].description}" title="${data.daily[0].weather[0].description}" src="http://openweathermap.org/img/wn/${data.daily[0].weather[0].icon}@2x.png" />
    `;
    content.innerHTML = today;
}

const mainNow = data => {
    console.log(data);
    let now = `
    <h1>Today's weather</h1>
    <h3>${city}</h3>
    <p>
    Temp: ${data.current.temp}&degF<br />
    Weather: ${data.current.weather[0].description}
    </p>
    <img id="icon" alt="${data.current.weather[0].description}" title="${data.current.weather[0].description}" src="http://openweathermap.org/img/wn/${data.current.weather[0].icon}@2x.png" />
    `;
    content.innerHTML = now;
}

const mainExpanded = data => {
    console.log(data);
    let today = `
    <h1>Today's weather</h1>
    <h3>${city}</h3>
    <p>
    Temp: ${data.daily[0].temp.day}&degF<br />
    Feels Like: ${data.daily[0].feels_like.day}<br />
    Min: ${data.daily[0].temp.min}&degF Max: ${data.daily[0].temp.max}&degF<br />
    Humidity: ${data.daily[0].humidity}%<br />
    Wind Speed: ${data.daily[0].wind_speed} mph<br />
    Weather: ${data.daily[0].weather[0].description}
    </p>
    <img id="icon" alt="${data.daily[0].weather[0].description}" title="${data.daily[0].weather[0].description}" src="http://openweathermap.org/img/wn/${data.daily[0].weather[0].icon}@2x.png" />
    `;
    content.innerHTML = today;
}

const call = () => {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let lat = data.city.coord.lat;
            let lon = data.city.coord.lon;
            console.log(lat, lon);
            console.log(city);
            let new_url = `https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&units=imperial&appid=${API}`;
            fetch(new_url)
                .then(response => response.json())
                .then(data => {
                    console.log(tabs.children[1].attributes[1].value == 'window_tab active_window_tab');
                    //I could do this much better with a variable that stores a number but I thought of that after I implemented this so here we are with this solution instead
                    if (tabs.children[1].attributes[1].value == 'window_tab active_window_tab') {
                        mainNow(data);
                        let time = (Date.now()-(Date.now()%1000))/1000;
                        if (time > data.current.sunrise && time < data.current.sunset) {
                            document.getElementById("icon").style.backgroundColor = "#69d2fc";
                        } else {
                            document.getElementById("icon").style.backgroundColor = "#031550";
                        }
                    } else if (tabs.children[2].attributes[1].value == 'window_tab active_window_tab') {
                        mainExpanded(data);
                    } else {
                        main(data)
                    }
                })
        })
}

if (localStorage.getItem('city') != null) {
    city = localStorage.getItem('city')
    url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`
    call();
}

for (let i = 0; i < tabs.childElementCount; i++) {
    tabs.children[i].addEventListener('click', evt => {
        for (let j = 0; j < tabs.childElementCount; j++) {
            if (j == i) {
                document.getElementById(`tab${j+1}`).classList.add('active_window_tab');
            } else {
                document.getElementById(`tab${j+1}`).classList.remove('active_window_tab');
            }
            
        }
        call();
    })
    
}








// Fun XP stuff
const notif_tray = document.getElementById('notif_tray');

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

notif_tray_update();
setInterval(notif_tray_update, 15000);

const exit = document.getElementById('exit');
const today_window = document.getElementById('today_window')
const today_tab = document.getElementById('today_tab');

exit.addEventListener('click', evt => {
    today_window.style.visibility = "hidden";
    today_tab.classList.remove('active');
})

today_tab.addEventListener('click', evt => {
    today_window.style.visibility = "visible";
    today_tab.classList.add('active');
})

//Dragabble windows thing
draggyBoi(document.getElementById("today_window"));

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


//resizeable windows thing
resizeElement(document.getElementById("today_window"));
// I made this from the move element function and made it my own thing
function resizeElement(elmnt) {
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0, pos5 = 0, pos6 = 0;
    var elmntBox = elmnt.getBoundingClientRect();
    if (document.getElementById(elmnt.id + "_resizer")) {
        // if present, the header is where you move the DIV from:
        document.getElementById(elmnt.id + "_resizer").onmousedown = dragMouseDown;
    } else {
        // otherwise, move the DIV from anywhere inside the DIV:
        elmnt.onmousedown = dragMouseDown;
    }

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

















//on mouse down create div at mouse coordinates
//movement resizes div








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