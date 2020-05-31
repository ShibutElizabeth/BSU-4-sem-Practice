package com.graviwave;

import java.util.Objects;

public class Dog extends Animal {
    public enum Maturity {ADULT, PUPPY}

    private Maturity maturity;
    private String breed;
    private Skill skill;

    public Dog (String breed, Maturity maturity, Skill skill) throws BadInputException {
        super(Type.MAMMAL);
        if(breed == null)
            throw new BadInputException("Breed cannot be null!");
        if(maturity == null)
            throw new BadInputException("Maturity cannot be null!");
        if(skill == null)
            throw new BadInputException("Skill cannot be null!");

        this.breed = breed;
        this.maturity = maturity;
        this.skill = skill;
    }


    public String vote(){
        if(skill == Skill.ABSENT){
            return "<Skill is " + Skill.ABSENT.toString().toLowerCase() + ">";
        }
        else
            return "Skill " + Skill.EXISTS.toString().toLowerCase() + ">";
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" | Breed: ");
        sb.append(breed).append(" | Maturity: ").append(maturity).
                append(" | Vote: ").append(vote()).append(super.toString());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), breed, maturity, skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return breed.equals(dog.breed) && maturity.equals(dog.maturity)
                && skill.equals(dog.skill);
    }
}
