package controllers.follow;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowerShowServlet
 */
@WebServlet("/follower/show")
public class FollowerShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowerShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account a = null;
        Integer pcheck = (Integer)request.getSession().getAttribute("pcheck");
        if(pcheck == 3) {
            a = em.find(Account.class, Integer.parseInt((String)request.getSession().getAttribute("aid")));
            request.getSession().setAttribute("account", a);
        } else {
            a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("account", a);
            request.getSession().setAttribute("aid", request.getParameter("id"));
        }

        List<Follow> followers = em.createNamedQuery("getMyFollowers", Follow.class)
                .setParameter("follower", a)
                .getResultList();

        long follows_count = (long)em.createNamedQuery("getFollowsCount", Long.class)
                .setParameter("follow", a)
                .getSingleResult();

        long followers_count = (long)em.createNamedQuery("getFollowersCount", Long.class)
                .setParameter("follower", a)
                .getSingleResult();

        long favorites_count = (long)em.createNamedQuery("getFavoriteCount", Long.class)
                .setParameter("account", a)
                .getSingleResult();

        request.setAttribute("followers", followers);
        request.setAttribute("followsC", follows_count);
        request.setAttribute("followersC", followers_count);
        request.setAttribute("favoritesC", favorites_count);
        request.getSession().setAttribute("pcheck", 0);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follower/show.jsp");
        rd.forward(request, response);

    }

}
