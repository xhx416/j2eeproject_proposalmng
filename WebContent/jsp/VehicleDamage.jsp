
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
<%@page import="com.tcs.ilp.fqpms.bean.ProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.Insurance"%>

<html>
<head>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
$("textarea").keyup(function(){
    if($(this).text().length > 500){
        var text = $(this).text();
        $(this).text(text.substr(0, 500));   
    }
});
</script>
<title>Insert title here</title>
</head>
<body>
<%if(session==null||session.getAttribute("userId")==null){ response.sendRedirect("/FQPMS/jsp/Login.jsp");}else{ %>
<%ArrayList<Insurance> availableInsuranceList = (ArrayList<Insurance>)session.getAttribute("availableInsuranceList");
boolean isValidToSubmit = false;	
if(availableInsuranceList!=null) {
	for(Insurance insurance: availableInsuranceList){
		if(insurance!=null &&insurance.getTypeOfInsurance().equals("Vehicle Damage Coverage")){
				isValidToSubmit = true; 
				break;
		}
		}
	}
		%>
<div>
<%
						boolean isVvalidate = (Boolean) session.getAttribute("isVValid");
                      if (isVvalidate) {
                        if(!isValidToSubmit){
					%>
					
					<p style="font-size: 15px; color:#336699; line-height: 25px">
			 Dear Customer,<br/><br/>
			You currently cannot submit vehicle damage proposal because you have one in pending, <br/>
			Please kindly wait for our system to process your submitted proposal or you can <br/>
			delete/manage your current pending proposal to process further
			</p>
					
					<%
					}else{
					%>
					<h2>Vehicle Damage Proposal</h2>
					
					<p class="proposalDescription">
					
					Vehicle Damage Proposal is for the case when your vehicle is damaged during an accident, 
					a quote will be generated once the proposal is approved.
					</p>
					
					<form id="veform" name="f2" onsubmit="return validate2()" method="POST"
						action="/FQPMS/ProposalSubmitServlet?type=vehicledamage"
						onreset="resetVD()">
						<hr></hr>

						<%
							HashMap<String, String> list_v = (HashMap<String, String>) request
									.getAttribute("msg2");
							ProposalBean proposalbean = (ProposalBean) request
									.getAttribute("bean");
							VehicleDamageProposalBean v =null;
							if(proposalbean instanceof VehicleDamageProposalBean){
								v = (VehicleDamageProposalBean)proposalbean;
							}
						%>
						<table style="margin-top:3%; width:85%;">
							<tr>
								<td class="right" style="width:20%">License Number</td>
								<td class="left"><select style="font-size: 12px; width:auto" name="vehicleid" id="vehicleid2"
									onchange="validVehicleid2()">
										<%
									String vid = "";
								ArrayList<String> vidlist = new ArrayList<String>();
								vidlist=(ArrayList<String>)session.getAttribute("vehicleLicenseNumberforVD");
										if (v != null) {
											vid = v.getVehicleid();
										}
								%>
								<option value="" <%if(vid==null || vid.equals("")) {%> selected <%}%>>Select</option>
								<%for(String vehicleId:vidlist) {%>
								<option value=<%=vehicleId %> <%if (vid.equals(vehicleId)) {%>
									selected <%}%>><%=vehicleId %></option>
								<%} %>
						</select></td>
								<%
									String vidmsg = "";
									if (list_v != null && list_v.get("vid") != null) {
										vidmsg = list_v.get("vid");
									}
								%>
								<td class="error"><span id="vehicleiddisp2"
									style="color: red"><%=vidmsg%></span></td>
							</tr>

							<tr>
								<td class="right">Date of Damage</td>
								<td class="left"><select  style="width:60px; font-size: 12px" name="day" id="day2"
									onchange="checkDate2()">
										<%
											String day_v = "";
											String month_v = "";
											String year_v = "";
											if (v != null) {
												day_v = v.getDate();
												month_v = v.getMonth();
												year_v = v.getYear();
											}
										%>
										<option value="0" <%if (day_v.equals("0")) {%> selected <%}%>>dd</option>
										<option value="1" <%if (day_v.equals("1")) {%> selected <%}%>>01</option>
										<option value="2" <%if (day_v.equals("2")) {%> selected <%}%>>02</option>
										<option value="3" <%if (day_v.equals("3")) {%> selected <%}%>>03</option>
										<option value="4" <%if (day_v.equals("4")) {%> selected <%}%>>04</option>
										<option value="5" <%if (day_v.equals("5")) {%> selected <%}%>>05</option>
										<option value="6" <%if (day_v.equals("6")) {%> selected <%}%>>06</option>
										<option value="7" <%if (day_v.equals("7")) {%> selected <%}%>>07</option>
										<option value="8" <%if (day_v.equals("8")) {%> selected <%}%>>08</option>
										<option value="9" <%if (day_v.equals("9")) {%> selected <%}%>>09</option>
										<option value="10" <%if (day_v.equals("10")) {%> selected <%}%>>10</option>
										<option value="11" <%if (day_v.equals("11")) {%> selected <%}%>>11</option>
										<option value="12" <%if (day_v.equals("12")) {%> selected <%}%>>12</option>
										<option value="13" <%if (day_v.equals("13")) {%> selected <%}%>>13</option>
										<option value="14" <%if (day_v.equals("14")) {%> selected <%}%>>14</option>
										<option value="15" <%if (day_v.equals("15")) {%> selected <%}%>>15</option>
										<option value="16" <%if (day_v.equals("16")) {%> selected <%}%>>16</option>
										<option value="17" <%if (day_v.equals("17")) {%> selected <%}%>>17</option>
										<option value="18" <%if (day_v.equals("18")) {%> selected <%}%>>18</option>
										<option value="19" <%if (day_v.equals("19")) {%> selected <%}%>>19</option>
										<option value="20" <%if (day_v.equals("20")) {%> selected <%}%>>20</option>
										<option value="21" <%if (day_v.equals("21")) {%> selected <%}%>>21</option>
										<option value="22" <%if (day_v.equals("22")) {%> selected <%}%>>22</option>
										<option value="23" <%if (day_v.equals("23")) {%> selected <%}%>>23</option>
										<option value="24" <%if (day_v.equals("24")) {%> selected <%}%>>24</option>
										<option value="25" <%if (day_v.equals("25")) {%> selected <%}%>>25</option>
										<option value="26" <%if (day_v.equals("26")) {%> selected <%}%>>26</option>
										<option value="27" <%if (day_v.equals("27")) {%> selected <%}%>>27</option>
										<option value="28" <%if (day_v.equals("28")) {%> selected <%}%>>28</option>
										<option value="29" <%if (day_v.equals("29")) {%> selected <%}%>>29</option>
										<option value="30" <%if (day_v.equals("30")) {%> selected <%}%>>30</option>
										<option value="31" <%if (day_v.equals("31")) {%> selected <%}%>>31</option>
								</select> / <select  style="width:60px; font-size: 12px" name="month" id="month2" onchange="checkDate2()">
										<option value="-1" <%if (month_v.equals("-1")) {%> selected <%}%>>mmm</option>
										<option value="1" <%if (month_v.equals("1")) {%> selected <%}%>>JAN</option>
										<option value="2" <%if (month_v.equals("2")) {%> selected <%}%>>FEB</option>
										<option value="3" <%if (month_v.equals("3")) {%> selected <%}%>>MAR</option>
										<option value="4" <%if (month_v.equals("4")) {%> selected <%}%>>APR</option>
										<option value="5" <%if (month_v.equals("5")) {%> selected <%}%>>MAY</option>
										<option value="6" <%if (month_v.equals("6")) {%> selected <%}%>>JUN</option>
										<option value="7" <%if (month_v.equals("7")) {%> selected <%}%>>JUL</option>
										<option value="8" <%if (month_v.equals("8")) {%> selected <%}%>>AUG</option>
										<option value="9" <%if (month_v.equals("9")) {%> selected <%}%>>SEP</option>
										<option value="10" <%if (month_v.equals("10")) {%> selected
											<%}%>>OCT</option>
										<option value="11" <%if (month_v.equals("11")) {%> selected
											<%}%>>NOV</option>
										<option value="12" <%if (month_v.equals("12")) {%> selected
											<%}%>>DEC</option>
								</select> / <select  style="width:70px; font-size: 12px" name="year" id="year2" onchange="checkDate2()">
										<option value="0" <%if (year_v.equals("0")) {%> selected <%}%>>yyyy</option>
										<option value="2008" <%if (year_v.equals("2008")) {%> selected
											<%}%>>2008</option>
										<option value="2009" <%if (year_v.equals("2009")) {%> selected
											<%}%>>2009</option>
										<option value="2010" <%if (year_v.equals("2010")) {%> selected
											<%}%>>2010</option>
										<option value="2011" <%if (year_v.equals("2011")) {%> selected
											<%}%>>2011</option>
										<option value="2012" <%if (year_v.equals("2012")) {%> selected
											<%}%>>2012</option>
										<option value="2013" <%if (year_v.equals("2013")) {%> selected
											<%}%>>2013</option>
								</select></td>
								<%
									String datemsg = "";
									if (list_v != null && list_v.get("date") != null) {
										datemsg = list_v.get("date");
									}
								%>
								<td class="error"><span id="datedisp2" style="color: red"><%=datemsg%></span></td>
							</tr>

							<tr>
								<td class="right">Claim Amount(USD)</td>
								<%
									String amount = "";
									if (v != null) {
										amount = v.getAmountclaimed();
									}
								%>
								<td class="left"><input maxlength="11" type="text" style="width:220px" name="amount"
									id="amount2" value="<%=amount%>" onblur="validAmount2()" /></td>
								<%
									String amtmsg = "";
									if (list_v != null && list_v.get("amount") != null) {
										amtmsg = list_v.get("amount");
									}
								%>
								<td class="error"><span id="amountdisp2" style="color: red"><%=amtmsg%></span></td>
							</tr>

							<tr>
								<td class="right">Nature of Damage</td>
								<%
									String ndamage = "";
									if (v != null) {
										ndamage = v.getNatureofdamage();
									}
								%>
								<td class="left"><textarea style="width:220px"name="natureofdamage"
										id="nature2" rows="5" onblur="validNatureDamage()" onkeyup="textCounter(this,'counter2');" onkeypress="checkMaxLength2(this)"><%=ndamage%></textarea></td>
								
								<%
									String naturemsg = "";
									if (list_v != null && list_v.get("naturedamage") != null) {
										naturemsg = list_v.get("naturedamage");
									}
								%>
								<td class="error"><span id="naturedisp2" style="color: red"><%=naturemsg%></span></td>
							</tr>
							<tr><td class="right"></td><td class="left">Charactors remain: <input disabled  maxlength="3" size="3" value="500" id="counter2"></td></tr>
							

						</table>

                      <input type="submit" class="submitbutton" value="Submit" style=" margin-left: 15.5%; margin-top: 50px; margin-bottom: 50px;"/>
                       <%if(proposalbean==null){ %>
                    <input type="reset" class="resetbutton" value="Reset" style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px;" />
                    <%}else{ %>
                    <a href="/FQPMS/ProposalSubmitServlet?execute=reset&&type=vehicledamage" class="resetbutton" style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px;" >Reset</a>
                    <%} %>

					</form>
					<%} 
                     }else{%>
			 
					<p style="font-size: 15px; color:#336699; line-height: 25px">
						Dear Customer, <br/><br/>
						System detects that you are not eligible for raising a Vehicle Damage Proposal.<br/>
						Please register a <font style="text-decoration: underline;">Vehicle Damage Insurance </font>via Fast Quote User Management System first,<br/>	
						or you can check with our staff at the counter if have you have already registered one.	
					</p>
					<%} %>


</div>
<%} %>
</body>
</html>