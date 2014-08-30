<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" type="text/css"
	href="/FQPMS/css/Template.css">
</head>
<body>
<%if(session==null||session.getAttribute("userId")==null){ response.sendRedirect("/FQPMS/jsp/Login.jsp");}else{%>

<div id="mainframe">
<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>
		<div id="rightContainer">
		
			<%
				String msg = (String) request.getAttribute("error");
			%>
			<p style="margin-top: 5%; margin-left:10%; font-size: 15px; color:#336699">
			<font style="font-size: 18px; color:red">System Error:</font><br/><br/>
			<%=msg%></p>
		</div>

		<div id="footer">
			<jsp:include page="/layout/Footer.jsp" />
		</div>
	</div>
<%} %>
</body>
</html>