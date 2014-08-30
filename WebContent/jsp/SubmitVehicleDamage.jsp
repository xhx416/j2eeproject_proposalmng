
<%@page import="com.tcs.ilp.fqpms.model.VehicleDamageProposal"%>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.LiabilityProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/FQPMS/css/Template.css"></link>
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>
<script>
  $(function() {
    $( "#tabs" ).tabs({active:1});
  });
  </script>


<title>Fast Quote Proposal Management System</title>
</head>
<body>
<%if(session==null||session.getAttribute("userId")==null){ response.sendRedirect("/FQPMS/jsp/Login.jsp");}else{%>

	<div id="mainframe">
		<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
		<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
		<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
		<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>

		<div id="rightContainer">
			<div id="tabs" style="font-size: 15px">
				<ul>
					<li><a href="#tabs-1"
						onclick="return <%=(session.getAttribute("isBValid"))%>">Body
							Injury Proposal</a></li>
					<li><a href="#tabs-2"
						onclick="return <%=(session.getAttribute("isVValid"))%>">Vehicle
							Damage Proposal</a></li>
					<li><a href="#tabs-3"
						onclick="return <%=(session.getAttribute("isLValid"))%>">Liability
							Proposal</a></li>
				</ul>
				<div id="tabs-1" style="height: auto">
					<jsp:include page="BodyInjury.jsp" />
				</div>
				<div id="tabs-2" style="height: auto">
					<jsp:include page="VehicleDamage.jsp" />
				</div>
				<div id="tabs-3">
					<jsp:include page="Liability.jsp" />
				</div>
			</div>

		</div>
		<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
	</div>
	<%} %>
</body>
</html>