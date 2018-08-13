package model;

import dao.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublisherManager {

    public static List<Publisher> displayAll(int index) throws SQLException {
        String query = "select P.P_id,P.M_id,P.P_title,M.M_nickname,P.P_updatetime from"
                + " publisher P join member M on  P.M_id=M.M_id order by 1 desc offset "
                + (index * 20) + " row fetch next 20 rows only";
        ArrayList<Publisher> outputs = new ArrayList<>();

        try (
                Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                int id = rs.getInt("P_id");
                int M_id = rs.getInt("M_id");
                String title = rs.getString("P_title");
                String nickname = rs.getString("M_nickname");
                Date updatetime = rs.getTimestamp("P_updatetime");

                outputs.add(new Publisher(id, M_id, title, nickname, updatetime));
            }
        }
        return outputs;
    }

    public static Publisher display(String P_id) throws SQLException {
        String query = "select P.P_id,P.P_title,P_description,C.C_code,M.M_nickname,P.P_updatetime "
                + "from publisher P join member M on  P.M_id=M.M_id left join code C on C.C_filename=P.C_filename and P.M_id= C.M_id "
                + "where P.P_id=" + P_id;
        Publisher output = null;

        try (
                Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                int id = rs.getInt("P_id");
                String title = rs.getString("P_title");
                String description = rs.getString("P_description");
                String code = rs.getString("C_code");
                String nickname = rs.getString("M_nickname");
                Date updatetime = rs.getTimestamp("P_updatetime");

                output = new Publisher(id, title, nickname, code, description, updatetime);
            }
        }
        return output;
    }

    public static int getAllPublisher() throws SQLException {
        String query = "select count(P_id) as total from Publisher";
        int total = 0;
        try (
                Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                total = rs.getInt("total");
            }
        }
        return total;
    }

    public static int insertPublisher(String title, String description, String M_id, String filename) throws SQLException {
        String query = "insert into Publisher(P_title,P_description,M_id,C_filename) values (?,?,?,?)";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, M_id);
            pstmt.setString(4, filename);

            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int updatePublisher(String title, String P_id, String M_id) throws SQLException {
        String query = "update Publisher set P_title=? where P_id=? and M_id=?";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, title);
            pstmt.setInt(2, Integer.parseInt(P_id));
            pstmt.setInt(3, Integer.parseInt(M_id));
            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int deletePublisher(String P_id, String M_id) throws SQLException {
        String query = "delete Publisher where P_id=? and M_id=?";
        int result = 0;
        try (
                PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setInt(1, Integer.parseInt(P_id));
            pstmt.setInt(2, Integer.parseInt(M_id));
            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int deletePublisher(int P_id) throws SQLException {
        String quary = "delete Publisher where P_id=?";
        int result = 0;
        try (PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(quary);) {
            pstmt.setInt(1, P_id);
            result = pstmt.executeUpdate();
        }
        return result;
    }
}
