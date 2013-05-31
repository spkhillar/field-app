var webContextPath;
var homeDataObject;
var homeSiteMap;
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
