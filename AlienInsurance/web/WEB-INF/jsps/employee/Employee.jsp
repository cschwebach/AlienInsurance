<%-- 
    Document   : Employee
    Created on : Apr 27, 2016, 11:15:26 PM
    Author     : Ibrahim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />

<jsp:useBean id="claim" class="alien.businesslogic.ClaimManager"/>

   <h2> Claims List </h2>
   <p>${claim.message}</p>
   <p>${claim.claimId}</p>
   <table style="width:100%" border="1">
       <tr>
           <th> Claim ID </th>
           <th> Content </th>
           <th> Occurrence date </th>
           <th> Claimed By </th>
           <th> Date Created </th>
           <th> Approved By </th>  
           <th> Processed By </th>
           <th> Date Processed </th>         
       </tr> 
      
         <c:forEach items="${claim.claimList}" var="iterator">   
          <tr>
            <td> ${iterator.claimId} </td>
           
            <td> ${iteraror.content} </td>
            <td> ${iterator.occurenctDate} </td>
            <td> ${iterator.claimedBy}  </td>
            <td> ${iterator.dateCreated} </td>
            <td> ${iterator.approvedBy} </td>  
            <td> ${iterator.processedBy}  </td>
            <td> ${iterator.dateProcessed} </td> 
          </tr>    
         </c:foreach>
      
       
   </table>     

<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />>
