var webContextPath;
var homeDataObject;
var homeSiteMap;
var trueOrFalseOption = "true:true;false:false";
var trueOrFalseNAOption = "true:true;false:false;Not Applicable:Not Applicable";
var bulkOrTransferOption = "Bulk:Bulk;Site:Site";
var jqgridUserRolesFilter;
var htmlClientOptions='<option value=""></option>';
var htmlFaultOptions='<option value=""></option>';
$(document).ready(function() {

	$.ajax({
		type : "get",
		url : webContextPath + "/home",
		async : false,
		success : function(data, textStatus) {
			homeDataObject = data;
			homeSiteMap = homeDataObject.sites;
			//console.log('....data....', homeSiteMap);
		},
		error : function(textStatus, errorThrown) {
			alert(textStatus + "" + errorThrown);
		}
	});
	jqgridUserRolesFilter = getRoles();
	jQuery.validator.addMethod('siteIdCheck', function(inputValue) {
		
		if (inputValue.length == 0){
			return true;
		}
		var found =false;
		$.each(homeSiteMap, function(index, value) {
			if(inputValue==value.name){
				found=true;
			}
		});

		return found;
	}, "Site does not match sites available in the system");
	

	getClientsForDropDown();
	getFaultsForDropDown();

});

function getRoles(){
	var rolesUrl = webContextPath+"/user/roles";
	var roleResponse="";
	$.ajax({
	    type:"get",
	    url:rolesUrl,
	    async: false,
	    success: function(data, textStatus){
	    	roleResponse=data;
	    },
	    error: function(textStatus,errorThrown){
		}
	});
	console.log('role response..',roleResponse);
	return roleResponse;
}

function getClientsForDropDown(){
	var clientArray = homeDataObject.clients;
	$.each(clientArray, function(index, value) {
		htmlClientOptions += '<option value="' + value.name + '">'
		+ value.name + '</option>';
		
	});
	htmlClientOptions += '</option>';
}

function getFaultsForDropDown(){
	var faultArray = homeDataObject.faults;
	$.each(faultArray, function(index, value) {
		htmlFaultOptions += '<option value="' + value.name + '">'
		+ value.name + '</option>';
		
	});
	htmlFaultOptions += '</option>';
}
