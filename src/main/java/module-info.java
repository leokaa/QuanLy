module com.example.quanly {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires itextpdf;


    opens com.example.quanly to javafx.fxml;
    exports com.example.quanly;
}