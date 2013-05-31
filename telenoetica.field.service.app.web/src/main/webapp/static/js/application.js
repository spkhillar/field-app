var webContextPath;
var homeDataObject;
$(document).ready(function(){
	
	$.ajax({
	    type:"get",
	    url:webContextPath+"/home",
	    async: true,
	    success: function(data, textStatus){
	    	homeDataObject = data;
	    	console.log('....data....',data);
	    },
	    error: function(textStatus,errorThrown){
		       alert(textStatus+""+errorThrown);
		}
	});
	
  $("#btn1").click(function(){
   	console.log('hello');
	myFunction();
  });
});

function myFunction(){
	$("#div1").empty();
	var htmlMarkup = "<div> hello 1234567 </div>";
	$("#div1").append(htmlMarkup);

var obj = { "one":"1", "two":"2", "three":3, "four":true};
	$.each(obj, function(key, value) {
	 console.log(key,'....', value);
	});

}