package controllers.communityContribution;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.CommunityContribution;
import utils.DBUtil;

/**
 * Servlet implementation class CommunityContributionCreateServlet
 */
@WebServlet("/communitycontribution/create")
public class CommunityContributionCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityContributionCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            String content = request.getParameter("content");

            CommunityContribution cc = new CommunityContribution();

            cc.setAccount((Account)request.getSession().getAttribute("login_account"));

            cc.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            cc.setCreated_at(currentTime);
            cc.setDelete_flag(0);

            if(content.equals("") || content == null) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("ccontribution", cc);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/community/show.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(cc);
                em.getTransaction().commit();
                em.close();

                response.sendRedirect(request.getContextPath() + "/community/show");
            }
        }
    }

}
