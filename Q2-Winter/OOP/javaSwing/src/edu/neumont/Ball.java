package edu.neumont;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Ball {
    private Game game;

    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;
    private double size;

    public Ball(Game game, double x, double y, double dx, double dy, double size) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        try {
            image = ImageIO.read(new File("src/resources/happy_face.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update() {
        x += dx;
        y += dy;

        if (x < 0 || x > game.getWidth()) dx *= -1;
        if (y < 0 || y > game.getHeight()) dy *= -1;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.blue);
        g2d.fillOval((int)x, (int)y, (int)size, (int)size);
        g2d.setColor(Color.black);
        g2d.fillOval((int)x, (int)y, (int)(size*.75), (int)(size*.75));

        g2d.drawImage(image, (int)x, (int)y, game);

    }
}
