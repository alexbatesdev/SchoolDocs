function insertDragonFly(name, ageYears) {
    db.animals.insert({
        name: name,
        ageYears: ageYears,
        species: {
            name: "dragonfly",
            canFly: true,
            environment: "fresh water"
        }
    })
}

db.animals.insert({
    name: "Gerald",
    ageYears: 69,
    spots: 420,
    species: {
        name: "pig",
        canFly: false,
        environment: "Ghetto"
    },
    canFly: true
});

db.animals.insert({
    name: "Phooey",
    ageYears: 1,
    lives: 8,
    species: {
        name: "Cat",
        lives: 9,
        hasTail: true,
        environment: "Outside"
    },
    canFly: true
});

db.animals.insert({
    name: "Jerbo",
    ageYears: 13,
    useTools: true,
    species: {
        name: "Monkey",
        hasTail: true,
        environment: "Outside"
    },
    canFly: true
});

function twoAnims(species, speciesDifferent) {
    db.animals.find({species : {$in: ["Monkey", "Cat"]}})
}