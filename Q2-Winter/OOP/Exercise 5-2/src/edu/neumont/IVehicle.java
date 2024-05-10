package edu.neumont;

public interface IVehicle extends IAcceleratable, IBrakable {
    void accelerate();
    void brake();
    void setSpeed(int s);

}
