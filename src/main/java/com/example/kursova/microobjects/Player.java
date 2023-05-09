package com.example.kursova.microobjects;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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

    int X, Y;
    Group group;
    String name;
    double money;
    int winRating = 0, loseRating = 0;
    final Random random = new Random();

    {
        try {
            poorImage = new Image(new FileInputStream("src/images/poor.png"), 50, 80, false, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Image poorImage;
    ImageView poorView;
    boolean elect = false;
    boolean active = false;
    Rectangle rectangle;
    Label nameLabel;
    Line line;

    public boolean isActive() {
        return active;
    }

    public void flipActive() {
        this.active = !this.active;
        if (active) line.setStroke(Color.LIGHTGREEN);
        else line.setStroke(Color.DARKGREEN);
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) line.setStroke(Color.LIGHTGREEN);
        else line.setStroke(Color.DARKGREEN);
    }

    public Image getPoorImage() {
        return poorImage;
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

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
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

    public void setMoney(double money) {
        this.money = money;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
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

    public void setPoorImage(Image poorImage) {
        this.poorImage = poorImage;
    }

    public ImageView getPoorView() {
        return poorView;
    }

    public void setPoorView(ImageView poorView) {
        this.poorView = poorView;
    }

    public void setRectangleColor() {
        if (isElect()) rectangle.setStroke(Color.RED);
        else rectangle.setStroke(Color.TRANSPARENT);

    }

    public Rectangle getrectangle() {
        return rectangle;
    }
}
