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
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by FU YUANQING on 5/21/2018.
 */
public class ManageradduserspageController implements Initializable {

    @FXML
    private TableView tv;
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

    @FXML
    private TextField userid,name,email,password,major;

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
    @FXML
    private void saveButton(ActionEvent event) throws SQLException {
        SaveToDatabase();
        refresh();
    }

    private void SaveToDatabase() throws SQLException, NumberFormatException {
        boolean isuserID = help.isInteger(userid.getText());

        if(!isuserID){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("USERID IS INVALID");
            alert.show();
        }
        else if(name.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("NAME IS INVALID");
            alert.show();
        }
        else if(email.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID EMAIL.");
            alert.show();
        }
        else if(password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID PASSWORD.");
            alert.show();
        }
        else if(major.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID MAJOR.");
            alert.show();
        }
        else{
            int userId = Integer.valueOf(userid.getText());
            User user;
            user = new User(
                    userId,
                    name.getText(),
                    email.getText(),
                    password.getText(),
                    major.getText()
                    //currentDate()
            );

            db.managerAddUser(user);
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
        UserData.clear();
        getUserData();
    }

    @FXML
    private void clearButton(ActionEvent event) {
        userid.clear();
        name.clear();
        email.clear();
        password.setText("");
        major.clear();
        //publisherCombo.setValue("publisher");
        //typeCombo.setValue("type");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUserData();
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

