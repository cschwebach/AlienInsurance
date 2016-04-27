<%-- 
    Document   : CreateBlog
    Created on : Apr 26, 2016, 6:27:55 PM
    Author     : Trent
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h2>Create Blog</h2>
        <form method="POST">
            <label for="title">Title</label>
            <input type="text" name="title" /><br />
            <label for="content">Content</label>
            <div>
                <textarea type="text" name="content"></textarea><br />
            </div>
            
            <label for="disableComments">Disable Comments?</label>
            <input type="checkbox" name="disableComments" value="true" /><br />
            <input type="submit" value="Create" />
        </form>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />
