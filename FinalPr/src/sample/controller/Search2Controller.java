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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Classes.Help;
import sample.Classes.NewsInformation;
import sample.Classes.NewsStorage;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/27/2018.
 */
public class Search2Controller implements Initializable {


    @FXML
    private TextField NewsNameTf1;
    @FXML
    private TableView tv;
    @FXML
    private TableColumn <NewsInformation, String> Title;
    @FXML
    private TableColumn<NewsInformation, String> Author;
   // @FXML
   // private TableColumn<NewsInformation, String> Type;
    @FXML
    private TableColumn<NewsInformation, String> Content;
    @FXML
    private TableColumn<NewsInformation, String> Datetime;

    @FXML
    private Button searchBt;

    private JCDB db = new JCDB();
    private Help help = new Help();

    //ObservableList used for holding Book values.
    private ObservableList<NewsInformation> newsData;
    private ObservableList<NewsInformation> searchData;
    //ObservableList used for showNewBook method.
    private ObservableList<NewsInformation> show;

    private NewsInformation intro;

    @FXML
    private void handlesearch2backButton (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    private void handleSearch2Action (ActionEvent event) {
        if (NewsNameTf1.getText().isEmpty()) {
            return;
        } else {
            getSearched();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(NewsStorage.getInstance().getNewsTitle());

        NewsNameTf1.setText(NewsStorage.getInstance().getNewsTitle());

        getNewsdata();



        }







    private void getSearched(){
        try {
            Title.getColumns().clear();
            Author.getColumns().clear();
            //Type.getColumns().clear();
            Content.getColumns().clear();
            Datetime.getColumns().clear();

            searchData = FXCollections.observableArrayList();
            ResultSet rs = db.searchingNews(NewsNameTf1.getText());
            while(rs.next()){
                searchData.add(new NewsInformation(
                        rs.getString("NewsTitle"),
                        rs.getString("newsman"),
                       // rs.getInt("CategoryNewsID"),
                        rs.getString("NewsContent"),
                        rs.getString("NewsDateTime")));
            }
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        }

        Title.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsTitle"));
        Author.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("newsman"));
        //Type.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("type"));
        Datetime.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsDateTime"));
        Content.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsContent"));

        tv.setItems(searchData);
    }











    private void getNewsdata(){

        try {
            newsData = FXCollections.observableArrayList();
            ResultSet rs = db.searchingNews(NewsStorage.getInstance().getNewsTitle());

            while(rs.next()){
                System.out.println(rs.getString("NewsTitle"));
                System.out.println(rs.getString("newsman"));
                System.out.println(rs.getString("CategoryNewsID"));
                System.out.println(rs.getDate("NewsDateTime"));
                System.out.println(rs.getString("NewsContent"));
                System.out.println("---------------------------------------------------------------");




                newsData.add(new NewsInformation(
                        rs.getString("NewsTitle"),
                        rs.getString("newsman"),
                        //rs.getInt("CategoryNewsID"),
                        rs.getString("NewsContent"),
                        rs.getString("NewsDateTime")
                        ));

            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }

        Title.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsTitle"));
        Author.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("newsman"));
        //Type.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("type"));
        Datetime.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsDateTime"));
        Content.setCellValueFactory(new PropertyValueFactory<NewsInformation,String>("NewsContent"));

        tv.setItems(newsData);
    }


}













