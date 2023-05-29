package com.example.kursova.windows;

import com.example.kursova.macroobjects.CasinoGame;
import com.example.kursova.microobjects.Poor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.example.kursova.Main.casinoGames;
import static com.example.kursova.ObjectArray.*;
import static com.example.kursova.ObjectArray.getActiveObjectList;

public class SearchWindow {
    @FXML
    private TextField enterNameField;

    @FXML
    private ListView<Poor> listView;
    @FXML
    MenuButton activityMenuButton, gameMenuButton;
    ObservableList<Poor> items = FXCollections.observableArrayList();
    boolean activity;

    @FXML
    void searchByName() {
        activityMenuButton.setText("Activity");
        gameMenuButton.setText("Games");
        items.clear();
        listView.getItems().clear();
        for (Poor player : getObjectList())
            if (player.getName().equals(enterNameField.getText()))
                items.add(player);
        listView.setItems(items);
    }

    @FXML
    void searchByActivity() {
        enterNameField.setText("");
        gameMenuButton.setText("Games");
        items.clear();
        listView.getItems().clear();
        if (activity) items.setAll(getActiveObjectList().stream().toList());//STREAM API
        else{
            for (Poor player : getObjectList()) {
                if (!player.isActive()) items.add(player);
            }
        }
        listView.setItems(items);
    }

    @FXML
    void searchByGame() {
        enterNameField.setText("");
        activityMenuButton.setText("Activity");
        items.clear();
        listView.getItems().clear();
        if (gameMenuButton.getText().equals("No game")) {
            for (Poor player : getObjectList()) {
                if (!getPlayersInGames().contains(player)) items.add(player);
            }
        } else {
            for (CasinoGame game : casinoGames) {
                if (gameMenuButton.getText().equals(game.getName())) {
                    items.addAll(game.getPlayerList());
                    break;
                }
            }
        }
        listView.setItems(items);
    }

    @FXML
    void blackjack() {
        gameMenuButton.setText("Blackjack");
    }

    @FXML
    void roulette() {
        gameMenuButton.setText("Roulette");
    }

    @FXML
    void poker() {
        gameMenuButton.setText("Poker");
    }

    @FXML
    void noGame() {
        gameMenuButton.setText("No game");
    }

    @FXML
    void activity() {
        activityMenuButton.setText("True");
        activity = true;
    }

    @FXML
    void noActivity() {
        activityMenuButton.setText("False");
        activity = false;
    }
}