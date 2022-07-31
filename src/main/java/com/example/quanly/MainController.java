package com.example.quanly;

import com.jfoenix.controls.JFXButton;
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
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @FXML
    private Button Btn_doanhthu;

    @FXML
    private Button Btn_hangnhap;

    @FXML
    private Button Btn_khachhang;

    @FXML
    private Button Btn_trangchu;


    @FXML
    private TextField txtSearch;

    @FXML
    private Label MonthRa;

    @FXML
    private Label MonthTong;

    @FXML
    private Label MonthVao;

    @FXML
    private Label YearTong;

    @FXML
    private Label yearRa;

    @FXML
    private Label yearVao;


    @FXML
    private Label dayRa;

    @FXML
    private Label dayTong;

    @FXML
    private Label dayVao;

    @FXML
    private JFXButton top1;

    @FXML
    private JFXButton top2;

    @FXML
    private JFXButton top3;

    @FXML
    private TableView<ChiTietKhachHang> tableKh;

    @FXML
    private TableColumn<ChiTietKhachHang, String> idKH;

    @FXML
    private TableColumn<ChiTietKhachHang, Integer> idLoc;

    @FXML
    private TableColumn<ChiTietKhachHang, String> idNgay;

    @FXML
    private TableColumn<ChiTietKhachHang, Integer> idThung;

    DoanhThuMain doanhthu = null ;
    PreparedStatement preparedStatement = null ;

    ChiTietKhachHang ctkh = null;

    public MainController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showNgay();
            showTop();
            showTopKH();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<ChiTietKhachHang> getCTKH() throws SQLException {
        ObservableList<ChiTietKhachHang> CTKHList = FXCollections.observableArrayList();
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();

        String query = "SELECT KH_tenkh,CTKH_soloc, CTKH_sothungmua,DATEDIFF(CURRENT_DATE() , max(quanly_chitietkhachhang.CTKH_ngay)) AS SNGD\n" +
                "FROM quanly_khachhang,quanly_chitietkhachhang\n" +
                "where quanly_khachhang.KH_stt = quanly_chitietkhachhang.KH_stt\n" +
                "GROUP BY KH_tenkh\n" +
                "HAVING DATEDIFF(CURRENT_DATE() , max(quanly_chitietkhachhang.CTKH_ngay)) > 30";

        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                ctkh = new ChiTietKhachHang(rs.getString("KH_tenkh"),rs.getInt("CTKH_soloc"),rs.getInt("CTKH_sothungmua"),rs.getString("SNGD"));
                CTKHList.add(ctkh);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }

        return CTKHList;
    }



    // Bang so ngay chua giao dich
    public ObservableList<DoanhThuMain> getDoanhThu() throws SQLException {
        ObservableList<DoanhThuMain> DoanhThuList = FXCollections.observableArrayList();
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();
        String query = "SELECT DT_ngay,DT_soluongban,DT_soluongtra,quanly_loaihang.LH_ten,quanly_loaihang.LH_dongia*DT_soluongban AS ThanhTien\n" +
                "FROM quanly_doanhthu,quanly_loaihang\n" +
                "WHERE quanly_doanhthu.LH_malh=quanly_loaihang.LH_malh";
        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                doanhthu = new DoanhThuMain(rs.getDate("DT_ngay"),rs.getInt("DT_soluongban"),rs.getInt("DT_soluongtra"),rs.getInt("LH_malh"),rs.getString("LH_ten"),rs.getInt("thanhtien"));
                DoanhThuList.add(doanhthu);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return DoanhThuList;
    }

    public void showNgay() throws SQLException {
        ObservableList<ChiTietKhachHang> chitietKHList = getCTKH();
        idKH.setCellValueFactory(new PropertyValueFactory<ChiTietKhachHang,String>("sotientra"));
        idLoc.setCellValueFactory(new PropertyValueFactory<ChiTietKhachHang,Integer>("soloc"));
        idThung.setCellValueFactory(new PropertyValueFactory<ChiTietKhachHang,Integer>("sothungmua"));
        idNgay.setCellValueFactory(new PropertyValueFactory<ChiTietKhachHang,String>("ngay"));

        tableKh.setItems(chitietKHList);


    }


    public void showTopKH() throws SQLException{

        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();

        String query = "SELECT KH_tenkh, SUM(KH_tienban) AS tien\n" +
                "FROM quanly_khachhang\n" +
                "GROUP BY KH_tenkh\n" +
                "ORDER BY tien DESC \n" +
                "LIMIT 0,1";
        Statement st;
        ResultSet rs;

        String query1 = "SELECT KH_tenkh, SUM(KH_tienban) AS tien\n" +
                "FROM quanly_khachhang\n" +
                "GROUP BY KH_tenkh\n" +
                "ORDER BY tien DESC \n" +
                "LIMIT 1,1";
        Statement st1;
        ResultSet rs1;

        String query2 = "SELECT KH_tenkh, SUM(KH_tienban) AS tien\n" +
                "FROM quanly_khachhang\n" +
                "GROUP BY KH_tenkh\n" +
                "ORDER BY tien DESC \n" +
                "LIMIT 2,1";
        Statement st2;
        ResultSet rs2;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            st1 = connectionDB.createStatement();
            rs1 = st1.executeQuery(query1);

            st2 = connectionDB.createStatement();
            rs2 = st2.executeQuery(query2);

            while (rs.next())
                top1.setText(rs.getString("KH_tenkh"));

            while (rs1.next())
                top2.setText(rs1.getString("KH_tenkh"));

            while (rs2.next())
                top3.setText(rs2.getString("KH_tenkh"));


        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public void showTop() throws SQLException {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();

        String query = "SELECT DT_ngay, SUM(DT_sothungban)+SUM(DT_solocban) as SLB, sum(DT_sothungtra) as SLT , sum(DT_tongtien) as DoanhThu \n" +
                "FROM `quanly_doanhthu` \n" +
                "WHERE DT_ngay = CURRENT_DATE \n" +
                "and 1 GROUP BY DT_ngay\n";

        Statement st;
        ResultSet rs;

        String query1 = "SELECT  month(CURRENT_DATE),sum(DT_sothungban)+sum(DT_solocban) AS SLB, sum(DT_sothungtra) AS SLT, sum(DT_tongtien) AS DoanhThu\n" +
                "FROM quanly_doanhthu\n" +
                "WHERE month(DT_ngay) = month(CURRENT_DATE)\n" +
                "and 1 GROUP BY month(CURRENT_DATE)\n";
        Statement st1;
        ResultSet rs1;

        String query2 = "SELECT year(DT_ngay), SUM(DT_sothungban)+sum(DT_solocban) as SLB, sum(DT_sothungtra) as SLT, sum(DT_tongtien) as DoanhThu \n" +
                "FROM `quanly_doanhthu` \n" +
                "WHERE 1 GROUP BY year(DT_ngay)";
        Statement st2;
        ResultSet rs2;


        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            st1 = connectionDB.createStatement();
            rs1 = st1.executeQuery(query1);

            st2 = connectionDB.createStatement();
            rs2 = st2.executeQuery(query2);

            Locale localeEN = new Locale("en", "EN");
            NumberFormat en = NumberFormat.getInstance(localeEN);

            while (rs.next()){
               dayTong.setText(en.format(rs.getInt("DoanhThu"))+"đ");
               dayRa.setText(String.valueOf(rs.getInt("SLB"))+" thùng");
               dayVao.setText(String.valueOf(rs.getInt("SLT"))+" thùng");
            }

            while (rs1.next()){
                MonthTong.setText(en.format(rs1.getInt("DoanhThu"))+"đ");
                MonthRa.setText(String.valueOf(rs1.getInt("SLB"))+" thùng");
                MonthVao.setText(String.valueOf(rs1.getInt("SLT"))+" thùng");
            }

            while (rs2.next()){
                YearTong.setText(en.format(rs2.getInt("DoanhThu"))+"đ");
                yearRa.setText(String.valueOf(rs2.getInt("SLB"))+" thùng");
                yearVao.setText(String.valueOf(rs2.getInt("SLT"))+" thùng");
            }


        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }












    //------------------------------------------Các nút menu------------------------------------------\\

    public void sceneTrangChu(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }

    public void sceneDoanhThu(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DoanhThu.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }
    public void sceneKhachHang(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("KhachHang.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setX(0);
        stage.setScene(scene);
        stage.show();
    }
    public void sceneHangNhap(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HangNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }


}
