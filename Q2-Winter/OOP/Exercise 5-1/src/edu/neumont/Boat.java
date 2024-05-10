package edu.neumont;

public class Boat implements Vehicle{
    private static final double HP = 15;
    private static final double BP = 3;
    private static final double MAX_SPEED = 80;

    private double speed;

    public double getSpeed() {
        return speed;
    }


    @Override
    public void Accelerate() {
        speed += HP;
        speed = Math.min(speed, MAX_SPEED);
    }
    @Override
    public void Brake() {
        speed -= 7;
        speed = Math.max(speed, 0);
    }
}
