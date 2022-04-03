package com.company.Frames;

import com.company.Classes.Animal;
import com.company.Classes.DigitFilter;
import com.company.Classes.Types;
import com.company.DataBase.DataBase;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JFrame {
    JComboBox<Types> animalType;
    JComboBox<String> animalColor;
    JTextField animalName;
    JTextField animaBreed;
    JTextField animalAge;
    JLabel log;
    JButton add;
    JButton close;
    JLabel name;
    JLabel color;
    JLabel breed;
    JLabel age;
    JLabel type;

    String[] colour = new String[] {"Черный", "Белый", "Коричневый", "Серый"};
    public AddFrame(){
        super("Добавить животное");
        setSize(400, 700);
        init();
        setLayout(null);
        setVisible(true);
        initListeners();
    }

    private void init(){
        log = new JLabel();
        log.setBounds(0, 50, 150, 30);
        add(log);

        animalName = new JTextField();
        animalName.setBounds(150, 150, 200, 30);
        add(animalName);

        name = new JLabel("Кличка");
        name.setBounds(75, 150, 60, 30);
        add(name);

        animalType = new JComboBox<>(Types.values());
        animalType.setBounds(150, 200, 200, 30);
        add(animalType);

        type = new JLabel("Тип");
        type.setBounds(75, 200, 60, 30);
        add(type);

        animaBreed = new JTextField();
        animaBreed.setBounds(150, 250, 200, 30);
        add(animaBreed);

        breed = new JLabel("Порода");
        breed.setBounds(75, 250, 60, 30);
        add(breed);

        animalColor = new JComboBox<>(colour);
        animalColor.setBounds(150, 300, 200, 30);
        add(animalColor);

        color = new JLabel("Цвет");
        color.setBounds(75, 300, 200, 30);
        add(color);

        animalAge = new JTextField();
        PlainDocument doc = (PlainDocument) animalAge.getDocument();
        doc.setDocumentFilter(new DigitFilter());
        animalAge.setBounds(150, 350, 200, 30);
        add(animalAge);

        age = new JLabel("Возраст");
        age.setBounds(75, 350, 200, 30);
        add(age);

        add = new JButton("Добавить");
        add.setBounds(150, 400, 100, 60);
        add(add);

        close = new JButton("Закрыть");
        close.setBounds(150, 470, 100, 60);
        add(close);
    }

    void initListeners(){
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var animal = new Animal();
                animal.setType((Types)animalType.getSelectedItem());
                try {
                    animal.setName(animalName.getText());
                } catch (Exception ex) {
                    log.setText("Вы не ввели имя!");
                    return;
                }
                try {
                    animal.setColour(String.valueOf(animalColor.getSelectedItem()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    animal.setBreed(animaBreed.getText());
                } catch (Exception ex) {
                    log.setText("Вы не ввели породу!");
                    return;
                }
                try {
                    animal.setAge(Integer.parseInt(animalAge.getText()));
                } catch (Exception ex) {
                    log.setText("Вы не ввели возраст!");
                    return;
                }
                DataBase.addAnimal(animal);
                animalAge.setText("");
                animaBreed.setText("");
                animalName.setText("");
                log.setText("Запись успешно давлена");
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });
    }

}
