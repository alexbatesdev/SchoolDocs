module me.alex.javafxlogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens me.alex.javafxlogin to javafx.fxml;
    exports me.alex.javafxlogin;
}