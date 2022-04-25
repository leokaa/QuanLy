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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class KhachHangController  implements Initializable {
    KhachHang khachHang = null ;

    @FXML
    private Label idlabel;
    @FXML
    private TableView<KhachHang> tableView;
    @FXML
    private TableColumn<KhachHang,String> tencol;

    @FXML
    private TableColumn<KhachHang,String> sdtcol;

    @FXML
    private TableColumn<KhachHang,String> diachicol;

    @FXML
    private TableColumn<KhachHang,String> nothungcol;

    @FXML
    private TableColumn<KhachHang,String> notiencol;

    @FXML
    private TableColumn<KhachHang,String> ghichucol;

    @FXML
    TableColumn<KhachHang,String> thucthicol;

    @FXML
    TableColumn<KhachHang,String> sttcol;

    @FXML
    private Pane create;

    @FXML
    private Pane bantra;

    @FXML
    private TextField fieldTen;
    @FXML
    private TextField fieldsdt;
    @FXML
    private TextField fielddiachi;
    @FXML
    private TextArea fieldghichu;
    @FXML
    private TextField fieldnotien;
    @FXML
    private TextField  fieldnothung;
    @FXML
    private TextField fieldstt;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField fieldtenban;
    @FXML
    private TextField fieldsdtban;
    @FXML
    private TextField fielddiachiban;
    @FXML
    private TextArea fieldghichuban;
    @FXML
    private TextField fieldsttban;
    @FXML
    private TextField fieldsothungban;
    @FXML
    private TextField fieldsotienthu;
    @FXML private ComboBox comboban;

    @FXML
    private Pane tra;
    @FXML
    private TextField fieldstttra;
    @FXML
    private TextField fieldsothungtra;
    @FXML
    private TextField fieldsotientra;
    @FXML
    private TextField fielddiachitra;
    @FXML
    private TextArea fieldghichutra;
    @FXML
    private  TextField fieldtentra;
    @FXML
    private TextField fieldsdttra;


    private  boolean update;
    Connection connection = null;
    String query = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create.setVisible(false);
        bantra.setVisible(false);
        tra.setVisible(false);
        try {
            showTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Search();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<KhachHang> getKhachhang() throws SQLException {
        ObservableList<KhachHang> HangNhapList = FXCollections.observableArrayList();
        connection = DatabaseConnection.getConnect();
        query = "SELECT * FROM `quanly_khachhang` WHERE 1";


        try{
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            Locale localeEN = new Locale("en", "EN");
            NumberFormat en = NumberFormat.getInstance(localeEN);

            while (resultSet.next()){
                khachHang = new KhachHang(resultSet.getInt("KH_stt"),resultSet.getString("KH_tenkh"),resultSet.getString("KH_sdt"),resultSet.getString("KH_diachi"),resultSet.getInt("KH_sothungno"),en.format(resultSet.getInt("KH_sotienno")),resultSet.getString("KH_ghichu"));
                HangNhapList.add(khachHang);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return HangNhapList;
    }
    public void showTable() throws SQLException {
        ObservableList<KhachHang> hangnhapList = getKhachhang();
        sttcol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("stt"));
        tencol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("tenkh"));
        sdtcol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("sdt"));
        diachicol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("diachi"));
        nothungcol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("sothungno"));
        notiencol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("sotienno"));
        ghichucol.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("ghichu"));




        Callback<TableColumn<KhachHang,String>, TableCell<KhachHang,String>> cellFoctory = (TableColumn<KhachHang,String> param )-> {
            final TableCell<KhachHang,String> cell = new TableCell<KhachHang,String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);

                    }
                    else {
                        //TableCell<SinhVien, String> cell = new TableCell<>();

                        Button editButton = new Button("Sửa");
                        editButton.setStyle("-fx-background-color: #f58181; -fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.2), 0, 0, 0, 2); -fx-background-radius: 3px;-fx-padding: 5 5 5 5;-fx-border-insets: 2px;-fx-background-insets: 2px;");

                        Button deleteButton = new Button("Xóa");
                        deleteButton.setStyle("-fx-background-color: #f5c285; -fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.2), 0, 0, 0, 2); -fx-background-radius: 3px;-fx-padding: 5 5 5 5;-fx-border-insets: 2px;-fx-background-insets: 2px;");

                        Button giaodichButton = new Button("Giao dịch");
                        giaodichButton.setStyle("-fx-background-color: #6EBF8B; -fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.2), 0, 0, 0, 2); -fx-background-radius: 3px;-fx-padding: 5 5 5 5;-fx-border-insets: 2px;-fx-background-insets: 2px;");


                        HBox manageButton = new HBox( editButton, deleteButton,giaodichButton);
                        manageButton.setStyle("-fx-alignment:center");
                        setGraphic(manageButton);

                        deleteButton.setOnMouseClicked((MouseEvent event) -> {
                            try {

//                              Dieu kien de loc ma lh

                                khachHang = getTableView().getItems().get(getIndex());
//                                System.out.println(hangnhap);
                                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Cảnh báo");
                                //alert.setHeaderText("Bạn có chắc muốn xóa loại hành: "+hangnhap.getTen_LH()+" ?");
                                alert.setHeaderText("Bạn có chắc muốn xóa khách hàng này không!");
                                ButtonType btT1 = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                                ButtonType btT2 = new ButtonType("No",ButtonBar.ButtonData.NO);

                                alert.getButtonTypes().setAll(btT1,btT2);
                                Optional<ButtonType> result = alert.showAndWait();

                                if(result.get().getButtonData()==ButtonBar.ButtonData.YES){
                                    String query = "DELETE FROM `quanly_khachhang` WHERE KH_sdt = "+khachHang.getSdt();                           System.out.println(query);
                                    DatabaseConnection connectionNow = new DatabaseConnection();
                                    Connection connectionDB = connectionNow.getConnect();
                                    preparedStatement = connectionDB.prepareStatement(query);
                                    preparedStatement.execute();
                                    showTable();
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(HangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });


                        editButton.setOnMouseClicked((MouseEvent event) -> {

                            update = true;
                            khachHang = getTableView().getItems().get(getIndex());
                            try {
                                connection  = DatabaseConnection.getConnect();
                                query = " SELECT * FROM quanly_khachhang Where KH_stt = "+ khachHang.getStt();
                                resultSet = preparedStatement.executeQuery(query);
                                resultSet.next();
                                fieldnotien.setText(String.valueOf(resultSet.getInt("KH_sotienno")));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            create.setVisible(true);
                            fieldTen.setText(khachHang.getTenkh());
                            fieldsdt.setText(String.valueOf(khachHang.getSdt()));
                            fielddiachi.setText(khachHang.getDiachi());
                            fieldghichu.setText(khachHang.getGhichu());
                            fieldstt.setText(String.valueOf(khachHang.getStt()));
                            fieldstt.setDisable(true);
                            fieldnothung.setText(String.valueOf(khachHang.getSothungno()));


                        });



                    }
                    setText(null);
                }
            };
            return cell;

        };
        thucthicol.setCellFactory(cellFoctory);
        tableView.setItems(hangnhapList);
    }

    public void thoat(ActionEvent event){
        create.setVisible(false);
    }
    public void create(ActionEvent event){
        idlabel.setText("Thêm khách hàng");
        create.setVisible(true);
        update = false;
        //fieldstt.setDisable(true);
        //fieldstt.setText(null);
        fieldsdt.setText(null);
        fielddiachi.setText(null);
        fieldghichu.setText(null);
        fieldTen.setText(null);
        fieldnothung.setDisable(true);
        fieldnothung.setText(null);
        fieldnotien.setText(null);
        fieldnotien.setDisable(true);
        fieldstt.setDisable(true);
    }
    public void save(ActionEvent event) throws SQLException {

        connection = DatabaseConnection.getConnect();
        String tenkh = fieldTen.getText();
        String sdt = fieldsdt.getText();
        String diachi = fielddiachi.getText();
        String ghichu = fieldghichu.getText();
        int sothungno = 0;
        int sotienno = 0;

        if(update == true){
            int stt = Integer.parseInt(fieldstt.getText());
            if(!fieldnothung.getText().isEmpty()) {
                sothungno = Integer.parseInt(fieldnothung.getText());
            }
            if(!fieldnotien.getText().isEmpty()){
                sotienno  = Integer.parseInt(fieldnotien.getText());
            }
            query = "UPDATE `quanly_khachhang` SET `KH_tenkh`='"+tenkh+"',`KH_sdt`="+sdt+ ",`KH_diachi`='"+diachi+"',`KH_ghichu`='"+ghichu+"',`KH_sothungno` = "+sothungno +",`KH_sotienno`= "+sotienno+" WHERE `KH_stt`="+stt;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }
        else{

            query = "INSERT INTO `quanly_khachhang`(`KH_tenkh`, `KH_sdt`, `KH_diachi`, `KH_ghichu`) VALUES ('" + tenkh + "','" + sdt + "','" + diachi + "','" + ghichu + "')";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

        }
        create.setVisible(false);
        showTable();

    }
    public void Search() throws SQLException {
        ObservableList<KhachHang> hangnhapList = getKhachhang();
        FilteredList<KhachHang> filteredList = new FilteredList<>(hangnhapList, b->true);
        textSearch.textProperty().addListener((observable, oldVale, newValue) -> {
            filteredList.setPredicate(khachHang->{
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (khachHang.getTenkh().toLowerCase().indexOf(searchKeyword) >-1)
                    return true;
                else if(String.valueOf(khachHang.getSdt()).toLowerCase().indexOf(searchKeyword) >-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<KhachHang> sort = new SortedList<>(filteredList);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }

    public void ban(ActionEvent event) throws SQLException {

        KhachHang khachHang = tableView.getSelectionModel().getSelectedItem();
        if(khachHang != null) {
            bantra.setVisible(true);
            int stt = khachHang.getStt();
            String tenkh = khachHang.getTenkh();
            String diachi = khachHang.getDiachi();
            String sdt = khachHang.getSdt();
            String ghichu = khachHang.getGhichu();
            fielddiachiban.setDisable(true);
            fielddiachiban.setText(diachi);

            fieldghichuban.setText(ghichu);

            fieldsdtban.setDisable(true);
            fieldsdtban.setText(sdt);

            fieldsttban.setDisable(true);
            fieldsttban.setText(String.valueOf(stt));

            fieldtenban.setDisable(true);
            fieldtenban.setText(tenkh);

            ObservableList<String> list = FXCollections.observableArrayList();
            query = "SELECT * FROM quanly_loaihang";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString("LH_malh"));
            }
            comboban.setItems(list);
        }




    }
    public void luuban(ActionEvent event) throws SQLException {
        connection = DatabaseConnection.getConnect();
        int stt = Integer.parseInt(fieldsttban.getText());
        String ghichu = fieldghichuban.getText();
        int soluong = Integer.parseInt(fieldsothungban.getText());
        String tien = fieldsotienthu.getText();
        String loaihang = String.valueOf(comboban.getValue());

        query = "SELECT * FROM quanly_loaihang WHERE  lh_malh='Thùng'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int giathung = resultSet.getInt("LH_dongia");

        query = "SELECT * FROM quanly_loaihang WHERE  lh_malh='Chai'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int giachai = resultSet.getInt("LH_dongia");

        query = "SELECT * FROM quanly_khachhang WHERE KH_stt="+stt;
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int noton = resultSet.getInt("KH_sotienno");

        if (loaihang.equals("Thùng")) {
            int tienno = soluong * giathung - Integer.parseInt(tien)+noton;

            query = "UPDATE `quanly_khachhang` SET `KH_sothungno`=" + soluong + ",`KH_sotienno`=" + tienno + ",`KH_ghichu`='" + ghichu + "' WHERE KH_stt =" + stt;

        }else {
            int tienno  = soluong*giachai - Integer.parseInt(tien)+noton;
            query = "UPDATE `quanly_khachhang` SET"+"`KH_sotienno`=" + tienno +",`KH_ghichu`='" + ghichu + "' WHERE KH_stt =" + stt;
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

        query = "SELECT * FROM `quanly_doanhthu` WHERE DT_ngay =CURRENT_DATE AND LH_malh ='"+loaihang+"'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            //resultSet.next();
            int soluongton = resultSet.getInt("DT_soluongban")+soluong;
            query = "UPDATE `quanly_doanhthu` SET `DT_soluongban`="+soluongton+" WHERE DT_ngay = CURRENT_DATE AND `LH_malh`= '"+loaihang+"'";
        }
        else {
            query ="INSERT INTO `quanly_doanhthu`(`DT_ngay`, `DT_soluongban`,`DT_soluongtra`, `LH_malh`) VALUES (CURRENT_DATE ,"+soluong+",0,'"+loaihang+"') ";
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

        bantra.setVisible(false);
        showTable();

    }
    public void thoatbantra(ActionEvent event){
        bantra.setVisible(false);
    }
    public void tra(ActionEvent event){
        KhachHang khachHang = tableView.getSelectionModel().getSelectedItem();
        if(khachHang !=null) {
            tra.setVisible(true);
            fieldstttra.setDisable(true);
            fieldstttra.setText(String.valueOf(khachHang.getStt()));

            fieldtentra.setDisable(true);
            fieldtentra.setText(khachHang.getTenkh());

            fielddiachitra.setDisable(true);
            fielddiachitra.setText(khachHang.getDiachi());

            fieldghichutra.setText(khachHang.getGhichu());


        }
    }
    public void thoattra(ActionEvent event){
        tra.setVisible(false);
    }
    public void luutra(ActionEvent event) throws SQLException {
        String stt = fieldstttra.getText();
        String ghichu = fieldghichu.getText();
        int sothung = 0;
        int sotien = 0;
        if(!fieldsothungtra.getText().isEmpty()){
            sothung  = Integer.parseInt(fieldsothungtra.getText());
        }
        if(!fieldsotientra.getText().isEmpty()){
            sotien = Integer.parseInt(fieldsotientra.getText());
        }

        connection = DatabaseConnection.getConnect();
        query = "UPDATE `quanly_khachhang` SET `KH_sothungno` = `KH_sothungno` -" + sothung + ",`KH_sotienno` = `KH_sotienno` -" + sotien + ",`KH_ghichu`='" + ghichu + "' WHERE KH_stt =" + stt;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

//        query = "UPDATE `quanly_doanhthu` SET `DT_soluongtra` = `DT_soluongtra`-"+sothung +" WHERE `DT_ngay` = CURRENT_DATE AND `LH_malh` ='thung' " ;
//        preparedStatement = connection.prepareStatement(query);
//        preparedStatement.execute();
        query = "SELECT * FROM `quanly_doanhthu` WHERE DT_ngay =CURRENT_DATE AND LH_malh ='Thùng'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            query = "UPDATE `quanly_doanhthu` SET `DT_soluongtra` = `DT_soluongtra`+"+sothung +" WHERE `DT_ngay` = CURRENT_DATE AND `LH_malh` ='Thùng' " ;

        }
        else {
            query ="INSERT INTO `quanly_doanhthu`(`DT_ngay`, `DT_soluongtra`, `LH_malh`) VALUES (CURRENT_DATE ,"+sothung+",'Thùng') ";
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        tra.setVisible(false);
        showTable();
    }
}
