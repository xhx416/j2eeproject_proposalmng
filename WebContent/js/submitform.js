function validate1() {
	var flag = false;

	if (!validAmount() | !validDate() | !validNatureInjury() | !validHospital()) {
		flag = false;
	} else {
		flag = true;
	}
	if (flag == true) {
		flag = confirm("Do you want to proceed?");
	}
	return flag;
}

function validate2() {
	var flag = false;

	if (!validAmount2() | !validNatureDamage() | !validVehicleid2()
			| !validDate2()) {
		flag = false;
	} else {
		flag = true;
	}

	if (flag == true) {
		flag = confirm("Do you want to proceed?");
	}
	return flag;
}

function validate3() {
	var flag = false;
	if (!validAmount3() | !validVehicleid3() | !validLiability() | !validDate3()) {
		flag = false;
	} else {
		flag = true;
	}

	if (flag == true) {
		flag = confirm("Do you want to proceed?");
	}
	return flag;
}

function checkDate(){
	if(document.getElementById("month").value !="-1"&&document.getElementById("day").value != "0"
		&& document.getElementById("year").value != "0"){
		validDate();
	}
}
function checkDate2(){
	if(document.getElementById("month2").value !="-1"&&document.getElementById("day2").value != "0"
		&& document.getElementById("year2").value != "0"){
		validDate2();
	}
}
function checkDate3(){
	if(document.getElementById("month3").value !="-1"&&document.getElementById("day3").value != "0"
		&& document.getElementById("year3").value != "0"){
		validDate3();
	}
}
function validDate() {
	if (document.getElementById("month").value == "-1"| document.getElementById("day").value == "0"
			| document.getElementById("year").value == "0") {
		document.getElementById("datedisp").innerHTML = "Please Select a valid date.";
		return false;
	}
	var DateVal = document.getElementById("month").value + "/"
			+ document.getElementById("day").value + "/" + document.getElementById("year").value;
	var dt = new Date(DateVal);
	if (dt.getDate() != document.getElementById("day").value) {		
		document.getElementById("datedisp").innerHTML = "Please Select a valid date.";
		return false;
	}
	else{
	document.getElementById("datedisp").innerHTML = "";
	return true;
	}
}

function validDate2() {
	if (document.getElementById("month2").value == "-1"
			| document.getElementById("day2").value == "0"
			| document.getElementById("year2").value == "0") {
		document.getElementById("datedisp2").innerHTML = "Please Select a valid date.";
		return false;
	}
	var DateVal = document.getElementById("month2").value + "/"
			+ document.getElementById("day2").value + "/" + document.getElementById("year2").value;
	var dt = new Date(DateVal);
	if (dt.getDate() != document.getElementById("day2").value) {
		document.getElementById("datedisp2").innerHTML = "Please Select a valid date.";
		return false;
	}
	else{
	document.getElementById("datedisp2").innerHTML = "";
	return true;
	}
}

function validDate3() {
	if (document.getElementById("month3").value == "-1"
			| document.getElementById("day3").value == "0"
			| document.getElementById("year3").value == "0") {
		document.getElementById("datedisp3").innerHTML = "Please Select a valid date.";
		return false;
	}
	var DateVal = document.getElementById("month3").value + "/"
			+ document.getElementById("day3").value + "/" + document.getElementById("year3").value;
	var dt = new Date(DateVal);
	if (dt.getDate() != document.getElementById("day3").value) {
		document.getElementById("datedisp3").innerHTML = "Please Select a valid date.";
		return false;
	}else{
	document.getElementById("datedisp3").innerHTML = "";
	return true;
	}
}

function validAmount() {
	var amount = document.getElementById("amount").value;

	if (amount == "") {
		document.getElementById("amountdisp").innerHTML = "Please enter your claim amount";
		return false;
	} else if(amount < 10){
		document.getElementById("amountdisp").innerHTML = "Claim amount cannot be less than 10 USD";
		return false;
	} 
	else {
		document.getElementById("amountdisp").innerHTML = "";
		return true;
	}
}

function validAmount2() {
	var amount = document.getElementById("amount2").value;

	if (amount == "") {
		document.getElementById("amountdisp2").innerHTML = "Please enter your claim amount";
		return false;
	} 
	else if(amount < 10){
		document.getElementById("amountdisp2").innerHTML = "Claim amount cannot be less than 10 USD";
		return false;
	} else {
		document.getElementById("amountdisp2").innerHTML = "";
		return true;
	}
}

function validAmount3() {
	var amount = document.getElementById("amount3").value;

	if (amount == "") {
		document.getElementById("amountdisp3").innerHTML = "Please enter your claim amount";
		return false;
	} 
	else if(amount < 10){
		document.getElementById("amountdisp3").innerHTML = "Claim amount cannot be less than 10 USD";
		return false;
	} else {
		document.getElementById("amountdisp3").innerHTML = "";
		return true;
	}
}

function validNatureInjury() {
	var nature = document.getElementById("nature").value;

	if (nature == "") {
		document.getElementById("naturedisp").innerHTML = "Please enter the nature of injury";
		return false;
	} else {
		document.getElementById("naturedisp").innerHTML = "";
		return true;
	}
}

function validNatureDamage() {
	var nature = document.getElementById("nature2").value;

	if (nature == "") {
		document.getElementById("naturedisp2").innerHTML = "Please enter the nature of damage";
		return false;
	} else {
		document.getElementById("naturedisp2").innerHTML = "";
		return true;
	}
}

function validHospital() {
	var status = document.getElementById("hospitalname").value;
	if (status == "") {
		document.getElementById("hospitaldisp").innerHTML = "Please select a hospital";
		return false;
	}

	else {
		document.getElementById("hospitaldisp").innerHTML = "";
		return true;
	}
}

function validVehicleid3() {
	var status = document.getElementById("vehicleid3").value;
	if (status == "") {
		document.getElementById("vehicleiddisp3").innerHTML = "Please select a License Number.";
		return false;
	} else {
		document.getElementById("vehicleiddisp3").innerHTML = "";
		return true;
	}
}

function validVehicleid2() {
	var status = document.getElementById("vehicleid2").value;
	if (status == "") {
		document.getElementById("vehicleiddisp2").innerHTML = "Please select a License Number";
		return false;
	} else {
		document.getElementById("vehicleiddisp2").innerHTML = "";
		return true;
	}
}

function validLiability() {
	var status = document.getElementById("liability").value;
	if (status == "") {
		document.getElementById("liabilitydisp").innerHTML = "Please select a liability type";
		return false;
	} else {
		document.getElementById("liabilitydisp").innerHTML = "";
		return true;
	}
}

function checkMaxLength1(Object)
{
	if(Object.value.length >500){
		document.getElementById("naturedisp").innerHTML ="Your input should be within 500 characters";
		return false;
	}
	else{
		document.getElementById("naturedisp").innerHTML ="";
		return true;
	}	
}

function checkMaxLength2(Object)
{
	if(Object.value.length >500){
		document.getElementById("naturedisp2").innerHTML ="Your input should be within 500 characters";
		return false;
	}
	else{
		document.getElementById("naturedisp2").innerHTML ="";
		return true;
	}	
}


function textCounter(field,field2)
{
 var countfield = document.getElementById(field2);
 if ( field.value.length > 500 ) {
  field.value = field.value.substring( 0, 500 );
  return false;
 } else {
  countfield.value = 500 - field.value.length;
 }
}

function validateLogin(){
	var flag=true;
	if(document.getElementById("userId").value==""){
		document.getElementById("login1").innerHTML ="Please enter user ID!";
		flag=false;
	}
	if(document.getElementById("Password").value==""){
		document.getElementById("login2").innerHTML ="Please enter password!";
		flag=false;
		}
	return flag;
}

function resetBI() {
	document.getElementById("datedisp").innerHTML = "";
	document.getElementById("hospitaldisp").innerHTML = "";
	document.getElementById("amountdisp").innerHTML = "";
	document.getElementById("naturedisp").innerHTML = "";
}
function resetVD() {
	document.getElementById("datedisp2").innerHTML = "";
	document.getElementById("amountdisp2").innerHTML = "";
	document.getElementById("naturedisp2").innerHTML = "";
	document.getElementById("vehicleiddisp2").innerHTML = "";
}
function resetLI() {
	document.getElementById("datedisp3").innerHTML = "";
	document.getElementById("amountdisp3").innerHTML = "";
	document.getElementById("vehicleiddisp3").innerHTML = "";
	document.getElementById("liabilitydisp").innerHTML = "";
}
function resetLogin(){
	document.getElementById("login1").innerHTML = "";
	document.getElementById("login2").innerHTML = "";
}