package com.example.flickrr.search_package;

public class People {
    private int image;
    private String name;
    private String photos;

    public People(int image, String name, String photos) {
        this.image = image;
        this.name = name;
        this.photos = photos;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String address) {
        this.photos = photos;
    }
}
