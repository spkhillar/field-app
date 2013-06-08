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
			  "siteId" : {
			      required : true,
			      siteIdCheck:true
			   },
			  "accessCode" : {
			       required : true
		       },
		      "actionsRequiredForPermanentResolution":{
		      		maxlength : 50
		      }
		    }
		});
		
		$( "#dialog").dialog({
			autoOpen: false,
			width: 300,
			height:150,
			resizable: false,
			draggable: false
		});
		$('#customer1Impacted').html(htmlClientOptions);
		$('#customer2Impacted').html(htmlClientOptions);
		$('#customer3Impacted').html(htmlClientOptions);
		$('#customer4Impacted').html(htmlClientOptions);
		$('#mainCategoryOfFault').html(htmlFaultOptions);
		$("#save").button();
		$("#reset").button();
		
		
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

function submitCalloutData(){
	 
	console.log('.....webContextPath....'+webContextPath);
	var actionUrl = webContextPath+"/callout/save";

	console.log('..actionUrl-callout..',actionUrl);
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
			$( "#dialog").append(data);
		    $( "#dialog" ).dialog( "open" );
		    },
		    error: function(textStatus,errorThrown){
			       alert(textStatus+""+errorThrown);
			}
		});
	}
}

function refreshCalloutData(){
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
					<label><spring:message code="fieldapp.label.site"/> <em>*</em> </label> 
					<form:input path="siteId"/>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.access.code"/>  <em>*</em> </label> 
					<form:input path="accessCode"/>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.callout.csr.ttnumber"/></label> 
					<form:input path="callOutCsrOrTtNumber" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.complain.received.time"/></label> 
					<form:input path="timeComplainReceived" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.time.to.reach.site"/></label> 
					<form:input path="timeReachedToSite" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.time.when.fault.resolved"/></label> 
					<form:input path="timeFaultReserved" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.customer1.impacted"/></label> 
					<form:select id="customer1Impacted" path="customer1Impacted">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.customer2.impacted"/></label> 
					<form:select id="customer2Impacted" path="customer2Impacted">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.customer3.impacted"/></label> 
					<form:select id="customer3Impacted" path="customer3Impacted">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.customer4.impacted"/></label> 
					<form:select id="customer4Impacted" path="customer4Impacted">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.main.category.of.fault"/></label> 
					<form:select id="mainCategoryOfFault" path="mainCategoryOfFault">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.equipment.component.caused.fault"/></label> 
					<form:input path="equipmentComponentCausedFault" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.equipment.componenet.repaired"/></label> 
					<form:input path="equipmentComponentRepaired" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.equipment.componenet.replaced"/></label> 
					<form:input path="equipmentComponentReplaced" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.fix.temporary.permanent"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="fixResolutionTemporaryOrPermanent" value="Temporary" /><spring:message code="fieldapp.label.yes.value"/> </label>
					<label style="width: 50px;"><form:radiobutton path="fixResolutionTemporaryOrPermanent" value="Permanent" /><spring:message code="fieldapp.label.no.value"/></label>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.actions.required.for.permanent.resolution"/></label> 
					<form:textarea path="actionsRequiredForPermanentResolution" />
				</p>
		</fieldset>
	</form:form>
			<button id="save" onclick="submitCalloutData();"><spring:message code="fieldapp.label.save"/> </button>
			<button id="reset" onclick="refreshCalloutData();"><spring:message code="fieldapp.label.reset"/></button>

</body>
</html>
