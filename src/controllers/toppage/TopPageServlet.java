package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Community;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageServlet
 */
@WebServlet("/community")
public class TopPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Community> communities = em.createNamedQuery("getAllCommunities", Community.class)
                                        .getResultList();

        em.close();

        request.setAttribute("communities", communities);

        request.getSession().setAttribute("pcheck", 0);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/community.jsp");
        rd.forward(request, response);
    }

}
