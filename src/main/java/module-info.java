module com.example.masterapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.masterapp to javafx.fxml;
    exports com.example.masterapp;
}