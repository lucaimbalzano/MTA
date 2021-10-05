package Controller;

import Service.IUserService;
import Service.UserServiceImpl;
import Utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportController implements Initializable {

    @FXML
    Button btnImport;

    @FXML
    Label lblPath;

    Double x, y;
    private static Logger logger = Logger.getLogger(ImportController.class);
    IUserService userServiceImpl = new UserServiceImpl();
    FileChooser fileChooser = new FileChooser();
    Utility utility = Utility.getUtilityIntance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BasicConfigurator.configure();

    }

    @FXML
    void onClickedimportCSV(MouseEvent event)  {
        Window owner = btnImport.getScene().getWindow();
        logger.info("### Inside onClickedimportCSV() ###");
        if(!lblPath.getText().isEmpty())
        {userServiceImpl.importUserFromExcel(lblPath.getText());}
        else
        {utility.showAlertError(Alert.AlertType.ERROR, owner, "⛔ MTA[福] || Error Occurred!",
                "Please enter a valid Path! ⛔",event);}

    }

    @FXML
    void IconifiedWindowImport(MouseEvent event) {
        logger.info("### Window Import.FXML iconified ###");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void closeWindowImport(MouseEvent event) {
        logger.info("### Window Import.FXML closed ###");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void onClickedGetPathFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.setInitialFileName("myExcel");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
//                new FileChooser().ExtensionFilter("CSV", "*.csv");
        try{
            File file = fileChooser.showOpenDialog(stage);
            String pathChoosen = file.getPath();
            lblPath.setText(pathChoosen);
        }catch(Exception e){
            logger.error(" ### Exception Occured : "+e.getMessage()+" ###");
            e.printStackTrace();
            logger.error(" ###  End ExceptionStackTrace  ###");
        }

    }

    @FXML
    void onMouseDraggedImport(MouseEvent event) {
        utility.setOnMouseDragged(event,x,y);
    }

    @FXML
    void onMousePressedImport(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


}
