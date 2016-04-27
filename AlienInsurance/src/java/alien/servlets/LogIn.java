/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.servlets;

import alien.businesslogic.UserManager;
import alien.helpers.SessionAssister;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trent
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {
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
            request.getRequestDispatcher("Home").forward(request, response);
        }
        
        request.getRequestDispatcher("/WEB-INF/jsps/admin/LogIn.jsp").forward(request, response);
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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String error = "";

        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();
        
        if (userName.isEmpty() || password.isEmpty()) {
            error = "Please enter your username and password.";
        } else {
            if (userManager.attemptLogIn(userName, password)) {
                session.setAttribute("user", userManager.getCurrentUser());
                request.getRequestDispatcher("Home").forward(request, response);
            } else {
                error = "Sorry, the information you entered was incorrect.";
            }
        }
        
        if (error.isEmpty()) {
            error = "Sorry, an error occurred while processing your request.";
        }
        
        SessionAssister.addError(request, error);
        request.getRequestDispatcher("/WEB-INF/jsps/admin/LogIn.jsp").forward(request, response);
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