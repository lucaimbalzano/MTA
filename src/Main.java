import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Main extends Application {
    private static Logger logger = Logger.getLogger(Main.class);
    double x, y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        logger.debug("### Application  MTA福 Started ###");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene sc = new Scene(root);

      primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.mouseTransparentProperty();
        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/icons/iconLogo.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        logger.debug("### Application  MTA福 Terminated ###");
    }
}
