<%-- 
    Document   : Error
    Created on : Apr 9, 2016, 11:15:26 PM
    Author     : Trent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h1>Current Happenings.</h1>
        <a href="CreateBlog" class="button">Create Blog</a>
        <table class="blogTable">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Date Created</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blog" items="${blogs}">
                    <tr onclick="location.href='ViewBlog?blogId=${blog.blogId}'">
                        <td>${blog.title}</td>
                        <td>${blog.createdBy}</td>
                        <td>${blog.dateCreated}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />