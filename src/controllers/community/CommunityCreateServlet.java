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
 * Servlet implementation class CommunityCreateServlet
 */
@WebServlet("/community/create")
public class CommunityCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Community c = new Community();

            c.setName(request.getParameter("name"));
            c.setGame(request.getParameter("game"));
            c.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setCreated_at(currentTime);
            c.setUpdated_at(currentTime);
            c.setDelete_flag(0);

            List<String> errors = CommunityValidator.validate(c);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("community", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.close();

                response.sendRedirect(request.getContextPath() + "/community");
            }
        }
    }

}
