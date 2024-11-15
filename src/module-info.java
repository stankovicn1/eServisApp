module Glavni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Glavni to javafx.fxml;
    exports Glavni;
}