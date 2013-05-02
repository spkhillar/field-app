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
		$("#calloutCreateForm").validate( {
		    success : "valid",
		    ignoreTitle : true,
		    rules : {
				"accessCode" : {
		        required : true,
		        siteIdCheck:true
		      },
		      "callOutCsrOrTtNumber" : {
		    	  required : true,
		    	  min: 1,
		    	  max:6000
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

function submitcalloutData(){
	 var saved = $("#save").attr("disabled");
	if(saved == 'disabled'){
		return;
	}
	
	var actionUrl = "/${contextPath}/callout/save";
	
	var isValid = $("#calloutCreateForm").valid();
	console.log('Form Valid...',isValid);
	if(isValid){
		var str = $("#calloutCreateForm").serialize();
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

function refreshcalloutData(){
	location.reload();
}

</script>
<style type="text/css">

#calloutCreateForm { width: 900px; }
#calloutCreateForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

</style>
</head>

<body>
<form:form id="calloutCreateForm" name="calloutCreateForm" modelAttribute="calloutForm" cssClass="cmxform">
		<fieldset>
				<p>
					<label>Access Code <em>*</em> </label> 
					<form:input path="accessCode"/>
				</p>
				<p>
					<label>callOut Csr Or Tt Number</label> 
					<form:input path="callOutCsrOrTtNumber" />
				</p>
		</fieldset>
	</form:form>
			<a class="button_class" onclick="refreshcalloutData();" id="reset">Reset</a>
			<a onclick="submitcalloutData();" class="button_class" id ="save">Save</a>

</body>
</html>
