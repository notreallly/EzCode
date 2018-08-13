
package model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
    private int id,M_id;
    private String content,nickname;
    private Date commenttime;

    public Comment() {
    } 

    public Comment(int id, int M_id, String content, String nickname, Date commenttime) {
        this.id = id;
        this.M_id = M_id;
        this.content = content;
        this.nickname = nickname;
        this.commenttime = commenttime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getM_id() {
        return M_id;
    }

    public void setM_id(int M_id) {
        this.M_id = M_id;
    }
    
    
}
