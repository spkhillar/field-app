
$(document).ready(function(){
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