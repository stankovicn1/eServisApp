module Glavni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Glavni to javafx.fxml;
    exports Glavni;
    exports Glavni.db;
    opens Glavni.db to javafx.fxml;
    opens Glavni.model to javafx.fxml;
    exports Glavni.model;
    exports Glavni.service;
    opens Glavni.service to javafx.fxml;
}