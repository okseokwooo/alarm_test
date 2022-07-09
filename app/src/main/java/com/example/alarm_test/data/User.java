package com.example.alarm_test.data;

public class User {
    private Object ID;
    private Object PassWord;
    private Object idToken;
    private Object img;

    public Object getID() {
        return ID;
    }

    public void setID(Object ID) {
        this.ID = ID;
    }

    public Object getPassWord() {
        return PassWord;
    }

    public void setPassWord(Object passWord) {
        PassWord = passWord;
    }

    public Object getIdToken() {
        return idToken;
    }

    public void setIdToken(Object idToken) {
        this.idToken = idToken;
    }

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public User(Object ID, Object passWord, Object idToken, Object img) {
        this.ID = ID;
        PassWord = passWord;
        this.idToken = idToken;
        this.img = img;
    }
}
