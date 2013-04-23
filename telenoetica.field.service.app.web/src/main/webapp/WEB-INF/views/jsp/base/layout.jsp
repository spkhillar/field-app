<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Field Service Application</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<link href="resources/css/templatemo_style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    
		<div id="templatemo_header">
        	<div id="site_title"><h1>Field Service Application</h1></div>
        </div>
        
       <!--  <div id="templatemo_menu">
            <ul>
                <li><a href="home" class="current">Home</a></li>
                <li><a href="about.html">About Us</a></li>
                <li><a href="blog.html">Blog</a></li>
                <li><a href="gallery.html">Gallery</a></li>
                <li><a href="contact.html" class="last">Contact</a></li>
            </ul>    	
        </div> --> <!-- end of templatemo_menu -->
        <nav>
	<ul>
		<li><a href="#">Home</a></li>
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
						<li><a href="#">Add</a></li>
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

        <div id="templatemo_main">
        	<tiles:insertAttribute name="body" />
            
        </div> <!-- end of templatemo main -->
    	
    </div>
</div>

<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">
        
        Copyright 2013 <a href="#">Telenoetica</a> | Designed by <a href="#" target="_parent">Telenoetica</a>
    
    </div> 
</div>

</body>
</html>