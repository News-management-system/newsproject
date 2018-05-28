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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Classes.Help;
import sample.Classes.Newinformation;
import sample.Classes.NewsInformation;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/3/2018.
 */
public class MenuController implements Initializable {
    @FXML private TableView viewNews;
    @FXML private TableColumn<Newinformation, String> newsTitle;
    @FXML private TableColumn<Newinformation,String> date1;

    @FXML private TableView viewSchool;
    @FXML private TableColumn<Newinformation,String> schoolTitle;
    @FXML private TableColumn<Newinformation,String> date2;

    @FXML private TableView viewResult;
    @FXML private TableColumn<Newinformation,String> resultTitle;
    @FXML private TableColumn<Newinformation,String> date3;

    @FXML private TableView viewSport;
    @FXML private TableColumn<Newinformation,String> sportTitle;
    @FXML private TableColumn<Newinformation,String> date4;

    @FXML private Button readNews;

    private JCDB db = new JCDB();
    private Help help  = new Help();
    private ObservableList<Newinformation> NewsData;
    private ObservableList<Newinformation> SportData;

    private void getNewsData(){
        try {
            NewsData = FXCollections.observableArrayList();
            ResultSet rs = db.retrieveOneNewsTitle();

            while(rs.next()){
                System.out.println(rs.getString("newsTitle"));
                System.out.println(rs.getString("NewsDateTime"));

                NewsData.add(new Newinformation(
                        rs.getString("newsTitle"),
                        rs.getString("NewsDateTime")));
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }

        newsTitle.setCellValueFactory(new PropertyValueFactory<Newinformation,String>("newsTitle"));
        date1.setCellValueFactory(new PropertyValueFactory<Newinformation,String>("NewsDateTime"));
        viewNews.setItems(NewsData);
    }
    private void getSportData(){
        try {
            SportData = FXCollections.observableArrayList();
            ResultSet rs = db.retrieveFNewsTitle();

            while(rs.next()){
                System.out.println(rs.getString("newsTitle"));
                System.out.println(rs.getString("NewsDateTime"));

                SportData.add(new Newinformation(
                        rs.getString("newsTitle"),
                        rs.getString("NewsDateTime")));
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }

        sportTitle.setCellValueFactory(new PropertyValueFactory<Newinformation,String>("newsTitle"));
        date4.setCellValueFactory(new PropertyValueFactory<Newinformation,String>("NewsDateTime"));
        viewSport.setItems(SportData);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getNewsData();
        getSportData();

    }

    @FXML
    private void handleuserButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void handlemanagerButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/LoginManagerpage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    private void handlesearchButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Search.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void readNews1Button(ActionEvent event) throws IOException {
        ObservableList<NewsInformation> newsSelect, allNews;
        allNews = viewNews.getItems();
        newsSelect = viewNews.getSelectionModel().getSelectedItems();
        if(newsSelect.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a new.");
            alert.showAndWait();
        }else{
            //ResultSet rs1 = db.retrieveOneNew(newsSelect.get(0).getNewsTitle());
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/News1.fxml"));
            stage.setTitle("News system");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();


    }
    }

    /**
     * Created by FU YUANQING on 5/21/2018.
     */

}


