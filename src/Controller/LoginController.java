package Controller;

import Encryptor.Encryptor;
import Service.UserServiceImpl;
import Session.UserSession;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {

    @FXML
    private TextField txtEmail,txtPassword;

    @FXML
    private Button btnLogin,btnSigupTo;

    @FXML
    private PasswordField pfPasswordLogin;

    @FXML
    private CheckBox showPassword;

    Double xCordinate, yCordinate;
    Utility utility = Utility.getUtilityIntance();
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    Encryptor encryptor = new Encryptor();

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPassword.toFront();
        txtPassword.setVisible(false);
    }

    @FXML
    void onActionLogin(ActionEvent event) {
        Window owner = btnLogin.getScene().getWindow();
        try{
            if(checkPasswords(owner,event)) {
                logger.debug("### Login user: "+txtEmail.getText()+" Success ###");

               // Load Main.FXML
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainController.class.getResource("/fxml/main.fxml"));
                GridPane root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.getIcons().add(new Image("/icons/iconLogo.png"));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

                //Set profileName
                MainController mainController = loader.getController();
                Integer id = userServiceImpl.getUserIdByEmail(txtEmail.getText());
                User user = userServiceImpl.getUserById(id);
                mainController.setUserLabel(user.getName(),user.getLastname());
                mainController.setUserConfigurationTxtFields(user,txtEmail.getText(),getPassword());
                //Close window after 1,5 sec
                Thread.sleep(1500);
                Stage stageLogin = (Stage) owner.getScene().getWindow();
                stageLogin.close();
            }else{
                logger.error("### Login user: "+txtEmail.getText()+" Error ###");
            }
        }catch (Exception e){
            logger.error("### Exception Occurred: "+e.getMessage()+" ###");
            e.printStackTrace();
            logger.error("### End StackTrace Exception ###");
        }
    }

    @FXML
    void onClickGoToSignup(MouseEvent event) throws IOException {
        //Open signup window
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
        Scene sc = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.mouseTransparentProperty();
        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/icons/iconLogo.png"));
        primaryStage.show();
        //Close login window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onMouseDraggedLogin(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() -xCordinate);
        stage.setY(event.getScreenY()-yCordinate);
    }

    @FXML
    void onMousePressedLogin(MouseEvent event) {
        this.xCordinate = event.getSceneX();
        this.yCordinate = event.getSceneY();
    }

    @FXML
    void onMouseClickedCloseWindowLogin(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private String getPassword(){
        if(txtPassword.isVisible()){
            return txtPassword.getText();
        } else {
            return pfPasswordLogin.getText();
        }
    }


    @FXML
    void changeVisibility(ActionEvent event) {
        if (showPassword.isSelected()) {
            txtPassword.setText(pfPasswordLogin.getText());
            txtPassword.setVisible(true);
            pfPasswordLogin.setVisible(false);
            return;
        }
            pfPasswordLogin.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            pfPasswordLogin.setVisible(true);
    }

    private Boolean checkPasswords(Window owner,ActionEvent event) throws NoSuchAlgorithmException {
        String password = userServiceImpl.getPasswordFromEmail(txtEmail.getText());
        if(password!=null){
            if(txtPassword.isVisible()) {
                if(encryptor.encryptString(txtPassword.getText()).equals(password)){
                    logger.debug("### Login check txtPassword Success ###");
                    return true;
                }else{
                    logger.error("### Login check txtPassword doesn't match ###");
                    return false;
                }
            }else{
                if(encryptor.encryptString(pfPasswordLogin.getText()).equals(password)){
                    logger.debug("### Login check PasswordField Success ###");
                    return true;
                }else{
                    logger.error("### Login check PasswordField doesn't match ###");
                    return false;
                }
            }
        }else{
            utility.showAlertErrorActionEvent(Alert.AlertType.ERROR, owner, "MTA[❌]          Error Occurred!",
                    "Password with this email doesn't exist! ⛔", event);
            return false;
        }
    }

    public String getTxtEmail(){
        return txtEmail.getText();
    }


}
