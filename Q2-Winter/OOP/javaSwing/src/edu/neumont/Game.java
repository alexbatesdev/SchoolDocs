package edu.neumont;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel {

    ArrayList<Ball> balls = new ArrayList<>();

    public Game() {
        for (int i = 0; i < 5; i++) {
            newBall();
        }
    }

    public void newBall() {
        Ball ball = new Ball(this, new Random().nextDouble(380), new Random().nextDouble(380), new Random().nextDouble(-10,10), new Random().nextDouble(-10,10), new Random().nextDouble(0,100));
        balls.add(ball);
    }

    public void update() {
        for (Ball ball : balls) {
            ball.update();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0,0, 400,400);

        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
}
