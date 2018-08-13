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
import model.PublisherManager;

/**
 *
 * @author user
 */
@WebServlet(name = "PublishServlet", urlPatterns = {"/PublishServlet"})
public class PublishServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        
        HttpSession session = request.getSession();
        String M_id = (String) session.getAttribute("M_id");
        String filename = (String) session.getAttribute("filename");
        String message = "發佈失敗";

        try {
            if (M_id == null) {
                message = "請先登入";
            } else if (filename == null) {
                message = "沒有程式碼或程式碼未儲存";
            } else {
                if (PublisherManager.insertPublisher(title, description, M_id, filename) != 0) {
                    message = "發佈成功";
                }
            }
        } catch (SQLException e) {
            message="請先開啟已儲存檔案或執行儲存檔案";
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
