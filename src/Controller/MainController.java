package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcLastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        tcAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));

        data = FXCollections.observableArrayList(
                new User("Malcom", "Ericson", "34", "34, Major Street NYC", "4350987696","Call"),
                new User("Mark", "Maricson", "14", "24, Major Street NYC", "0750987696","ToDo"),
                new User("Melman", "Ern", "34", "29, Major Street CG", "7050987696","Call"),
                new User("Marika", "Carricson", "24", "34, Major Street OH", "7050987096","ToDo")
        );
        tableView.setItems(data);


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
