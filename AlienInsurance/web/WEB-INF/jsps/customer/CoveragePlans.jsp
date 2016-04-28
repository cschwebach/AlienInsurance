<%-- 
    Document   : Error
    Created on : Apr 9, 2016, 11:15:26 PM
    Author     : Trent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
      <h1 style="padding-left:460px;">Your Coverage Options!</h1>
        <div class="claimArea">
            <h3>Individual Coverage</h3>
            <p>Covers body parts, alien abductions (leaving planet), and probing, also 
               includes personal belongings in case the out of this world beings steal from you during your encounter. $1200 deductible.</p>
            <a href='Billing' class="btnCov">Select</a>
        </div>
        <br />
        <div class="claimArea">
            <h3>Family Coverage</h3>
            <p>Covers all members of immediate family’s body parts, alien abductions
              (leaving planet), personal belongings, and also includes all of the above for pets. $13499 deductible</p>
           <a href='Billing' class="btnCov">Select</a>
        </div>
        <br />
        <div class="claimArea">
            <h3>Business Coverage</h3>
            <p>Covers all employees body parts, alien abductions (leaving planet), 
                business equipment, and business structure. $25000 deductible.</p>
            <a href='Billing' class="btnCov">Select</a>
        </div>
        <br />
        <div class="claimArea">
            <h3>Home Coverage</h3>
             <img class="alienHouse" style="width:300px; height:200px;" src="Images/ufohouse.jpg" />
            <p>Covers your home and all personal belongings, great coverage at a great 
               price for family who are visited by aliens frequently. Never can be too safe. $15000 deductible.</p>
            <a href='Billing' class="btnCov">Select</a>
        </div>
        <br />
        <br />
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />