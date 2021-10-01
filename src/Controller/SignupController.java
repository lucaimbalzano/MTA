package Controller;

import Encryptor.Encryptor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
import java.util.HashMap;
import java.util.ResourceBundle;

public class SignupController implements Initializable{


    @FXML
    private TextField lastnameTextField, nameTextField, phoneTextField, addressTextField, passwordTextField;

    @FXML
    private TextField ageTextField, actionTextField, confirmPasswordTextField,prefixTextField, emailTextField;

    @FXML
    private Button btnLogin, btnSigupTo;


    File file = new File("data.csv");

    //Map containing <Username, Password>
    HashMap<String, String> loginInfo = new HashMap<>();

    Encryptor encryptor = new Encryptor();

    Double xCordinate, yCordinate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onClickedReturnToLogin(MouseEvent event) {
        try {
            Parent loader =  FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/icons/iconLogo.png"));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Stage stageSignup = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageSignup.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void onClickedCloseSignup(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void onDraggedSignup(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() -xCordinate);
        stage.setY(event.getScreenY()-yCordinate);
    }

    @FXML
    void onPressedSignup(MouseEvent event) {
        this.xCordinate = event.getSceneX();
        this.yCordinate = event.getSceneY();

    }


    private void writeToFile() throws IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String username = emailTextField.getText();
        String password = getPassword();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));

        writer.write(username + "," + encryptor.encryptString(password) + "\n");
        writer.close();
    }

        private String getPassword(){
            if(passwordTextField.isVisible()){
                return passwordTextField.getText();
            } else {
                //return hiddenPasswordTextField.getText();
            }
            return null;
        }
}
