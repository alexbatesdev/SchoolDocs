package edu.neumont;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLayout(null);

        Game game = new Game();
        game.setBounds(0,0,400,400);
        frame.add(game);

        Timer timer = new Timer(17, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
                game.newBall();
            }
        });
        timer.start();

//        JLabel label = new JLabel("Hello World");
//        label.setBounds(100,100,100,100);
//        frame.add(label);
//
//        JTextField textField = new JTextField();
//        textField.setBounds(100,100,100,20);
//        frame.add(textField);
//
//        JButton button = new JButton("Click Me!");
//        button.setBounds(50,50,100,50);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                label.setText("Button was Pressed");
//            }
//        });
//        frame.add(button);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
