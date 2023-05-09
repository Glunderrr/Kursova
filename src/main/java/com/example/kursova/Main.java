package com.example.kursova;

import com.example.kursova.microobjects.Player;
import com.example.kursova.microobjects.Poor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.kursova.ObjectArray.electedObject;
import static com.example.kursova.ObjectArray.getObjectList;
import static com.example.kursova.ObjectArray.getActiveObjectList;

public class Main extends Application {
    public static Stage primaryStage;
    public static Pane primaryPane;
    public static Stage newStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryPane = new Pane();

        Scene scene = new Scene(primaryPane, 1280, 720);
        scene.setOnKeyPressed(event -> {
            for (Poor player : getActiveObjectList()) {
                if (event.getCode().equals(KeyCode.UP)) player.moveY(-5);
                if (event.getCode().equals(KeyCode.DOWN)) player.moveY(5);
                if (event.getCode().equals(KeyCode.LEFT)) player.moveX(-5);
                if (event.getCode().equals(KeyCode.RIGHT)) player.moveX(5);
            }
            if (event.getCode().equals(KeyCode.ESCAPE) && !getActiveObjectList().isEmpty()) {
                for (Poor player : getActiveObjectList()){
                    player.setActive(false);
                }
            }
            if (event.getCode().equals(KeyCode.ENTER)) {
                ObjectArray.showList("List of players in casino");
            }
            if (event.getCode().equals(KeyCode.C)) {
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addWindow.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene secondScene = new Scene(root);
                newStage = new Stage();
                newStage.setTitle("Add player");
                newStage.setScene(secondScene);
                newStage.show();
            }
            if (event.getCode().equals(KeyCode.D) && electedObject != null) {
                getObjectList().remove(electedObject);
                primaryPane.getChildren().remove(electedObject.getGroup());
            }
            if (event.getCode().equals(KeyCode.DELETE) && !getObjectList().isEmpty()) {
                for (Player player : getActiveObjectList()) {
                    getObjectList().remove(player);
                    primaryPane.getChildren().remove(player.getGroup());
                }
            }
            if (event.getCode().equals(KeyCode.U) && electedObject != null) {
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("changeParametersWindow.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene secondScene = new Scene(root);
                newStage = new Stage();
                newStage.setTitle("Change parameters");
                newStage.setScene(secondScene);
                newStage.show();
            }
            if (event.getCode().equals(KeyCode.V) && electedObject != null) {
                try {
                    Poor clonedElectObject = (Poor) electedObject.clone();
                    getObjectList().add(clonedElectObject);
                    primaryPane.getChildren().add(clonedElectObject.getGroup());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        scene.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                for (Poor poor : getObjectList()) {
                    if (poor.getGroup().boundsInParentProperty().get().contains(event.getX(), event.getY())) {
                        if (electedObject != null) {
                            electedObject.setElect();
                            if (electedObject == poor) {
                                electedObject = null;
                                break;
                            }
                        }
                        electedObject = poor;
                        electedObject.setElect();
                        break;
                    }
                }
            }
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                for (Poor poor : getObjectList()) {
                    if (poor.getGroup().boundsInParentProperty().get().contains(event.getX(), event.getY())) {
                        if (!poor.isActive()) getActiveObjectList().add(poor);
                        else getActiveObjectList().remove(poor);
                        poor.flipActive();
                    }
                }
            }
        });
        primaryStage.setTitle("Casino");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
