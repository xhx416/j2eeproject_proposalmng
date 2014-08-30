<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.BodyInjuryProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.VehicleDamageProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.LiabilityProposal"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Template.css">
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>
<title>Insert title here</title>

<script>

$(document).ready(function(){
  $("#FQPMS").click(function(){
    $(".navisideButton").toggle();
  });
});

 $(function() { $( "#datepicker" ).datepicker(); 
 
 });


</script>
<script type="text/javascript">


</script>
</head>
<body>

<%
String username = null;
int proposallistsizeinpending = 0;
int proposallistsize = 0;
int proposallistsizeqpproved = 0;

if(session!=null && session.getAttribute("userName") !=null){
	username = (String)session.getAttribute("userName");
}
if(session!=null && session.getAttribute("proposallist")!=null){
	ArrayList<Proposal> proposallist = (ArrayList<Proposal>)session.getAttribute("proposallist");
	proposallistsize = proposallist.size();
	for(int i=0;i<proposallistsize;i++){
		if(!(proposallist.get(i).getQuoteId()!=null && !proposallist.get(i).getQuoteId().trim().isEmpty())){
			proposallistsizeinpending ++;
		}
	}
	proposallistsizeqpproved = proposallistsize-proposallistsizeinpending;
	
}

%>
<!-- <div><a id="FQUMS" style="text-decoration: none; color:black;font-size: 18px" class="sidebuttonlink" -->
<!-- 	href="#">Fast Quote User Management System</a></div> -->
<!-- <div> -->
<!-- <a id="FQPMS" style="text-decoration: none; color:black;font-size: 18px" class="sidebuttonlink" -->
<!-- 	href="#">Fast Quote Proposal Management System</a> -->

<div class="navisideButton"><a style="text-decoration: none; color:black;font-size: 15px;" class="sidebuttonlink"
	href="/FQPMS/jsp/SubmitBodyInjury.jsp" id="submitproposal1" >Submit Proposal</a></div>

<!-- <div class="navisideButton"><a style="text-decoration: none; color:black;font-size: 15px;" class="sidebuttonlink" -->
<!-- 	href="/FQPMS/jsp/SubmitVehicleDamage.jsp" id="submitproposal2" >Submit Vehicle Damage Proposal</a></div> -->

<!-- <div class="navisideButton"><a style="text-decoration: none; color:black;font-size: 15px;" class="sidebuttonlink" -->
<!-- 	href="/FQPMS/jsp/SubmitLiability.jsp" id="submitproposal3" >Submit Liability Proposal</a></div> -->



<div class="navisideButton"><a style="text-decoration: none; color:black;font-size: 15px;" class="sidebuttonlink"
	href="/FQPMS/ProposalSearchServlet" id="manageproposal">Manage
Proposal</a></div>
<%if(session!=null && session.getAttribute("userId") !=null ){ %>
<div style="width:95%; height: 40%; margin-top: 80%; margin-left:5%; color: #336699; font-size: 15px">
   <p style="line-height: 25px">
   <font style="font-size: 16px">Today's date:</font></p>
   <div style="width: 100%; height:50px; font-size: 7px;" id="datepicker"></div>
   <p style="line-height: 20px; margin-top: 50%"><font style="font-size: 16px">You currently have :</font> <br/><br/> 
   <font style="color: black; font-weight: bold;"><%=proposallistsizeqpproved %></font> approved proposal(s).<br/>
   <font style="color:black; font-weight: bold;"><%=proposallistsizeinpending %></font> pending proposal(s).<br/>
   <font style="color:black; font-weight: bold;"><%=proposallistsize %></font> proposal(s) in total.<br/>
   </p>
</div>
<%} %>

<!-- <div><a id="FQQMS" style="text-decoration: none; color:black;font-size: 18px" class="sidebuttonlink" -->
<!-- 	href="#">Fast Quote Quote Management System</a></div> -->
</body>
</html>