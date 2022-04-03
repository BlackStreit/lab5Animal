package com.company.Frames;

import com.company.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JButton addAnimal;
    JButton editAnimal;
    JButton deleteAnimal;
    JTable table;

    public MainFrame(){
        super("Главная страница");
        setSize(1100, 750);
        init();
        setVisible(true);
        initListeners();
    }
    private void init(){
        //Содержимое таблицы
        Object[][] animals = DataBase.getAnimal();
        // Заголовки столбцов
        Object[] header = new String[]{"Номер", "Имя", "Тип", "Порода", "Цвет", "Возраст"};
        table = new JTable(animals, header);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));

        addAnimal = new JButton("Добавить животное");
        addAnimal.setBounds(50, getHeight() - 100, 150, 30);
        contents.add(new JScrollPane(addAnimal));

        editAnimal = new JButton("Изменить животное");
        editAnimal.setBounds(300, getHeight() - 100, 150, 30);
        contents.add(new JScrollPane(editAnimal));

        deleteAnimal = new JButton("Удалить животное");
        deleteAnimal.setBounds(550, getHeight() - 100, 150, 30);
        contents.add(new JScrollPane(deleteAnimal));

        setContentPane(contents);
    }

    private void initListeners(){
        addAnimal.addActionListener(e -> {
            new AddFrame();
            setVisible(false);
        });
    }
}
