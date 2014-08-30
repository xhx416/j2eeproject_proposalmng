
<%@page import="com.tcs.ilp.fqpms.model.BodyInjuryProposal"%>
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
<%@page import="com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.Insurance"%>

<html>
<head>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>

<script>
$(function() {
		$(".submitbutton, .resetbutton").button().click(function(event) {
		});
	});
$("textarea").keyup(function(){
    if($(this).text().length > 500){
        var text = $(this).text();
        $(this).text(text.substr(0, 500));   
    }
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if(session==null||session.getAttribute("userId")==null){ response.sendRedirect("/FQPMS/jsp/Login.jsp");}else{ %>
<%ArrayList<Insurance> availableInsuranceList = (ArrayList<Insurance>)session.getAttribute("availableInsuranceList");
boolean isValidToSubmit = false;	
if(availableInsuranceList!=null) {
	for(Insurance insurance: availableInsuranceList){
		if(insurance!=null && insurance.getTypeOfInsurance().equals("Body Injury Coverage")){
				isValidToSubmit = true; 
				break;
		}
		}
	}
		%>
<div>

<%
						boolean isBvalid = (Boolean)session.getAttribute("isBValid");
if (isBvalid) {                    
if(!isValidToSubmit){
						
					%>
						<p style="font-size: 15px; color:#336699; line-height: 25px">
			 Dear Customer, <br/><br/>
			You currently cannot submit a body injury proposal because you have one in pending, <br/>
			Please kindly wait for our system to process your submitted proposal or you can <br/>
			delete/manage your current pending proposal to process further
			</p>
					
					<%
						} else {
					%>
					<h2>Body Injury Proposal</h2>
					
					<p class="proposalDescription">
					
					Body Injury Proposal is for the case when you get body injuried during an accident, 
					a quote will be generated once the proposal is approved.
					</p>
					
					<form id="bodyform" name="f1" method="POST" onsubmit="return validate1()"
						action="/FQPMS/ProposalSubmitServlet?type=bodyinjury"
						onreset="resetBI()">
						<hr></hr>
						<%
						HashMap<String, String> list_b = (HashMap<String, String>) request.getAttribute("msg1");
						ProposalBean proposalbean = (ProposalBean) request.getAttribute("bean");
						BodyInjuryProposalBean b = null;
						if(proposalbean instanceof BodyInjuryProposalBean){
							b = (BodyInjuryProposalBean)proposalbean;
						}
						%>
						<font style="color: #336699; font-style: italic;">*All fields are mandatory.</font>
						<table style="margin-top:3%; width:85%;">
							<tr>
								<td class="right" style="width: 20%">Date of Injury </td>
								<td class="left"><select style="width:60px; font-size: 12px" name="day" id="day"
									onchange="checkDate()">
										<%
											String day_b = "";
												String month_b = "";
												String year_b = "";
												if (b != null) {
													day_b = b.getDate();
													month_b = b.getMonth();
													year_b = b.getYear();
												}
										%>
										<option value="0" <%if (day_b.equals("0")) {%> selected <%}%>>dd</option>
										<option value="1" <%if (day_b.equals("1")) {%> selected <%}%>>01</option>
										<option value="2" <%if (day_b.equals("2")) {%> selected <%}%>>02</option>
										<option value="3" <%if (day_b.equals("3")) {%> selected <%}%>>03</option>
										<option value="4" <%if (day_b.equals("4")) {%> selected <%}%>>04</option>
										<option value="5" <%if (day_b.equals("5")) {%> selected <%}%>>05</option>
										<option value="6" <%if (day_b.equals("6")) {%> selected <%}%>>06</option>
										<option value="7" <%if (day_b.equals("7")) {%> selected <%}%>>07</option>
										<option value="8" <%if (day_b.equals("8")) {%> selected <%}%>>08</option>
										<option value="9" <%if (day_b.equals("9")) {%> selected <%}%>>09</option>
										<option value="10" <%if (day_b.equals("10")) {%> selected <%}%>>10</option>
										<option value="11" <%if (day_b.equals("11")) {%> selected <%}%>>11</option>
										<option value="12" <%if (day_b.equals("12")) {%> selected <%}%>>12</option>
										<option value="13" <%if (day_b.equals("13")) {%> selected <%}%>>13</option>
										<option value="14" <%if (day_b.equals("14")) {%> selected <%}%>>14</option>
										<option value="15" <%if (day_b.equals("15")) {%> selected <%}%>>15</option>
										<option value="16" <%if (day_b.equals("16")) {%> selected <%}%>>16</option>
										<option value="17" <%if (day_b.equals("17")) {%> selected <%}%>>17</option>
										<option value="18" <%if (day_b.equals("18")) {%> selected <%}%>>18</option>
										<option value="19" <%if (day_b.equals("19")) {%> selected <%}%>>19</option>
										<option value="20" <%if (day_b.equals("20")) {%> selected <%}%>>20</option>
										<option value="21" <%if (day_b.equals("21")) {%> selected <%}%>>21</option>
										<option value="22" <%if (day_b.equals("22")) {%> selected <%}%>>22</option>
										<option value="23" <%if (day_b.equals("23")) {%> selected <%}%>>23</option>
										<option value="24" <%if (day_b.equals("24")) {%> selected <%}%>>24</option>
										<option value="25" <%if (day_b.equals("25")) {%> selected <%}%>>25</option>
										<option value="26" <%if (day_b.equals("26")) {%> selected <%}%>>26</option>
										<option value="27" <%if (day_b.equals("27")) {%> selected <%}%>>27</option>
										<option value="28" <%if (day_b.equals("28")) {%> selected <%}%>>28</option>
										<option value="29" <%if (day_b.equals("29")) {%> selected <%}%>>29</option>
										<option value="30" <%if (day_b.equals("30")) {%> selected <%}%>>30</option>
										<option value="31" <%if (day_b.equals("31")) {%> selected <%}%>>31</option>
								</select> / <select style="width:60px; font-size: 12px" name="month" id="month" onchange="checkDate()">
										<option value="-1" <%if (month_b.equals("-1")) {%> selected <%}%>>mmm</option>
										<option value="1" <%if (month_b.equals("1")) {%> selected <%}%>>JAN</option>
										<option value="2" <%if (month_b.equals("2")) {%> selected <%}%>>FEB</option>
										<option value="3" <%if (month_b.equals("3")) {%> selected <%}%>>MAR</option>
										<option value="4" <%if (month_b.equals("4")) {%> selected <%}%>>APR</option>
										<option value="5" <%if (month_b.equals("5")) {%> selected <%}%>>MAY</option>
										<option value="6" <%if (month_b.equals("6")) {%> selected <%}%>>JUN</option>
										<option value="7" <%if (month_b.equals("7")) {%> selected <%}%>>JUL</option>
										<option value="8" <%if (month_b.equals("8")) {%> selected <%}%>>AUG</option>
										<option value="9" <%if (month_b.equals("9")) {%> selected <%}%>>SEP</option>
										<option value="10" <%if (month_b.equals("10")) {%> selected
											<%}%>>OCT</option>
										<option value="11" <%if (month_b.equals("11")) {%> selected
											<%}%>>NOV</option>
										<option value="12" <%if (month_b.equals("12")) {%> selected
											<%}%>>DEC</option>
								</select> / <select style="width:70px; font-size: 12px" name="year" id="year" onchange="checkDate()">
										<option value="0" <%if (year_b.equals("0")) {%> selected <%}%>>yyyy</option>
										<option value="2008" <%if (year_b.equals("2008")) {%> selected
											<%}%>>2008</option>
										<option value="2009" <%if (year_b.equals("2009")) {%> selected
											<%}%>>2009</option>
										<option value="2010" <%if (year_b.equals("2010")) {%> selected
											<%}%>>2010</option>
										<option value="2011" <%if (year_b.equals("2011")) {%> selected
											<%}%>>2011</option>
										<option value="2012" <%if (year_b.equals("2012")) {%> selected
											<%}%>>2012</option>
										<option value="2013" <%if (year_b.equals("2013")) {%> selected
											<%}%>>2013</option>
								</select></td>
								<%
									String datemsg = "";
										if (list_b != null && list_b.get("date") != null) {
											datemsg = list_b.get("date");
										}
								%>
								<td class="error"><span id="datedisp" style="color: red"><%=datemsg%></span></td>
							</tr>

							<tr>
								<td class="right">Claim Amount(USD)</td>
								<%
									String amount = "";
										if (b != null) {
											amount = b.getAmountclaimed();
										}
								%>
								<td class="left"><input maxlength="11"  type="text" style="width:220px" name="amount"
									id="amount" value="<%=amount%>" onblur="validAmount()" /></td>
								<%
									String amtmsg = "";
										if (list_b != null && list_b.get("amount") != null) {
											amtmsg = list_b.get("amount");
										}
								%>
								<td class="error"><span id="amountdisp" style="color: red"><%=amtmsg%></span></td>
							</tr>

							<tr>
								<td class="right">Nature of Injury </td>
								<%
									String ninjury = "";
										if (b != null) {
											ninjury = b.getNatureofinjury();
										}
								%>
								<td class="left"><textarea style="width:220px;" name="natureofinjury"
										id="nature" rows="5" onblur="validNatureInjury()" onkeyup="textCounter(this,'counter1');" onkeypress="return checkMaxLength1(this)"><%=ninjury%></textarea></td>
								<%
									String naturemsg = "";
										if (list_b != null && list_b.get("natureinjury") != null) {
											naturemsg = list_b.get("natureinjury");
										}
								%>
								<td class="error"><span id="naturedisp" style="color: red"><%=naturemsg%></span></td>
							</tr>
							<tr><td class="right"></td><td class="left">Charactors remain: <input disabled  maxlength="3" size="3" value="500" id="counter1"></td></tr>
							<tr>
								<td class="right">Hospital Name</td>
								<%
									String hospital = "";
										if (b != null) {
											hospital = b.getHospitalname();
										}
								%>
								<td class="left"><select style="width:220px; font-size: 12px" name="hospitalname"
									id="hospitalname" onchange="validHospital()">
										<option value="">Please Select</option>
										<option value="Raffles Medical - Bugis"<%if (hospital.equals("Raffles Medical - Bugis")) {%>
											selected <%}%>>Raffles Medical - Bugis</option>
										<option value="Raffles Medical - Clementi"
											<%if (hospital.equals("Raffles Medical - Clementi")) {%>
											selected <%}%>>Raffles Medical - Clementi</option>
										<option value="Raffles Medical - Changi"<%if (hospital.equals("Raffles Medical - Changi")) {%>
											selected <%}%>>Raffles Medical - Changi</option>
										<option value="National University Hospital"
											<%if (hospital.equals("National University Hospital")) {%>
											selected <%}%>>National University Hospital</option>
										<option value="General Hospital"<%if (hospital.equals("General Hospital")) {%>
											selected <%}%>>General Hospital</option>
								</select></td>
								<%
									String hospitalmsg = "";
										if (list_b != null && list_b.get("hospital") != null) {
											naturemsg = list_b.get("hospital");
										}
								%>
								<td class="error"><span id="hospitaldisp"
									style="color: red"><%=hospitalmsg%></span></td>
							</tr>
		
						</table>
						
					
                    <input type="submit" class="submitbutton" value="Submit" style=" margin-left: 13.5%; margin-top: 50px; margin-bottom: 50px;"/>
                    <%if(proposalbean==null){ %>
                    <input type="reset" class="resetbutton" value="Reset" style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px;" />
                    <%}else{ %>
                    <a href="/FQPMS/ProposalSubmitServlet?execute=reset&&type=bodyinjury" class="resetbutton" style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px;" >Reset</a>
                    <%} %>
					</form>
					
					<%
						} 
						
                    }else{
					%>
					<p style="font-size: 15px; color:#336699; line-height: 25px">
						Dear Customer, <br/><br/>
						System detects that you are not eligible for raising a Body Injury Proposal.<br/>
						Please register a <font style="text-decoration: underline;">Body Injury Insurance </font>via Fast Quote User Management System first,<br/>	
						or you can check with our staff at the counter if have you have already registered one.	
					</p>
				
					<%} %>

</div>
<%} %>

</body>
</html>