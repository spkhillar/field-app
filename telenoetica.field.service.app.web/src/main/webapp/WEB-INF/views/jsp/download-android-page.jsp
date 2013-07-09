<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<tr><td><a href="<%=this.getServletContext().getContextPath() %>/test.xls">Sample Excel File</a></td></tr>
<% System.out.println( "Evaluating date now" );
this.getServletContext().getContextPath();
%> 
</body>
</html>