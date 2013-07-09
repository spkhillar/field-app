var webContextPath;
var homeDataObject;
var homeSiteMap;
var trueOrFalseOption = "true:true;false:false";
var trueOrFalseNAOption = "true:true;false:false;Not Applicable:Not Applicable";
var bulkOrTransferOption = "Bulk:Bulk;Site:Site";
var jqgridUserRolesFilter;
var htmlClientOptions = '<option value=""></option>';
var htmlFaultOptions = '<option value=""></option>';
var htmlEquipmentOptions = '<option value=""></option>';
var htmlMaintenanceCategoriesOptions = '<option value=""></option>';
var temproryPermanentOption = "Temporary:Temporary;Permanent:Permanent";

$(document).ready(function() {

	$.ajax({
		type : "get",
		url : webContextPath + "/home",
		async : false,
		success : function(data, textStatus) {
			homeDataObject = data;
			homeSiteMap = homeDataObject.sites;
			// console.log('....data....', homeSiteMap);
		},
		error : function(textStatus, errorThrown) {
			alert(textStatus + "" + errorThrown);
		}
	});
	jqgridUserRolesFilter = getRoles();
	jQuery.validator.addMethod('siteIdCheck', function(inputValue) {

		if (inputValue.length == 0) {
			return true;
		}
		var found = false;
		$.each(homeSiteMap, function(index, value) {
			if (inputValue == value.name) {
				found = true;
			}
		});

		return found;
	}, "Site does not match sites available in the system");

	getClientsForDropDown();
	getFaultsForDropDown();
	getEquipmentForDropDown();
	getMaintenanceCategoriesForDropDown();
	$('#loggedInTime').text(getDateTime());
	startTime();

});

function startTime() {
	$('#dateTime').text(getDateTime());
	t = setTimeout(function() {
		startTime();
	}, 500);
}

function getDateTime() {
	var today = new Date();

	var month = today.getMonth() + 1;
	var day = today.getDate();

	var output = today.getFullYear() + '/'
			+ (('' + month).length < 2 ? '0' : '') + month + '/'
			+ (('' + day).length < 2 ? '0' : '') + day;

	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	// add a zero in front of numbers<10
	m = checkTime(m);
	s = checkTime(s);
	var finalTime = output + " " + h + ":" + m + ":" + s;
	return finalTime;
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}

function getRoles() {
	var rolesUrl = webContextPath + "/user/roles";
	var roleResponse = "";
	$.ajax({
		type : "get",
		url : rolesUrl,
		async : false,
		success : function(data, textStatus) {
			roleResponse = data;
		},
		error : function(textStatus, errorThrown) {
		}
	});
	console.log('role response..', roleResponse);
	return roleResponse;
}

function getClientsForDropDown() {
	var clientArray = homeDataObject.clients;
	$.each(clientArray, function(index, value) {
		htmlClientOptions += '<option value="' + value.name + '">' + value.name
				+ '</option>';

	});
	htmlClientOptions += '</option>';
}

function getFaultsForDropDown() {
	var faultArray = homeDataObject.faults;
	$.each(faultArray, function(index, value) {
		htmlFaultOptions += '<option value="' + value.name + '">' + value.name
				+ '</option>';

	});
	htmlFaultOptions += '</option>';

}

function getEquipmentForDropDown() {
	var equipmentArray = homeDataObject.spares;
	$.each(equipmentArray, function(index, value) {
		htmlEquipmentOptions += '<option value="' + value.name + '">'
				+ value.name + '</option>';

	});
	htmlEquipmentOptions += '</option>';
}

function getMaintenanceCategoriesForDropDown() {
	var maintenanceCategoriesArray = homeDataObject.maintenanceCategories;
	$.each(maintenanceCategoriesArray, function(index, value) {
		htmlMaintenanceCategoriesOptions += '<option value="' + value.name
				+ '">' + value.name + '</option>';

	});
	htmlMaintenanceCategoriesOptions += '</option>';
}

function showVisitMessage(inputMessage) {
	$('#messageSpanId').text(inputMessage);
}