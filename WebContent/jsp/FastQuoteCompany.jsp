
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tcs.ilp.fqpms.model.Vehicle"%>
<%@page import="com.tcs.ilp.fqpms.model.Insurance"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/FQPMS/css/Template.css">

<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>
<title>Fast Quote Proposal Management System</title>

<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
</head>
<body
	style="margin-left: 7%; margin-right: 7%; background-color: #f2f2f2">


	<div id="mainframe">
		<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
		<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
		<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
		<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>

		<div id="rightContainer">
			<div id="header_index">
				<div id="headerleft">
					<img src="../images/cs-insurance-asset-management.jpg"
						style="width: 100%; height: 100%">
				</div>
				<div id="headerright">
					<p style="margin-top: 10%; margin-left: 20px; font-style: italic; font-size: 14px">Our
						Mission:<br/> To provide top-class service to customer, to make the
						world safer</p>
					<p style="margin-top: 20%; margin-left: 20px; font-style: italic; font-size: 14px">Our
						Core Values: Integrity, Leading Change and Team Work</p>
				</div>
			</div>
			<div style="font-size: 15px;" id="accordion">

				<p>Fast Quote Company</p>
			</div>
			<p
				style="font-size: 16px; margin-left: 4.5%;padding-right:2%; margin-top: 3%; line-height: 25px">
				Fast Quotes is a professional insurance agency, servicing lawyers,
				real estate and mortgage brokers, bankers, commercial and
				residential customers in the metropolitan area for over 20 years.
				With more than fifty five companies, we offer current products and
				competitive programs. We look forward to servicing your business.<br/><br/>

				Protecting yourself and your family from the unexpected takes
				planning and the right insurance policies. If you're looking for
				great insurance coverage at affordable prices, you're in the right
				place.</p>

		</div>


		<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
	</div>


</body>
</html>