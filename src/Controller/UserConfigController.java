package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;

public class UserConfigController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnConfirm;


    public void setTxtFields(User u,TextField txtName,
                                    TextField txtLastname,
                                    TextField txtAge,
                                    TextField txtAddress,
                                    TextField txtPhone,
                                    TextField txtEmail,
                                    TextField txtPassword,String email,String password){
        txtName.setText(u.getName());
        txtLastname.setText(u.getLastname());
        txtAge.setText(u.getAge());
        txtAddress.setText(u.getAddress());
        txtPhone.setText(u.getPhone());
        txtEmail.setText(email);
        txtPassword.setText(password);

    }
}
