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
import sample.Classes.User;
import sample.Data.JCDB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/13/2018.
 */
public class ManagerpageDeleteuserController implements Initializable{

    @FXML
    private TableView<User> tv;
    @FXML
    private TableColumn<User, Integer> UserID;
    @FXML
    private TableColumn<User, String> Name;
    //@FXML
    //private TableColumn<NewsInformation, LocalDate> NewsDateTime;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, String> Password;
    @FXML
    private TableColumn<User,String> Major;



    private JCDB db = new JCDB();
    private Help help  = new Help();

    //ObservableList used for holding Book values.
    private ObservableList<User> UserData;
    // private ObservableList<NewsInformation> searchData;

    private void getUserData(){
        try {
            UserData = FXCollections.observableArrayList();
            ResultSet rs = db.retrieveAllUser();

            while(rs.next()){
                System.out.println(rs.getInt("userID"));
                System.out.println(rs.getString("name"));
//                //System.out.println(rs.LocalDate("NewDateTime"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                System.out.println(rs.getString("major"));

                UserData.add(new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        //rs.("NewDateTime"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("major")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }

        UserID.setCellValueFactory(new PropertyValueFactory<User,Integer>("userID"));
        Name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        //NewsDateTime.setCellValueFactory(new PropertyValueFactory<NewsInformation,LocalDate>("NewsDateTime"));
        Email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        Password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        Major.setCellValueFactory(new PropertyValueFactory<User,String>("major"));

        tv.setItems(UserData);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUserData();
    }
    @FXML
    private void deleteButton(ActionEvent event){
        ObservableList<User> UserSelect, allUser;
        allUser = tv.getItems();
        UserSelect = tv.getSelectionModel().getSelectedItems();

        if(UserSelect.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a user.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this user?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){

                db.ManagerDeleteUsers(UserSelect.get(0).getName()); //remove book to database
                UserSelect.forEach(allUser::remove); //remove book to table view.
            }
            else{
                alert.close();
            }
        }
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

