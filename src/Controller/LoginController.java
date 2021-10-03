package Controller;

import Utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    Double xCordinate, yCordinate;
    Utility utility = Utility.getUtilityIntance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onActionLogin(ActionEvent event) {

    }

    @FXML
    void onClickGoToSignup(MouseEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
        Scene sc = new Scene(root);

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.mouseTransparentProperty();
        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/icons/iconLogo.png"));
        primaryStage.show();

//        try {
//            Parent loader =  FXMLLoader.load(getClass().getResource("/fxml/signu.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(loader);
//            stage.setScene(scene);
//            stage.getIcons().add(new Image("/icons/iconLogo.png"));
//            stage.setResizable(false);
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.show();
//            Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stageLogin.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
      //  utility.showDialog("import.fxml");

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



}
