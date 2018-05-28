package sample.Classes;

public class CategoryNews {
    private int CategoryNewsID;
    private String CategoryNewsName;

    public CategoryNews(int CategoryNewsID,String CategoryNewsName) {
        this.CategoryNewsID=CategoryNewsID;
        this.CategoryNewsName=CategoryNewsName;
    }

    public int getCategoryNewsID() {
        return CategoryNewsID;
    }

    public void setCategoryNewsID(int categoryNewsID) {
        CategoryNewsID = categoryNewsID;
    }

    public String getCategoryNewsName() {
        return CategoryNewsName;
    }

    public void setCategoryNewsName(String categoryNewsName) {
        CategoryNewsName = categoryNewsName;
    }
}
