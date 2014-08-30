
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<%@page import="com.tcs.ilp.fqpms.manager.FQPMS_Login"%>
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
	<%
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
		} else {
			FQPMS_Login service = new FQPMS_Login();
			session.setAttribute("vehicleList", service
					.getVehicleByUserId((String) session
							.getAttribute("userId")));
			session.setAttribute("insuranceList", service
					.getInsuranceByUserId((String) session
							.getAttribute("userId")));
	%>

	<div id="mainframe">
		<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
		<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
		<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
		<div id="wrapper">
			<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>

			<div id="rightContainer">
				<div id="header_index">
					<div id="headerleft">
						<img src="../images/vehicle_banner.png"
							style="width: 100%; height: 100%">
					</div>
					<div id="headerright">
							<p style="margin-top: 10%; margin-left: 20px; font-style: italic; font-size:14px">Our
						Mission:<br/> To provide top-class service to customer, to make the
						world safer</p>
					<p style="margin-top: 20%; margin-left: 20px; font-style: italic; font-size: 14px">Our
						Core Values: Integrity, Leading Change and Team Work</p>
					</div>
				</div>

				<div style="font-size: 15px;" id="accordion">
					<h3>Insurance Information</h3>
					<div style="padding-bottom: 5%; height: auto">

						<%
							ArrayList<Insurance> insuranceList = (ArrayList<Insurance>) session
										.getAttribute("insuranceList");

								if (insuranceList != null && !insuranceList.isEmpty()) {
						%>
						<table
							style="border: 1px solid black; width: 98%; margin-top: 3%; font-size: 12px; text-align: center">
							<tr>
								<th style="width: 8%">ID</th>
								<th style="width: 25%">Type Of Insurance</th>
								<th style="width: 8%">Vehicle</th>
								<th style="width: 8%">Coverage Amount(USD)</th>
								<th style="width: 10%">Start Date (dd/mmm/yyyy)</th>
								<th style="width: 4%">Duration (years)</th>
								
								<th style="width: 8%">Premium (USD)</th>
								<th style="width: 8%">Balance (USD)</th>

							</tr>
							<%
								for (int i = 0; i < insuranceList.size(); i++) {
											if (insuranceList.get(i) != null) {
							%>
							<tr>
								<td style="height: 25px"><%=insuranceList.get(i).getInsuranceID()%></td>
								<td><%=insuranceList.get(i).getTypeOfInsurance()%></td>
								<%
									if (insuranceList.get(i).getLicenseNumber() != null) {
								%>
								<td><%=insuranceList.get(i)
										.getLicenseNumber()%></td>
								<%
									} else {
								%>
								<td>N.A.</td>
								<%
									}
													SimpleDateFormat sdf = new SimpleDateFormat(
															"dd/MMM/yyyy");
													String startDate = sdf.format(insuranceList.get(i)
															.getStartDate().getTime());
								%>
								<td><%=insuranceList.get(i).getCoverageAmount()%></td>
								<td><%=startDate%></td>
								<td><%=insuranceList.get(i).getDuration() / 12%></td>
								
								<td><%=insuranceList.get(i).getPremium()%></td>
								<td><%=insuranceList.get(i).getBalance()%></td>
							</tr>

							<%
								}
										}
							%>
						</table>
						<%
							} else {
						%>
						<p style="margin-left: 5%; margin-top: 3%">You have not
							registered any Insurance</p>
						<%
							}
						%>


					</div>

					<h3>Vehicle Information</h3>
					<div style="padding-bottom: 5%">

						<%
							ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) session
										.getAttribute("vehicleList");
								if (vehicleList != null && !vehicleList.isEmpty()) {
						%>

						<table
							style="border: 1px solid black; width: 75%; margin-top: 3%; font-size: 12px; text-align: center">
							<tr>
								<th>License Number</th>
								<th>Make</th>
								<th>Model</th>
								<th>Year Of Manufacture</th>
								<th>Vehicle Type</th>
								<th>Price(USD)</th>
							</tr>
							<%
								for (int i = 0; i < vehicleList.size(); i++) {
											if (vehicleList.get(i) != null) {
							%>
							<tr>
								<td style="height: 25px"><%=vehicleList.get(i).getLincesenumber()%></td>
								<td><%=vehicleList.get(i).getMake()%></td>
								<td><%=vehicleList.get(i).getModel()%></td>
								<td><%=vehicleList.get(i).getYearOfManufacture()%></td>
								<td><%=vehicleList.get(i).getVehicleType()%></td>
								<td><%=vehicleList.get(i).getPrice()%></td>
							</tr>
							<%
								}
										}
							%>
						</table>
						<%
							} else {
						%>
						<p style="margin-left: 5%; margin-top: 3%">You have not
							registered any Vehicle</p>
						<%
							}
						%>

					</div>
					<h3>News Highlights</h3>
					<div style="padding-bottom: 5%">
						<p style="font-size: 14px; margin-left: 4.5%;padding-right:2%; margin-top: 3%; line-height: 25px">
						<font style="font-weight: bold">Facilitating access to private health care in New York</font><br/><br/>
						 The
					         New York health care system is a two-tier system. Public
							services provide good-quality care, attracting patients from the
							rest of the States. As a result, there can be a long wait to see a
							doctor at a public hospital. Semi-private and private facilities
							have more availability, but charge high rates, creating a need
							for individual health insurance. In response, FQ has developed
							solutions enabling New York residents to access private health
							care and also be covered for serious illnesses.</p>
					</div>
				</div>

			</div>
		</div>

		<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
	</div>
	<%
		}
	%>

</body>
</html>