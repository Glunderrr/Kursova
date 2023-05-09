package com.example.kursova.windows;

import com.example.kursova.Main;
import com.example.kursova.ObjectArray;
import com.example.kursova.microobjects.Poor;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class AddPoorWindow {
    @FXML
    private TextField nameString,moneyDouble,xInt, yInt;
    @FXML
    private RadioButton rb_poor, rb_rich, rb_swindler;

    @FXML
    void createPoor() {
        String _name = nameString.getText();
        double _money = Double.parseDouble(moneyDouble.getText());
        int _x = Integer.parseInt(xInt.getText());
        int _y = Integer.parseInt(yInt.getText());
        Poor player;
        if (rb_poor.isSelected()) player = new Poor(_name, _money, _x, _y);
        else if(rb_rich.isSelected()) player = new Poor("12",12,21,21); // ДОПИШЕТЬСЯ В ПРОЦЕСІ
        else  player = new Poor("12",12,21,21); // ДОПИШЕТЬСЯ В ПРОЦЕСІ

        Main.primaryPane.getChildren().add(player.getGroup());
        ObjectArray.getObjectList().add(player);
        Main.newStage.close();

    }
}
