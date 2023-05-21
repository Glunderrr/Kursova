package com.example.kursova;

import com.example.kursova.macroobjects.Blackjack;
import com.example.kursova.macroobjects.CasinoGame;
import com.example.kursova.macroobjects.Poker;
import com.example.kursova.macroobjects.Roulette;
import com.example.kursova.microobjects.Poor;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.kursova.ObjectArray.electedObject;
import static com.example.kursova.ObjectArray.getObjectList;
import static com.example.kursova.ObjectArray.getActiveObjectList;

public class Main extends Application {
    public static Stage primaryStage;
    public static Pane primaryPane;
    public static Stage newStage;
    public static CasinoGame[] casinoGames;
    /*FileWriter writer;
    BufferedWriter bufferedWriter;*/

    @Override
    public void start(Stage primaryStage) {
        try {
            FileWriter writer = new FileWriter("history.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.primaryStage = primaryStage;
        primaryPane = new Pane();
        Blackjack blackjack = new Blackjack(100.0, 220.0);
        Poker poker = new Poker(500.0, 220.0);
        Roulette roulette = new Roulette(900.0, 220.0);

        casinoGames = new CasinoGame[]{blackjack, poker, roulette};
        Scene scene = new Scene(primaryPane, 1280, 720);

        for(CasinoGame game: casinoGames){
            write(game.toString());
        }
        scene.setOnKeyPressed(event -> {
            for (Poor player : getActiveObjectList()) {
                if (event.getCode().equals(KeyCode.UP)) {
                    player.moveUP();
                    write(player.toString());
                }
                if (event.getCode().equals(KeyCode.DOWN)) {
                    player.moveDOWN();
                    write(player.toString());
                }
                if (event.getCode().equals(KeyCode.LEFT)) {
                    player.moveLEFT();
                    write(player.toString());
                }
                if (event.getCode().equals(KeyCode.RIGHT)) {
                    player.moveRIGHT();
                    write(player.toString());
                }
            }

            if (event.getCode().equals(KeyCode.S)) {
                createWindow("searchWindow.fxml", "Search player");
            }
            if (event.getCode().equals(KeyCode.ENTER)) ObjectArray.showList("List of players in casino");
            if (event.getCode().equals(KeyCode.C)) {
                createWindow("addWindow.fxml", "Add player");
            }

            if (event.getCode().equals(KeyCode.ESCAPE) && !getActiveObjectList().isEmpty()) {
                for (Poor player : getActiveObjectList()) player.setActive(false);
                getActiveObjectList().clear();
                write("Turn off all elements in activeObjectList");
            }
            if (event.getCode().equals(KeyCode.DELETE) && !getActiveObjectList().isEmpty()) {
                for (Poor player : getActiveObjectList()) {
                    for (CasinoGame game : casinoGames) game.decrement(player);
                    primaryPane.getChildren().remove(player.getGroup());
                }
                write("Delete all elements from activeObjectList");
                getActiveObjectList().forEach(getObjectList()::remove); //METHOD REFERENCE
                getActiveObjectList().clear();
            }

            if (event.getCode().equals(KeyCode.D) && electedObject != null) {
                for (CasinoGame game : casinoGames) game.decrement(electedObject);
                getActiveObjectList().remove(electedObject);
                getObjectList().remove(electedObject);
                primaryPane.getChildren().remove(electedObject.getGroup());
                write("Delete elected object");
            }
            if (event.getCode().equals(KeyCode.U) && electedObject != null) {
                createWindow("changeParametersWindow.fxml", "Change parameters");
            }
            if (event.getCode().equals(KeyCode.V) && electedObject != null) {
                try {
                    Poor clonedElectObject = electedObject.clone();
                    write(clonedElectObject.toString());
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
                        break;
                    }
                }
            }
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), actionEvent -> {
                    for (Poor poor : getObjectList()) {
                        for (CasinoGame game : casinoGames) {
                            if (!game.getGroup().getBoundsInParent().intersects(poor.getGroup().getBoundsInParent()) && game.getPlayerList().contains(poor)) {
                                game.decrement(poor);
                            }
                            if (game.getGroup().getBoundsInParent().intersects(poor.getGroup().getBoundsInParent()) && !game.getPlayerList().contains(poor)) {
                                game.increment(poor);
                            }
                        }
                    }
                }),
                new KeyFrame(Duration.millis(1000), actionEvent -> {
                    for (Poor player : blackjack.getPlayerList()) {
                        if (player.getMoney() > 0) player.playBlackjack(blackjack.createRate());
                    }
                    for (Poor player : roulette.getPlayerList()) {
                        if (player.getMoney() > 0) player.playRoulette(roulette.createRate());
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.setTitle("Casino");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public void createWindow(String fileName, String title) {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene secondScene = new Scene(root);
        newStage = new Stage();
        newStage.setTitle(title);
        newStage.setScene(secondScene);
        newStage.show();
    }

    public static void write(String text) {
        try {
            FileWriter writer = new FileWriter("history.txt", true);
            writer.write(text+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }/*
        try {
            FileWriter writer = new FileWriter("history.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.close();
*//*            bufferedWriter.newLine();*//*
            bufferedWriter.write(text);
            System.out.println("ПРАЦЮЄ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}