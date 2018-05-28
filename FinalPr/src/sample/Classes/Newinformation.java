package sample.Classes;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Zhenyu on 2018/5/28.
 */
public class Newinformation {
    private SimpleStringProperty newsTitle=new SimpleStringProperty();
    private SimpleStringProperty newsDateTime= new SimpleStringProperty();


    public Newinformation(String newsTitle,String newsDateTime){
        this.newsTitle=new SimpleStringProperty(newsTitle);
        this.newsDateTime=new SimpleStringProperty(newsDateTime);

    }

    public String getNewsTitle() {
        return newsTitle.get();
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle.set(newsTitle);
    }

    public String getNewsDateTime() {
        return newsDateTime.get();
    }

    public void setNewsDateTime(String newsDateTime) {
        this.newsDateTime.set(newsDateTime);
    }



    //property value

    public SimpleStringProperty newTitleProperty(){
        return newsTitle;}
}
