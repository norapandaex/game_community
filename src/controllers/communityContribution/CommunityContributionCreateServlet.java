package controllers.communityContribution;

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
import models.Community;
import models.CommunityContribution;
import utils.DBUtil;
import utils.ImageTranslation;

/**
 * Servlet implementation class CommunityContributionCreateServlet
 */
@WebServlet("/communitycontribution/create")
@MultipartConfig(maxFileSize = 1048576)
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
        String _token = ImageTranslation.getParamVal(request.getPart("_token"));
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            String content = ImageTranslation.getParamVal(request.getPart("content"));
            Community c = em.find(Community.class, Integer.parseInt((String)request.getSession().getAttribute("cid")));

            CommunityContribution cc = new CommunityContribution();

            cc.setAccount((Account)request.getSession().getAttribute("login_account"));
            cc.setCommunity(c);
            cc.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            cc.setCreated_at(currentTime);
            cc.setDelete_flag(0);

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

                    cc.setImage(blobimage);
                }
            }

            if(content.equals("") || content == null || part.equals("") || part == null) {
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

                request.getSession().setAttribute("c", c);
                response.sendRedirect(request.getContextPath() + "/community/show");
            }
        }
    }

}
