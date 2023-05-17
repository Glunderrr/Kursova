package com.example.kursova.macroobjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Blackjack extends CasinoGame {
    public Blackjack(double X, double Y) {
        this.X = X;
        this.Y = Y;
        {
            try {
                image = new Image(new FileInputStream("src/images/blackjack.jpg"), 300, 200, false, false);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        playerList = new ArrayList<>();
        defaultGroup("Blackjack", Color.PURPLE);
    }
    public int createRate(){return random.nextInt(8) + 16;}
}