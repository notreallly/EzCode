package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CheckLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String M_id = (String) session.getAttribute("M_id");
        String M_Administrator = (String) session.getAttribute("M_Administrator");

        if (M_id == null) {
            M_id = "0";
        }
        if (M_Administrator == null) {
            M_Administrator = "0";
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(M_id + " " + M_Administrator);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
