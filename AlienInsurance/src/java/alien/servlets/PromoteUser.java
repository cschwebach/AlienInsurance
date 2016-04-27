/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.servlets;

import alien.businesslogic.AdminManager;
import alien.commonobjects.models.UserTypes;
import alien.helpers.SessionAssister;
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
@WebServlet(name = "PromoteUser", urlPatterns = {"/PromoteUser"})
public class PromoteUser extends HttpServlet {
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
        
        if (SessionAssister.isRole(request, UserTypes.Administrator)) {
            String error = "";
            
            String userName = request.getParameter("userName");
            
            if (userName.isEmpty()) {
                error = "No valid user found.";
            } else {
                AdminManager adminManager = new AdminManager(
                    SessionAssister.retrieveSessionUser(request).getUserName());
            
                if (!adminManager.promoteUser(userName)) {
                    error = "Promoting user failed.";
                }
            }
            
            if (!error.isEmpty()) {
                SessionAssister.addError(request, error);
            }
            
            response.sendRedirect("Administration");
        } else {
            request.getRequestDispatcher("/WEB-INF/jsps/errors/AdminOnly.jsp").forward(request, response);
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
        request.getRequestDispatcher("/WEB-INF/jsps/errors/AdminOnly.jsp").forward(request, response);
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
