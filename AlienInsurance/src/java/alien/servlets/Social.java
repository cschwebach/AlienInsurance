/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.servlets;

import alien.businesslogic.BlogManager;
import alien.commonobjects.misc.Blogs;
import alien.commonobjects.models.Blog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trent
 */
@WebServlet(name = "Social", urlPatterns = {"/Social"})
public class Social extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Blogs blogs = new Blogs();
        String test = "Default message";
        Blog blog = BlogManager.retrieveBlog(1);
        try {
            blogs.setBlogs(BlogManager.retrieveRelevantBlogs());
            test = "Success";
        } catch (Exception ex) {
            Logger.getLogger(Social.class.getName()).log(Level.SEVERE, null, ex);
            test = ex.getMessage();
        }
        
        if (blogs.isEmpty()) {
            request.getRequestDispatcher("/WEB-INF/jsps/errors/Generic.jsp").forward(request, response);
        } else {
            // request.setAttribute("blogs", new String[] { "Please", "Test", "Work" });
            
            /*blogs.getBlogs().stream().forEach((blog) -> {
                String name = blog.getTitle();
            });*/
            // request.setAttribute("test", test);
            request.getRequestDispatcher("/WEB-INF/jsps/social/Social.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsps/social/Social.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
