module com.example.quanly {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.quanly to javafx.fxml;
    exports com.example.quanly;
}