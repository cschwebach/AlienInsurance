<%-- 
    Document   : Error
    Created on : Apr 9, 2016, 11:15:26 PM
    Author     : Trent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h1>Manage Users.</h1>
        <div class="usersDiv">
            <h2>Users</h2>
            <c:choose>
                 <c:when test="${fn:length(users) > 0}">
                     <table class="usersTable">
                         <thead>
                             <tr>
                                 <th>User Name</th>
                                 <th>First Name</th>
                                 <th>Last Name</th>
                                 <th>Email</th>
                                 <th>Date Created</th>
                                 <th>Options</th>
                             </tr>
                         </thead>
                         <tbody>
                             <c:forEach var="user" items="${users}">
                                 <tr>
                                     <td>${user.userName}</td>
                                     <td>${user.firstName}</td>
                                     <td>${user.lastName}</td>
                                     <td>${user.email}</td>
                                     <td>${user.dateCreated}</td>
                                     <td><a href="PromoteUser?userName=${user.userName}">Promote</a></td>
                                 </tr>
                             </c:forEach>
                         </tbody>
                     </table>
                 </c:when>
                 <c:otherwise>
                     <p>No Users</p>
                 </c:otherwise>
             </c:choose>
        </div>
         <div class="usersDiv">
             <h2>Employees</h2>
             <c:choose>
                 <c:when test="${fn:length(employees) > 0}">
                     <table class="usersTable">
                         <thead>
                             <tr>
                                 <th>User Name</th>
                                 <th>First Name</th>
                                 <th>Last Name</th>
                                 <th>Email</th>
                                 <th>Date Created</th>
                                 <th>Options</th>
                             </tr>
                         </thead>
                         <tbody>
                             <c:forEach var="employee" items="${employees}">
                                 <tr>
                                     <td>${employee.userName}</td>
                                     <td>${employee.firstName}</td>
                                     <td>${employee.lastName}</td>
                                     <td>${employee.email}</td>
                                     <td>${employee.dateCreated}</td>
                                     <td><a href="DemoteEmployee?userName=${employee.userName}"">Demote</a></td>
                                 </tr>
                             </c:forEach>
                         </tbody>
                     </table>
                 </c:when>
                 <c:otherwise>
                     <p>No Employees</p>
                 </c:otherwise>
             </c:choose>
        </div>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />