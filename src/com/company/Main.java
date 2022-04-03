package com.company;

import com.company.DataBase.DataBase;
import com.company.Frames.MainFrame;

public class Main {

    public static void main(String[] args) {
        DataBase.createDataBase();
        DataBase.createTable();
        new MainFrame();
    }
}
