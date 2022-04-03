package com.company.Frames;

import com.company.Classes.Animal;
import com.company.Classes.DigitFilter;
import com.company.Classes.Types;
import com.company.DataBase.DataBase;

import javax.swing.*;
import javax.swing.text.PlainDocument;

public class EditFrame extends JFrame {
    JComboBox<Integer> idAnimal;
    JButton find;
    JLabel id;
    JComboBox<Types> animalType;
    JComboBox<String> animalColor;
    JTextField animalName;
    JTextField animaBreed;
    JTextField animalAge;
    JLabel log;
    JButton update;
    JButton close;
    JLabel name;
    JLabel color;
    JLabel breed;
    JLabel age;
    JLabel type;
    String[] colour = new String[] {"Черный", "Белый", "Коричневый", "Серый"};

    JPanel highPanel;
    JPanel middlePanel;
    public EditFrame(){
        super("Редактировать животное");
        setSize(400, 700);
        init();
        setVisible(true);
        initListeners();
    }

    public void init(){
        Box contents = new Box(BoxLayout.Y_AXIS);

        var highestPane = new JPanel();
        highestPane.setLayout(new BoxLayout(highestPane, BoxLayout.Y_AXIS));
        highPanel = new JPanel();
        highPanel.setLayout(new BoxLayout(highPanel, BoxLayout.Y_AXIS));

        id = new JLabel("ID животного");
        highPanel.add(id);

        idAnimal = new JComboBox<>(DataBase.getAnimalsId());
        highPanel.add(idAnimal);

        find = new JButton("Найти");
        find.setBounds(150, 500, 100, 60);
        highPanel.add(find);

        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        log = new JLabel();
        log.setBounds(0, 80, 150, 30);
        highestPane.add(log);

        name = new JLabel("Кличка");
        middlePanel.add(name);

        animalName = new JTextField();
        middlePanel.add(animalName);

        type = new JLabel("Тип");
        //type.setBounds(75, 230, 60, 30);
        middlePanel.add(type);

        animalType = new JComboBox<>(Types.values());
        middlePanel.add(animalType);

        breed = new JLabel("Порода");
        middlePanel.add(breed);

        animaBreed = new JTextField();
        middlePanel.add(animaBreed);

        color = new JLabel("Цвет");
        middlePanel.add(color);

        animalColor = new JComboBox<>(colour);
        middlePanel.add(animalColor);

        age = new JLabel("Возраст");
        middlePanel.add(age);

        animalAge = new JTextField();
        PlainDocument doc = (PlainDocument) animalAge.getDocument();
        doc.setDocumentFilter(new DigitFilter());
        middlePanel.add(animalAge);

        update = new JButton("Изменить");
        middlePanel.add(update);

        close = new JButton("Закрыть");

        var dawnPane = new JPanel();
        dawnPane.setLayout(new BoxLayout(dawnPane, BoxLayout.Y_AXIS));
        dawnPane.add(close);

        middlePanel.setVisible(false);
        contents.add(highestPane);
        contents.add(highPanel);
        contents.add(middlePanel);
        contents.add(dawnPane);
        setContentPane(contents);
    }

    private void initListeners(){
        find.addActionListener(e -> {
            var animal = DataBase.findAnimal((Integer) idAnimal.getSelectedItem());
            middlePanel.setVisible(true);
            highPanel.setVisible(false);
            animalAge.setText(String.valueOf(animal.getAge()));
            animalName.setText(animal.getName());
            animalType.setSelectedItem(animal.getType());
            animaBreed.setText(animal.getBreed());
            animalColor.setSelectedItem(animal.getColour());
        });
        update.addActionListener(e -> {
            var animal = new Animal();
            try {
                animal.setId((Integer) idAnimal.getSelectedItem());
                animal.setAge(Integer.parseInt(animalAge.getText()));
                animal.setColour(String.valueOf(animalColor.getSelectedItem()));
                animal.setBreed(animaBreed.getText());
                animal.setType((Types)animalType.getSelectedItem());
                animal.setName(animalName.getText());
            } catch (Exception ex) {
                log.setText(ex.getMessage());
                return;
            }
            DataBase.updateAnimal(animal);
            log.setText("Запись успешно изменена");
            middlePanel.setVisible(false);
            highPanel.setVisible(true);
        });
        close.addActionListener(e -> {
            setVisible(false);
            new MainFrame();
        });
    }
}
