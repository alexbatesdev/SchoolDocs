const dal = require("./data/mongoDAL");
async function go() {
    var bulldogs = await dal.getAllBulldogs();
    console.log(bulldogs);

    dal.addBulldog("Buster", 2, ["Black", "White"]);
    dal.addBulldog("Sandy", 56, ["Brown", "White"]);
    dal.addBulldog("Gus", 39, ["Brown", "White"]);
}

go();
