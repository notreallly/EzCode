package model;

import java.io.Serializable;
import java.util.Date;

public class Publisher implements Serializable {

    private int p_id,m_id;
    private String title, nickname, code, description;
    private Date updatetime;

    public Publisher() {
    }

    public Publisher(int id, String title, String nickname, String code, String description, Date updatetime) {
        this.p_id = id;
        this.title = title;
        this.nickname = nickname;
        this.code = code;
        this.description = description;
        this.updatetime = updatetime;
    }

    public Publisher(int id, int M_id, String title, String nickname, Date updatetime) {
        this.p_id = id;
        this.m_id = M_id;
        this.title = title;
        this.nickname = nickname;
        this.updatetime = updatetime;
    }

    public Publisher(int p_id, String title, String nickname, Date updatetime) {
        this.p_id = p_id;
        this.title = title;
        this.nickname = nickname;
        this.updatetime = updatetime;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return p_id;
    }

    public void setId(int p_id) {
        this.p_id = p_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int M_id) {
        this.m_id = M_id;
    }
    
}
