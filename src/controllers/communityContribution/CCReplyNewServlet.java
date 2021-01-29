package controllers.communityContribution;

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
import models.Community;
import models.CommunityContribution;
import models.CommunityMember;
import models.CommunityReply;
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class CommunityContributionReplyServlet
 */
@WebServlet("/ccreply/new")
public class CCReplyNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CCReplyNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CommunityMember> mycommu = null;
        Account login_account = (Account)request.getSession().getAttribute("login_account");
        String cc_id = request.getParameter("id");
        String to_id = request.getParameter("tid");
        CommunityContribution cc = null;
        Account a = null;

        EntityManager em = DBUtil.createEntityManager();
        Community c = em.find(Community.class, Integer.parseInt((String)request.getSession().getAttribute("cid")));

        if(cc_id == null){
            cc = (CommunityContribution)request.getSession().getAttribute("contribution");
        } else {
            cc = em.find(CommunityContribution.class, Integer.parseInt(cc_id));
        }

        if(to_id != null){
            a = em.find(Account.class, Integer.parseInt(to_id));
            request.getSession().setAttribute("to_account", a);
        } else {
            a = cc.getAccount();
            request.getSession().setAttribute("to_account", a);
        }

        List<CommunityReply> creplies = em.createNamedQuery("getAllCommunityReply", CommunityReply.class)
                                              .setParameter("cc", cc)
                                              .getResultList();

        List<Favorite> fav = em.createNamedQuery("checkFav", Favorite.class)
                               .setParameter("account", login_account)
                               .getResultList();

        long communitymember_count = (long)em.createNamedQuery("getMemberCount", Long.class)
                .setParameter("c", c)
                .getSingleResult();

        try{
            mycommu = em.createNamedQuery("getMyCommunity", CommunityMember.class)
                        .setParameter("account", login_account)
                        .getResultList();
        } catch(NoResultException ex)  {}

        em.close();

        request.setAttribute("creplies", creplies);
        request.setAttribute("fav", fav);
        request.setAttribute("members_count", communitymember_count);
        request.getSession().setAttribute("mycommu", mycommu);
        request.getSession().setAttribute("myaccount", login_account);
        request.getSession().setAttribute("contribution", cc);
        request.getSession().setAttribute("pcheck", 1);
        request.getSession().setAttribute("p", 4);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/reply.jsp");
        rd.forward(request, response);
    }

}
