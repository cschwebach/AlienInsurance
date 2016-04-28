<%-- 
    Document   : Error
    Created on : Apr 9, 2016, 11:15:26 PM
    Author     : Trent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
        <h1>Approve Reject Claims</h1>

<div>
     <form class="claimForm" method="post"> 
         <br /> <br />
         <label for="ClaimID">Claim ID</label
         <input type="text" value="${claim.claimId}" id="ClaimID"> <br />
         <label for="Content">Content</label> 
         <input type="text" value="${claim.occurenceDate}" id="Content">  <br />
         <label for="OccurenceDate">Occurence Date</label>
         <input type="text" value="${claim.claimedBy}" id="OccurenceDate">  <br />
         <label for="DateCreated">Date Created</label> 
         <input type="text" value="${claim.dateCreated}" id="DateCreated">  <br />
         <label for="Approved">Approved</label> 
         <input type="text" value="${claim.Approved}" id="Approved">  <br />
         <label for="ProcessedBY">Processed By</label>
         <input type="text" value="${claim.processedBy}" id="ProcessedBy">  <br />
         <label for="DateProcessed">Date Processed</label> 
         <input type="text" value="${claim.dateProcessed}" id="DateProcessed">  <br />
         <label for="Status">Status</label> 
         <input type="text" value="${claim.active}" id="Status">  <br />  <br />
         <input type="submit"  id="approve" value="Approve" >
         <input type="submit"  id="edit" value="Edit" >
         <input type="submit"  id="decline" value="Decline" >
         <input type="submit"  id="back" value="Back" >
     </form> 
        
</div>
       
    <jsp:include page="/WEB-INF/includes/pagebottom.jsp" />