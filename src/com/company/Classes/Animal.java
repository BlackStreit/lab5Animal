package com.company.Classes;

public class Animal {
    private int id;
    private String name;
    private Types type;
    private String breed;
    private String colour;
    private float age;

    public int getId() {
        return id;
    }

    public Animal() {
        this.id = 0;
        this.name = "name";
        this.type = Types.Cat;
        this.breed = "breed";
        this.colour = "colour";
        this.age = 1.0f;
    }

    public Animal(int id, String name, Types type, String breed, String colour, float age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.colour = colour;
        this.age = age;
    }

    public void setId(int id) throws Exception {
        if(id<0){
            throw  new Exception("ID задано не правильно");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name.length() == 0){
            throw new Exception("Вы не задали имя");
        }
        this.name = name;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) throws Exception {
        if(breed.length() == 0){
            throw new Exception("Вы не ввели породу");
        }
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) throws Exception {
        if(colour.length()==0){
            throw new Exception("Вы не ввели цвет");
        }
        this.colour = colour;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) throws Exception {
        if(age <=0){
            throw new Exception("Введите корректный возраст");
        }
        this.age = age;
    }
}
