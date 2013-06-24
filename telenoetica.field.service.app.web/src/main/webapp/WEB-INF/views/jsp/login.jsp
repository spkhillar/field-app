<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
	<title>Login</title>
</head>

<body>
	<div class="wrapper">
		<form class="form1" action="j_spring_security_check" method="post">
			<div class="formtitle">Login to your account</div>
			<div class="input">
				<div class="inputtext">Username: </div>
				<div class="inputcontent">
					<input id="j_username" name="j_username" size="20" maxlength="50" type="text" />
				</div>
			</div>
			<div class="input nobottomborder">
				<div class="inputtext">Password: </div>
				<div class="inputcontent">
					<input id="j_password" name="j_password" size="20" maxlength="50" type="password" />
					<br/><a href="#">Forgot password?</a>
				</div>
			</div>
			<div class="message">${message}</div>
			<div class="buttons">
				<input class="orangebutton" type="submit" value="Login" />
				<input class="greybutton" type="submit" value="Cancel" />
			</div>
		</form>
		
	</div>
</body>
</html>