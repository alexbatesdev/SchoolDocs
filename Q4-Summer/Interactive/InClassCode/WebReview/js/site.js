console.log('It do!')

//variables
var myVariable;

var num = 1;
var text = 'text';
var text2 = "text";

var format = `this is a format string ${num}`;
var concat = "This " + num + " and this"

let num2 = 2;

const wowitcantchange = 100;

console.log(myVariable)

// True False Stuff

var num = 1;
var numOne = '1';

console.log(num == numOne); //true
console.log(num === numOne); //false

var myVar;
console.log(myNull == undefined);

var myNull = null;
console.log(myNull == undefined)

//Objects are created with curly braces
var myObject = {
    myKey: "myValue",
    otherKey: "otherValue",
    object: {
        desc: "Nested Deeper!"
    }
}

//Function
function Go() {
    console.log("Goed!")
}

Go();
console.log(Go)

var myFunction = function(name = "Gooby") {
    console.log(`Hello ${name}`);
}

myFunction("Alex");

console.log(myFunction);

//Funky Functions that are hard to grasp
//arrow functions

//Basic example
function SayHello(){
    console.log("Hello");
}

//Arrow Version
var myArrowFunction = () => console.log("Hello");

SayHello();
myArrowFunction();

var myArrowFunction2 = (name, weight, color) => {
    console.log(name);
    console.log(weight);
    console.log(color);
}

myArrowFunction2("Boof", "Doof", "Green");

//loop
//for loop
for (let i = 0; i < 10; i++) {
    console.log(i)
}

//while loop
var i = 0;
var go = true;
while(go) {
    i++;

    if(i >= 10){
        go = false;
    }
    console.log(i);
}

i = 0;
go = true;
do {
    i++;

    if(i >= 10){
        go = false;
    }
    console.log(i);
} while(go);

//arrays
var dogs = ["Finn", "Alba", "Spike", 1, 50, true];

dogs[6] = "Sue";

//console.log(dogs[1]);

for (let i = 0; i < dogs.length; i++) {
    console.log(dogs[i]);
}

// Directory/Hashmap
var dict = [];

dict["Bradley"] = "Fox";
dict["Grayson"] = "Sudweeks";
dict[{name: "Han"}] = "Calder";

// for in is a good way to iterate
for (item in dict) {
    console.log(item);
}

//for each

dict.forEach((value) => console.log(value));

// Stack/Queue
// Stack: Like a stack of plates
// FI LO - First In Last Out

// Queue: Like a line at a register
// FIFO - First In First Out
var stackOrQueue = [];

stackOrQueue.push(1);
stackOrQueue.push(2);
stackOrQueue.push(3);

console.log(stackOrQueue.pop())