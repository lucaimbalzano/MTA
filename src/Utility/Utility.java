package Utility;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    public static void showAlertError(Alert.AlertType alertType, Window owner, String title, String message,MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(
                new Image("⛔"));
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void getSetOnMouseDragged(MouseEvent e, Double x , Double y){
        setOnMouseDragged(e,x,y);
    }
}

