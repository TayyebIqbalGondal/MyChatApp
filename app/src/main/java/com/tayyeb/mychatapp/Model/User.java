package com.tayyeb.mychatapp.Model;

public class User {
    private String username;
    private  String id;
    private String imageURL;


    public User(String id, String username, String imageURL){

        this.id=id;
        this.username=username;
        this.imageURL=imageURL;


    }

    public User(){

    }

    public String getId()
    {
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }


    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }


    public String getImageURL(){
        return this.imageURL;
    }
    public void setImageURL(String imageURL)
    {
        this.imageURL=imageURL;
    }


}
