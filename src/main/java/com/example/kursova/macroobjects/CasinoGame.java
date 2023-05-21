package com.example.kursova.macroobjects;

import com.example.kursova.Main;
import com.example.kursova.microobjects.Poor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import static com.example.kursova.Main.write;


import static com.example.kursova.ObjectArray.getPlayersInGames;

import java.util.List;
import java.util.Random;

public class CasinoGame {
    final Random random = new Random();
    String name;
    double X, Y;
    Group group;
    Image image;
    ImageView imageView;
    Rectangle rectangle;
    Label nameLabel, playerCounterLabel;
    int playerCounter;

    boolean elected;
    List<Poor> playerList;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getPlayerCounterLabel() {
        return playerCounterLabel;
    }

    public void setPlayerCounterLabel(Label playerCounterLabel) {
        this.playerCounterLabel = playerCounterLabel;
    }

    public int getPlayerCounter() {
        return playerCounter;
    }

    public void setPlayerCounter(int playerCounter) {
        this.playerCounter = playerCounter;
    }

    public boolean isElected() {
        return elected;
    }

    public void setElected(boolean elected) {
        this.elected = elected;
    }

    public List<Poor> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Poor> playerList) {
        this.playerList = playerList;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setY(double y) {
        Y = y;
    }

    protected void defaultGroup(String name, Color color) {
        group = new Group();

        this.name = name;

        rectangle = new Rectangle(0, 0, 324, 259);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(color);

        imageView = new ImageView(image);
        imageView.setY(47);
        imageView.setX(12);

        nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        nameLabel.setLayoutX(12);
        nameLabel.setLayoutY(12);

        playerCounterLabel = new Label("Players at the table: " + playerCounter);
        playerCounterLabel.setFont(new Font(18));
        playerCounterLabel.setLayoutX(145);
        playerCounterLabel.setLayoutY(12);

        group.getChildren().addAll(rectangle, imageView, nameLabel, playerCounterLabel);
        group.setLayoutX(X);
        group.setLayoutY(Y);

        Main.primaryPane.getChildren().add(group);
    }

    public void increment(Poor poor) {
        getPlayersInGames().add(poor);
        playerList.add(poor);
        playerCounter = playerList.size();
        playerCounterLabel.setText("Players at the table: " + playerCounter);
        write(poor.toString());
    }

    public void decrement(Poor poor) {
        getPlayersInGames().remove(poor);
        playerList.remove(poor);
        playerCounter = playerList.size();
        playerCounterLabel.setText("Players at the table: " + playerCounter);
        write(poor.toString());
    }
}
