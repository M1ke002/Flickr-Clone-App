package com.example.flickrr;

public class PostData {
    public String username;
    public String likes;
    public String title;
    public String url;

    public PostData(String username, String likes, String title, String url) {
        this.username = username;
        this.likes = likes;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "PostData{" +
                "username='" + username + '\'' +
                ", likes='" + likes + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

