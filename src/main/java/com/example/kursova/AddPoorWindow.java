package com.example.kursova;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPoorWindow {
    @FXML
    private TextField nameString;
    @FXML
    private TextField moneyDouble;
    @FXML
    private TextField xInt;
    @FXML
    private TextField yInt;

    @FXML
    void createPoor() {
        String _name = nameString.getText();
        double _money = Double.parseDouble(moneyDouble.getText());
        int _x = Integer.parseInt(xInt.getText());
        int _y = Integer.parseInt(yInt.getText());
        Poor poor = new Poor(_name,_money,_x,_y);
        Main.primaryPane.getChildren().add(poor.getPane());
        ObjectArray.getObjectList().add(poor);
        Main.newStage.close();
    }
}
