let zipcode = 84111;

let url = `https://api.zippopotam.us/us/${zipcode}`;

fetch(url)
    .then(r => r.json())
    .then(data => {
        console.log(data)
    })
    .catch(err => console.log(err));

var goButt = document.getElementById("goButt")
console.log(goButt);

goButt.addEventListener("click", evt => {
    var zipcodeBox = document.getElementById("zipInput");

    alert(zipcodeBox.value);
})