module com.example.finalfour {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.sql;


    opens com.example.finalfour to javafx.fxml;
    exports com.example.finalfour;
}