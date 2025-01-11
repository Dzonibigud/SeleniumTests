package org.example;

public class Cat {
    String name;

    public void say(){
        System.out.println("Meooow");
    }

    public String getName(){
        return name;
    }

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }
}
