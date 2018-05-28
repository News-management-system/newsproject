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
import sample.Classes.NewsStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/27/2018.
 */
public class SearchController implements Initializable {

    @FXML
    private TextField searchField;


    @FXML
    public void handlesearchnewsButton(ActionEvent event) throws IOException {
        if (searchField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Search Field cannot be empty");
            alert.showAndWait();
        }else {
            NewsStorage.getInstance().setNewsTitle(searchField.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/Search2.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

        @FXML
    private void handlesearchbackButton (ActionEvent event) throws IOException {
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


