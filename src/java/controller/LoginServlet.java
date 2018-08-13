package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MemberManager;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");

        String mail = request.getParameter("mail"); //使用者帳號輸入
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true); //取得參數session

        try {
            String[] loginResult = MemberManager.login(mail, password);
            if (loginResult[0].equals("LoginSuccess")) {
                session.setAttribute("M_id", loginResult[1]);    //設定session值
                session.setAttribute("M_Administrator", loginResult[3]);
                // 若成功則返回主畫面
            } else {
                try (PrintWriter out = response.getWriter()) {
                    out.print("登入失敗，請確認帳號密碼");
                }
                // 若登入失敗則轉至註冊畫面
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
