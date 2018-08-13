package model;

import dao.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    public static String[] displayMember(int M_id) throws SQLException {
        String query = "select * from Member where M_id = " + M_id;
        String[] result = new String[3];
        result[0] = "SearchFailed";

        try (Statement pstmt = DBConnection.createConnection().createStatement();
                ResultSet rs = pstmt.executeQuery(query);) {
            while (rs.next()) {
                String nickname = rs.getString("M_nickname");
                String mail = rs.getString("M_mail");

                result[0] = "SearchSucess";
                result[1] = nickname;
                result[2] = mail;
            }
        }
        return result;
    }

    public static List<Member> displayAll(int index) throws SQLException {
        String quary = "select M_id,M_mail,M_nickname,M_password,M_actived from Member order by 1 offset "
                + (index * 20) + " row fetch next 20 rows only";

        ArrayList<Member> display = new ArrayList<>();

        try (Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(quary);) {

            while (rs.next()) {

                int id = rs.getInt("M_id");
                String nickname = rs.getString("M_nickname");
                String mail = rs.getString("M_mail");
                String password = rs.getString("M_password");
                int actived = rs.getInt("M_actived");

                display.add(new Member(id, nickname, mail, password, actived));
            }
        }
        return display;
    }

    public static int getAllMember() throws SQLException {
        String quary = "select count(M_id) as total from Member";
        int total = 0;
        try (Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(quary);) {
            while (rs.next()) {
                total = rs.getInt("total");
            }
        }
        return total;
    }

    public static String signUp(String nickname, String mail, String password) throws SQLException {
        String result = "SignUpFailed";

        if (checkRepeatMail(mail)) {
            System.out.println("checkMailFailed,Mail exists");
        } else {
            if (insertMember(nickname, mail, password) != 0) {
                result = "SignUpSuccess";
            } else {
                System.out.println("SignUpFailed");
            }
        }
        return result;
    }

    public static String[] login(String mail, String password) throws SQLException {
        String query = "select * from Member where M_mail='" + mail + "' and M_password ='" + password + "' Collate Chinese_Taiwan_Stroke_CS_AS";
        String[] loginResult = new String[4];
        loginResult[0] = "LoginFailed";

        try (Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            if (rs.next()) {
                loginResult[1] = rs.getString("M_id");
                loginResult[3] = rs.getString(6);
                loginResult[0] = "LoginSuccess";
            }
        }
        return loginResult;
    }

    //modify
    public static int modifyMember(int M_id, int actived, String nickname, String password) throws SQLException {
        String quary = "update Member set M_nickname=?,M_password=?,M_actived=? where M_id=?";
        int result = 0;
        try (PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(quary);) {
            pstmt.setString(1, nickname);
            pstmt.setString(2, password);
            pstmt.setInt(3, actived);
            pstmt.setInt(4, M_id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int modifyNickname(int M_id, String nickname) throws SQLException {
        String query = "update Member set M_nickname =? where M_id =?";
        int result = 0;
        try (PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, nickname);
            pstmt.setInt(2, M_id);

            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int modifyPassword(int M_id, String password) throws SQLException {
        String query = "update Member set M_password =? where M_id =?";
        int result = 0;
        try (PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, password);
            pstmt.setInt(2, M_id);

            result = pstmt.executeUpdate();
        }
        return result;
    }

    private static int insertMember(String nickname, String mail, String password) throws SQLException {
        String query = "insert into Member (M_nickname,M_mail,M_password,M_actived) values (?,?,?,?)";
        int result = 0;
        try (PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(query);) {
            pstmt.setString(1, nickname);
            pstmt.setString(2, mail);
            pstmt.setString(3, password);
            pstmt.setInt(4, 0);

            result = pstmt.executeUpdate();
        }
        return result;
    }

    public static int deleteMember(int M_id) throws SQLException {
        String quary = "delete Member where M_id=?";
        int result = 0;
        try (PreparedStatement pstmt = DBConnection.createConnection().prepareStatement(quary);) {
            pstmt.setInt(1, M_id);
            result = pstmt.executeUpdate();
        }
        return result;
    }

    //check
    public static boolean checkPassword(int M_id, String password) throws SQLException {
        String query = "select M_password from Member where M_id ='" + M_id + "' and M_password='" + password + "' Collate Chinese_Taiwan_Stroke_CS_AS";
        boolean result = false;
        try (Statement pstmt = DBConnection.createConnection().createStatement();
                ResultSet rs = pstmt.executeQuery(query);) {
            while (rs.next()) {
                result = true;
            }
        }
        return result;
    }

    private static boolean checkRepeatMail(String email) throws SQLException {
        String query = "select * from Member where M_mail='" + email + "'";
        boolean result = false;
        try (Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            if (rs.next()) {
                result = true;
            }
        }
        return result;
    }

}
