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
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HangNhapController implements Initializable {
    @FXML
    private Button Btn_them;

    @FXML
    private Pane Id_create;

    @FXML
    private Pane Id_edit;

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
    private TableColumn<HangNhap, String> IDColumn;

    @FXML
    private TableColumn<HangNhap, Date> NgayNhapColumn;


    @FXML
    private TableColumn<HangNhap, Integer> SoLuongColumn;

    @FXML
    private TableColumn<HangNhap, Integer> DonGiaColumn;

    @FXML
    private TableColumn<HangNhap, Integer> ThanhTienColumn;

    @FXML
    private TableColumn<HangNhap, String> GhiChuColumn;

    @FXML
    private TextArea create_ghichu;

    @FXML
    private TextField create_loaihang;


    @FXML
    private DatePicker create_ngaynhap;

    @FXML
    private TextField create_soluong;

    @FXML
    private TextField create_dongia;

    @FXML
    private TextArea edit_ghichu;

    @FXML
    private TextField edit_loaihang;

    @FXML
    private TextField edit_ngaynhap;

    @FXML
    private TextField edit_soluong;

    @FXML
    private TextField edit_dongia;

    @FXML
    private TextField txtSearch;


    @FXML
    private TableColumn<HangNhap, String> ThucThiColumn;

    HangNhap hangnhap = null ;
    PreparedStatement preparedStatement = null ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public ObservableList<HangNhap> getHangNhap() throws SQLException {
        ObservableList<HangNhap> HangNhapList = FXCollections.observableArrayList();
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();

        String query = "SELECT *, HN_dongia*HN_soluong AS ThanhTien\n" +
                "FROM quanly_hangnhap\n";
        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                hangnhap = new HangNhap(rs.getDate("HN_ngay"),rs.getString("HN_ten"),rs.getInt("HN_soluong"),rs.getInt("HN_Dongia"),rs.getInt("ThanhTien"),rs.getString("HN_ghichu"));
                HangNhapList.add(hangnhap);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return HangNhapList;
    }


    public void showTable() throws SQLException {
        ObservableList<HangNhap> hangnhapList = getHangNhap();
        LHColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,String>("Ten_LH"));
        NgayNhapColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Date>("ngayNhap"));
        NgayNhapColumn.setCellFactory(column -> {
            TableCell<HangNhap, Date> cell = new TableCell<HangNhap, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });

        SoLuongColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Integer>("SoLuong"));

        NumberFormat currencyFormat = NumberFormat.getNumberInstance();
        DonGiaColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Integer>("DonGia"));
        DonGiaColumn.setCellFactory(tc -> new TableCell<HangNhap, Integer>() {
            @Override
            protected void updateItem(Integer thanhtien, boolean empty) {
                super.updateItem(thanhtien, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(thanhtien));
                }
            }
        });

        ThanhTienColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,Integer>("ThanhTien"));
        ThanhTienColumn.setCellFactory(tc -> new TableCell<HangNhap, Integer>() {
            @Override
            protected void updateItem(Integer thanhtien, boolean empty) {
                super.updateItem(thanhtien, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(thanhtien));
                }
            }
        });

        GhiChuColumn.setCellValueFactory(new PropertyValueFactory<HangNhap,String>("GhiChu"));

        Callback<TableColumn<HangNhap,String>, TableCell<HangNhap,String>> cellFoctory = (TableColumn<HangNhap,String> param )-> {
            final TableCell<HangNhap,String> cell = new TableCell<HangNhap,String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);

                    }
                    else {
                        //TableCell<SinhVien, String> cell = new TableCell<>();

                        Button editButton = new Button("Sửa");
//                        Image image = new Image(getClass().getResourceAsStream("editing.png"));
                        editButton.setStyle("-fx-background-color: #f58181; -fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.2), 0, 0, 0, 2); -fx-background-radius: 3px;-fx-padding: 5 5 5 5;-fx-border-insets: 2px;-fx-background-insets: 2px;");

                        Button deleteButton = new Button("Xóa");
//                        Image image1 = new Image(getClass().getResourceAsStream("D:\\QuanLy\\src\\main\\resources\\com\\example\\quanly\\image\\editing.png"));
                        deleteButton.setStyle("-fx-background-color: #f5c285; -fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.2), 0, 0, 0, 2); -fx-background-radius: 3px;-fx-padding: 5 5 5 5;-fx-border-insets: 2px;-fx-background-insets: 2px;");
//                        deleteButton.setGraphic(new ImageView(image1));

                        HBox manageButton = new HBox( editButton, deleteButton);
                        manageButton.setStyle("-fx-alignment:center");
                        setGraphic(manageButton);

                        deleteButton.setOnMouseClicked((MouseEvent event) -> {
                            try {

//                              Dieu kien de loc ma lh

                                hangnhap = TableViewID.getSelectionModel().getSelectedItem();
//                                System.out.println(hangnhap);
                                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Cảnh báo");
                                //alert.setHeaderText("Bạn có chắc muốn xóa loại hành: "+hangnhap.getTen_LH()+" ?");
                                alert.setHeaderText("Bạn có chắc muốn xóa loại hàng nhập này không!");
                                ButtonType btT1 = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                                ButtonType btT2 = new ButtonType("No",ButtonBar.ButtonData.NO);

                                alert.getButtonTypes().setAll(btT1,btT2);
                                Optional<ButtonType> result = alert.showAndWait();

                                if(result.get().getButtonData()==ButtonBar.ButtonData.YES){
                                    String query = "DELETE FROM `quanly1`.`quanly_hangnhap` WHERE  `HN_ngay`='"+hangnhap.getNgayNhap()+"' AND `HN_ten`='"+hangnhap.getTen_LH()+"';";
                                    System.out.println(query);
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
                            Id_edit.setVisible(true);
                            hangnhap = TableViewID.getSelectionModel().getSelectedItem();
                            view(hangnhap);
                           // Edit_sua();
                        });


                    }
                    setText(null);
                }
            };
            return cell;

        };
        ThucThiColumn.setCellFactory(cellFoctory);
        TableViewID.setItems(hangnhapList);
    }

    public void Search() throws SQLException {
        ObservableList<HangNhap> hangnhapList = getHangNhap();
        FilteredList<HangNhap> filteredList = new FilteredList<>(hangnhapList,b->true);
        txtSearch.textProperty().addListener((observable, oldVale, newValue) -> {
            filteredList.setPredicate(HangNhap->{
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (HangNhap.getTen_LH().toLowerCase().indexOf(searchKeyword) >-1)
                    return true;
                else if(HangNhap.getNgayNhap().toString().toLowerCase().indexOf(searchKeyword) >-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<HangNhap> sort = new SortedList<>(filteredList);
        sort.comparatorProperty().bind(TableViewID.comparatorProperty());
        TableViewID.setItems(sort);
    }




    public void Create(ActionEvent event) throws Exception{
        Id_create.setVisible(true);

    }

    public void Create_thoat(ActionEvent event) throws Exception{
        Id_create.setVisible(false);

    }

    public void Create_them(ActionEvent event) throws Exception{

        System.out.println(create_ngaynhap.getValue());

        ObservableList<String> list = FXCollections.observableArrayList("1", "2");
        String query = "INSERT INTO `quanly1`.`quanly_hangnhap` (`HN_ngay`, `HN_ten`, `HN_soluong`, `HN_dongia`, `HN_ghichu`) VALUES ('"+create_ngaynhap.getValue()+"','"+create_loaihang.getText()+"','"+create_soluong.getText()+"','"+create_dongia.getText()+"','"+create_ghichu.getText()+"');";
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnect();
        Statement st;
        try{
            st = connectionDB.createStatement();
            st.executeUpdate(query);
            Id_create.setVisible(false);
            showTable();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void view(HangNhap hangnhap) {
        edit_ngaynhap.setText(String.valueOf(hangnhap.getNgayNhap()));
        edit_loaihang.setText(hangnhap.getTen_LH());
        edit_soluong.setText(String.valueOf(hangnhap.getSoLuong()));
        edit_dongia.setText(String.valueOf(hangnhap.getDonGia()));
        edit_ghichu.setText(hangnhap.getGhiChu());
    }

    public void Edit_sua() {
        HangNhap hangNhap = new HangNhap();
        //hangNhap.setNgayNhap();
        hangNhap.setSoLuong(Integer.valueOf(edit_soluong.getText()));
        hangNhap.setDonGia(Integer.valueOf(edit_dongia.getText()));
        //hangNhap.setTen_LH(edit_loaihang.getText());
        hangNhap.setGhiChu(edit_ghichu.getText());

        String query = "UPDATE `quanly1`.`quanly_hangnhap` SET `HN_ten`='"+edit_loaihang.getText()+"', `HN_dongia`='"+edit_dongia.getText()+"', `HN_soluong`='"+edit_soluong.getText()+"' " +
                "WHERE  `HN_ten`='"+edit_loaihang.getText()+"'";
        try{
            DatabaseConnection connectionNow = new DatabaseConnection();
            Connection connectionDB = null;
            connectionDB = connectionNow.getConnect();
            Statement st;

            st = connectionDB.createStatement();
            st.executeUpdate(query);
            Id_edit.setVisible(false);
            showTable();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Edit_thoat(ActionEvent event) throws Exception{
        Id_edit.setVisible(false);
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
