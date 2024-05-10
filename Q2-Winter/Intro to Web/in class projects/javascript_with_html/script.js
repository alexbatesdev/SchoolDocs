const container = document.getElementById('container');
const my_button = document.getElementById('my_button');
const btn1 = document.getElementById('btn1');
const btn2 = document.getElementById('btn2');
const btn3 = document.getElementById('btn3');
const guess_holder = document.getElementById('guess_holder');

container.innerHTML = '<h1>Grumblo bumblo</h1>';

const testvar = () => {
    var a = 5;
    for (var i=0; i <3; i++) {
        console.log(a, i);
    }
    console.log(i);
}

testvar();

const testlet = () => {
    let a = 5;
    for (let i=0; i <3; i++) {
        console.log(a, i);
    }
    // console.log(i)
}

testlet();

let name = 'Jon';

let message = `
<h1 style="color: #FF8604">
    I'm sorry ${name}, I need lasagna.
</h1>
<div id='content'>
    <p>This is my webpage!</p>
</div>
`;

container.innerHTML = message;

my_button.addEventListener('click', () => {
    container.innerHTML = '<h2>Someone clicked the button</h2>';
    my_button.innerHTML = 'You clicked me!';
});

const handleClick = evt => {
    // console.log(evt)
    container.innerHTML = `${evt.target.id} has been clicked`
    if(evt.target.id == 'btn1') {
        let rand = Math.floor(Math.random() * 100) + 1;
        btn1.innerHTML = rand;
    }

    if(evt.target.id == 'btn3') {
        let guess = Number(guess_holder.value);
        btn3.innerHTML = guess;
    }
}

btn1.addEventListener('click', handleClick);
btn2.addEventListener('click', handleClick);
btn3.addEventListener('click', handleClick);