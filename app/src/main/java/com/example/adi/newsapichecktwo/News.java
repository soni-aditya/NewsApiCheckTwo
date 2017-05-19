package com.example.adi.newsapichecktwo;

/**
 * Created by Adi on 5/19/2017.
 */

public class News {
    public News(int image_id,String title,String author,String discription){
        this.setImage_id(image_id);
        this.setAuthor(author);
        this.setTitle(title);
        this.setDiscription(discription);
    }
    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    private int image_id;
    private String title,author,discription;
}
