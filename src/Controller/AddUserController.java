package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtAction;

    @FXML
    private Button btnAddUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
