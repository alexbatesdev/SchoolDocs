package edu.neumont;

public class IntRef {
    public int value;

    public IntRef(int value) {this.value = value;}

    public IntRef(IntRef other) { value = other.value;}

}
