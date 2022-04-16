package com.example.quanly;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    LineChart<String, Number> idLineChart;

    DoanhThuMain doanhthu = null ;
    PreparedStatement preparedStatement = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showLienChart();
            showTop();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



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


    public void showLienChart() throws SQLException {
        //////////////////////////////////////////////////////////////////////////////
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        XYChart.Series series = new XYChart.Series();
        series.setName("Doanh thu tháng");
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();
        String query = "\n" +
                "SELECT month(DT_ngay), SUM(DT_soluongban)*quanly_loaihang.LH_dongia AS ThanhTien\n" +
                "FROM `quanly_doanhthu`, `quanly_loaihang`\n" +
                "WHERE quanly_doanhthu.LH_malh=quanly_loaihang.LH_malh\n" +
                "and 1 GROUP BY month(DT_ngay)\n" +
                "\n";
        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                series.getData().add(new XYChart.Data(rs.getString(1), Integer.parseInt(rs.getString(2))));//Add data to Chart. Changed the second input to Integer due to LineChart<Number,Number>. This should work, though I haven't tested it.

            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        idLineChart.getData().add(series);
    }

    public void showTop() throws SQLException {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();

        String query = "SELECT  CURDATE(),DT_soluongban AS SLB, DT_soluongtra AS SLT, DT_soluongban*LH_dongia AS DoanhThu\n" +
                "FROM quanly_doanhthu, quanly_loaihang\n" +
                "WHERE quanly_doanhthu.Lh_malh=quanly_loaihang.LH_malh\n" +
                "AND DT_ngay=CURDATE()\n";

        Statement st;
        ResultSet rs;

        String query1 = "SELECT  month(CURRENT_DATE),sum(DT_soluongban) AS SLB, sum(DT_soluongtra) AS SLT, sum(DT_soluongban)*LH_dongia AS DoanhThu\n" +
                "FROM quanly_doanhthu, quanly_loaihang\n" +
                "WHERE quanly_doanhthu.Lh_malh=quanly_loaihang.LH_malh\n" +
                "AND  month(DT_ngay) = month(CURRENT_DATE)\n" +
                "GROUP BY month(CURRENT_DATE)\n";
        Statement st1;
        ResultSet rs1;

        String query2 = "SELECT  year(CURRENT_DATE),sum(DT_soluongban) AS SLB, sum(DT_soluongtra) AS SLT, sum(DT_soluongban)*LH_dongia AS DoanhThu\n" +
                "FROM quanly_doanhthu, quanly_loaihang\n" +
                "WHERE quanly_doanhthu.Lh_malh=quanly_loaihang.LH_malh\n" +
                "AND  year(DT_ngay) = year(CURRENT_DATE)\n" +
                "GROUP BY year(CURRENT_DATE)\n";
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
