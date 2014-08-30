
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.BodyInjuryProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.VehicleDamageProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.LiabilityProposal"%>
<%@page import="com.tcs.ilp.fqpms.bean.InsuranceBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.VehicleBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.ProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.LiabilityProposalBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/FQPMS/css/Template.css"></link>
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>
<title>Fast Quote Management</title>

<script>

var doPrintPage;

function printPage(){
    window.print();
}

$(document).ready(function(){
    $('input').blur(function(){
        //3sec after the user leaves the input, printPage will fire
        doPrintPage = setTimeout('printPage();', 3000);
    });
    $('input').focus(function(){
        //But if another input gains focus printPage won't fire
        clearTimeout(doPrintPage);
    });
});

</script>

</head>
<body>
<%if(session==null||session.getAttribute("userId")==null){ response.sendRedirect("/FQPMS/jsp/Login.jsp");}else{ String username = (String)session.getAttribute("userName");%>

<div id="mainframe">
<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>
<div id="rightContainer">

<div style="font-size: 15px; color: #336699; margin-top:5%; margin-left: 10%; margin-bottom: 10%">
<p style="line-height: 25px; width: 75%"><font style="font-size: 18px">Thanks for using Fast Quote Proposal Submit System! </font><br/><br/>
Your proposal with ID number of <font style="text-decoration: underline;"><%=request.getAttribute("proposalID")%> </font> has been submitted and will be processed in 2-3 working days.<br/>
Following is your proposal detail:</p> 

<table style="margin-left:0%; margin-top:3%; width: 70%; border: 1px solid #f2f2f2">
			
			<tr class="tr_head">		
             </tr>
				<%
				ProposalBean p = null;
				if(request.getAttribute("bean")!=null)	{
				p = (ProposalBean) request.getAttribute("bean");
				}
                    String type = null;
                    
						if (p!= null && p instanceof BodyInjuryProposalBean) {
							BodyInjuryProposalBean b = (BodyInjuryProposalBean)p;
							type = "Body Injury";
				%>


				<tr class="td_odd">
					<td class="viewtable_right">Proposal ID</td>
					<td class="viewtable_left"><%=request.getAttribute("proposalID")%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">User Name</td>
					<td class="viewtable_left"><%=username%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal Type</td>
					<td class="viewtable_left"><%=type%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Date of Injury</td>
					<%
						String m = "" + (Integer.parseInt(b.getMonth()) + 1);
					%>
					<td class="viewtable_left"><%=b.getYear()%>-<%=m%>-<%=b.getDate()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Amount Claimed(USD)</td>
					<td class="viewtable_left"><%=b.getAmountclaimed()%></td>
				</tr>
				
			      <tr class="td_even">
					<td class="viewtable_right">Nature of Injury</td>
					<td class="viewtable_left"><%=b.getNatureofinjury()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Hospital Name</td>
					<td class="viewtable_left"><%=b.getHospitalname()%></td>
				</tr>
				<%
					} else if (p!= null && p instanceof VehicleDamageProposalBean) {
							type = "Vehicle Damage";
							VehicleDamageProposalBean v = (VehicleDamageProposalBean)p;
							//String vid = v.getVehicleid();
		
				%>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal ID</td>
					<td class="viewtable_left"><%=request.getAttribute("proposalID")%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">User Name</td>
					<td class="viewtable_left"><%=username%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal Type</td>
					<td class="viewtable_left"><%=type%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Vehicle</td>
					<td class="viewtable_left"><%=v.getVehicleid()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Date of Injury</td>
					<%
						String m = "" + (Integer.parseInt(v.getMonth()) + 1);
					%>
					<td class="viewtable_left"><%=v.getYear()%>-<%=m%>-<%=v.getDate()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Amount Claimed(USD)</td>
					<td class="viewtable_left"><%=v.getAmountclaimed()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Nature of Damage</td>
					<td class="viewtable_left"><%=v.getNatureofdamage()%></td>
				</tr>

				<%
					} else if (p!= null && p instanceof LiabilityProposalBean) {
											type = "Liability";
											LiabilityProposalBean l = (LiabilityProposalBean)p;
				%>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal ID</td>
					<td class="viewtable_left"><%=request.getAttribute("proposalID")%></td>
				
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">User Name</td>
					<td class="viewtable_left"><%=username%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal Type</td>
					<td class="viewtable_left"><%=type%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Vehicle</td>
					<td class="viewtable_left"><%=l.getVehicleid()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Date of Accident</td>
					<%
						String m = "" + (Integer.parseInt(l.getMonth()) + 1);
					%>
					<td class="viewtable_left"><%=l.getYear()%>-<%=m%>-<%=l.getDate()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Amount Claimed(USD)</td>
					<td class="viewtable_left"><%=l.getAmountclaimed()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Type of Liability</td>
					<td class="viewtable_left"><%=l.getTypeofliability()%></td>
				</tr>

				<%
					}
				%>

				<tr>
				</tr>
			</table>
			
			<a style="margin-top: 2%" href='javascript:window.print();'>Print</a>
			
			<p style="margin-top: 2%">If you want to make any change to your proposal, please click <a
							href="/FQPMS/ProposalSubmitServlet?pid=<%=request.getAttribute("proposalID")%>&&execute=manage" >here</a>.</p>


</div>
</div>


<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>

</div>
<%} %>
</body>
</html>