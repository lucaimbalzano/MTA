package Controller;

import Service.IUserService;
import Service.UserServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

    private static Logger logger = Logger.getLogger(ImportController.class);
    IUserService userServiceImpl = new UserServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BasicConfigurator.configure();

    }

    @FXML
    void onClickedimportCSV(MouseEvent event)  {
        logger.info("### Inside onClickedimportCSV() ###");
        userServiceImpl.importUserFromExcel("");

    }
}
