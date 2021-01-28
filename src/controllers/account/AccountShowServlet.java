package controllers.account;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import utils.DBUtil;

/**
 * Servlet implementation class AccountShowServlet
 */
@WebServlet("/account/show")
public class AccountShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountShowServlet() {
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
            if(request.getParameter("id") != null){
                a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("aid", request.getParameter("id"));
            } else {
                a = (Account)request.getSession().getAttribute("aid");
            }
            request.getSession().setAttribute("account", a);
        }

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

        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("followsC", follows_count);
        request.setAttribute("followersC", followers_count);
        request.setAttribute("favoritesC", favorites_count);
        request.setAttribute("follow_check", follow_check);
        request.getSession().setAttribute("account_id", a.getId());
        request.getSession().setAttribute("pcheck", 3);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/show.jsp");
        rd.forward(request, response);
    }

}
