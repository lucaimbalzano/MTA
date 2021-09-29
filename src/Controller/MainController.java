package Controller;

import Connections.DatabaseConnections;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;

import model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MainController implements Initializable {

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

    private ObservableList<User> data;

    @FXML private Button btnAdd, btnImport, btnExport;

    DatabaseConnections databaseConnections = new DatabaseConnections();
    Connection conn = databaseConnections.getConnection();

    String query = "SELECT * FROM user";

    private static Logger logger = Logger.getLogger(MainController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                data.add(new User(queryOutput.getString("Name"),queryOutput.getString("Lastname"), queryOutput.getString("Age"), queryOutput.getString("Address"),queryOutput.getString("Phone"),"N/A"));
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


    private void showDialog(String fxml){
       try {
           Parent loader =  FXMLLoader.load(getClass().getResource("/fxml/"+fxml+".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader);
            stage.setScene(scene);
           stage.setResizable(false);
           stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

       }catch (IOException e){
           e.printStackTrace();
       }


    }

}
