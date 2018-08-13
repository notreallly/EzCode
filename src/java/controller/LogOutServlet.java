package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogOutServlet", urlPatterns = {"/LogOutServlet"})
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();    // 取得參數session 
        try {
            if (session.getAttribute("M_id") != null) {
                session.removeAttribute("M_id");
                session.removeAttribute("filename");
                session.invalidate();
                try(PrintWriter out = response.getWriter()){
                	out.print("登出成功");
                }
            } else {
                request.setAttribute("subscribed", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
