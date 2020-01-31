package com.example.fakefblivestream;

public class CommentPojo {
    private String imagePath;
    private String userName;
    private String userComment;


    public CommentPojo(String imagePath, String userName, String userComment) {
        this.imagePath = imagePath;
        this.userName = userName;
        this.userComment = userComment;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}
