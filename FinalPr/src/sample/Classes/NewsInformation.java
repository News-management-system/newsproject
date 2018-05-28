package sample.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NewsInformation {

    private SimpleIntegerProperty newsID = new SimpleIntegerProperty();
    private SimpleStringProperty newsTitle=new SimpleStringProperty();
    private SimpleStringProperty newsContent=new SimpleStringProperty();
    private SimpleStringProperty newsDateTime= new SimpleStringProperty();
    private SimpleIntegerProperty categoryNewsID=new SimpleIntegerProperty();
    private SimpleStringProperty newsman= new SimpleStringProperty();


    /*public NewsInformation(int text, String contentAreaText, String newsId, String categorynewsID, String newsmanText) {
        this.newsID = new SimpleIntegerProperty();
        this.newsTitle = new SimpleStringProperty();
        //this.newsDateTime = null;
        this.newsContent = new SimpleStringProperty();
        this.categoryNewsID = new SimpleIntegerProperty();
        this.newsman = new SimpleStringProperty();
    }*/

  public NewsInformation(int newsID, String newsTitle, String newsContent, int categoryNewsID, String newsman, String newsDateTime){
        this.newsID=new SimpleIntegerProperty(newsID);
        this.newsTitle=new SimpleStringProperty(newsTitle);
        this.newsDateTime=new SimpleStringProperty(newsDateTime);
        this.newsContent=new SimpleStringProperty(newsContent);
        this.categoryNewsID=new SimpleIntegerProperty(categoryNewsID);
        this.newsman=new SimpleStringProperty(newsman);
  }
    public NewsInformation(String Newstitle, String newsman,String NewsContent, String NewsDateTime) {
        this.newsTitle  = new SimpleStringProperty(Newstitle);
        this.newsman = new SimpleStringProperty(newsman);
        //this.categoryNewsID = new SimpleIntegerProperty(categoryNewsID);
        this.newsContent  = new SimpleStringProperty(NewsContent);
        this.newsDateTime = new SimpleStringProperty(NewsDateTime);
    }
    public int getNewsID(){ return newsID.get(); }

    public void setNewsID(int newsID){
        this.newsID.set(newsID);
    }

    public String getNewsTitle() {
        return newsTitle.get();
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle.set(newsTitle);
    }

    public String getNewsContent() {
        return newsContent.get();
    }

    public void setNewsContent(String newsContent) {
        this.newsContent.set(newsContent);
    }

    public String getNewsDateTime() {
        return newsDateTime.get();
    }

    public void setNewsDateTime(String newsDateTime) {
        this.newsDateTime.set(newsDateTime);
    }

    public int getCategoryNewsID() {
        return categoryNewsID.get();
    }

    public void setCategoryNewsID(int categoryNewsID) {
        this.categoryNewsID.set(categoryNewsID);
    }

    public String getNewsman() {
        return newsman.get();
    }

    public void setNewsman(String newsman) {
        this.newsman.set(newsman);
    }


    //property value
    public SimpleIntegerProperty newsIDProperty(){

        return newsID;
    }
    public SimpleStringProperty newTitleProperty(){
        return newsTitle;}

    public SimpleStringProperty newsContentProperty() {
        return newsContent;
    }

    public SimpleIntegerProperty categoryNewsIDProperty() {
        return categoryNewsID;
    }

    public SimpleStringProperty newsmanProperty() {
        return newsman;
    }
}