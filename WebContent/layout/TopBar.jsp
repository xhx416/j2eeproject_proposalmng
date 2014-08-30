
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Template.css">
<title>Insert title here</title>
</head>
<body>
	<%
		String errorMessage = (String) request.getAttribute("errorMessage");
	%>
	<%
		if (session == null || (session.getAttribute("userId") == null)
				|| errorMessage != null) {
	%>
	<span id="navitopbutton_right_first"> <a
		style="text-decoration: none; color: #336699"
		href="<%=request.getContextPath()%>/jsp/Login.jsp">Login </a></span>
	<%
		} else {
	%>

	<span id="navitopbutton_right_first"> <a
		style="text-decoration: none; color: #336699"
		href="<%=request.getContextPath()%>/LoginServlet?action=logout">
			Logout</a></span>
	<span id="welcomeSpan">Welcome <%=session.getAttribute("userName")%></span>
	<%
		}
	%>

</body>
</html>