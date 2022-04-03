package com.company.Classes;

public enum Types {
    Cat("Кошка"), Dog("Собака"), Bird("Птица"), Horse("Лошадь");
    private String type;
    Types(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
