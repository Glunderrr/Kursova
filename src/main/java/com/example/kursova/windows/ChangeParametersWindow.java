package com.example.kursova.windows;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.example.kursova.Main.newStage;
import static com.example.kursova.ObjectArray.electedObject;
import static com.example.kursova.Main.write;
public class ChangeParametersWindow {
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
        int _money = Integer.parseInt(moneyDouble.getText());
        double _x = Double.parseDouble(xInt.getText());
        double _y = Double.parseDouble(yInt.getText());
        write(electedObject.toString());
        electedObject.changeParameters(_name, _money, _x, _y);
        newStage.close();
    }
}
