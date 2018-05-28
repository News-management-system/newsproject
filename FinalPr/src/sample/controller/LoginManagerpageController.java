package sample.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Classes.Longin;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/3/2018.
 */
public class LoginManagerpageController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button managerlogin;

    @FXML
    private void handlemanagerloginButton(ActionEvent event) throws IOException, SQLException {
        JCDB jcdb = new JCDB();
        String Memail = email.getText();
        String Mpassword = password.getText();
        int id = jcdb.Administrator(Memail, Mpassword);
        if (id == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing information");
            alert.setContentText("user name or password");
            alert.showAndWait();
        } else {
            Longin.getInstance().setEmail(Memail);
            Longin.getInstance().setPassword(Mpassword);
            Longin.getInstance().getLoginid(id);
            System.out.println(Memail);
            System.out.println(Mpassword);
            System.out.println(id);
            visitManager(event);
            /*Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Managerpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/
        }
    }

    private void visitManager(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Managerpage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void handlemanagerbackButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
