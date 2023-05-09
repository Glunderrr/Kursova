package com.example.kursova.microobjects;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Poor extends Player implements Cloneable {
    public Poor(String name, double money, int X, int Y) {
        this.name = name;
        this.money = money;
        this.X = X;
        this.Y = Y;
        createPane();
        System.out.println("the constructor with arguments was used\n" + this);
    }

    Poor() {
        this("Poor man without name", 0.0, 100, 100);
        System.out.println("the default constructor was used\n" + this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poor poor = (Poor) o;
        return Double.compare(poor.money, money) == 0 && winRating == poor.winRating && loseRating == poor.loseRating && Objects.equals(name, poor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, name, winRating, loseRating);
    }

    @Override
    public String toString() {
        return "Poor { name: " + name +
                "; money: " + money +
                "; Win rating: " + winRating +
                "; lose rating: " + loseRating +
                "; X: " + X +
                "; Y: " + Y + " }\n";
    }

    private void loosing(String gameName) {
        loseRating++;
        money -= money / 10;
        System.out.println("Player " + name + " lost at " + gameName);
    }

    private void winning(double winCoefficient, String gameName) {
        winRating++;
        money += money / 10 * winCoefficient;
        System.out.println("Player " + name + " won at " + gameName);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Poor poor = (Poor) super.clone();
        int _x = poor.getX() + 100;
        int _y = poor.getY() + 100;
        poor.setGroup(new Group());

        poor.setX(_x);
        poor.setY(_y);
        poor.setName(poor.name + ".cl");
        poor.elect = false;
        poor.active = false;

        try {
            poor.setPoorImage(new Image(new FileInputStream("src/images/poor.png"), 50, 80, false, false));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        poor.setPoorView(new ImageView(poor.getPoorImage()));
        poor.getPoorView().setLayoutX(_x);
        poor.getPoorView().setLayoutY(_y + 35);

        poor.setLine(new Line());
        poor.getLine().setLayoutX(_x);
        poor.getLine().setEndX(poor.getLine().getEndX() + 50);
        poor.getLine().setLayoutY(_y + 25);
        poor.getLine().setStrokeWidth(5);
        poor.getLine().setStroke(Color.DARKGREEN);

        poor.setNameLabel(new Label(poor.getNameLabel().getText() + ".cl"));
        poor.getNameLabel().setLayoutX(_x);
        poor.getNameLabel().setLayoutY(_y);
        poor.getNameLabel().setFont(Font.font("Impact", FontWeight.BOLD, 15));

        poor.setRectangle(new Rectangle(_x - 10, _y - 5, 70, 125));
        poor.getRectangle().setFill(Color.TRANSPARENT);
        poor.getRectangle().setStrokeWidth(3);
        poor.getRectangle().setStroke(Color.TRANSPARENT);

        poor.getGroup().getChildren().addAll(poor.getPoorView(), poor.getLine(), poor.getNameLabel(), poor.getRectangle());
        return poor;
    }

    private void createPane() {
        group = new Group();

        poorView = new ImageView(poorImage);
        poorView.setLayoutX(X);
        poorView.setLayoutY(Y + 35);

        line = new Line();
        line.setLayoutX(X);
        line.setEndX(line.getEndX() + 50);
        line.setLayoutY(Y + 25);
        line.setStrokeWidth(5);
        line.setStroke(Color.DARKGREEN);

        nameLabel = new Label(name);
        nameLabel.setLayoutX(X);
        nameLabel.setLayoutY(Y);
        nameLabel.setFont(Font.font("Impact", FontWeight.BOLD, 15));

        rectangle = new Rectangle(X - 10, Y - 5, 70, 125);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(Color.TRANSPARENT);

        group.getChildren().add(rectangle);
        group.getChildren().add(nameLabel);
        group.getChildren().add(poorView);
        group.getChildren().add(line);
    }

    public void changeParameters(String name, double money, int X, int Y) {
        group.setLayoutX(X - this.X);
        this.X = X;
        group.setLayoutY(Y - this.Y);
        this.Y = Y;
        nameLabel.setText(name);
        this.name = name;
        this.money = money;
    }

    public void playBlackjack(int casinoRate /*значення від 16 до 21*/) {
        String gameName = "Blackjack";
        int playerRate = random.nextInt(10) + 12;
        if (playerRate < casinoRate || loseRating < winRating) loosing(gameName);
        else if (playerRate == 21) winning(1.5, gameName);
        else winning(1.0, gameName);
    }

    public void playRoulette(int casinoRate) {
        String gameName = "roulette";
        int playerRate = random.nextInt(37);
        if (loseRating < winRating) loosing(gameName);
        else if (casinoRate == playerRate) winning(35.0, gameName);
        else if (casinoRate % 2 == playerRate % 2 && casinoRate != 0) winning(1.0, gameName);
        else loosing("roulette");
    }

    public void moveX(int stepX) {
        if (X + stepX > 0) {
            group.setLayoutX(group.getLayoutX() + stepX);
            X = X + stepX;
        }
    }

    public void moveY(int stepY) {
        if (Y + stepY > 0) {
            group.setLayoutY(group.getLayoutY() + stepY);
            Y = Y + stepY;
        }
    }

}



