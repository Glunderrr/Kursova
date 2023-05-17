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
        rate = money / 10;
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
        return Double.compare(poor.money, money) == 0 &&
                winRating == poor.winRating &&
                loseRating == poor.loseRating &&
                Objects.equals(name, poor.name) &&
                X == poor.X &&
                Y == poor.Y &&
                group == poor.group;
    }


    @Override
    public int hashCode() {
        return Objects.hash(money, name, winRating, loseRating, X, Y, group);
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

    @Override
    public Poor clone() throws CloneNotSupportedException {
        Poor poor = (Poor) super.clone();
        double _x = poor.getX() + 100;
        double _y = poor.getY() + 100;

        poor.setX(_x);
        poor.setY(_y);
        poor.setName(poor.getName() + ".cl");
        poor.elect = false;
        poor.active = false;

        try {
            poor.image = new Image(new FileInputStream("src/images/poor.png"), 50, 80, false, false);
            poor.coin = new Image(new FileInputStream("src/images/coin.png"), 15, 15, false, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        poor.coinView = new ImageView(coin);
        poor.coinView.setY(20);
        poor.coinView.setX(5);

        poor.imageView = new ImageView(poor.getImage());
        poor.imageView.setLayoutY(35);
        poor.imageView.setLayoutX(5);

        poor.moneyLabel = new Label();
        poor.moneyLabel.setText(String.valueOf(money));
        poor.moneyLabel.setFont(new Font(12));
        poor.moneyLabel.setLayoutX(20);
        poor.moneyLabel.setLayoutY(18);
        poor.moneyLabel.setTextFill(Color.FORESTGREEN);

        poor.nameLabel = new Label(poor.getNameLabel().getText() + ".cl");
        poor.nameLabel.setFont(Font.font("Impact", FontWeight.BOLD, 15));
        poor.nameLabel.setLayoutX(5);

        poor.rectangle = new Rectangle(0, 0, 70, 120);
        poor.rectangle.setFill(Color.WHITE);
        poor.rectangle.setStrokeWidth(3);
        poor.rectangle.setStroke(Color.TRANSPARENT);

        poor.group = new Group();
        poor.group.getChildren().addAll(poor.getRectangle(), poor.getMoneyLabel(), poor.getCoinView(), poor.getImageView(), poor.getNameLabel());
        poor.group.setLayoutX(_x);
        poor.group.setLayoutY(_y);

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
        rate = money / 10;
    }

    public void playBlackjack(int casinoRate /*значення від 16 до 21*/) {
        analysis();
        int playerRate = random.nextInt(6) + 16;
        if (loseRating <= winRating) {
            money -= rate;
            loseRating++;
        }
        else if (playerRate == casinoRate) return;
        else if (playerRate < casinoRate) {
            money -= rate;
            loseRating++;
        } else {
            winRating++;
            if (playerRate == 21) money += rate * 1.5;
            else money += rate * (random.nextInt(3) + 1);
        }
        moneyLabel.setText(String.valueOf(money));
    }

    public void playRoulette(int casinoRate) {
        analysis();
        int playerRate = random.nextInt(37);
        if (casinoRate % 2 == playerRate % 2 && casinoRate != 0 && loseRating > winRating) {
            money += rate;
            winRating++;
        } else {
            money -= rate;
            loseRating++;
        }
        moneyLabel.setText(String.valueOf(money));
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

    private void analysis() {
        if (money <= 200) rate = 40;
        else if (money <= 10000) rate = money / 10;
        else if (money <= 50000) rate = money / 5;
        else rate = money / 2;
    }
}