package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MemberManager;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String nickname = request.getParameter("nickname");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        try {
            if (MemberManager.signUp(nickname, mail, password).equals("SignUpFailed")) {
                try (PrintWriter out = response.getWriter()) {
                    out.print("註冊失敗，請更換帳號重新註冊");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
