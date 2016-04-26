<%-- 
    Document   : sessionKiller
    Created on : Apr 21, 2016, 1:57:30 PM
    Author     : Trent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.invalidate();
        %>
        <h1>Hello World!</h1>
    </body>
</html>
