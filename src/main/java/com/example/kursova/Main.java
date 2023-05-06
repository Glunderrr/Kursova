package com.example.kursova;

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
            if (event.getCode().equals(KeyCode.DELETE)) {
                ObjectArray.getObjectList().remove(electedObject);
                primaryPane.getChildren().remove(electedObject.getPane());
            }
            if (event.getCode().equals(KeyCode.U) ) {
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
        });

        scene.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                for (Poor poor : ObjectArray.getObjectList()) {
                    if (poor.getPane().boundsInParentProperty().get().contains(event.getX(), event.getY())) {
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
        });
        primaryStage.setTitle("Casino");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
