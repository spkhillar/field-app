<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'>
	$(function() {
		var actionUrl = webContextPath+"/user/records";
		var roles = getRoles();
		$("#grid").jqGrid({
		   	url:actionUrl,
			datatype: 'json',
			mtype: 'GET',
		   	colNames:['Id', 'Username', 'Password', 'First Name', 'Last Name',"Phone","Email","Enabled","Role"],
		   	colModel:[
		   		{name:'id',index:'id', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
		   		{name:'userName',index:'userName', width:100, editable:true, editrules:{required:true}, editoptions:{size:20}},
		   		{name:'password',index:'password', width:100, editable:true, editrules:{required:true}, editoptions:{size:20}, edittype:'password', hidden:true},
		   		{name:'firstName',index:'firstName', width:100, editable:true, editrules:{required:true}, editoptions:{size:20}},
		   		{name:'lastName',index:'lastName', width:100, editable:true, editrules:{required:false}, editoptions:{size:20}},
		   		{name:'phone',index:'phone', width:100, editable:true, editrules:{required:true}, editoptions:{size:20}},
		   		{name:'email',index:'email', width:100, editable:true, editrules:{required:true,email:true}, editoptions:{size:20}},
		   		{name:'enabled',index:'enabled', width:50, editable:true, editrules:{required:true},
		   			edittype:"checkbox",editoptions:{value:"true:false"}},
		   		{name:'roleId',index:'roleId', width:50, editable:true, editrules:{required:true}, 
		   			edittype:"select", formatter:'select', stype: 'select', 
		   			editoptions:{value:roles},
		   			searchoptions: {sopt:['eq'], value:":;"+roles}}
		   	],
		   	postData: {},
			rowNum:10,
		   	rowList:[10,20,40,60],
		   	height: 240,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "asc",
		    caption:"Users",
		    emptyrecords: "Empty records",
		    loadonce: false,
		    loadComplete: function() {},
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
		    }
		});

		$("#grid").jqGrid('navGrid','#pager',
				{edit:false, add:false, del:false, search:false,excel: true},
				{}, {}, {}, 
				{ 	/* // search
					sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
					closeOnEscape: true, 
					multipleSearch: false, 
					closeAfterSearch: false */
				}
		);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Add", 
					buttonicon:"ui-icon-plus", 
					onClickButton: addRow,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Edit", 
			buttonicon:"ui-icon-pencil", 
			onClickButton: editRow,
			position: "last", 
			title:"", 
			cursor: "pointer"
			} 
		);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Export", 
					buttonicon:"ui-icon-newwin", 
					onClickButton: exportExcel,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
		
		$("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
	});
	
	function exportExcel(){
		alert
		 jQuery("#grid").jqGrid('excelExport', {
             tag: 'excel',
             url: webContextPath+'/user/export'
         });
	}
	
	function getRoles(){
		var rolesUrl = webContextPath+"/user/roles";
		var roleResponse="";
		$.ajax({
		    type:"get",
		    url:rolesUrl,
		    async: false,
		    success: function(data, textStatus){
		    	roleResponse=data;
		    },
		    error: function(textStatus,errorThrown){
			}
		});
		console.log('role response..',roleResponse);
		return roleResponse;
	}
	
	function addRow(){

   		//$("#grid").jqGrid('setColProp', 'username', {editoptions:{readonly:false, size:10}});
   		$("#grid").jqGrid('setColProp', 'password', {hidden: false});
   		$("#grid").jqGrid('setColProp', 'password', {editrules:{required:true}});
   		
		// Get the currently selected row
		$('#grid').jqGrid('editGridRow','new',
	    		{ 	
					url: webContextPath+'/user/create', 
					editData: {},
	                serializeEditData: function(data){ 
	                    data.id = 0; 
	                    return $.param(data);
	                },
				    recreateForm: true,
				    beforeShowForm: function(form) {
			            $('#pData').hide();  
			            $('#nData').hide();
			            $('#password',form).addClass('ui-widget-content').addClass('ui-corner-all');
				    },
					beforeInitData: function(form) {},
					closeAfterAdd: true,
					reloadAfterSubmit:true,
					afterSubmit : function(response, postdata) 
					{ 
				        //var result = eval('(' + response.responseText + ')');
				        if(response.statusCode == 0){
				        	$('#msgbox').text(response.message);
							$('#msgbox').dialog( 
									{	title: 'Success',
										modal: true,
										buttons: {"Ok": function()  {
											$(this).dialog("close");} 
										}
									});
						}else{
							
						}
				    	// only used for adding new records
				    	var newId = null;
				    	
						return ["false", "", newId];
					}
	    		});

   		$("#grid").jqGrid('setColProp', 'password', {hidden: true});
	}
	
	function editRow() {
	$("#grid").jqGrid('setColProp', 'userName', {editoptions:{readonly:true, size:10}});
		$("#grid").jqGrid('setColProp', 'password', {hidden: true});
		$("#grid").jqGrid('setColProp', 'password', {editrules:{required:false}});
		
	// Get the currently selected row
	var row = $('#grid').jqGrid('getGridParam','selrow');
	
	if( row != null ) {
	
		$('#grid').jqGrid('editGridRow', row,
			{	url: webContextPath+'/user/update', 
				editData: {},
		        recreateForm: true,
		        beforeShowForm: function(form) {
		            $('#pData').hide();  
		            $('#nData').hide();
		        },
				beforeInitData: function(form) {},
				closeAfterEdit: true,
				reloadAfterSubmit:true,
				afterSubmit : function(response, postdata) 
				{ 

			        //var result = eval('(' + response.responseText + ')');
			        if(response.statusCode == 0){
			        	$('#msgbox').text(response.message);
						$('#msgbox').dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										$(this).dialog("close");} 
									}
								});
					}else{
						
					}
			    	// only used for adding new records
			    	var newId = null;
			    	
					return ["false", "", newId];
				}
			});
	} else {
		$('#msgbox').text('You must select a record first!');
		$('#msgbox').dialog( 
				{	title: 'Error',
					modal: true,
					buttons: {"Ok": function()  {
						$(this).dialog("close");} 
					}
				});
	}
	$("#grid").jqGrid('setColProp', 'userName', {editoptions:{readonly:false, size:10}});
}
	
	
	
	</script>
</head>
<body>

<div id='jqgrid'>
		<table id='grid'></table>
		<div id='pager'></div>
	</div>
	
	<div id='msgbox' title='' style='display:none'></div>


</body>
</html>