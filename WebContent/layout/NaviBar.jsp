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
<div id ="navibar">
	<span id="navitopbutton_left_first" >
	<a style="text-decoration: none; color:#336699"href="<%=request.getContextPath()%>/jsp/FQPMS_index.jsp">Home</a></span>
	<span class="navitopbutton_left" >
	<a style="text-decoration: none; color:#336699"href="<%=request.getContextPath()%>/jsp/FastQuoteCompany.jsp">Fast Quote Company</a></span>
    <span class="navitopbutton_left" >
	<a style="text-decoration: none; color:#336699"href="<%=request.getContextPath()%>/jsp/FastQuoteProduct.jsp">Fast Quote Product</a></span>
	<span class="navitopbutton_left" >
	<a style="text-decoration: none; color:#336699"href="<%=request.getContextPath()%>/jsp/ContactUs.jsp">Contact Us</a></span>
    </div>
</body>
</html>