<%-- 
    Document   : pagetop
    Created on : Apr 10, 2016, 8:29:09 PM
    Author     : Trent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="alien.commonobjects.models.User"%>
<%@page import="javax.servlet.http.HttpSession" %>
<% 
    User currentUser = (User)session.getAttribute("user");
    
    String displayName = "";
    String link = "<a href='LogIn'>Log In</a>";
    
    if (null != currentUser) {
        displayName = " " + currentUser.getFirstName() + " " + currentUser.getLastName();
        link = "<a href='LogOut'>Log Out</a>";
    } 
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alien Insurance</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/standard.css" />
        <script src="javascript/standard.js"></script>
    </head>
    <body>
        <header>
            <a href="Home">
                <h1>Alien Insurance</h1>
                <p>They're out to get you!</p>
            </a> 
        </header>
        <jsp:include page="/WEB-INF/includes/nav.html" />
        <section>
            <p>Welcome<%= displayName %>! <%= link %></p>
        </section>
        <main>
