<%-- 
    Document   : Billing
    Created on : Apr 25, 2016, 12:33:19 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<jsp:include page="/WEB-INF/includes/pagetop.jsp" />
<div class="billing">
    <h1>Billing Information:</h1>
    <p>We bill a month in advance so your first bill will be double of usual monthly installments!</p>
    <img class="money" src="Images/money.jpg" />
    <br />
    First Name: <input type="text" name="fname">
    <br />
    <br />
    Last Name: <input type="text" name="fname">
    <br />
    <br />
    Address: <input type="text" name="address">
    <br />
    <br />
    City:    <input type="text" name="city">
    <br />
    <br />
    State:   <input type="text" style=width:30px; name="state">
    <br />
    <br />
    Zip:    <input type="text" style=width:60px; name="zip">
    <br />
    <br />
    Name as it appears on card :    <input type="text" style="width: 200px;" name="cName">
    <br />
    <br />
    Card Number:  <input type="text" name="cardNum">
    <br />
    <br />
    Security Code(3 digit number on back of card):  <input type="text" style=width:30px; name="sCode" name="sCode">
    <br />
    <br />
    <img class="card" src="Images/credit3.jpg" />
    <br />
     <input style="padding-left: 20px; padding-top: 10px; padding-bottom: 10px; padding-right: 20px; background-color: yellow;" type="submit" value ="Submit">
     <br />
    <h1>Your welcome for the coverage! Covered from ET for life!!</h1>
</div>
<jsp:include page="/WEB-INF/includes/pagebottom.jsp" />

