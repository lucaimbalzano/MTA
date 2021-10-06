package Controller;

import Service.IUserService;
import Service.UserServiceImpl;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import model.User;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    Double xCordinate, yCordinate;

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
            logger.debug("User name:"+txtName.getText());
           if(userServiceImpl.addUser(new User(txtName.getText(),txtLastname.getText(),txtAge.getText(),txtPhone.getText(),txtAddress.getText(),txtAction.getText(),""))){
               showAlert(Alert.AlertType.CONFIRMATION, owner, "Success","You Added with Success");
                txtName.setText("");
                txtLastname.setText("");
                txtAction.setText("");
                txtAddress.setText("");
                txtAge.setText("");
                txtPhone.setText("");
                owner.getOnCloseRequest();
           }
            else{
               showAlert(Alert.AlertType.ERROR, owner, "Error",
                       "Error while Adding the user");
           }

        }


    }
    @FXML
    void closeWindowAddMemberR(MouseEvent event) {
        logger.info("### Window AddNewMember closed ###");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void minusWindowAddMemberL(MouseEvent event) {
        logger.info("### Window AddNewMember iconified ###");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
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


