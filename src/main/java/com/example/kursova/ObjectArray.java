package com.example.kursova;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ObjectArray {
    public static List<Poor> getObjectList() {
        return objectList;
    }

    private static final List<Poor> objectList = new ArrayList<>();
    public static Poor electedObject;

   static public void showList(String stageTitle) {
        Label list = new Label();
        list.setText("*Press ENTER to close the window*\n");
        for (Object object : objectList)
            list.setText(list.getText() + object.toString());
        list.setPadding(new Insets(5, 0, 0, 20));
        list.setFont(new Font(15));
        Pane pane = new Pane();
        pane.getChildren().add(list);
        Scene scene = new Scene(pane, 550, 300);
        Stage stage = new Stage();
        stage.setTitle(stageTitle);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    stage.close();
                }
            }
        });
    }

}
