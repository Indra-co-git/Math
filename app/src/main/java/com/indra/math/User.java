package com.indra.math;

public class User {


    private String name,username,email,token;

    private int score;

    public User(){

    }


    public User(String n,String un,String e,String t,int s)
    {
        name=n;
        username=un;
        email=e;
        token=t;
        s = 0;

    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}
