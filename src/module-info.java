module Glavni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.mail;

    opens Glavni to javafx.fxml;
    exports Glavni;
}