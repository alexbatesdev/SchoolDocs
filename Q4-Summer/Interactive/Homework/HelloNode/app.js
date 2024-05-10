const owo = require('@zuzak/owo');
const eightball = require('8ball')();
const ball = require("8ball.js");
const calculator = require('./modules/calc');
var rl = require('rocket-launcher');

let prompt = `Do I need to attend this class?\n8Ball: ${eightball}`;

console.log("Hello Node!");
console.log(owo(prompt));

console.log(owo(`Are we all doomed?\nFixed8Ball.js: ${ball()}`));

console.log("Math: ", calculator.calc.add(10,5));
console.log("Math 2: ", calculator.calc.sub(10, 5));

rl.frame(16).type(owo("Node Packages Rock!!!"),1000).delay(1000).count(20).launch();