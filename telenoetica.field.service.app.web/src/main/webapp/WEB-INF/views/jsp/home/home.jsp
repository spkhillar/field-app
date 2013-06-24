<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>
<html>
<head>
<spring:url value="/resources/css/jquery.jqplot.min.css" var="resourceJqplotCssUrl"/>

<spring:url value="/resources/js/jqplot.barRenderer.min.js" var="barRendererUrl"/>
<spring:url value="/resources/js/jqplot.categoryAxisRenderer.min.js" var="categoryAxisRendererUrl"/>
<spring:url value="/resources/js/jqplot.pointLabels.min.js" var="pointLabelsUrl"/>
<spring:url value="/resources/js/jquery.jqplot.min.js" var="jqplotUrl"/>

<link href="${resourceJqplotCssUrl}" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${jqplotUrl}"></script>
<script type="text/javascript" src="${barRendererUrl}"></script>
<script type="text/javascript" src="${categoryAxisRendererUrl}"></script>
<script type="text/javascript" src="${pointLabelsUrl}"></script>

<script type="text/javascript">

	$().ready(function() {

		var jsonurl = webContextPath + "/rest/webHomeData";
		var homePageData = populateDataForHomeScreen(jsonurl);
		populateRoutineTableData(homePageData.routineVisits);
		populateCalloutTableData(homePageData.callOutVisits);
		populateDieselTableData(homePageData.dieselVisits);
		populateMaintenanceTableData(homePageData.maintenanceVisits);
		drawGraph(homePageData.chartData);
	});

	function populateDataForHomeScreen(url) {
		var ret = null;
		$.ajax({
			// have to use synchronous here, else the function 
			// will return before the data is fetched
			async : false,
			url : url,
			success : function(data) {
				ret = data;
			}
		});
		return ret;
	}

	function populateRoutineTableData(input) {
		$.each(input, function(i, row) {
			$('[name="routineTable"]').append(
					"<tr><td>" + row.siteId + "</td><td>" + row.createdAt
							+ "</td></tr>");
		});
	}
	function populateCalloutTableData(input) {
		$.each(input, function(i, row) {
			$('[name="calloutTable"]').append(
					"<tr><td>" + row.siteId + "</td><td>" + row.createdAt
							+ "</td></tr>");
		});
	}
	function populateDieselTableData(input) {
		$.each(input, function(i, row) {
			$('[name="dieselTable"]').append(
					"<tr><td>" + row.siteId + "</td><td>" + row.createdAt
							+ "</td></tr>");
		});
	}

	function populateMaintenanceTableData(input) {

		$.each(input, function(i, row) {
			$('[name="maintenanceTable"]').append(
					"<tr><td>" + row.siteId + "</td><td>" + row.createdAt
							+ "</td></tr>");
		});

	}
	function drawGraph(input) {
		$.jqplot.config.enablePlugins = true;
		var ticks = [ 'Routine Visit', 'Callout Visit', 'Diesel Visit',
				'Maintenance Visit' ];
		plot1 = $.jqplot('chartdiv', [ input ], {
			// Only animate if we're not using excanvas (not in IE 7 or IE 8)..
			title : "Visit Analysis Chart",
			seriesDefaults : {
				renderer : $.jqplot.BarRenderer,
				pointLabels : {
					show : true
				}
			},
			axes : {
				xaxis : {
					renderer : $.jqplot.CategoryAxisRenderer,
					ticks : ticks
				}
			},
			seriesColors : [ "#FF9D19" ],
			highlighter : {
				show : false
			}
		});

	}
</script>
</head>
<body>
	<div style="height: 650px;">
	<div id="chartdiv" style="height:200px;width:600px; margin-left:130px;">
	
	</div>
	<br>
		<div class="col_w420 lp_box float_l">
			<h5>Routine Visit</h5>
			<table id="newspaper-b" name="routineTable">
				<thead>
					<tr>
						<th scope="col">Site</th>
						<th scope="col">Visit Time</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
		<div class="col_w420 float_r">
			<h5>Callout Visit</h5>
			<table id="newspaper-b" name="calloutTable" style="float: right">
				<thead>
					<tr>
						<th scope="col">Site</th>
						<th scope="col">Visit Time</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<br/>
		<div class="col_w420 lp_box float_l">
			<h5>Diesel Visit</h5>
			<table id="newspaper-b" name="dieselTable">
				<thead>
					<tr>
						<th scope="col">Site</th>
						<th scope="col">Visit Time</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
		<div class="col_w420 float_r">
			<h5>Maintenance Visit</h5>
			<table id="newspaper-b" name="maintenanceTable" style="float: right">
				<thead>
					<tr>
						<th scope="col">Site</th>
						<th scope="col">Visit Time</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
