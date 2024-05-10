const content = document.getElementById('content');

const API = 'bd9c77d7032a0813ffd29e8c529bc6f6';
const city = "Salt Lake City, UT, US";

let url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`;

const main = data => {
    console.log(data);
    console.log(data.daily[0].weather[0].description);

    let forecast = "";
    for(let i = 0; i < 4; i++) {
        forecast += `
        <h2>Today</h2>
        <p>
        Temp: ${data.daily[i].temp.day}&degF<br />
        </p>
        <p>
        Weather: ${data.daily[i].weather[0].description}
        </p>
        <hr>
        `
    }

    // let now = `
    // <h2>Today</h2>
    // <p>
    // Temp: ${data.daily[0].main.temp}&degF<br />
    // Weather: ${data.daily[0].weather[0].description}
    // </p>
    // `;
    content.innerHTML = forecast;
}


const call = () => {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let lat = data.city.coord.lat;
            let lon = data.city.coord.lon;
            console.log(lat, lon);
            let new_url = `https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&units=imperial&appid=${API}`;
            fetch(new_url)
                .then(response => response.json())
                .then(data => {
                    main(data);
                })
        })
}

call();