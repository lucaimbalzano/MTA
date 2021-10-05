package Controller;

import Encryptor.Encryptor;
import Service.UserServiceImpl;
import Utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.management.openmbean.InvalidKeyException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController implements Initializable {


    @FXML
    private TextField txtLastname, txtName,txtPrefix, txtPhone, txtAddress, txtAge , txtAction, txtEmail;

    @FXML
    private Button btnReturnLogin,btnSaveSignup;

    @FXML
    private PasswordField hiddenPasswordTextField,hiddenConfirmPasswordTextField;

    @FXML
    public TextField passwordTextField,confirmPasswordTextField;

    @FXML
    private CheckBox showPassword;

    Double x, y;
    Utility utility = Utility.getUtilityIntance();
    File file = new File("data.csv");
    Encryptor encryptor = new Encryptor();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public final String FORMAT_EMAIL = "\n"+" ● Uppercase and lowercase English letters (a-z, A-Z)"+"\n"+" ● Characters valid: . _ + -"+"\n"+" ● Example: example01@domain.com";
    public final String PREFIX_$RGX = "^[+]+[0-9]+$";
    public final String LENGTH_$RGX ="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    public final String AGE_$RGX = "^(?:[1-9][0-9]?|1[01][0-9]|140)$";

    UserServiceImpl userServiceImpl = new UserServiceImpl();


    private static Logger logger = Logger.getLogger(RegistrationController.class);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BasicConfigurator.configure();

        passwordTextField.setVisible(false);
        confirmPasswordTextField.setVisible(false);



    }

    @FXML
    void IconifiedWindowRegistration(MouseEvent event) {
        logger.info("### Window Registration.FXML iconified ###");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void closeWindowRegistration(MouseEvent event)  throws IOException {
        logger.info("### Window Registration.FXML closed###");
        //close signup window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickedGetPathFile(ActionEvent event) {

    }

    @FXML
    void onClickedReturnOnLogin(MouseEvent event) {
        logger.info("### Window Registration.FXML closed && Login.FXML opened ###");
        //open login window
        Window owner = btnReturnLogin.getScene().getWindow();
        if(event.getSource() == btnReturnLogin){
            utility.showDialog("login");}
        else{
            utility.showAlertError(Alert.AlertType.ERROR, owner, "⛔ MTA[福] || Error Occurred!",
                    "Error while closing the window, button not recognized! ⛔",event);
        }

        //close signup window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onMouseDraggedRegistration(MouseEvent event) {
        utility.setOnMouseDragged(event,x,y);
    }

    @FXML
    void onMousePressedRegistration(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void changeVisibility(ActionEvent event) {
        if (showPassword.isSelected()) {

            confirmPasswordTextField.setText(hiddenConfirmPasswordTextField.getText());
            confirmPasswordTextField.setVisible(true);
            passwordTextField.setText(hiddenPasswordTextField.getText());
            hiddenConfirmPasswordTextField.setVisible(false);
            passwordTextField.setVisible(true);
            hiddenPasswordTextField.setVisible(false);
            return;
        }
        hiddenPasswordTextField.setText(passwordTextField.getText());
        hiddenPasswordTextField.setVisible(true);
        passwordTextField.setVisible(false);
        hiddenConfirmPasswordTextField.setText(confirmPasswordTextField.getText());
        hiddenConfirmPasswordTextField.setVisible(true);
        confirmPasswordTextField.setVisible(false);
    }

    @FXML
    void createAccount(ActionEvent event) throws IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        writeToFile(event);
    }


    //Utility Methods

    private String getPassword(){
        if(passwordTextField.isVisible()){
            return passwordTextField.getText();
        } else {
            return hiddenPasswordTextField.getText();
        }
    }

    private void writeToFile(ActionEvent event) throws NoSuchPaddingException,  IllegalBlockSizeException , BadPaddingException ,InvalidAlgorithmParameterException {
        Window owner = btnSaveSignup.getScene().getWindow();
        String email = txtEmail.getText();
        String password = getPassword();
        if(checkTxtFields(owner, event)) {
            if (validate(email)) {
                if (checkPasswords()) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write(email + "," + encryptor.encryptString(password) + "\n");
                        logger.debug(" ### file wrote with MD5: " + encryptor.encryptString(password) + "  ###");
                        writer.close();
                       // userServiceImpl.signupUser(utility.setNewUserByTxtForm(txtLastname,txtName,txtPrefix,txtPhone,txtAddress,txtAge,txtAction),getPassword(),txtEmail.getText());
                        userServiceImpl.signupUser(new User(txtName.getText(),txtLastname.getText(),txtAge.getText(),txtPrefix.getText()+txtPhone.getText(),txtAddress.getText(),txtAction.getText()),getPassword(),txtEmail.getText());
                    } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
                        logger.error(" ### Exception occurred: " + e.getMessage() + " ###");
                        e.printStackTrace();
                        logger.error("### End Exception StackTrace ###");
                    }
                } else {
                    utility.showAlertErrorActionEvent(Alert.AlertType.ERROR, owner, "MTA[❌]          Error Occurred!",
                            "Passwords doesn't match! ⛔", event);
                }
            } else {
                utility.showAlertErrorActionEvent(Alert.AlertType.ERROR, owner, "MTA[❌]          Error Occurred!",
                        "Email format not valid! ⛔" + "\n" + "Format valid: " + FORMAT_EMAIL, event);
            }
        }

 }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private Boolean checkPasswords(){
        if(passwordTextField.isVisible()){
            if(passwordTextField.getText().equals(confirmPasswordTextField.getText()))
                return true;
        } else {
            if(hiddenPasswordTextField.getText().equals(hiddenConfirmPasswordTextField.getText()))
                return true;
        }
        return false;
    }


    private Boolean checkTxtFields(Window owner, ActionEvent event){
        Pattern pattern_PREFIX_$RGX = Pattern.compile(PREFIX_$RGX, Pattern.CASE_INSENSITIVE);
        Pattern pattern_LENGTH_$RGX = Pattern.compile(LENGTH_$RGX, Pattern.CASE_INSENSITIVE);
        Pattern pattern_AGE_$RGX = Pattern.compile(AGE_$RGX, Pattern.CASE_INSENSITIVE);

      //  Matcher matcher_LENGTH_$RGX = pattern_LENGTH_$RGX.matcher(txtPrefix.getText());
        Matcher matcher = pattern_PREFIX_$RGX.matcher(txtPrefix.getText());
        boolean match = matcher.find();
        if(match) {
            matcher = pattern_LENGTH_$RGX.matcher(txtPhone.getText());
            if(matcher.find())
            {
                matcher = pattern_AGE_$RGX.matcher(txtAge.getText());
                if(matcher.find()){
                    return true;
                }
                else{
                    utility.showAlertErrorActionEvent(Alert.AlertType.ERROR, owner, "MTA[❌]          Error Occurred!",
                            "Age format not valid! ⛔"+"\n"+"Format valid: 1-140",event);
                    return false;
                }
            }
            else{
                utility.showAlertErrorActionEvent(Alert.AlertType.ERROR, owner, "MTA[❌]          Error Occurred!",
                        "Phone format not valid! ⛔"+"\n"+"Format valid: [0-9] > 20 numbers",event);
            return false;
            }
        }else{
            utility.showAlertErrorActionEvent(Alert.AlertType.ERROR, owner, "MTA[❌]          Error Occurred!",
                    "Prefix format not valid! ⛔"+"\n"+"Format valid: +[09]",event);
            return false;}
    }
}
