<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>


<script type="text/javascript">
$().ready(function() {	
	$("#myForm").validate();	
	
});

</script>
</head>

<body>
	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
