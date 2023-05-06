package com.example.kursova;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import static com.example.kursova.ObjectArray.electedObject;
import static com.example.kursova.Main.primaryPane;

public class changeParametersWindow {
    @FXML
    private TextField nameString;
    @FXML
    private TextField moneyDouble;
    @FXML
    private TextField xInt;
    @FXML
    private TextField yInt;

    @FXML
    void changeParameters() {
        String _name = nameString.getText();
        double _money = Double.parseDouble(moneyDouble.getText());
        int _x = Integer.parseInt(xInt.getText());
        int _y = Integer.parseInt(yInt.getText());

        primaryPane.getChildren().remove(electedObject.getPane());
        electedObject.changeParameters(_name,_money,_x,_y);
        primaryPane.getChildren().add(electedObject.getPane());
    }

}