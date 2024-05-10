package edu.neumont;

public class SportsCar implements IVehicle {
    private static final double HP = 20;
    private static final double BP = 10;
    private static final double MAX_SPEED = 180;

    private double speed;

    public double getSpeed() {
        return speed;
    }


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
        return "SportsCar{" +
                "speed=" + speed +
                '}';
    }
}
