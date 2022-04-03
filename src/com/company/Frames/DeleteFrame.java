package com.company.Frames;

import com.company.DataBase.DataBase;

import javax.swing.*;

public class DeleteFrame extends JFrame {
    JComboBox idAnimals;
    JLabel log;
    JLabel id;
    JButton delete;
    JButton close;
    public DeleteFrame(){
        super("Удалить животное");
        setSize(300, 500);
        init();
        setLayout(null);
        setVisible(true);
        initListeners();
    }
    private void init(){
        close = new JButton("Закрыть");
        close.setBounds(75, 350, 150, 75);
        add(close);

        delete = new JButton("Удалить");
        delete.setBounds(75, 250, 150, 75);
        add(delete);

        log = new JLabel();
        log.setBounds(0, 50, 150, 30);
        add(log);

        id = new JLabel("ID животного");
        id.setBounds(75, 100, 150, 30);
        add(id);
        updateCombos();
    }

    private void updateCombos(){
        idAnimals = new JComboBox(DataBase.getAnimalsId());
        idAnimals.setBounds(75, 150, 150, 30);
        add(idAnimals);
    }

    private void initListeners(){
        delete.addActionListener(e -> {
            if(idAnimals.getItemCount()==0){
                log.setText("Все записи удалены");
                return;
            }
            DataBase.deleteAnimal(Integer.parseInt(idAnimals.getSelectedItem().toString()));
            log.setText("Запись успешно удалена");
            remove(idAnimals);
            updateCombos();
        });
        close.addActionListener(e -> {
            new MainFrame();
            setVisible(false);
        });
    }
}
