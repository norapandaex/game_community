package controllers.community;

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
import utils.DBUtil;

/**
 * Servlet implementation class CommunityShowServlet
 */
@WebServlet("/community/show")
public class CommunityShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CommunityContribution> contributions = null;
        long contributions_count = 0;
        long communitymember_count = 0;
        CommunityMember cm = null;
        List<CommunityMember> mycommu = null;

        EntityManager em = DBUtil.createEntityManager();

        Integer pcheck = (Integer)request.getSession().getAttribute("pcheck");
        if(pcheck == 2) {
            Community c = em.find(Community.class, Integer.parseInt((String)request.getSession().getAttribute("cid")));
            request.getSession().setAttribute("community", c);
        } else {
            Community c = em.find(Community.class, Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("community", c);
            request.getSession().setAttribute("cid", request.getParameter("id"));
        }

        Account login_account = (Account)request.getSession().getAttribute("login_account");
        Community c = em.find(Community.class, Integer.parseInt((String)request.getSession().getAttribute("cid")));



        try{
            contributions = em.createNamedQuery("getAllContributions", CommunityContribution.class)
                              .setParameter("c", c)
                              .getResultList();

            contributions_count = (long)em.createNamedQuery("getContributionsCount", Long.class)
                                          .setParameter("c", c)
                                          .getSingleResult();

            communitymember_count = (long)em.createNamedQuery("getMemberCount", Long.class)
                                            .setParameter("c", c)
                                            .getSingleResult();

            cm = em.createNamedQuery("checkAdd", CommunityMember.class)
                   .setParameter("account", login_account)
                   .setParameter("c", c)
                   .getSingleResult();

            mycommu = em.createNamedQuery("getMyCommunity", CommunityMember.class)
                        .setParameter("account", login_account)
                        .getResultList();
        } catch(NoResultException ex)  {}

        em.close();
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("contributions", contributions);
        request.setAttribute("contributions_count", contributions_count);
        request.setAttribute("members_count", communitymember_count);
        request.getSession().setAttribute("pcheck", 2);
        request.getSession().setAttribute("community_account", cm);
        request.getSession().setAttribute("mycommu", mycommu);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/show.jsp");
        rd.forward(request, response);
    }

}
