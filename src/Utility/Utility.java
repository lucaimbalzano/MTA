package Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.User;

import java.io.IOException;

public class Utility {


    public static Utility u = null;

    private Utility(){}

    public static Utility getUtilityIntance(){
        if(u == null)
            return u = new Utility();

        return u;
    }

    @FXML
    public static void setOnMousePressed(MouseEvent e, Double x, Double y){
        x = e.getSceneX();
        y = e.getSceneY();
    }

    @FXML
    public static void setOnMouseDragged(MouseEvent event,Double x, Double y) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() -x);
        stage.setY(event.getScreenY()-y);
    }

    public void getSetOnMouseDragged(MouseEvent e, Double x , Double y){
        setOnMouseDragged(e,x,y);
    }

    public User setNewUserByTxtForm(TextField txtLastname,TextField txtName,TextField txtPrefix,TextField txtPhone,TextField txtAddress,TextField txtAge ,TextField txtAction){
        User user = new User();
        user.setName(txtName.getText());
        user.setLastname(txtLastname.getText());
        user.setPhone(txtPrefix.getText()+txtPhone.getText());
        user.setAction(txtAction.getText());
        user.setAddress(txtAddress.getText());
        user.setAge(txtAge.getText());
        return user;
    }

    /*
    *
    *  SHOW ALERTS
    *
     */

    public static void showAlertError(Alert.AlertType alertType, Window owner, String title, String message,MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void showAlertErrorPublic(Alert.AlertType alertType, Window owner, String title, String message,MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void showAlertErrorActionEvent(Alert.AlertType alertType, Window owner, String title, String message, ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void showDialog(String fxml){
        try {
            Parent loader =  FXMLLoader.load(getClass().getResource("/fxml/"+fxml+".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/icons/iconLogo.png"));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }


    }


}

