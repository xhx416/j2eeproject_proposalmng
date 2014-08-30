
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Template.css">
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>

<script>
$(function() {
	$(".loginbutton, .resetbutton").button().click(function(event) {
	
	});
});

</script>
<title>Login Page</title>
</head>
<body style="margin-left: 7%; margin-right: 7%; background-color: #f2f2f2">
<div id="mainframe">
<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
<%
	String errorMessage = (String) session.getAttribute("errorMessage");
%>

<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>



<div id="rightContainer">
	

	<%if(session==null||(session.getAttribute("userId")==null)||errorMessage!=null){
	%>

<form name="loginForm" id="loginForm" method="POST"
	action="<%=request.getContextPath()%>/LoginServlet?action=login" onsubmit="return validateLogin()" onreset="resetLogin()">


<center><h2 style="margin-top:5%; margin-right: 13%"> User Login</h2></center>
<hr/>
<table style="margin-top: 3%; margin-left:25%" width="60%" >
	<tr>
		<td class="td1" style="height: 30px; width:20%; text-align: left" >User ID:</td>
		<td class="td2" style="width:30%"><input type="text" name="userId" id="userId" /></td>
		<td style="width:50%"><span id="login1" style="color: red; font-size: 12px"></span></td>
	</tr>
	<tr>
		<td class="td1" style=" text-align: left">Password:</td>
		<td class="td2"><input type="password" name="Password"
			id="Password" /></td>
			<td style="width:50%"><span id="login2" style="color: red; font-size: 12px"></span></td>
	</tr>
	<tr>
	<td colspan="2 ">
	<% if(errorMessage!=null){%>
	<font style="color: red"><%= errorMessage%></font>
	<%} %>
	</td>
	</tr>
</table>
	
	
<input class="loginbutton" style="margin-top:20px;margin-left:37.3%; font-size: 12px"type="submit" value="Login"/><input style="margin-left: 2%; margin-top:20px; font-size: 12px" class="resetbutton" type="reset" value="Reset" />
	        



</form>
<%}else{ response.sendRedirect("/FQPMS/jsp/FQPMS_index.jsp");%>
<p>Welcome <%=session.getAttribute("userId") %>!</p>
<p>You have successfully logged in!</p>
<%} %>


</div>

<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
</div>
</body>
</html>