package sample;

import java.io.Serializable;

public class Planet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double mass;
    private double diameter;
    private int count;
    private String system;

    Planet(String name, double mass, double diameter, int count, String system){
        try {
            this.name = name;
            this.mass = mass;
            this.diameter = diameter;
            this.count = count;

            this.system = system.toUpperCase();
        }
        catch (NumberFormatException e){
            throw new MyException(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getDiameter() {
        return diameter;
    }

    public int getCount() {
        return count;
    }

    public String getSystem() {
        return system;
    }
}
