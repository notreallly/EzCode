package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final Pattern FILENAME_REGEX = Pattern.compile("[ ]?public[ ]+class[ ]+(\\D\\w*)[ ]*[\u007B]");

    public static void createFile(Path userhome, String fileName, String code) throws IOException {
        Files.createDirectories(userhome);

        Path procedure = userhome.resolve(fileName + ".java");

        try (BufferedWriter writer = Files.newBufferedWriter(procedure, Charset.forName("UTF-8"));) {
            writer.write(code);
        }
    }

    public static String getFileName(String code) {
        Matcher token = FILENAME_REGEX.matcher(code);
        if (token.find()) {
            return token.group(1);
        }
        return null;
    }

    public static void clearFile(Path userhome) throws IOException {
        File dir = new File(userhome.toString());
        if (dir.exists()) {
            File[] tmp = dir.listFiles();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].isDirectory()) {
                } else {
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }

    public static String roadFile(String M_id, String fileName) throws IOException, SQLException, ClassNotFoundException {
        String query = "select * from Code where M_id='" + M_id + "' and C_filename='" + fileName + "'";

        try (
                Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            if (rs.next()) {
                String code = rs.getString("C_code");
                return code;
            }
        }
        return null;
    }

    public static String saveFile(String M_id, String fileName, String code) throws IOException, SQLException, ClassNotFoundException {
        String query = "insert into Code (M_id,C_filename,C_code) values (" + M_id + ",'" + fileName + "','" + code + "')";
        String message = "儲存成功";
        try (
                Connection con = DBConnection.createConnection();
                PreparedStatement pstmt = con.prepareStatement(query);) {
            if (checkFileQty(M_id)) {
                pstmt.executeUpdate();
            } else {
                message = "已超過儲存檔案上限，請先刪除檔案";
            }
        }
        return message;
    }

    public static String updateFile(String M_id, String fileName, String code) throws IOException, SQLException, ClassNotFoundException {
        String query = "update Code set C_code = '" + code + "' where C_filename = '" + fileName + "' and M_id='" + M_id + "'";
        String message = "修改成功";
        try (
                Connection con = DBConnection.createConnection();
                PreparedStatement pstmt = con.prepareStatement(query);) {

            try {
                pstmt.executeUpdate();
            } catch (SQLException e) {
                message = "修改失敗";
            }

        }
        return message;
    }

    private static boolean checkFileQty(String M_id) throws SQLException {
        String query = "select count(C_filename)  as count from Code where M_id=?";
        try (
                Connection con = DBConnection.createConnection();
                PreparedStatement pstmt = con.prepareStatement(query);) {
            pstmt.setString(1, M_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("count") < 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String deleteFile(String M_id, String filename) {
        String message = "刪除成功";
        String query = "delete from Code where M_id=? and C_filename=?";
        try (
                Connection con = DBConnection.createConnection();
                PreparedStatement pstmt = con.prepareStatement(query);) {
            pstmt.setString(1, M_id);
            pstmt.setString(2, filename);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            message = "刪除失敗，檔案已發布";
        }
        return message;
    }

    public static List<model.File> displayAll(String M_id) throws SQLException {
        String query = "select M_id,C_filename,C_code from Code where M_id= " + M_id;
        ArrayList<model.File> outputs = new ArrayList<>();

        try (
                Statement stmt = DBConnection.createConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                int id = rs.getInt("M_id");
                String filename = rs.getString("C_filename");
                String code = rs.getString("C_code");
                outputs.add(new model.File(filename, code, id));
            }
        }
        return outputs;
    }

}
