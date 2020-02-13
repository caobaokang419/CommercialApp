package com.gary.commercial.bean;


public class UserBean {
    private String nickname;
    private String username;
    private String email;
    private String mobileNumber;
    private String p;

    public UserBean(String nickname, String username, String email, String mobileNumber, String p) {
        this.nickname = nickname;
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.p = p;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() {
        return username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserBean info={nickname=");
        sb.append(this.nickname);
        sb.append("}");
        return sb.toString();
    }
}
