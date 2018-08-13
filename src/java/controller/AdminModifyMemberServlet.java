package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MemberManager;

@WebServlet(name = "AdminModifyMemberServlet", urlPatterns = {"/AdminModifyMemberServlet"})
public class AdminModifyMemberServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        int M_id = Integer.parseInt((String) request.getParameter("M_id"));
        String nickname = request.getParameter("nickname");
        int actived = Integer.parseInt((String)request.getParameter("actived"));
        String password = request.getParameter("password");

        String action = request.getParameter("action");

        switch (action) {
            case "修改":
                int modify = MemberManager.modifyMember(M_id,actived, nickname,password);
                if (modify != 0) {
                    System.out.println("modifyMemberSuccess!"+M_id);
                    response.sendRedirect(getServletContext().getContextPath() + "/AdminMemberListServlet?index=0");
                } else {
                    System.out.println("modifyMemberFailed!"+M_id);
                }
                break;
            case "刪除":
                int del = MemberManager.deleteMember(M_id);
                if (del != 0) {
                    System.out.println("deleteSuccess!");
                    response.sendRedirect(getServletContext().getContextPath() + "/AdminMemberListServlet?index=0");
                    } else {
                    System.out.println("deleteFailed!");
                }
                break;
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminModifyMemberServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminModifyMemberServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
