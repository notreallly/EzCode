package controller;

import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import model.Execute;
import model.OrderNo;
import model.FileManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ExecuteServlet", urlPatterns = {"/ExecuteServlet"})
public class ExecuteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = new String(request.getParameter("code").trim().getBytes("iso8859-1"), "UTF-8");
        String fileName = FileManager.getFileName(code);
        String M_id;
        
        HttpSession session = request.getSession();
        if (session.getAttribute("mail") == null) {
            M_id = OrderNo.getNumber();
            session.setAttribute("mail", M_id);
        }else{
            M_id = (String)session.getAttribute("mail");
        }

        Path dir = Paths.get("C:\\code", M_id);
        try {
            FileManager.createFile(dir, fileName, code);

            List<String> output;
            output = Execute.runProcess("javac -encoding UTF-8 "
                    + dir.toString() + "\\" + fileName + ".java");
            if (output.get(0).equals("0")) {
                output = Execute.runProcess("java -cp " + dir.toString() + " " + fileName);
            }
            FileManager.clearFile(dir);
            output.remove(0);
            request.setAttribute("output", output);
            request.getRequestDispatcher("/WEB-INF/output.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
