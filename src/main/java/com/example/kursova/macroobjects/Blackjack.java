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

import static com.example.kursova.ObjectArray.getObjectList;

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
       // playerList = new ArrayList<>();
        defaultGroup("Blackjack", Color.PURPLE);
    }
}