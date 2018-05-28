package sample.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by FU YUANQING on 5/17/2018.
 */
public class User {
    private SimpleIntegerProperty userID=new SimpleIntegerProperty();
    private SimpleStringProperty name=new SimpleStringProperty();
    private SimpleStringProperty password= new SimpleStringProperty();
    private SimpleStringProperty major=new SimpleStringProperty();
    private SimpleStringProperty comment=new SimpleStringProperty();
    private SimpleIntegerProperty commentID=new SimpleIntegerProperty();
    private SimpleStringProperty email=new SimpleStringProperty();
    private SimpleIntegerProperty longinid=new SimpleIntegerProperty();


    public User(int userID, String name, String email, String password, String major) {
        this.userID = new SimpleIntegerProperty(userID);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.major = new SimpleStringProperty(major);
        //this.comment = new SimpleStringProperty() ;
        //this.commentID = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty(email);
        //this.longinid = new SimpleIntegerProperty();
    }

    /*public User(String name, int userID, String email,String password,String major) {
        this.userID = new SimpleIntegerProperty(userID);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password=new SimpleStringProperty(password);
        this.major=new SimpleStringProperty(major);
    }*/
    public User(String email,String password,int longinid){
        this.email=new SimpleStringProperty(email);
        this.password=new SimpleStringProperty(password);
        this.longinid=new SimpleIntegerProperty(longinid);
    }

    public  int getUserID() {
        return userID.get();
    }
    public void setUserID(int UserID) {

        this.userID.set(UserID);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getMajor(){
        return major.get();
    }

    public void setMajor(String major) {
        this.major.set(major);
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
    public int getCommentID(){
        return commentID.get();
    }
    public void setCommentID(int commentID) {
        this.commentID.set(commentID);
    }
    public String getEmail(){
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    public int getLonginid(){
        return longinid.get();
    }
    public void setLonginid(int longinid){
        this.longinid.set(longinid);
    }


    public SimpleIntegerProperty userIDProperty(){

        return userID;
    }
    public SimpleStringProperty nameProperty(){
        return  name;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty majorProperty() {
        return major;
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleIntegerProperty commentIDProperty() {
        return commentID;
    }

    public SimpleIntegerProperty longinidProperty() {
        return longinid;
    }
}