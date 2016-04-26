<%-- 
    Document   : Error
    Created on : Apr 9, 2016, 11:15:26 PM
    Author     : Trent
--%>
<%
    
  String[] wtf = new String[] { "WHY", "NO", "WORK" };  
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h1>View collections of blogs and comment on them.</h1>
        <c:forEach var="blog" items="${blogs}">
            <h2>${blog.title}</h2>
        </c:forEach>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />