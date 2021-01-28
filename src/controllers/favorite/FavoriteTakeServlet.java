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
 * Servlet implementation class FavoriteTakeServlet
 */
@WebServlet("/favorite/take")
public class FavoriteTakeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteTakeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Account login_account = (Account)request.getSession().getAttribute("login_account");
            String ac_id = request.getParameter("aid");
            String ar_id = request.getParameter("arid");
            String cc_id = request.getParameter("cid");
            String cr_id = request.getParameter("crid");
            Favorite f = null;

            EntityManager em = DBUtil.createEntityManager();

            if(ac_id != null){
                AccountContribution ac = em.find(AccountContribution.class, Integer.parseInt(ac_id));
                f = em.createNamedQuery("getDeleteACFavorite", Favorite.class)
                        .setParameter("account", login_account)
                        .setParameter("ac", ac)
                        .getSingleResult();
            } else if(ar_id != null){
                AccountReply ar = em.find(AccountReply.class, Integer.parseInt(ar_id));
                f = em.createNamedQuery("getDeleteARFavorite", Favorite.class)
                        .setParameter("account", login_account)
                        .setParameter("ac", ar)
                        .getSingleResult();
            } else if(cc_id != null){
                CommunityContribution cc = em.find(CommunityContribution.class, Integer.parseInt(cc_id));
                f = em.createNamedQuery("getDeleteCCFavorite", Favorite.class)
                        .setParameter("account", login_account)
                        .setParameter("ac", cc)
                        .getSingleResult();
            } else if(cr_id != null){
                CommunityReply cr = em.find(CommunityReply.class, Integer.parseInt(cr_id));
                f = em.createNamedQuery("getDeleteCRFavorite", Favorite.class)
                        .setParameter("account", login_account)
                        .setParameter("ac", cr)
                        .getSingleResult();
            }

            em.getTransaction().begin();
            em.remove(f);
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
