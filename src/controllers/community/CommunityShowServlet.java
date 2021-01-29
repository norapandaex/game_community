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
import models.Favorite;
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
        List<CommunityMember> mycommu = null;
        CommunityMember cm = null;
        Community c = null;
        Integer l = (Integer)request.getSession().getAttribute("l");
        Account login_account = (Account)request.getSession().getAttribute("login_account");

        EntityManager em = DBUtil.createEntityManager();

        if(l != null) {
            c = em.find(Community.class, Integer.parseInt((String)request.getSession().getAttribute("cid")));
            request.getSession().setAttribute("community", c);
            request.getSession().removeAttribute("l");
        } else {
            if(request.getParameter("id") != null){
                c = em.find(Community.class, Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("cid", request.getParameter("id"));
            } else {
                c = (Community)request.getSession().getAttribute("cid");
            }

            request.getSession().setAttribute("community", c);
        }

        //Community c = em.find(Community.class, Integer.parseInt((String)request.getSession().getAttribute("cid")));

        List<Favorite> fav = em.createNamedQuery("checkFav", Favorite.class)
                .setParameter("account", login_account)
                .getResultList();

        long contributions_count = (long)em.createNamedQuery("getContributionsCount", Long.class)
                .setParameter("c", c)
                .getSingleResult();

        long communitymember_count = (long)em.createNamedQuery("getMemberCount", Long.class)
                .setParameter("c", c)
                .getSingleResult();

        try{
            contributions = em.createNamedQuery("getAllContributions", CommunityContribution.class)
                    .setParameter("c", c)
                    .getResultList();

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
        request.setAttribute("community_account", cm);
        request.setAttribute("fav", fav);
        request.setAttribute("mycommu", mycommu);
        request.getSession().setAttribute("pcheck", 2);
        request.getSession().setAttribute("p", 3);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/show.jsp");
        rd.forward(request, response);
    }

}
