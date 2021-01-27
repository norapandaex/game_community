package controllers.follow;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class FollowAddServlet
 */
@WebServlet("/follow/add")
public class FollowAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = DBUtil.createEntityManager();

            Account a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));

            Follow f = new Follow();

            f.setFollow((Account)request.getSession().getAttribute("login_account"));
            f.setFollower(a);
            f.setFollowed_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("account_id", a.getId());
            response.sendRedirect(request.getContextPath() + "/account/show");

        }

}
