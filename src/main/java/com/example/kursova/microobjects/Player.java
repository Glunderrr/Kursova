package com.example.kursova.microobjects;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public abstract class Player {
    static {
        System.out.println("Start of static block");
    }

    {
        System.out.println("Start of dynamic block");
    }

    double X, Y;
    Group group;
    String name;
    int money;
    int winRating = 0, loseRating = 0;
    final Random random = new Random();

    Image coin;
    ImageView coinView;

    Image image;
    ImageView imageView;
    boolean elect = false;
    boolean active = false;
    Rectangle rectangle;
    Label nameLabel;
    Label moneyLabel;

    public boolean isActive() {
        return active;
    }

    public void flipActive() {
        this.active = !this.active;
        if (active) nameLabel.setTextFill(Color.ORANGE);
        else nameLabel.setTextFill(Color.BLACK);
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) nameLabel.setTextFill(Color.ORANGE);
        else nameLabel.setTextFill(Color.BLACK);

    }

    public Image getImage() {
        return image;
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


    public int getLoseRating() {
        return loseRating;
    }

    public void setLoseRating(int loseRating) {
        this.loseRating = loseRating;
    }

    public int getWinRating() {
        return winRating;
    }

    public void setWinRating(int winRating) {
        this.winRating = winRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public void setMoneyLabel(Label moneyLabel) {
        this.moneyLabel = moneyLabel;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public void setY(double y) {
        Y = y;
    }

    public boolean isElect() {
        return elect;
    }

    public void setElect(boolean elect) {
        this.elect = elect;
        setRectangleColor();
    }

    public void setElect() {
        this.elect = !this.elect;
        setRectangleColor();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public void setRectangleColor() {
        if (isElect()) rectangle.setStroke(Color.RED);
        else rectangle.setStroke(Color.TRANSPARENT);

    }

    public Rectangle getrectangle() {
        return rectangle;
    }

    public Image getCoin() {
        return coin;
    }

    public void setCoin(Image coin) {
        this.coin = coin;
    }

    public ImageView getCoinView() {
        return coinView;
    }

    public void setCoinView(ImageView coinView) {
        this.coinView = coinView;
    }

}
