package edu.neumont;

public class Car implements IVehicle {
    private static final double HP = 10;
    private static final double BP = 6;
    private static final double MAX_SPEED = 106;

    private double speed;

    @Override
    public void accelerate() {
        speed += HP;
        speed = Math.min(speed, MAX_SPEED);
    }
    @Override
    public void brake() {
        speed -= BP;
        speed = Math.max(speed, 0);
    }

    @Override
    public void setSpeed(int s){
        speed = s;
    }

    @Override
    public String toString() {
        return "Car{" +
                "speed=" + speed +
                '}';
    }
}
