<%-- 
    Document   : LogIn
    Created on : Apr 9, 2016, 6:13:24 PM
    Author     : Trent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
<%
    String error = "";
    
    if (null != session.getAttribute("error")) {
        error = session.getAttribute("error").toString();
    }
%>
        <h1>Log In</h1>
        <div>
            <p class="error"><%= error %></p>
            <form method="POST">
                <label for="userName">User Name </label><input type="text" name="userName" /><br />
                <label for="password">Password </label><input type="password" name="password" /><br />
                <input type="submit" value="Log In" />
            </form>
            <a href='Register'>Register</a>
        </div>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />