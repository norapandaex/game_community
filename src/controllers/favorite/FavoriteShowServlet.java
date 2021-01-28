package controllers.favorite;

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
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteShowServlet
 */
@WebServlet("/favorite/show")
public class FavoriteShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account login_account = (Account)request.getSession().getAttribute("login_account");
        Account a = null;
        Integer pcheck = (Integer)request.getSession().getAttribute("pcheck");
        if(pcheck == 3) {
            a = em.find(Account.class, Integer.parseInt((String)request.getSession().getAttribute("aid")));
            request.getSession().setAttribute("account", a);
            request.getSession().removeAttribute("aid");
        } else {
            a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("account", a);
            request.getSession().setAttribute("aid", request.getParameter("id"));
        }


        List<Favorite> fav = em.createNamedQuery("checkFav", Favorite.class)
                .setParameter("account", a)
                .getResultList();

        long follow_check = (long)em.createNamedQuery("getFollowCheck", Long.class)
                .setParameter("follow", login_account)
                .setParameter("follower", a)
                .getSingleResult();

        long follows_count = (long)em.createNamedQuery("getFollowsCount", Long.class)
                .setParameter("follow", a)
                .getSingleResult();

        long followers_count = (long)em.createNamedQuery("getFollowersCount", Long.class)
                .setParameter("follower", a)
                .getSingleResult();

        long favorites_count = (long)em.createNamedQuery("getFavoriteCount", Long.class)
                .setParameter("account", a)
                .getSingleResult();

        em.close();

        request.setAttribute("fav", fav);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("followsC", follows_count);
        request.setAttribute("followersC", followers_count);
        request.setAttribute("favoritesC", favorites_count);
        request.setAttribute("follow_check", follow_check);
        request.getSession().setAttribute("pcheck", 0);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/favorite/show.jsp");
        rd.forward(request, response);
    }

}
