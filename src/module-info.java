module Glavni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Glavni to javafx.fxml;
    exports Glavni;
}