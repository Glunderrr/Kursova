package com.example.kursova;

import com.example.kursova.microobjects.Poor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ObjectArray {
    private static final List<Poor> objectList = new ArrayList<>();

    private static final List<Poor> activeObjectList = new ArrayList<>();
    private static final List<Poor> playersInGames = new ArrayList<>();

    public static List<Poor> getPlayersInGames() {
        return playersInGames;
    }

    public static Poor electedObject;


    public static List<Poor> getObjectList() {
        return objectList;
    }


    public static List<Poor> getActiveObjectList() {
        return activeObjectList;
    }

    public static void showList(String stageTitle) {
        Label list = new Label();
        for (Object object : objectList) list.setText(list.getText() + object.toString());
        list.setPadding(new Insets(5, 0, 0, 20));
        list.setFont(new Font(15));
        Pane pane = new Pane();
        pane.getChildren().add(list);
        Scene scene = new Scene(pane, 600, 300);
        Stage stage = new Stage();
        stage.setTitle(stageTitle);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() { //ВИКОРИСТАННЯ АНОНІМНОГО КЛАСУ
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    stage.close();
                }
            }
        });
    }

}
