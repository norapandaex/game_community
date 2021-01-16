package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.CommunityMember;
import utils.DBUtil;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CommunityMember> mycommu = null;

        EntityManager em = DBUtil.createEntityManager();

        Account login_account = (Account)request.getSession().getAttribute("login_account");

        try{
            mycommu = em.createNamedQuery("getMyCommunity", CommunityMember.class)
                   .setParameter("account", login_account)
                   .getResultList();
        } catch(NoResultException ex)  {}

        em.close();

        request.getSession().setAttribute("mycommu", mycommu);
        request.getSession().setAttribute("pcheck", 1);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/home.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
