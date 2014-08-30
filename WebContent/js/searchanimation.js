function enabletext_merge(){
	var a = document.getElementById("proposaltype_merge").value;
	if(a=="Body Injury Proposal"){
	document.getElementById("hospitalname_merge").disabled=false;
	document.getElementById("vehicleid_merge").disabled=true;
	}
	if(a=="Vehicle Damage Proposal"){
	document.getElementById("vehicleid_merge").disabled=false;
	document.getElementById("hospitalname_merege").disabled=true;
	}
	if(a=="Liability Proposal"){
	document.getElementById("vehicleid_merge").disabled=false;
	document.getElementById("hospitalname_merge").disabled=true;
	}
	if(a=="no value"){
	document.getElementById("vehicleid_merge").disabled=true;
	document.getElementById("hospitalname_merge").disabled=true;
	}
}

function enabletext_hname(){
	var a = document.getElementById("proposaltype_byhname").value;
	if(a=="Body Injury Proposal"){
	document.getElementById("hospitalname_id").disabled=false;

	}
	if(a=="Vehicle Damage Proposal"){

	document.getElementById("hospitalname_id").disabled=true;
	}
	if(a=="Liability Proposal"){

	document.getElementById("hospitalname_id").disabled=true;
	}
	if(a=="no value"){

	document.getElementById("hospitalname_id").disabled=true;
	}
}

function enabletext_vid(){
	var a = document.getElementById("proposaltype_vid").value;
	if(a=="Body Injury Proposal"){
	document.getElementById("vehicleid_id").disabled=true;
	}
	if(a=="Vehicle Damage Proposal"){
	document.getElementById("vehicleid_id").disabled=false;

	}
	if(a=="Liability Proposal"){
	document.getElementById("vehicleid_id").disabled=false;

	}
	if(a=="no value"){
	document.getElementById("vehicleid_id").disabled=true;

	}
}