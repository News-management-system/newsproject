package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Classes.Help;
import sample.Classes.NewsCommentary;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/21/2018.
 */
public class ManagerpageDeleteCommentController implements Initializable {

    @FXML
    private TableColumn<NewsCommentary, Integer> CommentaryID;
    @FXML
    private TableView tv1;
    @FXML
    private TableColumn<NewsCommentary, Integer> NewsID;
    @FXML
    private TableColumn<NewsCommentary, String> Commentator;
    @FXML
    private TableColumn<NewsCommentary, String> commentaryDateTime;
    @FXML
    private TableColumn<NewsCommentary, String> commentaryContent;


    private JCDB db = new JCDB();
    private Help help  = new Help();

    //ObservableList used for holding Book values.
    private ObservableList<NewsCommentary> commentaryData;
    // private ObservableList<NewsInformation> searchData;
    //ObservableList used for showNewBook method.
    private ObservableList<NewsCommentary> show;



    private void getCommentaryData(){
        try {
            commentaryData = FXCollections.observableArrayList();
            ResultSet rs = db.retrieveAllcomment();

            while(rs.next()){
                System.out.println(rs.getInt("CommentaryID"));
                System.out.println(rs.getInt("NewsID"));
                System.out.println(rs.getString("commentaryContent"));
                System.out.println(rs.getString("Commentator"));
                System.out.println(rs.getString("commentaryDateTime"));

                commentaryData.add(new NewsCommentary(
                        rs.getInt("CommentaryID"),
                        rs.getInt("NewsID"),
                        rs.getString("commentaryContent"),
                        rs.getString("Commentator"),
                        rs.getString("commentaryDateTime")));
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
        CommentaryID.setCellValueFactory(new PropertyValueFactory<NewsCommentary,Integer>("CommentaryID"));
        NewsID.setCellValueFactory(new PropertyValueFactory<NewsCommentary,Integer>("NewsID"));
        commentaryDateTime.setCellValueFactory(new PropertyValueFactory<NewsCommentary,String>("commentaryDateTime"));
        Commentator.setCellValueFactory(new PropertyValueFactory<NewsCommentary,String>("Commentator"));
        commentaryContent.setCellValueFactory(new PropertyValueFactory<NewsCommentary,String>("commentaryContent"));

        tv1.setItems(commentaryData);
    }
    @FXML
    private void deleteButton(ActionEvent event){
        ObservableList<NewsCommentary> CommentSelect, allComment;
        allComment = tv1.getItems();
        CommentSelect = tv1.getSelectionModel().getSelectedItems();

        if(CommentSelect.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a comment.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this comment?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){

                db.ManagerDeleteComment(CommentSelect.get(0).getCommentaryContent()); //remove book to database
                CommentSelect.forEach(allComment::remove); //remove book to table view.
            }
            else{
                alert.close();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCommentaryData();
    }
    @FXML
    private void logoutButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/LoginManagerpage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void homeButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Managerpage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
