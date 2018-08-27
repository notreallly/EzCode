package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CommentManager;

@WebServlet(name = "ModifyCommentServlet", urlPatterns = {"/ModifyCommentServlet"})
public class ModifyCommentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String P_id = request.getParameter("P_id");
        String COM_id = request.getParameter("COM_id");
        String M_id = (String) session.getAttribute("M_id");
        String content = request.getParameter("content");
        String message = "修改失敗";

        try {
            if (CommentManager.updateComment(new String(content.trim().getBytes("iso8859-1"), "UTF-8"),COM_id, M_id) != 0) {
                message = "修改成功";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("output", message);
        response.sendRedirect(getServletContext().getContextPath() + "/DisplayCommentServlet?P_id=" + P_id);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
