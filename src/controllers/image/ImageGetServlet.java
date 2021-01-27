package controllers.image;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AccountContribution;
import models.CommunityContribution;
import utils.DBUtil;

/**
 * Servlet implementation class ImageGetServlet
 */
@WebServlet("/getImage")
@MultipartConfig(maxFileSize = 1048576)
public class ImageGetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedInputStream bin=null;
        BufferedOutputStream bout=null;
        InputStream in =null;
        CommunityContribution cc = null;
        AccountContribution ac = null;

        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();

        EntityManager em = DBUtil.createEntityManager();

        String cid = request.getParameter("cid");
        if(cid != null) {
            cc = em.find(CommunityContribution.class, Integer.parseInt(cid));
        }

        String aid = request.getParameter("aid");
        if(aid != null) {
            ac = em.find(AccountContribution.class, Integer.parseInt(aid));
        }


        try {
            if(cc != null){
                Blob cb = cc.getImage();
                in = cb.getBinaryStream();
            } else if(ac != null){
                Blob ab = ac.getImage();
                in = ab.getBinaryStream();
            }

            bin = new BufferedInputStream(in);
            bout = new BufferedOutputStream(out);

            int ch=0;
            while((ch=bin.read())!=-1)
            {
                bout.write(ch);
            }

        } catch (SQLException ex) {
        }finally{
            try{
                if(bin!=null)bin.close();
                if(in!=null)in.close();
                if(bout!=null)bout.close();
                if(out!=null)out.close();
            }catch(IOException ex){
            }
        }
        em.close();
    }




}
