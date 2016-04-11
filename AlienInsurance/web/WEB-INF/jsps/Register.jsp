<%-- 
    Document   : Register
    Created on : Apr 9, 2016, 6:12:58 PM
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
        <h1>Account Registration</h1>
         <div>
            <p class="error"><%= error %></p>
            <form method="POST">
                <label for="userName">User Name </label><input type="text" name="userName" /><br />
                <label for="passwordOne">Password </label><input type="password" name="passwordOne" /><br />
                <label for="passwordTwo">Confirm Password </label><input type="password" name="passwordTwo" /><br />
                <label for="firstName">First Name </label><input type="text" name="firstName" /><br />
                <label for="lastName">Last Name </label><input type="text" name="lastName" /><br />
                <label for="email">Email </label><input type="text" name="email" /><br />
                <input type="submit" value="Register" />
            </form>
        </div>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />