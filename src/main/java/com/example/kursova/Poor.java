package com.example.kursova;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;

public class Poor {
    static {
        System.out.println("Start of static block");
    }

    {
        System.out.println("Start of dynamic block");
    }

    Poor(String name, double money, int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.name = name;
        this.money = money;
        createImaginePane();
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
                "; lose rating: " + loseRating + "}\n";
    }

    private int X;
    private int Y;
    private Pane pane;

    private String name;
    private double money;
    private int winRating = 0;
    private int loseRating = 0;

    private final Random random = new Random();
    private final double standardRate = money / 10;

    private final Image poorImage;
    private boolean elect = false;
    private Rectangle rectangle;

    {
        try {
            poorImage = new Image(new FileInputStream("src/images/poor.png"), 50, 80, false, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeParameters(String name, double money, int X, int Y){
        this.X = X;
        this.Y = Y;
        this.name = name;
        this.money = money;
        createImaginePane();
    }
    private void createImaginePane() {
        pane = new Pane();

        ImageView poorView = new ImageView(poorImage);
        poorView.setLayoutX(X);
        poorView.setLayoutY(Y + 35);

        Line line = new Line();
        line.setLayoutX(X);
        line.setEndX(line.getEndX() + 50);
        line.setLayoutY(Y + 25);
        line.setStrokeWidth(5);
        line.setStroke(Color.GREEN);

        Label nameLabel = new Label(name);
        nameLabel.setLayoutX(X);
        nameLabel.setLayoutY(Y);
        nameLabel.setFont(Font.font("Impact", FontWeight.BOLD, 15));

        rectangle = new Rectangle(X - 10, Y - 5, 70, 125);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(Color.TRANSPARENT);

        pane.getChildren().add(rectangle);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(poorView);
        pane.getChildren().add(line);
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

    public Pane getPane() {
        return pane;
    }

    public void setRectangleColor() {
        if (isElect()) rectangle.setStroke(Color.RED);
        else rectangle.setStroke(Color.TRANSPARENT);

    }

    public Rectangle getrectangle() {
        return rectangle;
    }
}
