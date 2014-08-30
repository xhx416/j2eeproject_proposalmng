
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.BodyInjuryProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.VehicleDamageProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.LiabilityProposal"%>
<%@page import="com.tcs.ilp.fqpms.bean.ProposalBean"%>
<%@page import="java.util.ArrayList"%>

<html>

<head>
<%@taglib prefix="disp" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/FQPMS/css/Template.css"></link>
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>
<script type="text/javascript" src="/FQPMS/js/searchanimation.js"></script>
<title>Display Proposals</title>

<style>
  label {
    display: inline-block;
    width: 5em;
  }
  </style>

<script>
$(function() {
    $( document ).tooltip({
      track: true
    });
  });
	$(function() {
		var clickfrom = document.getElementById('clickfrom').value;
		if(clickfrom =="bypid"){
		$("#tabs").tabs({active:0 });
		}
		else if(clickfrom =="bytype"){
		$("#tabs").tabs({active:1 });	
		}
		else if(clickfrom =="bydate"){
			$("#tabs").tabs({active:2 });	
			}
		else if(clickfrom =="byhopitalname"){
			$("#tabs").tabs({active:3 });	
			}
		else if(clickfrom =="byvehicle"){
			$("#tabs").tabs({active:4 });	
			}
		else if(clickfrom =="byothers"){
			$("#tabs").tabs({active:5 });	
			}
		else{
			$("#tabs").tabs();	
		}
	});
	
</script>
<script type="text/javascript">
function validateDelete(){
	var flag = confirm("Do you want to proceed?");
	return flag;
}
</script>

<style type="text/css">
th a{
text-decoration: none;
color: #336699;
}

</style>

</head>

<body	style="margin-left: 7%; margin-right: 7%; background-color: #f2f2f2">

<div id="mainframe">
<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>

		<div id="rightContainer">

<%

if(session==null||session.getAttribute("userId")==null){ response.sendRedirect("/FQPMS/jsp/Login.jsp");}else{

ArrayList<ProposalBean> proposalbeanlist = (ArrayList<ProposalBean>) request.getAttribute("proposallist");
String clickfrom = (String)request.getAttribute("clickfrom");
if(!(clickfrom==null && proposalbeanlist ==null && request.getParameter("status") == null)){

boolean isBvalid = false;
boolean isVValid = false;
boolean isLValid = false;


if(session.getAttribute("isBValid")!=null){
isBvalid = (Boolean)session.getAttribute("isBValid"); 
}
if(session.getAttribute("isVValid")!=null){
isVValid = (Boolean)session.getAttribute("isVValid");
}
if(session.getAttribute("isLValid")!=null){
isLValid = (Boolean)session.getAttribute("isLValid");
}%>

          
              <input type="hidden" id="clickfrom" value="<%=clickfrom %>">         
                <%String error = (String)request.getAttribute("errormessage");
                if(error!=null){%>
                 <div style="margin-top: 5px; margin-bottom: 5px; margin-left: 5px;">
                 
                    <font style="color: red"><%= error%></font>
                 	
                </div>	
                <%}                %>
                
           
			<div id="tabs">
				<ul>
					<li><a style="font-size: 10px" href="#tabs-1">Search by
							Proposal ID </a></li>
					<li><a style="font-size: 10px" href="#tabs-2">Search by
							Type</a></li>
					<li><a style="font-size: 10px" href="#tabs-3">Search by
							Date</a></li>
					<li><a style="font-size: 10px" href="#tabs-4">Search by
							Hospital</a></li>
					<li><a style="font-size: 10px" href="#tabs-5">Search by
							Vehicle</a></li>
					<li><a style="font-size: 10px" href="#tabs-6">Customized
							Search</a></li>
				</ul>
				<div style="font-size: 12px" id="tabs-1">
					<form id="searchform_pid" method="POST"
						action="/FQPMS/ProposalSearchServlet?searchtype=bypid">
						<font style="color: #336699; font-style: italic;">Proposal ID should be in the format of propi/propv/propl + digit.</font>
						<table>
							<tr>							
								<td>Proposal ID: <%
									String pid_init = (String) request.getAttribute("initsearchbypid");
									if (pid_init != null) {
								%> <input type="text" name="proposalid" value="<%=pid_init%>">
									<%
										} else {
									%><input type="text" name="proposalid"> <%
 	}
 %></td>
								<td><input class="searchbutton"  type="submit" value="Search"></td>
							</tr>
						</table>
					</form>
				</div>

                
				<div style="font-size: 12px; " id="tabs-2" >
					<form id="searchform_type" method="POST"
						action="/FQPMS/ProposalSearchServlet?searchtype=bytype">
						<table>
							<tr>
								<td>Proposal Type: <select name="proposaltype">
									    <option value="no value">Please Select</option>
										<%if(isBvalid){ %>
										<option>Body Injury Proposal</option>
										<%} %>
										<%if(isVValid){ %>
										<option>Vehicle Damage Proposal</option>
										<%} %>
										<%if(isLValid){ %>
										<option>Liability Proposal</option>
										<%} %>

								</select>
								</td>
								<td><input class="searchbutton" type="submit" value="Search"></td>
							</tr>

						</table>
					</form>
				</div>
				<div style="font-size: 12px" id="tabs-3">
					<form id="searchform_date" method="POST"
						action="/FQPMS/ProposalSearchServlet?searchtype=bydate">
						<table>
							<tr>
								<td>Date: <select name="day">
										<option value="0">dd</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>

										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>

										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>

								</select> / <select name="month">
										<option value="0">mmm</option>
										<option>JAN</option>
										<option>FEB</option>
										<option>MAR</option>
										<option>APR</option>
										<option>MAY</option>
										<option>JUN</option>
										<option>JUL</option>
										<option>AUG</option>
										<option>SEP</option>
										<option>OCT</option>
										<option>NOV</option>
										<option>DEC</option>
								</select> / <select name="year">
										<option value="0">yyyy</option>
										<option>2008</option>
										<option>2009</option>
										<option>2010</option>
										<option>2011</option>
										<option>2012</option>
										<option>2013</option>
								</select>
								</td>

								<td><input class="searchbutton" type="submit" value="Search"></td>
							</tr>

						</table>
					</form>

				</div>
	
			
				<div style="font-size: 12px;" id="tabs-4">
				<%if(isBvalid){ %>
					<form id="searchform_hname" method="POST"
						action="/FQPMS/ProposalSearchServlet?searchtype=byhopitalname">
						<table>
							<tr>
								<td>Proposal Type: <input name="proposaltype" value="Body Injury Proposal" readonly>						             
								</td>
								<td>HospitalName: <select id="hospitalname_id"
									name="hospitalname">
										<option value="no value">Please Select</option>
										<option>Raffles Medical - Bugis</option>
										<option>Raffles Medical - Clementi</option>
										<option>Raffles Medical - Changi</option>
										<option>National University Hospital</option>
										<option>General Hospital</option>
								</select>
								</td>

								<td><input class="searchbutton" type="submit" value="Search"></td>
							</tr>


						</table>
					</form>
					<%} else{%>
					<p style="margin-left: 10%"> You do not have any body injury proposal, not applicable for this search</p>
                     <%} %>
				</div>
				
				
			
				<div style="font-size: 12px" id="tabs-5">
                   <%if(isLValid||isVValid){ %>
					<form id="searchform_vid" method="POST"
						action="/FQPMS/ProposalSearchServlet?searchtype=byvehicle">
						<table>
							<tr>

								<td>Proposal Type: <select name="proposaltype"
									id="proposaltype_vid" onchange="enabletext_vid()">
										<option value="no value">Please Select</option>
                                       <%if(isVValid){ %>
										<option>Vehicle Damage Proposal</option>
										<%} %>
										<%if(isLValid){ %>
										<option>Liability Proposal</option>
										<%}%>

								</select>
								</td>

								<td>Vehicle: 
								<select style="font-size: 12px; width:auto" name="vehicleid" id="vehicleid_id" disabled="disabled">
								<%
								ArrayList<String> vidlist = new ArrayList<String>();
								vidlist=(ArrayList<String>)session.getAttribute("vehicleIdList");
								%>
								<option value="">Select</option>
								<%for(String vehicleId:vidlist) {%>
								<option value=<%=vehicleId %>><%=vehicleId %></option>
								<%} %>
						</select>
								</td>
								
								<td><input class="searchbutton" type="submit" value="Search"></td>
							</tr>


						</table>
					</form>
					<%} else{ %>
					
      <p style="margin-left: 10%"> You do not have any Vehicle Damage or Liability proposal, not applicable for this search</p>
      <%} %>

				</div>
				<div style="font-size: 12px" id="tabs-6">

					<form id="searchform_others" method="POST"
						action="/FQPMS/ProposalSearchServlet?searchtype=byothers">
						<table style="width: 50%">
						<tr>
						<td colspan="2">
						<font style="color: #336699; font-style: italic;">Please select at least one search criteria.</font>
						</td>
						</tr>
							<tr>
								<td style="width: 30%">Date:</td><td><select name="day">
										<option value="0">dd</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>

										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>

										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>

								</select> / <select name="month">
										<option value="0">mmm</option>
										<option>JAN</option>
										<option>FEB</option>
										<option>MAR</option>
										<option>APR</option>
										<option>MAY</option>
										<option>JUN</option>
										<option>JUL</option>
										<option>AUG</option>
										<option>SEP</option>
										<option>OCT</option>
										<option>NOV</option>
										<option>DEC</option>
								</select> / <select name="year">
										<option value="0">yyyy</option>
										<option>2008</option>
										<option>2009</option>
										<option>2010</option>
										<option>2011</option>
										<option>2012</option>
										<option>2013</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>Proposal Type:</td> <td><select name="proposaltype"
									id="proposaltype_merge" onchange="enabletext_merge()">
										<option value="no value">Please Select</option>
										<%if(isBvalid){ %>
										<option>Body Injury Proposal</option>
										<%} %>
										<%if(isVValid){ %>
										<option>Vehicle Damage Proposal</option>
										<%} %>
										<%if(isLValid){ %>
										<option>Liability Proposal</option>
										<%} %>
								</select>
								</td>
							</tr>
							<tr>
							<td>HospitalName:</td><td> <select id="hospitalname_merge"
								name="hospitalname" disabled="disabled">
										<option value="no value" selected="selected">Please
											Select</option>
										<option>Raffles Medical - Bugis</option>
										<option>Raffles Medical - Clementi</option>
										<option>Raffles Medical - Changi</option>
										<option>National University Hospital</option>
										<option>General Hospital</option>

								</select></td>
								
							</tr>
													
							<tr>
								<td>Vehicle: </td>
								<td><select style="font-size: 12px; width:auto" name="vehicleid" id="vehicleid_merge" disabled="disabled">
								<%
								ArrayList<String> vidlist = new ArrayList<String>();
								vidlist=(ArrayList<String>)session.getAttribute("vehicleIdList");
								%>
								<option value="">Select</option>
								<%for(String vehicleId:vidlist) {%>
								<option value=<%=vehicleId %>><%=vehicleId %></option>
								<%} %>
						        </select></td>
							</tr>
							<tr>
								<td><input class="searchbutton" type="submit" value="Search"></td>
							</tr>


						</table>
					</form>



				</div>
			</div>

			<%
				if (request.getParameter("status") == null) {
			 	   proposalbeanlist = (ArrayList<ProposalBean>) request.getAttribute("proposallist");
			 	   if(proposalbeanlist!=null && !proposalbeanlist.isEmpty()){
			 		   
			%>

			<form name=results id=results action="/FQPMS/ProposalManageServlet"
				method="post">
              <div style="margin-left:2%; margin-top:5%;">
                 <font class="bodyfont_ita">You can click on the table header to sort the respective columns.</font>
				<disp:table style="width:95%;font-size:14px; border:1px solid #cfcfcf ; margin-top:2%; margin-bottom:10px; " id="proposalbean" name="proposallist" pagesize="3"
					sort="list" 
					requestURI="/FQPMS/ProposalSearchServlet" defaultsort="1">
					<disp:column property="pid" sortable="true" headerClass="datatableHeader" style="text-align: center; width:10%" title="Proposal ID" />
					<disp:column property="type" headerClass="datatableHeader" style="width:15%; text-align:center" title="Type" sortable="true" />
					<disp:column headerClass="datatableHeader"property="date_s"  style="text-align: center; width:15%" title="Date Of Occurrance (dd/mmm/yyyy)"
						sortable="true" />
					<disp:column headerClass="datatableHeader"property="amountclaimed" style="text-align: center; width:10%" title="Amount Claimed(USD)"
						sortable="true" />
					<disp:column headerClass="datatableHeader"property="quoteStatus" style="text-align: center; width:12%"title="Quote Status"
						sortable="true" />
					<disp:column headerClass="datatableHeader" media="html"style="text-align: center; width:6%" title="View">
						<a
							href="/FQPMS/ProposalManageServlet?pid=${proposalbean.pid}&&action=view">View</a>
					</disp:column>
					
					<disp:column headerClass="datatableHeader"media="html"style="text-align: center; width:6%" title="Update">
						
						<c:if test = "${proposalbean.quoteStatus eq 'Pending'}">
						<a
							href="/FQPMS/ProposalManageServlet?pid=${proposalbean.pid}&&action=update1" >Update</a>
						</c:if>
						<!-- 					<a -->
						<%-- 						href="<%=request.getContextPath() %>/EmployeeController?action=updatemp&id=${employee.id}">Update</a> --%>
					</disp:column>

					<disp:column headerClass="datatableHeader" media="html"style="text-align: center;width:6%" title="Delete">
					<c:if test = "${proposalbean.quoteStatus eq 'Pending'}">
						<%-- <%if((((ProposalBean)pageContext.getAttribute("proposalbean")).getQuoteStatus()).equals("pending")){ %> --%>
						<a
							href="/FQPMS/ProposalManageServlet?pid=${proposalbean.pid}&&action=delete&&userId=${proposalbean.uid}" onclick="return validateDelete()">Delete</a>
							</c:if>
							<%-- <%} %> --%>
					</disp:column>
					

					<disp:setProperty name="paging.banner.placement" value="bottom" />
				</disp:table>
             </div>

			</form>
			<%
			 	   }else{
			%>
			<p style="margin-top:5%;margin-left:2%">There is no matched proposal found, please click <a href="/FQPMS/ProposalSearchServlet">here</a> to view all proposals</p>
			
			<%
			}
			} else if (request.getParameter("status").equals("deleted")) {
			%>
			<p style="margin-top:5%;margin-left:2%">
				The Proposal has been deleted successfully! Click <a
					href="/FQPMS/ProposalSearchServlet">Here</a> to continue.
			</p>

			<%
				} else if (request.getParameter("status").equals("updated")) {
			%>
			<p style="margin-top:5%;margin-left:2%">
				The Proposal has been updated successfully! Click <a
					href="/FQPMS/ProposalSearchServlet">Here</a> to continue.
			</p>
			<%
				}
			%>




		</div>
<%} else{%>
<p style="margin-top:5%; margin-left:5%; color: #336699; font-size: 15px">
Dear Customer,<br/><br/> You have not submitted any proposal.
</p>
<%} 
}%>
	</div>
	
			<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
</body>
</html>