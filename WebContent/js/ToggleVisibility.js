function ToggleInsurance(){
	var table=document.getElementById("insuranceTable");
	if(table.style.display=="none")
		table.style.display="block";
	else{
		table.style.display=="none";
		location.reload();
	}
}
function ToggleVehicle(){
	var table=document.getElementById("vehicleTable");
	if(table.style.display=="none")
		table.style.display="block";
	else{
		table.style.display=="none";
		location.reload();
	}
}