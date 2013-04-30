<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript">
function submitRoutineData(){
	
	var actionUrl = "/fieldapp/routine/save";
	
	var str = $("#routineCreateForm").serialize();
	console.log('values...',str);
	$.ajax({
	    type:"post",
	    data:str,
	    url:actionUrl,
	    async: false,
	    dataType : 'json',
	    contentType :'application/json',
	    accepts :'application/json',
	    success: function(){
	       alert("success");
	    }
	});
	
}

</script>
</head>

<body>
	<form id="routineCreateForm" name="routineCreateForm" >
		<fieldset>
			<legend>Delivery Details</legend>
			<ol>
				<li><label>accessCode</label> <input id="accessCode" /></li>
				<li><label>dieselLevelT1</label> <input id="dieselLevelT1" /></li>
				<li><label>dieselLevelT2</label> <input id="dieselLevelT2" /></li>
				<li><label>runHourGen1</label> <input id="runHourGen1" /></li>
				<li><label>runHourGen2</label> <input id="runHourGen2" /></li>
				<li><label>voltageNrVolts</label> <input id="voltageNrVolts" /></li>
				<li><label>voltageNbVolts</label> <input id="voltageNbVolts" /></li>

			</ol>
		</fieldset>
	</form>

			<input type="button" onclick="submitRoutineData();" title="Submit"/>

</body>
</html>
