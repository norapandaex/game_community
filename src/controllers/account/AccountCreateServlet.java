package controllers.account;

import java.io.IOException;
import java.sql.Timestamp;
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
import models.Follow;
import models.validators.AccountValidators;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class AccountCreateServlet
 */
@WebServlet("/account/create")
public class AccountCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Account a = new Account();

            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String vpassword = request.getParameter("vpassword");

            a.setCode(id);
            a.setName(request.getParameter("name"));
            if(password.equals(vpassword)){
            a.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String) this.getServletContext().getAttribute("pepper")));
            } else {
                a.setPassword("error");
            }

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            a.setCreated_at(currentTime);
            a.setUpdated_at(currentTime);
            a.setDelete_flag(0);

            List<String> errors = AccountValidators.validate(a, true, true);
            if (errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("account", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();

                try{
                    a = em.createNamedQuery("checkLoginIdAndPassword", Account.class)
                          .setParameter("code", id)
                          .setParameter("pass", password)
                          .getSingleResult();
                } catch(NoResultException ex)  {}

                Follow f = new Follow();

                f.setFollow(a);
                f.setFollower(a);
                f.setFollowed_at(currentTime);

                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();

                em.close();

                request.getSession().setAttribute("login_account", a);
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }
    }
}
