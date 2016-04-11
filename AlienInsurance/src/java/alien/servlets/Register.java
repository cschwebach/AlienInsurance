/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.servlets;

import alien.businesslogic.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
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
        HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        
        if (null != session.getAttribute("user")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } 
        
        request.getRequestDispatcher("/WEB-INF/jsps/Register.jsp").forward(request, response);
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        
        String error = "";
        
        if (userName.isEmpty()) {
            error = "Please enter a username.";
        } else if (password.isEmpty()) {
            error = "Please enter a password.";
        } else if (firstName.isEmpty()) {
            error = "Please enter your first name.";
        } else if (lastName.isEmpty()) {
            error = "Please enter your last name.";
        } else if (email.isEmpty()) {
            error = "Please enter an email.";
        }
        
        if (password.length() < 5) {
            error = "Passwords must be at least 5 characters long.";
        }
        
        UserManager userManager = new UserManager();
        
        if (!userManager.isUser(userName)) {
            if (userManager.createUser(
            userName, password, firstName, lastName, email)) {
                if (userManager.attemptLogIn(userName, password)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", userManager.getCurrentUser());

                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    error = "Something went wrong attempting to log in.";
                }
            } else {
                error = "Something went wrong processing your account.";
            }
        } else {
            error = "That user already exists.";
        }

        if (!error.isEmpty()) {
            request.getSession().setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/jsps/Register.jsp").forward(request, response);
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
