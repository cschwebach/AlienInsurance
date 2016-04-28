/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.servlets;

import alien.businesslogic.BlogManager;
import alien.helpers.SessionAssister;
import alien.helpers.StringAssister;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Trent
 */
@WebServlet(name = "CreateBlog", urlPatterns = {"/CreateBlog"})
public class CreateBlog extends HttpServlet {
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
        SessionAssister.clearErrors(request);
        
        if (SessionAssister.loggedIn(request)) {
            request.getRequestDispatcher("/WEB-INF/jsps/social/CreateBlog.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsps/errors/LogInRequired.jsp").forward(request, response);
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
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        boolean disableComments = Boolean.parseBoolean(request.getParameter("disableComments"));
        
        String error = "";
        
        if (title.isEmpty()) {
            error = "Title is required.";
        } else if (content.isEmpty()) {
            error = "Content is required.";
        }
        
        if (error.isEmpty()) {
            title = StringAssister.checkBlank(title, 100);
            content = StringAssister.checkBlank(content, 1024);
            
            if (StringUtils.isEmptyOrWhitespaceOnly(content)) {
                content = "HELP ME THE GOVERNMENT'S MIND CONTROL WORKS";
            }
            
            BlogManager blogManager = new BlogManager(
                    SessionAssister.retrieveSessionUser(request).getUserName());
            
            if (blogManager.createBlog(title, content, disableComments)) {
                response.sendRedirect(request.getContextPath() + "/Social");
            } else {
                error = "There was a problem creating your blog.";
            }
        } 
        
        if (!error.isEmpty()) {
            SessionAssister.addError(request, error);
            request.getRequestDispatcher("/WEB-INF/jsps/social/CreateBlog.jsp").forward(request, response);
        }
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
