<%-- 
    Document   : ViewBlog
    Created on : Apr 26, 2016, 4:39:33 PM
    Author     : Trent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h1>${blog.title}</h1>
        <div>
            <p>${blog.content}</p>
        </div>
        <div>
            <c:choose>
                <c:when test="${!blog.disableComments}">
                    <form action="POST">
                        <label name="blogComment">Add Comment</label>
                        <input type="text" name="blogComment" /><br />
                        <input type="submit" value="Submit" />
                    </form>
                    <br />
                </c:when>
                <c:otherwise>
                    <p>Commenting has been disabled for this blog.</p>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${fn:length(blog.blogComments) > 0}">
                    
                    <c:forEach var="blogComment" items="${blog.blogComments}">
                        <div>
                            <p>${blogComment.content} ~ ${blogComment.createdBy} ${blogComment.dateCreated}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:if test="${!blog.disableComments}">
                        <p>No comments made.</p>
                    </c:if>
                </c:otherwise>
            </c:choose>
            
        </div>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />
