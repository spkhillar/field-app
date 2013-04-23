<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/application.js"></script>
</head>

<body>
	<button id="btn1">hello</button>
	<div id="div1">
		<div>Field App Div</div>
	</div>

	<P>The time on the server is ${serverTime}.</P>
<form>
	<fieldset>
		<legend>Delivery Details</legend>
		<ol>
			<li><label for="name">Name<em>*</em></label> <input id="name" />
			</li>
			<li><label for="address1">Address<em>*</em></label> <input
				id="address1" /></li>
			<li><label for="address2">Address 2</label> <input id="address2" />
			</li>
			<li><label for="town-city">Town/City</label> <input
				id="town-city" /></li>
			<li><label for="county">County<em>*</em></label> <input
				id="county" /></li>
			<li><label for="postcode">Postcode<em>*</em></label> <input
				id="postcode" /></li>
			<li>
				<fieldset>
					<legend>
						Is this address also your invoice » address?<em>*</em>
					</legend>
					<label><input type="radio" name="invoice-address" /> Yes</label>
					<label><input type="radio" name="invoice-address" /> No</label>
				</fieldset>
			</li>
		</ol>
		<a href="#" class="button_class">Submit</a>
	</fieldset>
</form>
</body>
</html>
