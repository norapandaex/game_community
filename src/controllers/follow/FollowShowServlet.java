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
 * Servlet implementation class FollowShowServlet
 */
@WebServlet("/follow/show")
public class FollowShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account login_account = (Account)request.getSession().getAttribute("login_account");
        Account a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));

        List<Follow> follows = em.createNamedQuery("getMyFollows", Follow.class)
                .setParameter("follow", a)
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

        long follow_check = (long)em.createNamedQuery("getFollowCheck", Long.class)
                .setParameter("follow", login_account)
                .setParameter("follower", a)
                .getSingleResult();

        request.setAttribute("follows", follows);
        request.setAttribute("followsC", follows_count);
        request.setAttribute("followersC", followers_count);
        request.setAttribute("favoritesC", favorites_count);
        request.setAttribute("follow_check", follow_check);
        request.getSession().setAttribute("pcheck", 0);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follow/show.jsp");
        rd.forward(request, response);
    }

}
