package controllers.community;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Community;
import models.validators.CommunityValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CommunityUpdateServlet
 */
@WebServlet("/community/update")
public class CommunityUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Community c = em.find(Community.class, (Integer) (request.getSession().getAttribute("community_id")));

            c.setName(request.getParameter("name"));
            c.setGame(request.getParameter("game"));
            c.setContent(request.getParameter("content"));
            c.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = CommunityValidator.validate(c);
            if (errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("community", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.setAttribute("community", c);
                request.getSession().removeAttribute("community_id");

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/show.jsp");
                rd.forward(request, response);
            }
        }
    }

}
