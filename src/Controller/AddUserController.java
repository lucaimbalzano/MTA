package Controller;

import Service.IUserService;
import Service.UserServiceImpl;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Window;

import model.User;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtAction;

    @FXML
    private Button btnAddMember;

    private static Logger logger = Logger.getLogger(AddUserController.class);
    IUserService userServiceImpl = new UserServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BasicConfigurator.configure();


    }

    @FXML
    void addMemberOnClick(ActionEvent event) {
        Window owner = btnAddMember.getScene().getWindow();
        if(checkAllTxtParameters(owner)){
            logger.debug("User name:"+txtName);
            userServiceImpl.addUser(new User(txtName.getText(),txtLastname.getText(),txtAge.getText(),txtPhone.getText(),txtAddress.getText(),txtAction.getText()));
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Success",
                    "You Added with Success");
        }

        showAlert(Alert.AlertType.ERROR, owner, "Error",
                "Error while Adding the user");
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public Boolean checkAllTxtParameters(Window owner){
        Boolean result = true;
        if (txtName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "MTA[福] || Error Occurred!",
                    "Please enter your name");
            result = false;
        }
        if (txtLastname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "MTA[福] || Error Occurred!",
                    "Please enter your Lastaname");
            result = false;
        }
        if (txtAge.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "MTA[福] || Error Occurred!",
                    "Please enter your age");
            result = false;
        }
        if (txtPhone.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "MTA[福] || Error Occurred!",
                    "Please enter your phone");
            result = false;
        }
        if (txtAddress.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "MTA[福] || Error Occurred!",
                    "Please enter your address");
            result = false;
        }
        if (txtAction.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "MTA[福] || Error Occurred!",
                    "Please enter your action");
            result = false;
        }
        logger.debug("### Result CheckTxts:"+result+" ###");
        return result;
    }
}


