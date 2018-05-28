package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Classes.Longin;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/3/2018.
 */
public class UserpageController implements Initializable {


    @FXML
    private TextField name,username,major;
    @FXML
    private TextField myusername;


    @FXML
    private TextArea showComment;


    private JCDB db = new JCDB();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setEditable(false);
        username.setText(Longin.getInstance().getEmail());

        myusername.setText(Longin.getInstance().getEmail());

        db.readFeedback(username,showComment);

        db.showuserinformation(major,name,username);

    }




    @FXML
    private void handlemypagelogoutButton (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void  handlemypagebackButton (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/FinnalMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
