package com.graviwave;

import java.util.Objects;

public abstract class Animal {

    public enum Type {MAMMAL, FISH, REPTILE, AMPHIBIAN, BIRD}
    private Type type;
    public enum Skill {EXISTS, ABSENT}

    public Animal(Type type) throws BadInputException {
        if(type == null)
            throw new BadInputException("Type cannot be empty!");
        this.type = type;
    }

    public Animal(){
        this(Type.MAMMAL);
    }

    public String jump(){
        if(type == Type.MAMMAL || type == Type.BIRD){
            return "Skill " + Skill.EXISTS.toString().toLowerCase();
        }
        else
            return "Skill is " + Skill.ABSENT.toString().toLowerCase();
    }
    public String run(){
        if(type == Type.MAMMAL || type == Type.REPTILE || type == Type.AMPHIBIAN){
            return "Skill " + Skill.EXISTS.toString().toLowerCase();
        }
        else
            return "Skill is " + Skill.ABSENT.toString().toLowerCase();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" | Type of animal: ").append(type).append(" | Jump: ").append(jump()).
                append(" | Run: ").append(run());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return type.equals(animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

}
