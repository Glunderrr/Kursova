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
import java.text.NumberFormat;
import java.util.Objects;

public class Poor extends Player implements Cloneable {
    public Poor(String name, int money, double X, double Y) {
        this.name = name;
        this.money = money;
        this.X = X;
        this.Y = Y;
        createGroup();
        System.out.println("the constructor with arguments was used\n" + this);
    }

    Poor() {
        this("Poor man without name", 0, 100.0, 100.0);
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
        double _x = poor.getX() + 100;
        double _y = poor.getY() + 100;
        poor.setGroup(new Group());

        poor.setX(_x);
        poor.setY(_y);
        poor.setName(poor.getName() + ".cl");
        poor.elect = false;
        poor.active = false;

        try {
            poor.setImage(new Image(new FileInputStream("src/images/poor.png"), 50, 80, false, false));
            poor.setCoin(new Image(new FileInputStream("src/images/coin.png"), 15, 15, false, false));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        poor.setCoinView(new ImageView(coin));
        poor.getCoinView().setY(20);
        poor.getCoinView().setX(5);

        poor.setImageView(new ImageView(poor.getImage()));
        poor.getImageView().setLayoutY(35);
        poor.getImageView().setLayoutX(5);

        poor.setMoneyLabel(new Label());
        poor.getMoneyLabel().setText(String.valueOf(money));
        poor.getMoneyLabel().setFont(new Font(12));
        poor.getMoneyLabel().setLayoutX(20);
        poor.getMoneyLabel().setLayoutY(18);
        poor.getMoneyLabel().setTextFill(Color.FORESTGREEN);

        poor.setNameLabel(new Label(poor.getNameLabel().getText() + ".cl"));
        poor.getNameLabel().setFont(Font.font("Impact", FontWeight.BOLD, 15));
        poor.getNameLabel().setLayoutX(5);

        poor.setRectangle(new Rectangle(0, 0, 70, 120));
        poor.getRectangle().setFill(Color.WHITE);
        poor.getRectangle().setStrokeWidth(3);
        poor.getRectangle().setStroke(Color.TRANSPARENT);

        poor.getGroup().getChildren().addAll(poor.getRectangle(), poor.getMoneyLabel(), poor.getCoinView(), poor.getImageView(), poor.getNameLabel() );
        poor.getGroup().setLayoutX(_x);
        poor.getGroup().setLayoutY(_y);

        return poor;
    }

    private void createGroup() {
        group = new Group();
        {
            try {
                image = new Image(new FileInputStream("src/images/poor.png"), 50, 80, false, false);
                coin = new Image(new FileInputStream("src/images/coin.png"), 15, 15, false, false);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        coinView = new ImageView(coin);
        coinView.setY(20);
        coinView.setX(5);

        imageView = new ImageView(image);
        imageView.setY(35);
        imageView.setX(5);

        moneyLabel = new Label();
        moneyLabel.setText(String.valueOf(money));
        moneyLabel.setFont(new Font(12));
        moneyLabel.setLayoutX(20);
        moneyLabel.setLayoutY(18);
        moneyLabel.setTextFill(Color.FORESTGREEN);

        nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Impact", 15));
        nameLabel.setLayoutX(5);

        rectangle = new Rectangle(0, 0, 70, 120);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(Color.TRANSPARENT);

        group.getChildren().addAll(rectangle, moneyLabel, coinView, nameLabel, imageView);

        group.setLayoutX(X);
        group.setLayoutY(Y);
    }

    public void changeParameters(String name, int money, double X, double Y) {
        nameLabel.setText(name);
        this.name = name;
        this.money = money;
        moneyLabel.setText(String.valueOf(money));
        group.setLayoutX(X);
        this.X = X;
        group.setLayoutY(Y);
        this.Y = Y;
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
            X += stepX;
            group.setLayoutX(X);
        }
    }

    public void moveY(int stepY) {
        if (Y + stepY > 0) {
            Y += stepY;
            group.setLayoutY(Y);
        }
    }
}