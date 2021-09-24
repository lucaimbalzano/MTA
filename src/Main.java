import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    double x, y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene sc = new Scene(root);

       primaryStage.initStyle(StageStyle.UNDECORATED);

        //Move the page around the screen
        root.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX());
            primaryStage.setY(mouseEvent.getScreenY());
        });
        root.mouseTransparentProperty();
        primaryStage.setScene(sc);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
