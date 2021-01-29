package controllers.account;

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
import models.AccountContribution;
import models.AccountReply;
import utils.DBUtil;

/**
 * Servlet implementation class AccountSearchServlet
 */
@WebServlet("/account/search")
public class AccountSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skeyword = (String) request.getServletContext().getAttribute("skeyword");
        List<Account> asearches = null;
        List<AccountContribution> acsearches = null;
        List<AccountReply> arsearches = null;

        EntityManager em = DBUtil.createEntityManager();

        try{
            asearches = em.createNamedQuery("getAccountSearch", Account.class)
                    .setParameter("name", "%" + skeyword + "%")
                    .setParameter("code", skeyword)
                    .setParameter("profile", "%" + skeyword + "%")
                    .getResultList();
        } catch (NoResultException ex) {}

        try{
            acsearches = em.createNamedQuery("getAccountContributionSearch", AccountContribution.class)
                    .setParameter("content", "%" + skeyword + "%")
                    .setParameter("name", "%" + skeyword + "%")
                    .setParameter("code", "%" + skeyword + "%")
                    .getResultList();

        } catch (NoResultException ex) {}

        try{
            arsearches = em.createNamedQuery("getAccountReplySearch", AccountReply.class)
                    .setParameter("content", "%" + skeyword + "%")
                    .setParameter("name", "%" + skeyword + "%")
                    .setParameter("code", "%" + skeyword + "%")
                    .getResultList();

        } catch (NoResultException ex) {}

        em.close();

        request.setAttribute("asearches", asearches);
        request.setAttribute("acsearches", acsearches);
        request.setAttribute("arsearches", arsearches);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/search.jsp");
        rd.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().setAttribute("skeyword", request.getParameter("search"));
        response.sendRedirect(request.getContextPath() + "/account/search");
    }

}
