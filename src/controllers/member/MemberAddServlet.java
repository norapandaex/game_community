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
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = DBUtil.createEntityManager();

            Community c = em.find(Community.class, Integer.parseInt(request.getParameter("id")));

            CommunityMember cm = new CommunityMember();

            cm.setAccount((Account)request.getSession().getAttribute("login_account"));
            cm.setCommunity(c);

            em.getTransaction().begin();
            em.persist(cm);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("c", c);
            response.sendRedirect(request.getContextPath() + "/community/show");

        }
    }


