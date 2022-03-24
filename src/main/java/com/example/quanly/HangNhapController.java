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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

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
    private TableColumn<HangNhap, Date> NgayNhapColumn;


    @FXML
    private TableColumn<HangNhap, Integer> SoLuongColumn;

    @FXML
    private TableColumn<HangNhap, Integer> ThanhTienColumn;

    @FXML
    private TableColumn<HangNhap, String> GhiChuColumn;

    @FXML
    private TextArea create_ghichu;

    @FXML
    private ComboBox<String> create_loaihang;


    @FXML
    private DatePicker create_ngaynhap;

    @FXML
    private TextField create_soluong;

    @FXML
    private TextArea edit_ghichu;

    @FXML
    private ComboBox<String> edit_loaihang;

    @FXML
    private DatePicker edit_ngaynhap;

    @FXML
    private TextField edit_soluong;




    @FXML
    private TableColumn<HangNhap, String> ThucThiColumn;

    HangNhap hangnhap = null ;
    PreparedStatement preparedStatement = null ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showTable();

//      Gan gia tri cua combobox
        ObservableList<String> list = FXCollections.observableArrayList("1", "2");
        create_loaihang.setItems(list);

    }

    public ObservableList<HangNhap> getHangNhap(){
        ObservableList<HangNhap> HangNhapList = FXCollections.observableArrayList();
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();

        String query = "SELECT quanly_hangnhap.HN_ngay,quanly_loaihang.LH_ten,quanly_hangnhap.HN_soluong,quanly_hangnhap.HN_soluong*quanly_loaihang.LH_dongia AS thanhtien,quanly_hangnhap.HN_ghichu \n" +
                "FROM quanly_hangnhap, quanly_loaihang\n" +
                "WHERE quanly_hangnhap.LH_malh=quanly_loaihang.LH_malh";
        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                hangnhap = new HangNhap(rs.getDate("HN_ngay"),rs.getString("LH_ten"),rs.getInt("HN_soluong"),rs.getInt("thanhtien"),rs.getString("HN_ghichu"));
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
//                        Image image1 = new Image(getClass().getResourceAsStream("editing.png"));
                        deleteButton.setStyle("-fx-background-color: #f5c285; -fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.2), 0, 0, 0, 2); -fx-background-radius: 3px;-fx-padding: 5 5 5 5;-fx-border-insets: 2px;-fx-background-insets: 2px;");
//                        deleteButton.setGraphic(new ImageView(image1));

                        HBox manageButton = new HBox( editButton, deleteButton);
                        manageButton.setStyle("-fx-alignment:center");
                        setGraphic(manageButton);

                        deleteButton.setOnMouseClicked((MouseEvent event) -> {

                        });


                        editButton.setOnMouseClicked((MouseEvent event) -> {
                            Id_edit.setVisible(true);
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


    public void Create(ActionEvent event) throws Exception{
        Id_create.setVisible(true);

    }

    public void Create_thoat(ActionEvent event) throws Exception{
        Id_create.setVisible(false);

    }

    public void Create_them(ActionEvent event) throws Exception{

        System.out.println(create_ngaynhap.getValue());

        ObservableList<String> list = FXCollections.observableArrayList("1", "2");
        create_loaihang.setItems(list);
        String query = "INSERT INTO `quanly1`.`quanly_hangnhap` (`HN_ngay`, `LH_malh`, `HN_soluong`, `HN_ghichu`) VALUES ('"+create_ngaynhap.getValue()+"','"+create_loaihang.getValue()+"','"+create_soluong.getText()+"','"+create_ghichu.getText()+"');";
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();
        Statement st;
        try{
            st = connectionDB.createStatement();
            st.executeUpdate(query);
            Id_create.setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void edit(ActionEvent event) throws Exception{

        Id_edit.setVisible(true);

//        HangNhap hangnhap = new HangNhap();

//        //student.setSsTT(LabelSTT.getText());
//        hangnhap.setHoTen(GetHoTen.getText());
//        student.setMSSV(GetMSSV.getText());
//        student.setDiaChi(GetDiaChi.getText());
//        student.setEmail(GetEmail.getText());
//        student.setDienThoai(GetSDT.getText());
//        student.setDate(GetNgaySinh.getText());
//        student.setCMND(GetCMND.getText());
//
//        String query = "UPDATE `user`.`student` SET `MSSV`='"+GetMSSV.getText()+"',`HoTen`='"+GetHoTen.getText()+"',`Email`='"+GetEmail.getText()+"',`SoDienThoai`='"+GetSDT.getText()+"',`NgaySinh`='"+GetNgaySinh.getText()+"',`CMND`='"+GetCMND.getText()+"'  WHERE  `STT`="+LabelSTT.getText();
//
//        DatabaseConnection connectionNow = new DatabaseConnection();
//        Connection connectionDB = connectionNow.getConnection();
//        Statement st;
//
//        try{
//            st = connectionDB.createStatement();
//            st.executeUpdate(query);
//            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("table_view.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
    }

    public void Edit_sua(ActionEvent event) throws Exception{
        HangNhap hangnhap = new HangNhap();
        LocalDate tamp = edit_ngaynhap.getValue();

//        hangnhap.setNgayNhap(edit_ngaynhap);
//        hangnhap.setTen_LH(edit_loaihang.getValue());
//        hangnhap.setSoLuong(edit_soluong.getText().);
//        student.setDiaChi(GetDiaChi.getText());
//        student.setEmail(GetEmail.getText());
//        student.setDienThoai(GetSDT.getText());
//        student.setDate(GetNgaySinh.getText());
//        student.setCMND(GetCMND.getText());
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
