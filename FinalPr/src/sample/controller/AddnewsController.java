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
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/21/2018.
 */
public class AddnewsController implements Initializable {
    @FXML
    private TextArea ContentArea;
    @FXML
    private TableView tv;
    @FXML
    private TableColumn<NewsInformation, Integer> newsID;
    @FXML
    private TableColumn<NewsInformation, String> newsTitle;
    @FXML
    private TableColumn<NewsInformation,String> NewsDateTime;
    @FXML
    private TableColumn<NewsInformation, String> NewsContent;
    @FXML
    private TableColumn<NewsInformation, Integer> CategoryNewsID;
    @FXML
    private TableColumn<NewsInformation,String> newsman;

    @FXML
    private TextField newstitle,categoryNewsID,newsid,Newsman,newstime;

    @FXML
    private Button saveBt,clearBt,logoutBt,homeBt;

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
    private void saveButton(ActionEvent event) throws SQLException {
        SaveToDatabase();
        refresh();
    }

    private void SaveToDatabase() throws SQLException, NumberFormatException {
        boolean isNewsID = help.isInteger(newsid.getText());
        boolean isCategoryNewsID = help.isInteger(categoryNewsID.getText());

        if(!isNewsID){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("NEWSID IS INVALID");
            alert.show();
        }
        else if(!isCategoryNewsID){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("CATEGORYNEWSID IS INVALID");
            alert.show();
        }
        else if(newstitle.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID NEWS NAME.");
            alert.show();
        }
        else if(Newsman.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID NEWSMAN.");
            alert.show();
        }
        else if(ContentArea.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID CONTENT.");
            alert.show();
        }else if(newstime.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID NEWSTIME.");
            alert.show();
        }
        else{
            int newsId = Integer.valueOf(newsid.getText());
            int categorynewsID= Integer.valueOf(categoryNewsID.getText());
            NewsInformation newsInformation;
            newsInformation = new NewsInformation(
                    newsId,
                    newstitle.getText(),
                    ContentArea.getText(),
                    categorynewsID,
                    Newsman.getText(),
                    newstime.getText()//currentDate()
            );

            db.managerAddNews(newsInformation);
            /*refresh();
            newstitle.clear();
            categoryNewsID.clear();
            newsid.clear();
            Newsman.clear();
            ContentArea.clear();*/
            //publisherCombo.setValue("publisher");
            //typeCombo.setValue("type");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED");
            alert.setContentText("The Data Has Been Saved");
            alert.show();
        }
    }

    private void refresh(){
        NewsData.clear();
        getNewsData();
    }

    @FXML
    private void clearButton(ActionEvent event) {
        newstitle.clear();
        categoryNewsID.clear();
        newsid.clear();
        ContentArea.setText("");
        Newsman.clear();
        newstime.clear();
        //publisherCombo.setValue("publisher");
        //typeCombo.setValue("type");
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getNewsData();

    }
}
