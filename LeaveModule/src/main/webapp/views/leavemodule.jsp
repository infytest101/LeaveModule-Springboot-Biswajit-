<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Module</title>

<script type="text/javascript" charset="utf8"src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8"src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"src="https://cdn.datatables.net/1.10.16/js/dataTables.jqueryui.min.js"></script>

<link rel="stylesheet" type="text/css"href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css"href="https://cdn.datatables.net/1.10.16/css/dataTables.jqueryui.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
 
<style>
	h2{
	
	text-align :center;
	
	} 
	#mailbtn{
	display : flex;
	justifyContent: center;
	alignItems : center;
	}
</style>
</head>

<body>
		<h2>Leave Status Module</h2>

		<label> Employee Name :</label><select id="mycombo" name="roomtype"></select><br>
		
		<div id ="deatilsId">
		<div><label> Designation : </label> <span id="designation"></span><br></div>
		<div><label> Email : </label><span id="email"></span><br></div>
		<div><label> Mobile : </label><span id="mobile"></span><br></div>
		<div><label> Band : </label><span id="band"></span><br></div>
		</div>


		<table id="Hotel" class="table table-bordered table-striped"style="width: 100%">
		<thead>
			<tr>
				<th id="tId">SL#</th>
				<th id="leaveType">Leave Type</th>
				<th id="entitled">Entitled</th>
				<th id="applied">Applied</th>
				<th id="granted">Granted</th>
				<th id="rejected">Rejected</th>
				<th id="balance">Balance</th>

				
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>

<button  type="submit"  id ="mailbtn" onclick="mailToHR()" class="btn btn-warning">mail to HR as .pdf</button>


<script>

function showMassage(message,duration,colordata) {  
	var massageElement=document.createElement("div");
	massageElement.textContent=message;
	massageElement.style.position="fixed";
	massageElement.style.top="50px";
	massageElement.style.left="50px";
	massageElement.style.display="flex";
	massageElement.style.justifyContent="center";
	massageElement.style.alignItems="center";
	massageElement.style.textAlign="center";
	massageElement.style.padding="10px";
	massageElement.style.backgroundColor=colordata;
	massageElement.style.color="white";
	massageElement.style.borderRadius="5px";
	massageElement.style.width="60%";
	massageElement.style.textAlign="center";
	document.body.appendChild(massageElement);
	
	setTimeout(function(){
		massageElement.remove();
	},duration);
} 

function mailToHR(){

	$.ajax({
		type :'GET',
		url:'/amt/MailtoHR',
			success: function(result) {
				console.log("value addded:::::"+result);
				if(result==""){
				 showMassage("Employee Data not found in DB please provide different input",3000,"red");  
				}
				
			},
			error:function(xhr,status,error){
				console.error(error);
			}
	});
}




$(document).ready(function () {
	$("#deatilsId").hide();
	
	var tabledata=$('#Hotel').DataTable({
		"lengthMenu":[5, 10, 15, 20],
		//"searching": false,
		"columnDefs": [ {
			//"targets":0,
		//	"visible":false
		},
		   {
			"targets":0,"width":"10%"
	   		},
		   {
				"targets":1,"width":"10%"
		   },
		   {
				"targets":2,"width":"10%"
		   },
		   {
				"targets":3,"width":"10%"
		   },
		   {
				"targets":4,"width":"10%"
		   },
		   {
				"targets":5,"width":"10%"
			},
			{
				"targets":6,"width":"10%"
			}
		   ]
	
		});
	
	$('#mycombo').change(function(){
		var rmtype = $("#mycombo").val();
		
		if (rmtype === "") {
		    	$("#deatilsId").hide();
		    	showMassage("please provide different input",3000,"red");  
		    	tabledata.clear();
		} 
		else{
			$.ajax({
				type :'GET',
				url:'/amt/fetchEmployeeDetails?employeeName='+rmtype,
					success: function(result) {
						console.log("value addded:::::"+result);
						if(result==""){
						 showMassage("Employee Data not found in DB please provide different input",3000,"red");  
						}
						else{
							$('#designation').text(result[0].designation);
							$('#mobile').text(result[0].mobileNo);
							$('#email').text(result[0].email);
							$('#band').text(result[0].bandName);
						}
						
						
					},
					error:function(xhr,status,error){
						console.error(error);
					}
			});
			
			$("#deatilsId").show(); 	  
			var tdata=$('#Hotel').DataTable();
			tdata.clear();
			$.ajax({
				type :'GET',
				url:'/amt/fetchFilterEmployeeDetils?employeeName='+rmtype,
					success: function(result) {
						console.log("value addded:::::"+result);
						if(result==""){
						 showMassage("Employee Data not found in DB please provide different input",3000,"red");  
						}
						
						dataList=result;
						console.log("value addded:::::"+dataList)
						
						for (var i=0;i<dataList.length; i++) { 
						var leaveType = dataList[i]; 
						var newrow= tabledata.row.add([
								leaveType.sNo,
								leaveType.leaveType,
								leaveType.entitled,
								leaveType.applied,
								leaveType.granted,
								leaveType.reject,
								leaveType.balance
						 	   ]).draw(false).node();
							   $(newrow).find('td:eq(0)').attr('data-field-name','sNo').addClass('rm');
							   $(newrow).find('td:eq(1)').attr('data-field-name','leaveType');
							   $(newrow).find('td:eq(2)').attr('data-field-name','entitled');
							   $(newrow).find('td:eq(3)').attr('data-field-name','applied').addClass('fmdate');
							   $(newrow).find('td:eq(4)').attr('data-field-name','granted').addClass('tmdate');
							   $(newrow).find('td:eq(5)').attr('data-field-name','reject');
							   $(newrow).find('td:eq(6)').attr('data-field-name','balance');
						    
						}
					},
					error:function(xhr,status,error){
						console.error(error);
					}
			});
			$('#Hotel').find("tr:eq(1) td:eq(0)").hide;
		}
	});
	
	
	var dataList=[];
	
 	$.ajax({
		type :'GET',
		url:'/amt/fetchEmployeeName',
		success: function(result) {
          console.log("value addded:::::"+result);
          dataList=result;
          populateComboBox(dataList);
		},
         error:function(xhr,status,error){
			console.error(error);
           }
	}); 

	var comboBox;
	function populateComboBox(data) {// this method used for showing Employee Name in dynamicDrop Down
		 comboBox=$("#mycombo");
		comboBox.empty();
		comboBox.append($('<option>').text("Select EmployeeName...").val(''));
		$.each(data,function(index,value){
			comboBox.append($('<option>').text(value).val(value));
		});
	}
});
</script>
</body>
</html>
