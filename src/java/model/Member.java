package model;

import java.io.Serializable;

public class Member implements Serializable {

    private int id;
    private String nickname;
    private String mail;
    private String password;
    private int administrator;
    private int actived;

    public Member() {
    }

    public Member(int id, String nickname, String mail, String password, int actived) {
        this.id = id;
        this.nickname = nickname;
        this.mail = mail;
        this.password = password;
        this.actived = actived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdministrator() {
        return administrator;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }

    public int getActived() {
        return actived;
    }

    public void setActived(int actived) {
        this.actived = actived;
    }

}
