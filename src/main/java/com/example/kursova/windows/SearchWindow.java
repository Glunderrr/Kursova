package com.example.kursova.windows;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;

import java.util.ResourceBundle;

public class SearchWindow implements Initializable {
    @FXML
    ChoiceBox<String> game, activity;
    @FXML
    Button activityButton, nameButton, gameButton;
    @FXML
    TextField enterNameField;
    @FXML
    ListView<String> listView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        game.getItems().addAll("Blackjack", "Roulette", "Poker", "No game");
        activity.getItems().addAll("Activity", "No activity");
    }

    @FXML
    void searchByName() {
        System.out.println("searchByName");
    }

    @FXML
    void searchByActivity() {
        System.out.println("searchByActivity");
    }

    @FXML
    void searchByGame() {
        System.out.println("searchByGame");
    }
}
