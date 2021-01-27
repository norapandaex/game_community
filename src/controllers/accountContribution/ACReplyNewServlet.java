package controllers.accountContribution;

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
import models.CommunityMember;
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class ACReplyNewServlet
 */
@WebServlet("/acreply/new")
public class ACReplyNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ACReplyNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CommunityMember> mycommu = null;
        Account login_account = (Account)request.getSession().getAttribute("login_account");
        String ac_id = request.getParameter("id");
        String to_id = request.getParameter("tid");
        AccountContribution ac = null;
        Account a = null;

        EntityManager em = DBUtil.createEntityManager();

        if(ac_id == null){
            ac = (AccountContribution)request.getSession().getAttribute("contribution");
        } else {
            ac = em.find(AccountContribution.class, Integer.parseInt(ac_id));
        }

        if(to_id != null){
            a = em.find(Account.class, Integer.parseInt(to_id));
            request.getSession().setAttribute("to_account", a);
        } else {
            a = ac.getAccount();
            request.getSession().setAttribute("to_account", a);
        }

        List<AccountReply> areplies = em.createNamedQuery("getAllAccountReply", AccountReply.class)
                                              .setParameter("ac", ac)
                                              .getResultList();

        List<Favorite> fav = em.createNamedQuery("checkFav", Favorite.class)
                               .setParameter("account", login_account)
                               .getResultList();

        try{
            mycommu = em.createNamedQuery("getMyCommunity", CommunityMember.class)
                        .setParameter("account", login_account)
                        .getResultList();
        } catch(NoResultException ex)  {}

        em.close();

        request.setAttribute("areplies", areplies);
        request.setAttribute("fav", fav);
        request.getSession().setAttribute("mycommu", mycommu);
        request.getSession().setAttribute("myaccount", login_account);
        request.getSession().setAttribute("contribution", ac);
        request.getSession().setAttribute("pcheck", 1);
        request.getSession().setAttribute("p", 2);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/reply.jsp");
        rd.forward(request, response);
    }

}
