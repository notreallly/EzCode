package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import model.FileManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SaveFileServlet", urlPatterns = {"/SaveFileServlet"})
public class SaveFileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String M_id = (String) session.getAttribute("M_id");
        String temp_fileName = (String) session.getAttribute("filename");
        String code = request.getParameter("code").trim();
        String fileName = FileManager.getFileName(code);
        String message = "請先登入";
        try {
            if (M_id != null) {
                if (temp_fileName != null && temp_fileName.equals(fileName)) {
                    message = FileManager.updateFile(M_id, fileName, code);
                } else if (fileName != null) {
                    message = FileManager.saveFile(M_id, fileName, code);
                    session.setAttribute("filename", fileName);
                } else {
                    message = "找不到檔名";
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            message="檔名重複";
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(message);
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
