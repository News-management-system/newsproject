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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Classes.Help;
import sample.Classes.NewsInformation;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeletenewsController implements Initializable {
    @FXML
    private TextArea ContentArea;
    @FXML
    private TableView tv;
    @FXML
    private TableColumn<NewsInformation, Integer> newsID;
    @FXML
    private TableColumn<NewsInformation, String> newsTitle;
    @FXML
    private TableColumn<NewsInformation, String> NewsDateTime;
    @FXML
    private TableColumn<NewsInformation, String> NewsContent;
    @FXML
    private TableColumn<NewsInformation, Integer> CategoryNewsID;
    @FXML
    private TableColumn<NewsInformation,String> newsman;



    private JCDB db = new JCDB();
    private Help help  = new Help();

    //ObservableList used for holding Book values.
    private ObservableList<NewsInformation> NewsData;
    // private ObservableList<NewsInformation> searchData;
    //ObservableList used for showNewBook method.
    private ObservableList<NewsInformation> show;

    private void getNewsData(){
        try {
            NewsData = FXCollections.observableArrayList();
            ResultSet rs = db.retrieveAllNews();

            while(rs.next()){
                System.out.println(rs.getInt("newsID"));
                System.out.println(rs.getString("NewsTitle"));
                System.out.println(rs.getString("NewsContent"));
                System.out.println(rs.getInt("CategoryNewsID"));
                System.out.println(rs.getString("newsman"));
                System.out.println(rs.getString("NewsDateTime"));
                NewsData.add(new NewsInformation(
                        rs.getInt("newsID"),
                        rs.getString("NewsTitle"),
                        rs.getString("NewsContent"),
                        rs.getInt("CategoryNewsID"),
                        rs.getString("newsman"),
                        rs.getString("NewsDateTime")));
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }

        newsID.setCellValueFactory(new PropertyValueFactory<NewsInformation,Integer>("newsID"));
        newsTitle.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("newsTitle"));
        NewsDateTime.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsDateTime"));
        NewsContent.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsContent"));
        CategoryNewsID.setCellValueFactory(new PropertyValueFactory<NewsInformation,Integer>("CategoryNewsID"));
        newsman.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("newsman"));


        tv.setItems(NewsData);
    }
    @FXML
    private void deleteButton(ActionEvent event){
        ObservableList<NewsInformation> newsSelect, allNews;
        allNews = tv.getItems();
        newsSelect = tv.getSelectionModel().getSelectedItems();

        if(newsSelect.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a new.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this new?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){

                db.ManagerDeleteNews(newsSelect.get(0).getNewsTitle()); //remove book to database
                newsSelect.forEach(allNews::remove); //remove book to table view.
            }
            else{
                alert.close();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getNewsData();
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
