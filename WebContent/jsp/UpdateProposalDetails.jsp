
<%@page import="java.util.ArrayList"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tcs.ilp.fqpms.validate.*"%>
<%@page import="com.tcs.ilp.fqpms.bean.*"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Proposal Information</title>
<link rel="stylesheet" href="/FQPMS/css/Template.css" type="text/css">
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>


<script>
	$(function() {
		$("#tabs").tabs();
	});

	$(function() {
		$(".updatebutton, .resetbutton").button().click(function(event) {

		});
	});
	$("textarea").keyup(function(){
	    if($(this).text().length > 500){
	        var text = $(this).text();
	        $(this).text(text.substr(0, 500));   
	    }
	});
</script>
</head>
<body>
	<%
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
		} else {
			String username = (String)session.getAttribute("userName");
	%>

	<div id="mainframe">
		<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
		<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
		<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
		<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>


		<div id="rightContainer">

			<div id="tabs">
				<%
					HashMap<String, String> list = (HashMap<String, String>) request
								.getAttribute("msg");
						ProposalBean pb = (ProposalBean) request.getAttribute("bean");
						String type = "";
						if (pb != null && pb.getPid().startsWith("propi")) {
							type = "Body Injury";
							BodyInjuryProposalBean b = (BodyInjuryProposalBean) pb;
				%>

				<h2>Update Proposal</h2>
				<hr /> <a style="font-size:15px; float:right; margin-bottom:50px; margin-left: 3%; left: 58%;" id="backbutton1"
						href="javascript:history.go(-1)"
						>Back</a>
				
				<form name="f1" onsubmit="return validate1()" method="POST"
					action="/FQPMS/ProposalManageServlet?action=update2&&type=bodyinjury&&pid=<%=b.getPid()%>&&uid=<%=b.getUid()%>"
					onreset="resetBI()">
					<table style="width: 75%">
						<tr>
							<td class="left" >Proposal ID</td>
							<td class="right" style="font-style: italic;"><%=b.getPid()%></td>
						</tr>
						<tr>
							<td class="left">User Name</td>
							<td class="right" style="font-style: italic;"><%=username%></td>
						</tr>
						<tr>
							<td class="left">Proposal Type</td>
							<td class="right" style="font-style: italic;"><%=type%></td>
						</tr>

						<tr>
							<td class="left" style="width: 30%">Date of Injury</td>
							<td class="right"><select name="day"
								style="width: 25%; font-size: 12px" id="day"
								onchange="validDate()">
									<%
										String day = "";
												String month = "";
												String year = "";
												if (b != null) {
													day = b.getDate();
													month = b.getMonth();
													year = b.getYear();
												}
									%>
									<option value="0" <%if (day.equals("0")) {%> selected <%}%>>dd</option>
									<option value="1" <%if (day.equals("1")) {%> selected <%}%>>01</option>
									<option value="2" <%if (day.equals("2")) {%> selected <%}%>>02</option>
									<option value="3" <%if (day.equals("3")) {%> selected <%}%>>03</option>
									<option value="4" <%if (day.equals("4")) {%> selected <%}%>>04</option>
									<option value="5" <%if (day.equals("5")) {%> selected <%}%>>05</option>
									<option value="6" <%if (day.equals("6")) {%> selected <%}%>>06</option>
									<option value="7" <%if (day.equals("7")) {%> selected <%}%>>07</option>
									<option value="8" <%if (day.equals("8")) {%> selected <%}%>>08</option>
									<option value="9" <%if (day.equals("9")) {%> selected <%}%>>09</option>
									<option value="10" <%if (day.equals("10")) {%> selected <%}%>>10</option>
									<option value="11" <%if (day.equals("11")) {%> selected <%}%>>11</option>
									<option value="12" <%if (day.equals("12")) {%> selected <%}%>>12</option>
									<option value="13" <%if (day.equals("13")) {%> selected <%}%>>13</option>
									<option value="14" <%if (day.equals("14")) {%> selected <%}%>>14</option>
									<option value="15" <%if (day.equals("15")) {%> selected <%}%>>15</option>
									<option value="16" <%if (day.equals("16")) {%> selected <%}%>>16</option>
									<option value="17" <%if (day.equals("17")) {%> selected <%}%>>17</option>
									<option value="18" <%if (day.equals("18")) {%> selected <%}%>>18</option>
									<option value="19" <%if (day.equals("19")) {%> selected <%}%>>19</option>
									<option value="20" <%if (day.equals("20")) {%> selected <%}%>>20</option>
									<option value="21" <%if (day.equals("21")) {%> selected <%}%>>21</option>
									<option value="22" <%if (day.equals("22")) {%> selected <%}%>>22</option>
									<option value="23" <%if (day.equals("23")) {%> selected <%}%>>23</option>
									<option value="24" <%if (day.equals("24")) {%> selected <%}%>>24</option>
									<option value="25" <%if (day.equals("25")) {%> selected <%}%>>25</option>
									<option value="26" <%if (day.equals("26")) {%> selected <%}%>>26</option>
									<option value="27" <%if (day.equals("27")) {%> selected <%}%>>27</option>
									<option value="28" <%if (day.equals("28")) {%> selected <%}%>>28</option>
									<option value="29" <%if (day.equals("29")) {%> selected <%}%>>29</option>
									<option value="30" <%if (day.equals("30")) {%> selected <%}%>>30</option>
									<option value="31" <%if (day.equals("31")) {%> selected <%}%>>31</option>
							</select> / <select style="width: 25%; font-size: 12px" name="month"
								id="month" onchange="validDate()">
									<option value="-1" <%if (month.equals("-1")) {%> selected <%}%>>mmm</option>
									<option value="1" <%if (month.equals("1")) {%> selected <%}%>>JAN</option>
									<option value="2" <%if (month.equals("2")) {%> selected <%}%>>FEB</option>
									<option value="3" <%if (month.equals("3")) {%> selected <%}%>>MAR</option>
									<option value="4" <%if (month.equals("4")) {%> selected <%}%>>APR</option>
									<option value="5" <%if (month.equals("5")) {%> selected <%}%>>MAY</option>
									<option value="6" <%if (month.equals("6")) {%> selected <%}%>>JUN</option>
									<option value="7" <%if (month.equals("7")) {%> selected <%}%>>JUL</option>
									<option value="8" <%if (month.equals("8")) {%> selected <%}%>>AUG</option>
									<option value="9" <%if (month.equals("9")) {%> selected <%}%>>SEP</option>
									<option value="10" <%if (month.equals("10")) {%> selected <%}%>>OCT</option>
									<option value="11" <%if (month.equals("11")) {%> selected <%}%>>NOV</option>
									<option value="12" <%if (month.equals("12")) {%> selected <%}%>>DEC</option>
							</select> / <select style="width: 30%; font-size: 12px" name="year"
								id="year" onchange="validDate()">
									<option value="0" <%if (year.equals("0")) {%> selected <%}%>>yyyy</option>
									<option value="2008" <%if (year.equals("2008")) {%> selected
										<%}%>>2008</option>
									<option value="2009" <%if (year.equals("2009")) {%> selected
										<%}%>>2009</option>
									<option value="2010" <%if (year.equals("2010")) {%> selected
										<%}%>>2010</option>
									<option value="2011" <%if (year.equals("2011")) {%> selected
										<%}%>>2011</option>
									<option value="2012" <%if (year.equals("2012")) {%> selected
										<%}%>>2012</option>
									<option value="2013" <%if (year.equals("2013")) {%> selected
										<%}%>>2013</option>
							</select></td>
							<%
								String datemsg = "";
										if (list != null && list.get("date") != null) {
											datemsg = list.get("date");
										}
							%>
							<td class="error"><span id="datedisp" style="color: red"><%=datemsg%></span></td>
						</tr>

						<tr>
							<td class="left">Amount claimed</td>
							<%
								String amount = "";
										if (b != null) {
											amount = b.getAmountclaimed();
										}
							%>
							<td class="right"><input style="width: 100%" type="text"
								name="amount" id="amount" value="<%=amount%>"
								onblur="validAmount()" /></td>
							<%
								String amtmsg = "";
										if (list != null && list.get("amount") != null) {
											amtmsg = list.get("amount");
										}
							%>
							<td class="error"><span id="amountdisp" style="color: red"><%=amtmsg%></span></td>
						</tr>

						<tr>
							<td class="left">Nature of Injury</td>
							<%
								String ninjury = "";
										if (b != null) {
											ninjury = b.getNatureofinjury();
										}
							%>
							<td class="right"><textarea style="width: 100%"
									name="natureofinjury" id="nature" rows="5" onkeyup="textCounter(this,'counter3');" 
									onblur="validNatureInjury()"><%=ninjury%></textarea></td>
							<%
								String naturemsg = "";
										if (list != null && list.get("natureinjury") != null) {
											naturemsg = list.get("natureinjury");
										}
							%>
							<td class="error"><span id="naturedisp" style="color: red"><%=naturemsg%></span></td>
						</tr>
							<tr><td class="right"></td><td class="left">Charactors remain: <input disabled  maxlength="3" size="3" value="<%=(500-ninjury.length()) %>" id="counter3"></td></tr>

						<tr>
							<td class="left">Hospital Name</td>
							<%
								String hospital = "";
										if (b != null) {
											hospital = b.getHospitalname();
										}
							%>
							<td class="right"><select style="font-size: 12px; width:220px"name="hospitalname"
								id="hospitalname" onchange="validHospital()">
									<option value="">Please Select</option>
									<option value="Raffles Medical - Bugis"
										<%if (hospital.equals("Raffles Medical - Bugis")) {%> selected
										<%}%>>Raffles Medical - Bugis</option>
									<option value="Raffles Medical - Clementi"
										<%if (hospital.equals("Raffles Medical - Clementi")) {%>
										selected <%}%>>Raffles Medical - Clementi</option>
									<option value="Raffles Medical - Changi"
										<%if (hospital.equals("Raffles Medical - Changi")) {%>
										selected <%}%>>Raffles Medical - Changi</option>
									<option value="National University Hospital"
										<%if (hospital.equals("National University Hospital")) {%>
										selected <%}%>>National University Hospital</option>
									<option value="General Hospital"
										<%if (hospital.equals("General Hospital")) {%> selected <%}%>>General
										Hospital</option>
							</select></td>
							<%
								String hospitalmsg = "";
										if (list != null && list.get("hospital") != null) {
											naturemsg = list.get("hospital");
										}
							%>
							<td class="error"><span id="hospitaldisp" style="color: red"><%=hospitalmsg%></span></td>
						</tr>
					</table>

					<input type="submit" class="updatebutton" value="Update"
						style="margin-left: 15.5%; margin-top: 50px; margin-bottom: 50px; font-size: 15px" />
					<a href="/FQPMS/ProposalManageServlet?action=resetform" class="resetbutton" 
					style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px; font-size: 15px">Reset</a>	
				</form>


				<%
					} else if (pb != null && pb.getPid().startsWith("propv")) {
							type = "Vehicle Damage";
							VehicleDamageProposalBean b = (VehicleDamageProposalBean) pb;
				%>

				<h2>Update Proposal</h2>
				<hr /> <a style="font-size:15px; float:right; margin-bottom:50px; margin-left: 3%; left: 58%;" id="backbutton1"
						href="javascript:history.go(-1)"
						>Back</a>
				
				<form name="f2" onsubmit="return validate2()" method="POST"
					action="/FQPMS/ProposalManageServlet?action=update2&&type=vehicledamage&&pid=<%=b.getPid()%>&&uid=<%=b.getUid()%>"
					onreset="resetVD()">

					<table>
						<tr>
							<td class="left" style="width: 120px">Proposal ID:</td>
							<td class="right" style="font-style: italic;"><%=b.getPid()%></td>
						</tr>
						<tr>
							<td class="left">User Name</td>
							<td class="right"><%=username%></td>
						</tr>
						<tr>
							<td class="left">Proposal Type</td>
							<td class="right"><%=type%></td>
						</tr>
						<tr>
							<td class="left">License Number</td>
							<td class="right"><%if(b!=null){%><%=b.getVehicleid()%><input type="hidden" value="<%=b.getVehicleid()%>" name="vehicleid"><% } %> </td>
							
							
							<%
								String vidmsg = "";
										if (list != null && list.get("vid") != null) {
											vidmsg = list.get("vid");
										}
							%>
							<td class="error"><span id="vehicleiddisp"
								style="color: red"><%=vidmsg%></span></td>
						</tr>
						<tr>
							<td class="left">Date of Damage</td>
							<td class="right"><select name="day" id="day"
								style="width: 60px; font-size: 12px" onchange="validDate()">
									<%
										String day = "";
												String month = "";
												String year = "";
												if (b != null) {
													day = b.getDate();
													month = b.getMonth();
													year = b.getYear();
												}
									%>
									<option value="0" <%if (day.equals("0")) {%> selected <%}%>>dd</option>
									<option value="1" <%if (day.equals("1")) {%> selected <%}%>>01</option>
									<option value="2" <%if (day.equals("2")) {%> selected <%}%>>02</option>
									<option value="3" <%if (day.equals("3")) {%> selected <%}%>>03</option>
									<option value="4" <%if (day.equals("4")) {%> selected <%}%>>04</option>
									<option value="5" <%if (day.equals("5")) {%> selected <%}%>>05</option>
									<option value="6" <%if (day.equals("6")) {%> selected <%}%>>06</option>
									<option value="7" <%if (day.equals("7")) {%> selected <%}%>>07</option>
									<option value="8" <%if (day.equals("8")) {%> selected <%}%>>08</option>
									<option value="9" <%if (day.equals("9")) {%> selected <%}%>>09</option>
									<option value="10" <%if (day.equals("10")) {%> selected <%}%>>10</option>
									<option value="11" <%if (day.equals("11")) {%> selected <%}%>>11</option>
									<option value="12" <%if (day.equals("12")) {%> selected <%}%>>12</option>
									<option value="13" <%if (day.equals("13")) {%> selected <%}%>>13</option>
									<option value="14" <%if (day.equals("14")) {%> selected <%}%>>14</option>
									<option value="15" <%if (day.equals("15")) {%> selected <%}%>>15</option>
									<option value="16" <%if (day.equals("16")) {%> selected <%}%>>16</option>
									<option value="17" <%if (day.equals("17")) {%> selected <%}%>>17</option>
									<option value="18" <%if (day.equals("18")) {%> selected <%}%>>18</option>
									<option value="19" <%if (day.equals("19")) {%> selected <%}%>>19</option>
									<option value="20" <%if (day.equals("20")) {%> selected <%}%>>20</option>
									<option value="21" <%if (day.equals("21")) {%> selected <%}%>>21</option>
									<option value="22" <%if (day.equals("22")) {%> selected <%}%>>22</option>
									<option value="23" <%if (day.equals("23")) {%> selected <%}%>>23</option>
									<option value="24" <%if (day.equals("24")) {%> selected <%}%>>24</option>
									<option value="25" <%if (day.equals("25")) {%> selected <%}%>>25</option>
									<option value="26" <%if (day.equals("26")) {%> selected <%}%>>26</option>
									<option value="27" <%if (day.equals("27")) {%> selected <%}%>>27</option>
									<option value="28" <%if (day.equals("28")) {%> selected <%}%>>28</option>
									<option value="29" <%if (day.equals("29")) {%> selected <%}%>>29</option>
									<option value="30" <%if (day.equals("30")) {%> selected <%}%>>30</option>
									<option value="31" <%if (day.equals("31")) {%> selected <%}%>>31</option>
							</select> / <select style="width: 60px; font-size: 12px" name="month"
								id="month" onchange="validDate()">
									<option value="-1" <%if (month.equals("-1")) {%> selected <%}%>>mmm</option>
									<option value="1" <%if (month.equals("1")) {%> selected <%}%>>JAN</option>
									<option value="2" <%if (month.equals("2")) {%> selected <%}%>>FEB</option>
									<option value="3" <%if (month.equals("3")) {%> selected <%}%>>MAR</option>
									<option value="4" <%if (month.equals("4")) {%> selected <%}%>>APR</option>
									<option value="5" <%if (month.equals("5")) {%> selected <%}%>>MAY</option>
									<option value="6" <%if (month.equals("6")) {%> selected <%}%>>JUN</option>
									<option value="7" <%if (month.equals("7")) {%> selected <%}%>>JUL</option>
									<option value="8" <%if (month.equals("8")) {%> selected <%}%>>AUG</option>
									<option value="9" <%if (month.equals("9")) {%> selected <%}%>>SEP</option>
									<option value="10" <%if (month.equals("10")) {%> selected <%}%>>OCT</option>
									<option value="11" <%if (month.equals("11")) {%> selected <%}%>>NOV</option>
									<option value="12" <%if (month.equals("12")) {%> selected <%}%>>DEC</option>
							</select> / <select style="width: 60px; font-size: 12px" name="year"
								id="year" onchange="validDate()">
									<option value="0" <%if (year.equals("0")) {%> selected <%}%>>yyyy</option>
									<option value="2008" <%if (year.equals("2008")) {%> selected
										<%}%>>2008</option>
									<option value="2009" <%if (year.equals("2009")) {%> selected
										<%}%>>2009</option>
									<option value="2010" <%if (year.equals("2010")) {%> selected
										<%}%>>2010</option>
									<option value="2011" <%if (year.equals("2011")) {%> selected
										<%}%>>2011</option>
									<option value="2012" <%if (year.equals("2012")) {%> selected
										<%}%>>2012</option>
									<option value="2013" <%if (year.equals("2013")) {%> selected
										<%}%>>2013</option>
							</select></td>
							<%
								String datemsg = "";
										if (list != null && list.get("date") != null) {
											datemsg = list.get("date");
										}
							%>
							<td class="error"><span id="datedisp" style="color: red"><%=datemsg%></span></td>
						</tr>

						<tr>
							<td class="left">Amount claimed</td>
							<%
								String amount = "";
										if (b != null) {
											amount = b.getAmountclaimed();
										}
							%>
							<td class="right"><input style="width: 220px" type="text"
								name="amount" id="amount" value="<%=amount%>"
								onblur="validAmount()" /></td>
							<%
								String amtmsg = "";
										if (list != null && list.get("amount") != null) {
											amtmsg = list.get("amount");
										}
							%>
							<td class="error"><span id="amountdisp" style="color: red"><%=amtmsg%></span></td>
						</tr>
						<tr>
							<td class="left">Nature of Damage</td>
							<%
								String ndamage = "";
										if (b != null) {
											ndamage = b.getNatureofdamage();
										}
							%>
							<td class="right"><textarea style="width: 220px"
									name="natureofdamage" id="nature" rows="5"
									onblur="validNatureDamage()" onkeyup="textCounter(this,'counter1');" ><%=ndamage%></textarea></td>
							<%
								String naturemsg = "";
										if (list != null && list.get("naturedamage") != null) {
											naturemsg = list.get("naturedamage");
										}
							%>
							<td class="error"><span id="naturedisp" style="color: red"><%=naturemsg%></span></td>
						</tr>
						<tr><td class="right"></td><td class="left">Charactors remain: <input disabled  maxlength="3" size="3" value="<%=(500-ndamage.length()) %>" id="counter1"></td></tr>

					</table>

					<input type="submit" class="updatebutton" value="Update"
						style="margin-left: 15.5%; margin-top: 50px; margin-bottom: 50px; font-size: 15px" />
					<input type="reset" class="resetbutton" value="Reset"
						style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px; font-size: 15px" />
				</form>
				<%
					} else if (pb != null && pb.getPid().startsWith("propl")) {
							type = "Liability";
							LiabilityProposalBean b = (LiabilityProposalBean) pb;
				%>
				<h2>Update Proposal</h2>
				<hr /> <a style="font-size:15px; float:right; margin-bottom:50px; margin-left: 3%; left: 58%;" id="backbutton1"
						href="javascript:history.go(-1)"
						>Back</a>
				
				<form name="f3" onsubmit="return validate3()" method="POST"
					action="/FQPMS/ProposalManageServlet?action=update2&&type=liability&&pid=<%=b.getPid()%>&&uid=<%=b.getUid()%>"
					onreset="return resetLI()">
					<table>
						<tr>
							<td class="left" style="width: 120px">Proposal ID:</td>
							<td class="right" style="font-style: italic;"><%=b.getPid()%></td>
						</tr>
						<tr>
							<td class="left">User Name</td>
							<td class="right"><%=username%></td>
						</tr>
						<tr>
							<td class="left">Proposal Type</td>
							<td class="right"><%=type%></td>
						</tr>
						<tr>
							<td class="left">License Number</td>
							<td class="right"><%if(b!=null){%><%=b.getVehicleid()%><input type="hidden" value="<%=b.getVehicleid()%>" name="vehicleid"><%} %> </td>

							<%
								String vidmsg = "";
										if (list != null && list.get("vid") != null) {
											vidmsg = list.get("vid");
										}
							%>
							<td class="error"><span id="vehicleiddisp"
								style="color: red"><%=vidmsg%></span></td>
						</tr>
						<tr>
							<td class="left">Date of Accident</td>
							<td class="right"><select
								style="width: 60px; font-size: 12px" name="day" id="day"
								onchange="validDate()">
									<%
										String day = "";
												String month = "";
												String year = "";
												if (b != null) {
													day = b.getDate();
													month = b.getMonth();
													year = b.getYear();
												}
									%>
									<option value="0" <%if (day.equals("0")) {%> selected <%}%>>dd</option>
									<option value="1" <%if (day.equals("1")) {%> selected <%}%>>01</option>
									<option value="2" <%if (day.equals("2")) {%> selected <%}%>>02</option>
									<option value="3" <%if (day.equals("3")) {%> selected <%}%>>03</option>
									<option value="4" <%if (day.equals("4")) {%> selected <%}%>>04</option>
									<option value="5" <%if (day.equals("5")) {%> selected <%}%>>05</option>
									<option value="6" <%if (day.equals("6")) {%> selected <%}%>>06</option>
									<option value="7" <%if (day.equals("7")) {%> selected <%}%>>07</option>
									<option value="8" <%if (day.equals("8")) {%> selected <%}%>>08</option>
									<option value="9" <%if (day.equals("9")) {%> selected <%}%>>09</option>
									<option value="10" <%if (day.equals("10")) {%> selected <%}%>>10</option>
									<option value="11" <%if (day.equals("11")) {%> selected <%}%>>11</option>
									<option value="12" <%if (day.equals("12")) {%> selected <%}%>>12</option>
									<option value="13" <%if (day.equals("13")) {%> selected <%}%>>13</option>
									<option value="14" <%if (day.equals("14")) {%> selected <%}%>>14</option>
									<option value="15" <%if (day.equals("15")) {%> selected <%}%>>15</option>
									<option value="16" <%if (day.equals("16")) {%> selected <%}%>>16</option>
									<option value="17" <%if (day.equals("17")) {%> selected <%}%>>17</option>
									<option value="18" <%if (day.equals("18")) {%> selected <%}%>>18</option>
									<option value="19" <%if (day.equals("19")) {%> selected <%}%>>19</option>
									<option value="20" <%if (day.equals("20")) {%> selected <%}%>>20</option>
									<option value="21" <%if (day.equals("21")) {%> selected <%}%>>21</option>
									<option value="22" <%if (day.equals("22")) {%> selected <%}%>>22</option>
									<option value="23" <%if (day.equals("23")) {%> selected <%}%>>23</option>
									<option value="24" <%if (day.equals("24")) {%> selected <%}%>>24</option>
									<option value="25" <%if (day.equals("25")) {%> selected <%}%>>25</option>
									<option value="26" <%if (day.equals("26")) {%> selected <%}%>>26</option>
									<option value="27" <%if (day.equals("27")) {%> selected <%}%>>27</option>
									<option value="28" <%if (day.equals("28")) {%> selected <%}%>>28</option>
									<option value="29" <%if (day.equals("29")) {%> selected <%}%>>29</option>
									<option value="30" <%if (day.equals("30")) {%> selected <%}%>>30</option>
									<option value="31" <%if (day.equals("31")) {%> selected <%}%>>31</option>
							</select> / <select style="width: 60px; font-size: 12px" name="month"
								id="month" onchange="validDate()">
									<option value="-1" <%if (month.equals("-1")) {%> selected <%}%>>mmm</option>
									<option value="1" <%if (month.equals("1")) {%> selected <%}%>>JAN</option>
									<option value="2" <%if (month.equals("2")) {%> selected <%}%>>FEB</option>
									<option value="3" <%if (month.equals("3")) {%> selected <%}%>>MAR</option>
									<option value="4" <%if (month.equals("4")) {%> selected <%}%>>APR</option>
									<option value="5" <%if (month.equals("5")) {%> selected <%}%>>MAY</option>
									<option value="6" <%if (month.equals("6")) {%> selected <%}%>>JUN</option>
									<option value="7" <%if (month.equals("7")) {%> selected <%}%>>JUL</option>
									<option value="8" <%if (month.equals("8")) {%> selected <%}%>>AUG</option>
									<option value="9" <%if (month.equals("9")) {%> selected <%}%>>SEP</option>
									<option value="10" <%if (month.equals("10")) {%> selected <%}%>>OCT</option>
									<option value="11" <%if (month.equals("11")) {%> selected <%}%>>NOV</option>
									<option value="12" <%if (month.equals("12")) {%> selected <%}%>>DEC</option>
							</select> / <select style="width: 60px; font-size: 12px" name="year"
								id="year" onchange="validDate()">
									<option value="0" <%if (year.equals("0")) {%> selected <%}%>>yyyy</option>
									<option value="2008" <%if (year.equals("2008")) {%> selected
										<%}%>>2008</option>
									<option value="2009" <%if (year.equals("2009")) {%> selected
										<%}%>>2009</option>
									<option value="2010" <%if (year.equals("2010")) {%> selected
										<%}%>>2010</option>
									<option value="2011" <%if (year.equals("2011")) {%> selected
										<%}%>>2011</option>
									<option value="2012" <%if (year.equals("2012")) {%> selected
										<%}%>>2012</option>
									<option value="2013" <%if (year.equals("2013")) {%> selected
										<%}%>>2013</option>
							</select></td>
							<%
								String datemsg = "";
										if (list != null && list.get("date") != null) {
											datemsg = list.get("date");
										}
							%>
							<td class="error"><span id="datedisp" style="color: red"><%=datemsg%></span></td>
						</tr>

						<tr>
							<td class="left">Amount claimed</td>
							<%
								String amount = "";
										if (b != null) {
											amount = b.getAmountclaimed();
										}
							%>
							<td class="right"><input style="width: 220px" type="text"
								name="amount" id="amount" value="<%=amount%>"
								onblur="validAmount()" /></td>
							<%
								String amtmsg = "";
										if (list != null && list.get("amount") != null) {
											amtmsg = list.get("amount");
										}
							%>
							<td class="error"><span id="amountdisp" style="color: red"><%=amtmsg%></span></td>
						</tr>
						<tr>
							<td class="left">Type of Liability</td>
							<%
								String typeliability = "";
										if (b != null) {
											typeliability = b.getTypeofliability();
										}
							%>
							<td class="right"><select style="font-size: 12px"name="liability" id="liability"
								onchange="validLiability()">
									<option value="">Select</option>
									<option value="Body injury to third person"
										<%if (typeliability.equals("Body injury to third person")) {%>
										selected <%}%>>Body injury to third person</option>
									<option value="Third person vehicle damage"
										<%if (typeliability.equals("Third person vehicle damage")) {%>
										selected <%}%>>Third person vehicle damage</option>
							</select></td>
							<%
								String lmsg = "";
										if (list != null && list.get("typeliability") != null) {
											lmsg = list.get("typeliability");
										}
							%>
							<td class="error"><span id="liabilitydisp"
								style="color: red"><%=lmsg%></span></td>
						</tr>


					</table>

					<input type="submit" class="updatebutton" value="Update"
						style="margin-left: 15.5%; margin-top: 50px; margin-bottom: 50px; font-size: 15px" />
					<a href="/FQPMS/ProposalManageServlet?action=resetform" class="resetbutton" 
					style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px; font-size: 15px">Reset</a>	
						
<!-- 					<input type="reset" class="resetbutton" value="Reset" -->
<!-- 						style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px; font-size: 15px" /> -->
				</form>
				<%
					}
				%>
				
			</div>
			
		</div>
		<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
	</div>
	<%
		}
	%>
</body>
</html>