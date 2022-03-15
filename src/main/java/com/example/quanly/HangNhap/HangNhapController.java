package com.example.quanly.HangNhap;

import com.example.quanly.DatabaseConnection;
import com.example.quanly.HangNhap.HangNhap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class HangNhapController implements Initializable {
    @FXML
    private Button Btn_them;

    @FXML
    private TableView<HangNhap> TableViewID;


    public TableView<HangNhap> getTableView() {
        return TableViewID;
    }

    public void setTableView(TableView<HangNhap> tableView) {
        this.TableViewID = tableView;
    }

    public TableView getTable(){
        return TableViewID;
    }

    @FXML
    private TableColumn<HangNhap, String> LHColumn;

    @FXML
    private TableColumn<HangNhap, Date> NgayNhapColumn;


    @FXML
    private TableColumn<HangNhap, Integer> SoLuongColumn;

    @FXML
    private TableColumn<HangNhap, Integer> ThanhTienColumn;

    @FXML
    private TableColumn<HangNhap, String> GhiChuColumn;


    @FXML
    private TableColumn<HangNhap, String> ThucThiColumn;

    HangNhap hangnhap = null ;
    PreparedStatement preparedStatement = null ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();
    }

    public ObservableList<HangNhap> getHangNhap(){
        ObservableList<HangNhap> HangNhapList = FXCollections.observableArrayList();
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();

        String query = "SELECT quanly_hangnhap.HN_mahn,quanly_hangnhap.HN_ngay,quanly_loaihang.LH_ten,quanly_hangnhap.HN_soluong,quanly_hangnhap.HN_soluong*quanly_loaihang.LH_dongia AS thanhtien,quanly_hangnhap.HN_ghichu\n" +
                "FROM quanly_hangnhap, quanly_loaihang\n" +
                "WHERE quanly_hangnhap.LH_malh=quanly_loaihang.LH_malh";
        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                hangnhap = new HangNhap(rs.getInt("HN_mahn"),rs.getDate("HN_ngay"),rs.getString("LH_ten"),rs.getInt("HN_soluong"),rs.getInt("thanhtien"),rs.getString("HN_ghichu"));
                HangNhapList.add(hangnhap);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return HangNhapList;
    }


    public void showTable(){
        ObservableList<HangNhap> hangnhapList = getHangNhap();

        LHColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,String>("Ten_LH"));
        NgayNhapColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Date>("ngayNhap"));
        SoLuongColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Integer>("SoLuong"));
        ThanhTienColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Integer>("ThanhTien"));
        GhiChuColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,String>("GhiChu"));

        TableViewID.setItems(hangnhapList);
    }

    public void Create(ActionEvent event) throws Exception{
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("s_create.fxml"));
//        Parent sampleParent = loader.load();
//        //CreateController controller = loader.getController();
//        //Student student = new Student();
//        //controller.setStudent(student);
//
//        //Map<Integer,Student> stu = new HashMap<Integer,Student>();
//        //tableView.getItems().add( stu.put(1,student));
//
//        Scene scene = new Scene(sampleParent);
//        stage.setScene(scene);
//        stage.show();


    }



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
