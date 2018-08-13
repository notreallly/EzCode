package model;

import dao.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentManager {

    public static List<Comment> displayAll(int P_id) throws SQLException {
        String query = "select COM_id,C.M_id,COM_content,COM_commenttime,M.M_nickname "
                + "from Comment C join Member M on  C.M_id=M.M_id where P_id = " + P_id + " order by 1 ";
        ArrayList<Comment> outputs = new ArrayList<>();

        try (
                Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                int id = rs.getInt("COM_id");
                int M_id = rs.getInt("M_id");
                String content = rs.getString("COM_content");                
                Date updatetime = rs.getTimestamp("COM_commenttime");
                String nickname = rs.getString("M_nickname");
                outputs.add(new Comment(id, M_id, content, nickname, updatetime));
            }
        }
        return outputs;
    }

    public static int insertComment(String content, String M_id, String P_id) throws SQLException {
        String query = "insert into comment(COM_content,M_id,P_id) values (?,?,?)";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, content);
            pstmt.setString(2, M_id);
            pstmt.setString(3, P_id);

            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int updateComment(String content, String COM_id, String M_id) throws SQLException {
        String query = "update Comment set COM_content=? where COM_id=? and M_id=?";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, content);
            pstmt.setInt(2, Integer.parseInt(COM_id));
            pstmt.setInt(3, Integer.parseInt(M_id));
            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int deleteComment(String COM_id, String M_id) throws SQLException {
        String query = "delete Comment where COM_id=? and M_id=?";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setInt(1, Integer.parseInt(COM_id));
            pstmt.setInt(2, Integer.parseInt(M_id));
            result = pstmt.executeUpdate();
        }
        return result;
    }
    
    public static int deleteComment(String COM_id) throws SQLException {
        String query = "delete Comment where COM_id=?";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setInt(1, Integer.parseInt(COM_id));
            result = pstmt.executeUpdate();
        }
        return result;
    }
}
