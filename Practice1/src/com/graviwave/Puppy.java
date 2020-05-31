package com.graviwave;


import java.util.Objects;

public class Puppy extends Dog {

    private String name;
    public enum How {PAINFULLY, CAREFULLY, TOOTHLESS}
    private How bites;


    public Puppy(String breed, Skill skill, String name, How bites) throws BadInputException {
        super(breed, Maturity.PUPPY, skill);
        if (name == null)
            throw new BadInputException("Name can't be empty");
        if(bites == null)
            throw new BadInputException("Bites cannot be null!");
        this.name = name;
        this.bites = bites;
    }

    public String getName(){
        return name;
    }

    public String bite(){
        if(bites == How.CAREFULLY) {
            return "Bites carefully. Good boy:)";
        }
        if(bites == How.PAINFULLY){
            return "Bites painfully. Don't run up!";
        }
        else
            return "Doesn't bites. Toothless puppy.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" | Name: ");
        sb.append(name).append(" | Bites: ").append(bite()).
                append(super.toString());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, bites);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puppy)) return false;
        if (!super.equals(o)) return false;
        Puppy puppy = (Puppy) o;
        return name.equals(puppy.name) && bites.equals(puppy.bites);
    }

}
