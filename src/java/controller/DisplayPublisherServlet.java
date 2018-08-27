package controller;

import java.io.IOException;
import java.sql.SQLException;
import model.PublisherManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DisplayPublisherServlet", urlPatterns = {"/DisplayPublisherServlet"})
public class DisplayPublisherServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int index = Integer.parseInt(request.getParameter("index"));
        String dir =getServletContext().getContextPath();

        try {
            session.setAttribute("publisher", PublisherManager.displayAll(index));
            session.setAttribute("total", ((PublisherManager.getAllPublisher()+19) / 20 ));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(dir + "/publisher.view");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
