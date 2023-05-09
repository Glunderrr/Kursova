module com.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kursova to javafx.fxml;
    exports com.example.kursova;
    exports com.example.kursova.windows;
    opens com.example.kursova.windows to javafx.fxml;
    exports com.example.kursova.microobjects;
    opens com.example.kursova.microobjects to javafx.fxml;
    exports com.example.kursova.macroobjects;
    opens com.example.kursova.macroobjects to javafx.fxml;
}