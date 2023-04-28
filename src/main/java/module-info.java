module com.example.masterapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.xml;


    opens com.example.masterapp to javafx.fxml;
    exports com.example.masterapp;
}