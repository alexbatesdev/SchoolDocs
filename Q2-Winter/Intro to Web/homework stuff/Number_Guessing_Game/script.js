const btn1 = document.getElementById('btn1');
const btn2 = document.getElementById('btn2');
const btn3 = document.getElementById('btn3');
const guess_holder = document.getElementById('guess_holder');
let tries = 0;
let answer = 0;
let inProgress = false;

const handleClick = evt => {
    // console.log(evt)
    if(evt.target.id == 'btn1') {
        if (!inProgress) {
            let rand = Math.floor(Math.random() * 100) + 1;
            console.log(rand);
            answer = rand;
            tries = 0;
            btn1.innerHTML = "Game Started";
            btn2.innerHTML = "Give up"
            inProgress = true;
        }
    }

    if(evt.target.id == 'btn2') {
        if(inProgress) {
            btn1.innerHTML = "Restart?";
            btn2.innerHTML = `Correct answer was ${answer}`;
            inProgress = false;
        }
    }

    if(evt.target.id == 'btn3') {
        if(inProgress) {
            tries++;
            let guess = Number(guess_holder.value);
            console.log(guess);
            console.log(answer);
            if(guess == answer) {
                btn1.innerHTML = `You got it! Play Again?`;
                btn2.innerHTML = `It took you ${tries} tries`;
                inProgress = false;
                document.getElementById('win').innerHTML = `
            <div id="win_window">
                <h1>You win!!</h1>
                <img src="img/win.gif"/>
                <p id="quit">Click to dismiss</p>
            </div>
            `
            document.getElementById('quit').addEventListener('click', () => {
                document.getElementById('win').innerHTML = ''
            })
            } else if(guess > answer) {
                btn1.innerHTML = "Too high";
            } else if(guess < answer) {
                btn1.innerHTML = "Too low";
            }
        }
    }
}

btn1.addEventListener('click', handleClick);
btn2.addEventListener('click', handleClick);
btn3.addEventListener('click', handleClick);