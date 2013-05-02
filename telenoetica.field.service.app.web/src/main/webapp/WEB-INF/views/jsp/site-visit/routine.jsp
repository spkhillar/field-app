<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>
<html>


<spring:url value="/resources/css/screen.css" var="resourceCmxUrl"/>

<link href="${resourceCmxUrl}" rel="stylesheet" type="text/css" />
<head>
<script type="text/javascript">

//$("#accountForm").validate( {
	
	$(document).ready(function() {	
		$("#routineCreateForm").validate( {
		    success : "valid",
		    ignoreTitle : true,
		    rules : {
				"accessCode" : {
		        required : true,
		        siteIdCheck:true
		      },
		      "dieselLevelT1" : {
		    	  required : true,
		    	  min: 1,
		    	  max:6000
		      },
		      "dieselLevelT2" : {
		    	  number : true,
		    	  min: 1,
		    	  max:6000
		      },
		      "runHourGen1":{
		    	  number : true,
		    	  min: 1,
		    	  max:30000
		      },
		      "runHourGen2":{
		    	  number : true,
		    	  min: 1,
		    	  max:30000
		      },
		      "voltageNrVolts":{
		    	  number : true,
		    	  min: 1,
		    	  max:400
		      },
		      "voltageNyVolts":{
		    	  number : true,
		    	  min: 1,
		    	  max:400
		      },
		      "voltageNbVolts":{
		    	  number : true,
		    	  min: 1,
		    	  max:400
		      },
		      "loadRPhaseAmps":{
		    	  number : true,
		    	  min: 1,
		    	  max:999
		      },
		      "loadYPhaseAmps":{
		    	  number : true,
		    	  min: 1,
		    	  max:999
		      },
		      "loadBPhaseAmps":{
		    	  number : true,
		    	  min: 1,
		    	  max:999
		      },
		      "rectifierOpVoltage":{
		    	  number : true,
		    	  min: 1,
		    	  max:99
		      },
		      "rectifierOpCurrentAmp":{
		    	  number : true,
		    	  min: 1,
		    	  max:999
		      },
		      "batteryCapcityV":{
		    	  number : true,
		    	  min: 1,
		    	  max:50
		      },
		      "batteryCapcityAh":{
		    	  number : true,
		    	  min: 1,
		    	  max:2000
		      }
		    }
		});
		
		jQuery.validator.addMethod('siteIdCheck', 
		    function (value) {
			return value.match(/(^\d{2}(\/\d{2})(\/\d{3})?$)/);
		    }, 
		    "Site should be in format xx/xx/nnn");
		
		
	});
	
	function test(){

		/* messages: {
			required: "This field is required.",
			remote: "Please fix this field.",
			email: "Please enter a valid email address.",
			url: "Please enter a valid URL.",
			date: "Please enter a valid date.",
			dateISO: "Please enter a valid date (ISO).",
			number: "Please enter a valid number.",
			digits: "Please enter only digits.",
			creditcard: "Please enter a valid credit card number.",
			equalTo: "Please enter the same value again.",
			maxlength: $.validator.format("Please enter no more than {0} characters."),
			minlength: $.validator.format("Please enter at least {0} characters."),
			range: $.validator.format("Please enter a value between {0} and {1} characters long."),
			rangelength: $.validator.format("Please enter a value between {0} and {1}."),
			max: $.validator.format("Please enter a value less than or equal to {0}."),
			min: $.validator.format("Please enter a value greater than or equal to {0}.")
		}
 */	}

function submitRoutineData(){
	 var saved = $("#save").attr("disabled");
	if(saved == 'disabled'){
		return;
	}
	
	var actionUrl = "/${contextPath}/routine/save";
	
	var isValid = $("#routineCreateForm").valid();
	console.log('Form Valid...',isValid);
	if(isValid){
		var str = $("#routineCreateForm").serialize();
		console.log('values...',str);
		$.ajax({
		    type:"post",
		    data:str,
		    url:actionUrl,
		    async: false,
		    success: function(data, textStatus){
		       alert(data+""+textStatus);
		       $("#save").attr("disabled","disabled");
		       $("#save").css("background-color","silver");
		    },
		    error: function(textStatus,errorThrown){
			       alert(textStatus+""+errorThrown);
			}
		});
	}
}

function refreshRoutineData(){
	location.reload();
}

</script>
<style type="text/css">

#routineCreateForm { width: 900px; }
#routineCreateForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

</style>
</head>

<body>
<form:form id="routineCreateForm" name="routineCreateForm" modelAttribute="routineForm" cssClass="cmxform">
		<fieldset>
				<p>
					<label>Access Code <em>*</em> </label> 
					<form:input path="accessCode"/>
				</p>
				<p>
					<label>Diesel Level T1</label> 
					<form:input path="dieselLevelT1" />
				</p>
				<p>
					<label>diesel Level T2</label> 
					<form:input path="dieselLevelT2" />
				</p>
				<p>
					<label>Run Hour Genenerator 1</label> 
					<form:input path="runHourGen1" />
				</p>
				<p>
					<label>Run Hour Genenerator 2</label> 
					<form:input path="runHourGen2" />
				</p>
				<p>
					<label>Voltage Nr Volts</label> 
					<form:input path="voltageNrVolts" />
				</p>
				<p>
					<label>Voltage Nb Volts</label> 
					<form:input path="voltageNyVolts" />
				</p>
				<p>
					<label>Voltage Nb Volts</label> 
					<form:input path="voltageNbVolts" />
				</p>
				<p>
					<label>Load RPhase Amps</label> 
					<form:input path="loadRPhaseAmps" />
				</p>
				<p>
					<label>Load YPhase Amps</label> 
					<form:input path="loadYPhaseAmps" />
				</p>
				<p>
					<label>Load BPhaseAmps</label> 
					<form:input path="loadBPhaseAmps" />
				</p>
				<p>
					<label>Rectifier Op Voltage</label> 
					<form:input path="rectifierOpVoltage" />
				</p>
				<p>
					<label>Rectifier Op CurrentAmp</label> 
					<form:input path="rectifierOpCurrentAmp" />
				</p>
				<!-- New fields -->
				
				<p>
					<label>Battery Capcity V</label> 
					<form:input path="batteryCapcityV" />
				</p>
				<p>
					<label>Battery Capcity Ah</label> 
					<form:input path="batteryCapcityAh" />
				</p>
				<p>
					<label>Ats Functional</label> 
					<label style="width: 50px;"><form:radiobutton path="atsFunctional" value="true" />Yes</label>
					<label style="width: 50px;"><form:radiobutton path="atsFunctional" value="false" />No</label>
				</p>
				<p>
					<label>Ats Sysncronising</label> 
					<label style="width: 50px;"><form:radiobutton path="atsSysncronising" value="true" />Yes</label>
					<label style="width: 50px;"><form:radiobutton path="atsSysncronising" value="false" />No</label>
				</p>
				<p>
					<label>Diesel Log Book Maintained</label> 
					<label style="width: 50px;"><form:radiobutton path="dieselLogBookMaintained" value="true" />Yes</label>
					<label style="width: 50px;"><form:radiobutton path="dieselLogBookMaintained" value="false" />No</label>
				</p>
		</fieldset>
	</form:form>
			<a class="button_class" onclick="refreshRoutineData();" id="reset">Reset</a>
			<a onclick="submitRoutineData();" class="button_class" id ="save">Save</a>

</body>
</html>
