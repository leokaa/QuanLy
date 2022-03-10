module com.example.quanly {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quanly to javafx.fxml;
    exports com.example.quanly;
}