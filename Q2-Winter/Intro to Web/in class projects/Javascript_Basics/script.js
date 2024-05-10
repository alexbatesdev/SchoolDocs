let a = 5;
console.log(a);

a = "Bob";
console.log(a);

a = false;
console.log(a);

a = [3, "Joe", true, "Sally", 23, a]
console.log(a);

a.push('Fred');
console.log(a);

a.pop();
console.log(a);

a.unshift('Diane');
console.log(a);

a.shift();
console.log(a);

console.log(a[3]);

let fish = [1, 2, 'red', 'blue'];

a.push(fish);
console.log(a);
console.log(a[6][2]);

let bob = {
    name: 'Bob',
    age: 21,
    species: 'Zombie',
    hobbies: ['moaning', 'wandering aimlessly', 'eating brains']
};

console.log(bob);

for(let i = 0; i < 5; i++) {
    console.log(i)
}

function hello(name) {
    console.log("Hello" + name);
}

hello('Biff');

let goodbye = function(name) {
    console.log('Goodbye ' + name);
}

goodbye('Fred');

let howdy = (name) => {
    return(`Howdy ${name}!! *BANG*`)
}

console.log(howdy('Abe'));