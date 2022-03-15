package com.example.quanly;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button Btn_doanhthu;

    @FXML
    private Button Btn_hangnhap;

    @FXML
    private Button Btn_khachhang;

    @FXML
    private Button Btn_trangchu;


    //------------------------------------------Các nút menu------------------------------------------\\

    public void sceneTrangChu(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));
        Parent sampleParent = loader.load();
        //CreateController controller = loader.getController();
        //Student student = new Student();
        //controller.setStudent(student);

        //Map<Integer,Student> stu = new HashMap<Integer,Student>();
        //tableView.getItems().add( stu.put(1,student));

        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }

    public void sceneDoanhThu(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DoanhThu.fxml"));
        Parent sampleParent = loader.load();
        //CreateController controller = loader.getController();
        //Student student = new Student();
        //controller.setStudent(student);

        //Map<Integer,Student> stu = new HashMap<Integer,Student>();
        //tableView.getItems().add( stu.put(1,student));

        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }
    public void sceneKhachHang(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("KhachHang.fxml"));
        Parent sampleParent = loader.load();
        //CreateController controller = loader.getController();
        //Student student = new Student();
        //controller.setStudent(student);

        //Map<Integer,Student> stu = new HashMap<Integer,Student>();
        //tableView.getItems().add( stu.put(1,student));

        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }
    public void sceneHangNhap(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HangNhap.fxml"));
        Parent sampleParent = loader.load();
        //CreateController controller = loader.getController();
        //Student student = new Student();
        //controller.setStudent(student);

        //Map<Integer,Student> stu = new HashMap<Integer,Student>();
        //tableView.getItems().add( stu.put(1,student));

        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }
}
