package Controller;

import Encryptor.Encryptor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

public class SignupController implements Initializable


    @FXML
    private TextField lastnameTextField, nameTextField, phoneTextField, addressTextField, passwordTextField;

    @FXML
    private TextField ageTextField, actionTextField, confirmPasswordTextField,prefixTextField, emailTextField;

    @FXML
    private Button btnLogin, btnSigupTo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onClickedReturnToLogin(MouseEvent event) {

    }

    @FXML
    void onClickedSignup(MouseEvent event) {

    }

        File file = new File("data.csv");

        //Map containing <Username, Password>
        HashMap<String, String> loginInfo = new HashMap<>();

        Encryptor encryptor = new Encryptor();

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
                return hiddenPasswordTextField.getText();
            }
        }
}
