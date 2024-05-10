
let config = {
    type: Phaser.AUTO,
    parent: 'content',
    width: 800,
    height: 600,
    scene: {
        key: 'main',
        preload,
        create,
        update
    }
};
let game = new Phaser.Game(config);

let gfx;
let path;
let bloons;


function preload() {
    this.load.image("spawner", "assets/spawner.png");
    this.load.image("troop", "assets/troop.png");

}

class Troop extends Phaser.GameObjects.GameObject {
    
    constructor(scene, x, y, key = 'troop') {
        super(scene);
        this.scene = scene;
        this.health;
        this.speed;
        this.sprite = key;

        this.bloonConfig = {
            positionOnPath: true,
            duration: 10000,
            repeat: 0,
            yoyo: false,
            rotateToPath: false,
            verticalAdjust: true
        };
        this.scene.add.follower(path, x, y, 'troop').startFollow(this.bloonConfig);
    }

    
}

class WaveMachine {
    constructor(scene) {
        this.scene = scene;
        this.waveProgress = 0;
        this.inProgress = false;
        this.isAuto = false;
        this.waveGroup;
        this.timer;
    }

    startWave(roundNum, delaySec, quantity = this.fib(roundNum)) {
        console.log(`Starting wave ${roundNum} with ${quantity} bloons`);
        let delayMilli = delaySec * 1000;
        this.waveGroup = new Phaser.GameObjects.Group(this.scene);
        this.timer = this.scene.time.addEvent({
            delay: delayMilli,
            callback: () => {
                let troop = new Troop(this.scene, 50, 50);
                this.waveGroup.add(troop);
                log(this.waveGroup);
                console.log(`Added troop to troop to group`);
                this.waveProgress++;
                if (this.waveProgress >= quantity) {
                    this.waveProgress = 0;
                    if (this.isAuto) {
                        // wait for 5 seconds after the timer finishes before starting the next wave
                        this.scene.time.addEvent({
                            delay: quantity * delayMilli + 5000,
                            callback: () => {
                                this.startWave(roundNum + 1, delaySec);
                            }
                        });
                    }
                }
            },
            repeat: quantity - 1
        });
    }

    startAutoWaves(roundNum, delaySec, quantity) {
        this.isAuto = true;
        console.log(`Starting auto waves`);
        this.startWave(roundNum, delaySec, quantity);
    }

    stopAutoWaves() {
        this.isAuto = false;
        this.timer.remove(false);
    }

    stopWave() {
        this.timer.remove(false);
    }

    // a function that takes in an input and returns the value of the fibbonaci sequence at that value
    fib(n) {
        if (n < 2) {
            return n;
        }
        return this.fib(n - 1) + this.fib(n - 2);
    }
}

// Might be completely redundant, will have to get popping of bloons working first
class Wave {
    constructor(scene) {
        this.scene = scene;
        this.waves = [];
        this.tempwave = [];
    }
    
    addToWave(troop) {
        Tempwave.push(troop);
    }

    endWave() {
        this.wave.push(this.tempwave);
        this.tempwave = [];
    }
}

function create() {
    gfx = this.add.graphics();
    path = new Phaser.Curves.Path(50, 0);
    path.lineTo(50, 400);
    path.lineTo(500, 400);
    path.lineTo(500, 600);

    bloons = new Phaser.GameObjects.Group(this);

    let spawner = new WaveMachine(this);
    spawner.startAutoWaves(1, 0.5);
    //spawner.startWave(7, 0.5)


    console.log(this);
}

function update(time, delta) {
    gfx.clear();
    gfx.lineStyle(2, 0xffffff, 1);
    path.draw(gfx)

}

// Eddie stuff

class Tower extends Phaser.GameObjects.Image{
    constructor(scene, x, y){
        super(scene, x, y, "tower");
        this.cost = 100;
        this.setScale(2);
        console.log("Made a tower");
    }
    turret(scene) {
        Phaser.GameObjects.Image.call(this, scene, this.x, this.y, "turret");
        this.nextFire = 0;
    }
    placeTurret(scene) {
        this.turret(scene);
        scene.add.existing(this);
    }
    update(time, delta) {
        if (time > this.nextFire) {
            this.nextFire = time + 1000;
            this.scene.physics.add.sprite(this.x, this.y, "bullet");
            this.dx = 0;
            this.dy = 0;
            this.lifespan = 0;
            this.speed = Phaser.Math.GetSpeed(600, 1);
        }
    }
}

class Bullet extends Phaser.GameObjects.Image{
    constructor(scene, x, y){
        super(scene, x, y, "bullet");
        this.speed = 600;
        this.lifespan = 0;
        this.setActive(false);
        this.setVisible(false);
        this.setScale(2);
    }
    fire(x, y, angle) {
        this.setActive(true);
        this.setVisible(true);
        this.setPosition(x, y);
        this.setRotation(angle);
        this.dx = Math.cos(angle);
        this.dy = Math.sin(angle);
        this.lifespan = 100;
    }
    update(time, delta) {
        this.lifespan -= delta;
        this.x += this.dx * (this.speed * delta);
        this.y += this.dy * (this.speed * delta);
        if (this.lifespan <= 0) {
            this.setActive(false);
            this.setVisible(false);
        }
    }
}