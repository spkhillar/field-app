var webContextPath;
var homeDataObject;
var homeSiteMap;
var trueOrFalseOption = "true:true;false:false";
var jqgridUserRolesFilter;
$(document).ready(function() {

	$.ajax({
		type : "get",
		url : webContextPath + "/home",
		async : true,
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
		var found =false;
		$.each(homeSiteMap, function(index, value) {
			if(inputValue==value.name){
				found=true;
			}
		});

		return found;
	}, "Site does not match sites available in the system");

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
