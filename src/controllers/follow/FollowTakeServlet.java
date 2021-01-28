package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowTakeServlet
 */
@WebServlet("/follow/take")
public class FollowTakeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowTakeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account login_account = (Account)request.getSession().getAttribute("login_account");

        EntityManager em = DBUtil.createEntityManager();

        Account a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));
        Follow f = em.createNamedQuery("getDeleteFollow", Follow.class)
                     .setParameter("follow", login_account)
                     .setParameter("follower", a)
                     .getSingleResult();

        em.getTransaction().begin();
        em.remove(f);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("pcheck", 0);
        request.getSession().setAttribute("aid", a);
        response.sendRedirect(request.getContextPath() + "/account/show");
    }

}
