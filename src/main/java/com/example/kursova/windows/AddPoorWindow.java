package com.example.kursova.windows;

import com.example.kursova.Main;
import com.example.kursova.ObjectArray;
import com.example.kursova.microobjects.Poor;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import static com.example.kursova.Main.write;


public class AddPoorWindow {
    @FXML
    private TextField nameString,moneyDouble,xInt, yInt;
    @FXML
    private RadioButton rb_poor, rb_rich, rb_swindler;

    @FXML
    void createPoor() {
        String _name = nameString.getText();
        int _money = Integer.parseInt(moneyDouble.getText());
        double _x = Double.parseDouble(xInt.getText());
        double _y = Double.parseDouble(yInt.getText());
        Poor player;
        if (rb_poor.isSelected()) player = new Poor(_name, _money, _x, _y);
        else if(rb_rich.isSelected()) player = new Poor("12",12,21,21); // ДОПИШЕТЬСЯ В ПРОЦЕСІ
        else  player = new Poor("12",12,21,21); // ДОПИШЕТЬСЯ В ПРОЦЕСІ

        write(player.toString());
        Main.primaryPane.getChildren().add(player.getGroup());
        ObjectArray.getObjectList().add(player);
        Main.newStage.close();

    }
}
