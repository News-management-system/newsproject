package sample.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NewsCommentary {
    private SimpleIntegerProperty commentaryID = new SimpleIntegerProperty();
    private SimpleIntegerProperty newsID = new SimpleIntegerProperty();
    private SimpleStringProperty commentaryContent = new SimpleStringProperty();
    private SimpleStringProperty commentaryDateTime=new SimpleStringProperty();
    private SimpleStringProperty commentator = new SimpleStringProperty();


    public NewsCommentary() {
        this.commentaryID=new SimpleIntegerProperty();
        this.newsID=newsID=new SimpleIntegerProperty();
        //this.commentaryDateTime=commentaryDateTime;
        this.commentaryContent=new SimpleStringProperty();
        this.commentator=new SimpleStringProperty();
    }

    public NewsCommentary(int commentaryID,int newsID,String commentaryContent,String commentator,String commentaryDateTime){
        this.commentaryID=new SimpleIntegerProperty(commentaryID);
        this.newsID=new SimpleIntegerProperty(newsID);
        this.commentaryContent=new SimpleStringProperty(commentaryContent);
        this.commentator=new SimpleStringProperty(commentator);
        this.commentaryDateTime=new SimpleStringProperty(commentaryDateTime);
    }
    public int getCommentaryID(){
        return commentaryID.get();
    }
    public void setCommentaryID(int commentaryID){
        this.commentaryID.set(commentaryID);
    }
    public int getNewsID(){
        return newsID.get();
    }
    public void setNewsID(int newsID){
        this.newsID.set(newsID);
    }

    public String getCommentaryContent() {
        return commentaryContent.get();
    }

    public void setCommentaryContent(String commentaryContent) {
        this.commentaryContent.set(commentaryContent);
    }

    public String getCommentator() {
        return commentator.get();
    }

    public void setCommentator(String commentator) {
        this.commentator.set(commentator);
    }
    public String getcommentaryDateTime(){
        return commentaryDateTime.get();
    }
    public void setCommentaryDateTime(String commentaryDateTime){
        this.commentaryDateTime.set(commentaryDateTime);
    }

    //property value
    public SimpleIntegerProperty commentaryIDProperty() {
        return commentaryID;
    }

    public SimpleIntegerProperty newsIDProperty() {
        return newsID;
    }

    public SimpleStringProperty commentaryContentProperty() {
        return commentaryContent;
    }

    public SimpleStringProperty commentatorProperty() {
        return commentator;
    }
    public SimpleStringProperty commentaryDateTimeProperty(){
        return commentaryDateTime;
    }
}
