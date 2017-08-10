$(document).ready(function() {

	$("#login").click(function() {
		$.ajax({
			url : "VerifyLogin",
			type : "POST",
			data : {
				username : $("#uname").val(),
				password : $("#psw").val()
			},
			success : function(data, textStatus,jqXHR) {
				if (jqXHR.status === 200) {
					window.location = "employee.html";
				} else {
					alert("Server Failed to validate Username and Password");
				}
			},
			error : function(jqXHR, exception) {
				var msg = '';
				if (jqXHR.status === 0) {
					msg = 'Not connect.\n Verify Network.';
				} else if (jqXHR.status == 404) {
					msg = 'Requested page not found. [404]';
				} else if (jqXHR.status == 500) {
					msg = 'Server failed to validate Username and Password';
				} else if (exception === 'parsererror') {
					msg = 'Requested JSON parse failed.';
				} else if (exception === 'timeout') {
					msg = 'Time out error.';
				} else if (exception === 'abort') {
					msg = 'Ajax request aborted.';
				} else {

				}
				alert(msg);
			}
		});
	});
});