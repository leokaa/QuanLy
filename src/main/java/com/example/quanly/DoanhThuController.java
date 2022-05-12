package com.example.quanly;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoanhThuController implements Initializable {

    @FXML
    private TableView<DoanhThu> tableView;
    @FXML
    private TableColumn<DoanhThu,String> ngaycol;

    @FXML
    private TableColumn<DoanhThu,String> soluongbancol;

    @FXML
    private TableColumn<DoanhThu,String> soluongtracol;

    @FXML
    private TableColumn<DoanhThu,String> malhcol;

    @FXML
    private TableColumn<DoanhThu,String> tongdoanhthucol;

    @FXML
    private TextField textSearch;

    Connection connection = null;
    String query = null;
    DoanhThu doanhThu = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    Locale localeEN = new Locale("en", "EN");
    NumberFormat en = NumberFormat.getInstance(localeEN);

    private ObservableList<DoanhThu> doanhthuList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ngaycol.setCellValueFactory(new PropertyValueFactory<DoanhThu, String>("ngay"));
        malhcol.setCellValueFactory(new PropertyValueFactory<DoanhThu, String>("maLH"));
        soluongbancol.setCellValueFactory(new PropertyValueFactory<DoanhThu, String>("soluongban"));
        soluongtracol.setCellValueFactory(new PropertyValueFactory<DoanhThu, String>("soluongtra"));
        tongdoanhthucol.setCellValueFactory(new PropertyValueFactory<DoanhThu, String>("tongdoanhthu"));

        try {
            connection = DatabaseConnection.getConnect();
            Search();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoanhThu> getDoanhThu() throws SQLException {
        ObservableList<DoanhThu> DoanhThuList = FXCollections.observableArrayList();
        connection = DatabaseConnection.getConnect();
        query = "SELECT *  FROM `quanly_doanhthu` WHERE 1";


        try{
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            Locale localeEN = new Locale("en", "EN");
            NumberFormat en = NumberFormat.getInstance(localeEN);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            resultSet.next();
            String formattedDate = formatter.format(resultSet.getDate("DT_ngay"));

            while (resultSet.next()){
                doanhThu  = new DoanhThu(formattedDate,resultSet.getInt("DT_soluongban"),resultSet.getInt("DT_soluongtra"),resultSet.getString("LH_malh"),resultSet.getString("DT_tongtien"));
                DoanhThuList.add(doanhThu);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return DoanhThuList;
    }



    public void showTable_Thang(ActionEvent event) throws Exception {
        /////////////////////////////

        ngaycol.setText("Tháng");
        doanhthuList.clear();

        query = "SELECT month(DT_ngay),year(DT_ngay), LH_malh, SUM(DT_soluongban), sum(DT_soluongtra),sum(DT_tongtien) FROM `quanly_doanhthu` WHERE 1 GROUP BY month(DT_ngay),year(DT_ngay), LH_malh";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String maLH = resultSet.getString("LH_malh");

            query = "SELECT * FROM  `quanly_loaihang` Where LH_malh ='" +maLH+"'";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            resultSet1.next();
            doanhthuList.add(new DoanhThu(resultSet.getString("month(DT_ngay)")+"/"+
                    resultSet.getString("year(DT_ngay)"),
                    resultSet.getInt("SUM(DT_soluongban)"),
                    resultSet.getInt("sum(DT_soluongtra)"),
                    maLH,
                    en.format(resultSet.getInt("sum(DT_tongtien)"))));
            tableView.setItems(doanhthuList);
        }
    }

    public void showTable_Ngay(ActionEvent event) throws Exception {
        ngaycol.setText("Ngày");

        doanhthuList.clear();

        query = "SELECT * FROM  `quanly_doanhthu`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String maLH = resultSet.getString("LH_malh");

            query = "SELECT * FROM  `quanly_loaihang` Where LH_malh = '"+maLH+"'";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            resultSet1.next();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = formatter.format(resultSet.getDate("DT_ngay"));
            doanhthuList.add(new DoanhThu(formattedDate
                    ,
                    resultSet.getInt("DT_soluongban"),
                    resultSet.getInt("DT_soluongtra"),
                    maLH,
                    en.format(resultSet.getInt("DT_tongtien"))));
            tableView.setItems(doanhthuList);
        }

    }

    public void showTable_Nam(ActionEvent event) throws Exception {
        doanhthuList.clear();
        ngaycol.setText("Năm");
        query = "SELECT year(DT_ngay), LH_malh, SUM(DT_soluongban), sum(DT_soluongtra), sum(DT_tongtien) FROM `quanly_doanhthu` WHERE 1 GROUP BY year(DT_ngay), LH_malh";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String maLH = resultSet.getString("LH_malh");


            doanhthuList.add(new DoanhThu(
                    resultSet.getString("year(DT_ngay)"),
                    resultSet.getInt("SUM(DT_soluongban)"),
                    resultSet.getInt("sum(DT_soluongtra)"),
                    maLH,
                    en.format(resultSet.getInt("sum(DT_tongtien)") )));
            tableView.setItems(doanhthuList);
        }



    }

    public void Search() throws SQLException {
        ObservableList<DoanhThu> doanhthuList = getDoanhThu();
        FilteredList<DoanhThu> filteredList = new FilteredList<>(doanhthuList, b->true);
        textSearch.textProperty().addListener((observable, oldVale, newValue) -> {
            filteredList.setPredicate(DoanhThu->{
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (DoanhThu.getNgay().toLowerCase().indexOf(searchKeyword) >-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<DoanhThu> sort = new SortedList<>(filteredList);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
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

    private void refreshTable() throws SQLException {


    }

}