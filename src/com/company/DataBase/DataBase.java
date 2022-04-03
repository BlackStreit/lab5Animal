package com.company.DataBase;

import com.company.Classes.Animal;
import com.company.Classes.Types;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    //Класс для отправки запросов в БД
    private static Statement statement;

    //Открыть соединение с БД
    public static void createDataBase() {
        try {
            Class.forName("org.sqlite.JDBC"); //Проверяем наличие JDBC драйвера для работы с БД
            //Класс для установки соединения
            Connection connection = DriverManager.getConnection("jdbc:sqlite:ZooDataBase");//соединение с БД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }
    //Создать таблицу в БД
    public static void createTable(){

        var sql = """
                CREATE TABLE IF NOT EXISTS Animals (
                \tid integer PRIMARY KEY AUTOINCREMENT,
                \tname text,
                \ttype text,
                \tbreed text,
                \tcolor text,
                \tage float
                );
                """;
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Добавление записей в бд
    public static void addAnimal(Animal a){
        //Запрос
        var sql = "INSERT INTO Animals (name, type, breed, color, age) VALUES ('"+a.getName()+"', '"+a.getType().getType()+"'" +
                ", '"+a.getBreed()+"', '"+a.getColour()+"', "+a.getAge()+")";
        //Отправка запроса
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Удаление записей из бд
    public static void deleteAnimal(int id){
        var sql = "delete from Animals where id = "+id+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Получение записей из БД
    public static Object[][] getAnimal(){
        ArrayList<Animal> animals = new ArrayList<>();
        var sql = "SELECT * FROM Animals";
        try {
            //Для получения результатов из БД в Java используется ResultSet
            ResultSet resultSet = statement.executeQuery(sql);
            //До тех пор, пока есть что читать
            while (resultSet.next()){
                var animal = new Animal();
                //Чтобы получить конкретное поле, нужно указать его тип данных и название столбца в таблице БД
                animal.setAge(resultSet.getFloat("age"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setColour(resultSet.getString("color"));
                animal.setId(resultSet.getInt("id"));
                animal.setName(resultSet.getString("name"));
                switch (resultSet.getString("type")){
                    case "Кошка"-> animal.setType(Types.Cat);
                    case "Собака"-> animal.setType(Types.Dog);
                    case "Птица"-> animal.setType(Types.Bird);
                    case "Лошадь"-> animal.setType(Types.Horse);
                }
                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Для записи данных в таблицу, нужно перенести лист в массив объектов
        Object[][] array = new String[animals.size()][6];
        for(int i = 0;i < array.length; i++){
            var animal = animals.get(i);
            array[i][0] = String.valueOf(animal.getId());
            array[i][1] = animal.getName();
            array[i][2] = animal.getType().getType();
            array[i][3] = animal.getBreed();
            array[i][4] = animal.getColour();
            array[i][5] = String.valueOf(animal.getAge());

        }
        return array;
    }
    //Получить конкретное животное по ID
    public static Animal findAnimal(int id){
        var animal = new Animal();
        var sql = "SELECT * FROM Animals WHERE id = "+id+";";
        try {
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                animal.setAge(resultSet.getFloat("age"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setColour(resultSet.getString("color"));
                animal.setId(resultSet.getInt("id"));
                animal.setName(resultSet.getString("name"));
                switch (resultSet.getString("type")){
                    case "Кошка"-> animal.setType(Types.Cat);
                    case "Собака"-> animal.setType(Types.Dog);
                    case "Птица"-> animal.setType(Types.Bird);
                    case "Лошадь"-> animal.setType(Types.Horse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animal;
    }
    //Получить все ID животных
    public static Integer[] getAnimalsId(){
        ArrayList<Integer> ids = new ArrayList<>();
        var sql = "select id from Animals";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer[] array = new Integer[ids.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ids.get(i);
        }
        return array;
    }
    //Изменить данные для конкретного животного
    public static void updateAnimal(Animal animal){
        var sql = "UPDATE Animals SET name = '"+animal.getName()+"', breed = '"+animal.getBreed()+"', type = '"+animal.getType().getType()+"', " +
                "color = '"+animal.getColour()+"', age = "+animal.getAge()+" WHERE id = "+animal.getId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
