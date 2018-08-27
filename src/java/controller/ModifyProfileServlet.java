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

@WebServlet(name = "ModifyProfileServlet", urlPatterns = {"/ModifyProfileServlet"})
public class ModifyProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        int M_id = Integer.parseInt((String) session.getAttribute("M_id"));

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String newpassword = request.getParameter("newpassword");
        String output = null;

        try {
            boolean checkPassword = MemberManager.checkPassword(M_id, password);

            if (newpassword == null && checkPassword) {
                int result = MemberManager.modifyNickname(M_id, nickname);

                if (result <= 0) {
                    output = "暱稱修改失敗，請重新輸入";
                }
            } else if (newpassword != null && checkPassword) {
                int result = MemberManager.modifyNickname(M_id, nickname);
                int result2 = MemberManager.modifyPassword(M_id, newpassword);

                if (result <= 0 && result2 <= 0) {
                    output = "暱稱與密碼修改失敗，請重新輸入";
                }
            } else {
                output = "密碼不正確";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(output);
        }
    }
}
