package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin,btnSigupTo;

    @FXML
    private PasswordField txtPasswordLogin;

    @FXML
    private CheckBox showPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onActionLogin(ActionEvent event) {

    }

    @FXML
    void onClickedGoToSignup(MouseEvent event) {

    }



}
