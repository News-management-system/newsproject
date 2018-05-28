package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Classes.Longin;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/2/2018.
 */
public class LoginController implements Initializable {
    @FXML
    private TextField Useremail;
    @FXML
    private TextField Userpassword;


    @FXML
    private void handleusersignButton(ActionEvent event) throws IOException, SQLException {
        JCDB jcdb = new JCDB();
        String Uemail = Useremail.getText();
        String Upassword = Userpassword.getText();
        int id = jcdb.User(Uemail, Upassword);
        if (id == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing information");
            alert.setContentText("user name or password");
            alert.showAndWait();
        } else {
            Longin.getInstance().setEmail(Uemail);
            Longin.getInstance().setPassword(Upassword);
            Longin.getInstance().setLoginid(id);
            System.out.println(Uemail);
            System.out.println(Upassword);
            System.out.println(id);
            visitUser(event);
        /*Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Managerpage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        }
    }

    private void visitUser(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/FinnalMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleuserbackButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*@FXML
    private void handleusersignButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/FinnalMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}