package com.graviwave;


public class Test {
    public static void main(String[] args) {

        MyContainer<Puppy> puppies = new MyContainer<>();
        puppies.add(new Puppy("Pug", Animal.Skill.ABSENT, "Keks", Puppy.How.CAREFULLY));
        puppies.add(new Puppy("Dalmatian", Animal.Skill.EXISTS, "Lucky", Puppy.How.TOOTHLESS));
        puppies.add(new Puppy("Shepherd", Animal.Skill.EXISTS, "Doophy", Puppy.How.PAINFULLY));
        puppies.add(new Puppy("Spaniel", Animal.Skill.ABSENT, "Loly", Puppy.How.CAREFULLY));
        System.out.println("    TESTING METHODS      ");
        System.out.println("***toString***");
        System.out.println(puppies.get(0).toString());
        System.out.println("***jump***");
        System.out.println(puppies.get(1).jump());
        System.out.println("***run***");
        System.out.println(puppies.get(3).run());
        System.out.println("***getName***");
        System.out.println(puppies.get(0).getName());
        System.out.println("***bite***");
        System.out.println(puppies.get(1).bite());
        System.out.println(puppies.get(2).bite());
        System.out.println(puppies.get(3).bite());


    }
}
