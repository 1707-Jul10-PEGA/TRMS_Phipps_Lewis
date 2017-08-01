$(document).ready(function() {

	$("#login").click(function(){
		$.post("VerifyLogin", {username:$("#uname").val(), password:$("#psw").val()})
	});
});