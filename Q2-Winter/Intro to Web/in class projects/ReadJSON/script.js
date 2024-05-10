const content = document.getElementById('content');

const API = 'bd9c77d7032a0813ffd29e8c529bc6f6';
const city = "Salt Lake City, UT, US";

let url = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API}&units=imperial`;

const main = data => {
    console.log(data.list[0].weather[0].description);
    let now = `
    <h2>Today</h2>
    <p>
    Temp: ${data.list[0].main.temp}&degF<br />
    Weather: ${data.list[0].weather[0].description}
    </p>
    `;
    content.innerHTML = now;
}

fetch(url)
    .then(response => response.json())
    .then(data => {
        main(data)
    })
