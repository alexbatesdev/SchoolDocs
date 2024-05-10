package edu.neumont;

public class Car implements Vehicle{
    private static final double HP = 10;
    private static final double BP = 100;
    private static final double MAX_SPEED = 106;

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
