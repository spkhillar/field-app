<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<spring:url value="/resources/css/templatemo_style.css" var="resourceCssUrl"/>
<spring:url value="/resources/js/jquery.js" var="resourceJqUrl"/>
<spring:url value="/resources/js/jquery.validate.min.js" var="resourceJqvUrl"/>

<script type="text/javascript" src="${resourceJqUrl}"></script>
<script type="text/javascript" src="${resourceJqvUrl}"></script>
<link href="${resourceCssUrl}" rel="stylesheet" type="text/css" />
</head>
<body>
	
	<spring:url value="/" var="homeUrl" htmlEscape="true"/>
	<spring:url value="routine/create" var="addRoutineUrl" htmlEscape="true"/>
	

    
		<div id="templatemo_header">
        	<div id="site_title"><h1>Field Service Application</h1></div>
        </div>
        
        <nav>
	<ul>
		<li><a href="${homeUrl}">Home</a></li>
		<li><a href="#">Administration</a>
			<ul>
				<li><a href="#">User Management</a>				
					<ul>
						<li><a href="#">Add</a></li>
						<li><a href="#">Update</a></li>
						<li><a href="#">Delete</a></li>
					</ul>
					</li>
				<li><a href="#">Phone/Device Management</a>			
					<ul>
						<li><a href="#">Add</a></li>
						<li><a href="#">Update</a></li>
						<li><a href="#">Delete</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="#">Site Visit</a>
			<ul>
				<li><a href="#">Routine Visit</a>			
					<ul>
						<li><a href="${addRoutineUrl}">Add</a></li>
						<li><a href="#">Update</a></li>
					</ul>
					</li>
				<li><a href="#">Diesel Visit</a>			
					<ul>
						<li><a href="#">Add</a></li>
						<li><a href="#">Delete</a></li>
					</ul>
				</li>
				<li><a href="#">Call Out Visit</a>			
					<ul>
						<li><a href="#">Add</a></li>
						<li><a href="#">Delete</a></li>
					</ul>
				</li>
				<li><a href="#">Maintainance Visit</a>			
					<ul>
						<li><a href="#">Add</a></li>
						<li><a href="#">Delete</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="#">Site Statistics</a>
			<ul>
				<li><a href="#">Routine Visit</a></li>
				<li><a href="#">Diesel Visit</a></li>
				<li><a href="#">Call Out Visit</a></li>
				<li><a href="#">Maintainance Visit</a></li>
			</ul>
		</li>
	</ul>
</nav>

</body>
</html>