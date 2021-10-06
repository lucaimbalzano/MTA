package Controller;

import Connections.DatabaseConnections;


import Session.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.util.Duration;
import model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MainController implements Initializable {
     @FXML
    private Button btnReports, btnChats, btnLogs, btnTasks, btnProjects ,btnUsers, btnProfile;

    @FXML
    private GridPane pnChats, pnTable ,pnTasks, pnProjects, pnProfile, pnReports, pnLogs;

    @FXML
    private TextField txtName, txtLastname, txtAge, txtAddress , txtPhone, txtEmail ,txtPassword;

    @FXML
    private Label lblPathIndex,labelProfile;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<User,String> tcName;

    @FXML
    private TableColumn<User,String> tcLastname;

    @FXML
    private TableColumn<User,String> tcAge;

    @FXML
    private TableColumn<User,String> tcAddress;

    @FXML
    private TableColumn<User,String> tcPhone;

    @FXML
    private TableColumn<User,String> tcAction;

    @FXML
    private Button btnAdd, btnImport, btnExport;


    @FXML
    private FontAwesomeIconView iconRefreshTable;

    private ObservableList<User> data;
    Double xCordinate, yCordinate;
    UserConfigController userConfigController = new UserConfigController();
    DatabaseConnections databaseConnections = new DatabaseConnections();
    Connection conn = databaseConnections.getConnection();
    LoginController loginController = new LoginController();
    UserSession session = UserSession.getInstanceUserSession();
    String query = "SELECT * FROM user";

    private static Logger logger = Logger.getLogger(MainController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BasicConfigurator.configure();
        Preferences userPreferences = Preferences.userRoot();



        tcAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcLastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        tcAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Statement statement;
        try {

            data = FXCollections.observableArrayList();
            statement = conn.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while(queryOutput.next()){
                data.add(new User(queryOutput.getString("Name"),queryOutput.getString("Lastname"), queryOutput.getString("Age"), queryOutput.getString("Address"),queryOutput.getString("Phone"),"N/A",""));
            }

            logger.debug("### Users Data Retrived ###");

        } catch (SQLException e) {
            logger.debug(" ### Exception Occurred: "+e.getMessage()+" ###");
            e.printStackTrace();
           logger.debug(" ### End StackTraceException ###");
        }
        tableView.setItems(data);
        logger.debug("### Data set into TableView ###");
    }


    @FXML
    void onActionMenuBtn(ActionEvent event) {
        if(event.getSource() == btnUsers){
            lblPathIndex.setText("Home/Users");
            pnTable.toFront();
        }
        if(event.getSource() == btnProfile){
            lblPathIndex.setText("Home/Profile");
//            String x = loginController.getTxtEmail();
//            System.out.println(x);
//            labelProfile.setText("Ciao");
            pnProfile.toFront();
        }
        if(event.getSource() == btnChats){
            lblPathIndex.setText("Home/Chats");
            pnChats.toFront();
        }
        if(event.getSource() == btnReports){
            lblPathIndex.setText("Home/Reports");
            pnReports.toFront();
        }
        if(event.getSource() == btnLogs){
            lblPathIndex.setText("Home/Logs");
            pnLogs.toFront();
        }
        if(event.getSource() == btnProjects){
            lblPathIndex.setText("Home/Projects");
            pnProjects.toFront();
        }
        if(event.getSource() == btnTasks){
            lblPathIndex.setText("Home/Tasks");
            pnTasks.toFront();
        }


    }


    @FXML
    void onClickRefreshTable(MouseEvent event) {
        logger.debug("### Refresh Table ###");
//        RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), iconRefreshTable);
//        rotation.setCycleCount(Animation.INDEFINITE);
//        rotation.setByAngle(360);
//        iconRefreshTable.setOnMouseEntered(e -> rotation.play());
//        iconRefreshTable.setOnMouseExited(e -> rotation.pause());
        //iconRefreshTable.setStyle("-fx-text-fill: gray");

        BasicConfigurator.configure();

        tcAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcLastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        tcAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Statement statement;
        try {

            data = FXCollections.observableArrayList();
            statement = conn.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while(queryOutput.next()){
                data.add(new User(queryOutput.getString("Name"),queryOutput.getString("Lastname"), queryOutput.getString("Age"), queryOutput.getString("Address"),queryOutput.getString("Phone"),"N/A",""));
            }

            logger.debug("### Users Data Retrived ###");

        } catch (SQLException e) {
            logger.debug(" ### Exception Occurred: "+e.getMessage()+" ###");
            e.printStackTrace();
            logger.debug(" ### End StackTraceException ###");
        }
        tableView.setItems(data);
        logger.debug("### Data set into TableView ###");
    }

    @FXML
    void topBarOnMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() -xCordinate);
        stage.setY(event.getScreenY()-yCordinate);
    }

    @FXML
    void topBarOnMousePressed(MouseEvent event) {
        this.xCordinate = event.getSceneX();
        this.yCordinate = event.getSceneY();
    }

    @FXML
    void handleButtonClick(ActionEvent event) {
        if(event.getSource() == btnAdd){
                showDialog("addNewUser");
        }
        if(event.getSource() == btnExport){
           // showDialog("addNew");
        }
        if(event.getSource() == btnImport){
            showDialog("import");
        }
    }

    @FXML
    void closeMainWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void iconifiedMainWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onClickConfirmUserConfig(MouseEvent event) {

    }


    private void showDialog(String fxml){
       try {
           Parent loader =  FXMLLoader.load(getClass().getResource("/fxml/"+fxml+".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/icons/iconLogo.png"));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

       }catch (IOException e){
           e.printStackTrace();
       }

    }

    public void setUserLabel(String name,String lastname){
        labelProfile.setText(name+" "+lastname);
    }

    public void setUserConfigurationTxtFields(User u, String email,String password){
        userConfigController.setTxtFields(u, txtName,txtLastname,txtAge,txtAddress, txtPhone, txtEmail,txtPassword, email,password);
    }



}
