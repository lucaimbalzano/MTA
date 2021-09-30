package Service;

import Connections.DatabaseConnections;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements IUserService {
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Boolean addUser(User user) {
        BasicConfigurator.configure();
        String preparedSql = "INSERT INTO USER (Name,Lastname,Age,Address,Phone,Action) VALUES (?,?,?,?,?,?)";
//        user.getName(),user.getLastname(),user.getAddress(), user.getPhone(),user.getAge(), user.getAction()
        int result = 0;
        int cont = 0 ;
        PreparedStatement preparedStmt = null;
        DatabaseConnections databaseConnections = new DatabaseConnections();
        try {
                Connection conn = databaseConnections.getConnection();
                //Creates a PreparedStatement object for sending parameterized SQL statements to the database
                preparedStmt = conn.prepareStatement(preparedSql);
                preparedStmt.setString(++cont, user.getName());
                preparedStmt.setString(++cont, user.getLastname());
                preparedStmt.setInt(++cont, Integer.parseInt(user.getAge()));
                preparedStmt.setString(++cont, user.getAddress());
                preparedStmt.setString(++cont, user.getPhone());
                preparedStmt.setString(++cont, user.getAction());
                result = preparedStmt.executeUpdate();
                if (result > 0) {
                    logger.debug("### User Added Successfully ###");
                    conn.close();
                    return true;
                }
            conn.close();

        } catch (Exception e) {
            logger.debug("### Error occured inside addUser(): " + e.getMessage() + " ###");
            e.printStackTrace();
            logger.debug("### End stackTraceError ###");
        }
        logger.debug("### Error while adding the User ###");

        return false;
    }

    @Override
    public void importUserFromExcel(String path) {
        BasicConfigurator.configure();
        String preparedSql = "INSERT INTO USER (Name,Lastname,Age,Address,Phone,Action) VALUES (?,?,?,?,?,?)";
//        user.getName(),user.getLastname(),user.getAddress(), user.getPhone(),user.getAge(), user.getAction()
        int result = 0;
        PreparedStatement preparedStmt = null;
        DatabaseConnections databaseConnections = new DatabaseConnections();
        try {
                 Connection conn = databaseConnections.getConnection();
                //Creates a PreparedStatement object for sending parameterized SQL statements to the database
                preparedStmt = conn.prepareStatement(preparedSql);

                //Import and creation Excel to work on
                path = "C:/Users/Lucas/Desktop/Members.xlsx";
             //   path = URLEncoder.encode(path, "UTF-8");
                FileInputStream fileIn = new FileInputStream(new File(path));
                XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Row row;
                for(int i=1; i<= sheet.getLastRowNum(); i++){
                    row = sheet.getRow(i);
                    preparedStmt.setString(1, row.getCell(0).getStringCellValue());
                    preparedStmt.setString(2, row.getCell(1).getStringCellValue());
                    preparedStmt.setInt(3, (int)row.getCell(2).getNumericCellValue());
                    preparedStmt.setString(4, row.getCell(3).getStringCellValue());
                    preparedStmt.setString(5, row.getCell(4).getStringCellValue());
                    preparedStmt.setString(6, row.getCell(5).getStringCellValue());
                    preparedStmt.execute();
                }
                logger.debug("### Data Imported from Excel File ###");
                //Alert notification
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("✅ Import Dialog");
                alert.setHeaderText(null);
                alert.setContentText(" Members Imported From Excel Sheet to Database! ✅ ");
                alert.showAndWait();

                workbook.close();
                fileIn.close();
                preparedStmt.close();


        } catch (NullPointerException npe) {
            printStackTrace(npe);
        }catch(FileNotFoundException fe )
        {
           printStackTrace(fe);
        }catch( IOException ioe){
           printStackTrace(ioe);
        }catch(SQLException se){
            printStackTrace(se);
        }
    }

    private void printStackTrace(Exception e){
        logger.fatal("### Error occured inside importUserFromExcel(): " + e.getMessage() + " ###");
        e.printStackTrace();
        logger.fatal("### End stackTraceError ###");
    }

}
