package controllers.community;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Community;
import utils.DBUtil;

/**
 * Servlet implementation class CommunitySearchServlet
 */
@WebServlet("/community/search")
public class CommunitySearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunitySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skeyword = (String) request.getServletContext().getAttribute("skeyword");

        EntityManager em = DBUtil.createEntityManager();

        try{
            List<Community> searches = em.createNamedQuery("getSearch", Community.class)
                                         .setParameter("skeyword", "%" + skeyword + "%")
                                         .getResultList();
            request.setAttribute("searches", searches);
        } catch (NoResultException ex) {
            request.setAttribute("searches", null);
        }

        em.close();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/search.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().setAttribute("skeyword", request.getParameter("search"));
        response.sendRedirect(request.getContextPath() + "/community/search");
    }

}
