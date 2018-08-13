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

@WebServlet(name = "SearchProfileServlet", urlPatterns = {"/SearchProfileServlet"})
public class SearchProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String exist = (String) session.getAttribute("M_id");
        String output = ("查詢失敗");

        if (exist != null) {
            int M_id = Integer.parseInt((String) session.getAttribute("M_id"));

            try {
                String[] searchResult = MemberManager.displayMember(M_id);
                if (searchResult[0].equals("SearchSucess")) {
                    output = (searchResult[0] + " " + searchResult[1] + " " + searchResult[2]);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("Login.jsp");
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
