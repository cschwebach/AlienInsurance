<%-- 
    Document   : Error
    Created on : Apr 9, 2016, 11:15:26 PM
    Author     : Trent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h1>View collections of blogs and comment on them.</h1>
        <a href="CreateBlog">Create Blog</a>
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Date Created</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blog" items="${blogs}">
                    <tr>
                        <td><a href="ViewBlog?blogId=${blog.blogId}">${blog.title}</a></td>
                        <td>${blog.createdBy}</td>
                        <td>${blog.dateCreated}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:out value="${fn:length(blogs)}" />
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />