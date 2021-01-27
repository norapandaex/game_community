package controllers.member;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Community;
import models.CommunityMember;
import utils.DBUtil;

/**
 * Servlet implementation class MemberShowServlet
 */
@WebServlet("/member/show")
public class MemberShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Community c = em.find(Community.class, Integer.parseInt((String)request.getParameter("id")));

        List<CommunityMember> members = em.createNamedQuery("getAllMember", CommunityMember.class)
                                          .setParameter("c", c)
                                          .getResultList();

        long communitymember_count = (long)em.createNamedQuery("getMemberCount", Long.class)
                                        .setParameter("c", c)
                                        .getSingleResult();

        em.close();

        request.setAttribute("members", members);
        request.setAttribute("members_count", communitymember_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/show.jsp");
        rd.forward(request, response);
    }

}
