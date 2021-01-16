package controllers.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("login_account");
        Integer pcheck = (Integer)request.getSession().getAttribute("pcheck");

        if(pcheck == 0){
            response.sendRedirect(request.getContextPath() + "/community");
        } else if(pcheck == 1){
            response.sendRedirect(request.getContextPath() + "/community");
        } else if(pcheck == 2){
            response.sendRedirect(request.getContextPath() + "/community/show");
        }
    }

}
