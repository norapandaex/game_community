package controllers.favorite;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.AccountContribution;
import models.AccountReply;
import models.CommunityContribution;
import models.CommunityReply;
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteAddServlet
 */
@WebServlet("/favorite/add")
public class FavoriteAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ac_id = request.getParameter("aid");
        String ar_id = request.getParameter("arid");
        String cc_id = request.getParameter("cid");
        String cr_id = request.getParameter("crid");
        AccountContribution ac = null;
        CommunityContribution cc = null;
        AccountReply ar = null;
        CommunityReply cr = null;

        EntityManager em = DBUtil.createEntityManager();

        Favorite f = new Favorite();

        f.setAccount((Account)request.getSession().getAttribute("login_account"));

        if(ac_id != null){
            ac = em.find(AccountContribution.class, Integer.parseInt(ac_id));
            f.setAccountcontribution(ac);
        } else if(ar_id != null){
            ar = em.find(AccountReply.class, Integer.parseInt(ar_id));
            f.setAccountreply(ar);
        } else if(cc_id != null){
            cc = em.find(CommunityContribution.class, Integer.parseInt(cc_id));
            f.setCommunitycontribution(cc);
        } else if(cr_id != null){
            cr = em.find(CommunityReply.class, Integer.parseInt(cr_id));
            f.setCommunityreply(cr);
        }

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();

        int p = (Integer)request.getSession().getAttribute("p");
        if(p == 1){
            response.sendRedirect(request.getContextPath() + "/home");
        } else if(p == 2) {
            response.sendRedirect(request.getContextPath() + "/acreply/new");
        } else if(p == 3) {
            response.sendRedirect(request.getContextPath() + "/community/show");
        } else if(p == 4) {
            response.sendRedirect(request.getContextPath() + "/ccreply/new");
        }
    }

}
