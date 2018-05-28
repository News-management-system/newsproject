package sample.Classes;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by FU YUANQING on 5/28/2018.
 */
public class NewsStorage {
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty NewsTitle = new SimpleStringProperty();
    private SimpleStringProperty NewsDateTime=new SimpleStringProperty();
    private SimpleStringProperty NewsContent = new SimpleStringProperty();
    private SimpleStringProperty newsman = new SimpleStringProperty();

    private static NewsStorage newsStorage;

    private NewsStorage(){

    }

    public static NewsStorage getInstance(){
        if(newsStorage == null){
            newsStorage = new NewsStorage();
        }
        return newsStorage;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getNewsTitle() {
        return NewsTitle.get();
    }

    public SimpleStringProperty newsTitleProperty() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.NewsTitle.set(newsTitle);
    }

    public String getNewsDateTime() {
        return NewsDateTime.get();
    }

    public void setNewsDateTime(String newsDateTime) {
        this.NewsDateTime.set(newsDateTime);
    }

    public String getNewsContent() {
        return NewsContent.get();
    }

    public SimpleStringProperty newsContentProperty() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        this.NewsContent.set(newsContent);
    }

    public String getNewsman() {
        return newsman.get();
    }

    public SimpleStringProperty newsmanProperty() {
        return newsman;
    }

    public void setNewsman(String newsman) {
        this.newsman.set(newsman);
    }
}
