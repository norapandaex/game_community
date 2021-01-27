package controllers.accountContribution;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Account;
import models.AccountContribution;
import utils.DBUtil;
import utils.ImageTranslation;

/**
 * Servlet implementation class AccountContributionCreateServlet
 */
@WebServlet("/accountcontribution/create")
@MultipartConfig(maxFileSize = 1048576)
public class AccountContributionCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountContributionCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String _token = ImageTranslation.getParamVal(request.getPart("_token"));
        //if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            String content = ImageTranslation.getParamVal(request.getPart("content"));

            AccountContribution ac = new AccountContribution();

            ac.setAccount((Account)request.getSession().getAttribute("login_account"));
            ac.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            ac.setCreated_at(currentTime);
            ac.setDelete_flag(0);

            Part part = request.getPart("image");
            if(part != null){
                String name = ImageTranslation.getFileName(part);
                if (name != null && name.length() != 0) {

                    InputStream inputStream = part.getInputStream();
                    byte[] byteData = ImageTranslation.getByteArray(inputStream);
                    Blob blobimage = null;

                    try{
                        blobimage = new javax.sql.rowset.serial.SerialBlob(byteData);
                    } catch(SQLException e) {}

                    ac.setImage(blobimage);
                }
            }

            if(content.equals("") || content == null || part.equals("") || part == null) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("acontribution", ac);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/home.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(ac);
                em.getTransaction().commit();
                em.close();

                response.sendRedirect(request.getContextPath() + "/home");
            }
        }

    }
//}
