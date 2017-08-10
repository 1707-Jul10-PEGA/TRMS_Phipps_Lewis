$(document).ready(function() {
$('#submission').click(function(){
		$.post("SubmitForm", {cost: $('#cost').val() ,grade: $('#sel1').val(), date: $('#theDate').val(), description : $('#descript').val()})
		.done(function(data,textStatus,jqXHR){
//			alert(jqXHR.status);
			if(jqXHR.status == 200){
				
			alert("Successfully submitted form!");
			window.location = "employee.html";
			}
		})
		.fail(function(data){
			alert("Failed to properly submit form, please try again.");
		});
});
});