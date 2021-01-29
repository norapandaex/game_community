package controllers.member;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Community;
import models.CommunityMember;
import utils.DBUtil;

/**
 * Servlet implementation class MemberTakeServlet
 */
@WebServlet("/member/take")
public class MemberTakeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberTakeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account login_account = (Account)request.getSession().getAttribute("login_account");

        EntityManager em = DBUtil.createEntityManager();

        Community c = em.find(Community.class, Integer.parseInt(request.getParameter("id")));
        CommunityMember cm = em.createNamedQuery("checkAdd", CommunityMember.class)
                .setParameter("account", login_account)
                .setParameter("c", c)
                .getSingleResult();

        em.getTransaction().begin();
        em.remove(cm);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("c", c);
        response.sendRedirect(request.getContextPath() + "/community/show");
    }

}
