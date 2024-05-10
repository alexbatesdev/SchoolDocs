package edu.neumont;

public class Person {

    private String prefix;
    private String name;
    private String suffix;
    private int age;


    @Override
    public String toString() {
        return getCompleteName() + "\nAge: " + age;
    }



    public String getCompleteName() {
        return prefix + " " + name + " " + suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
