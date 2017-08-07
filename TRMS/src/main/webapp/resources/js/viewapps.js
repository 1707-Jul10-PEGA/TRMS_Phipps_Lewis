$(document).ready(function() {
	$.post("GetFormServlet", function (data) {
		form = JSON.parse(data);
		
		for(var i = 0; i < form.length ; i++){
		var tr;
		tr = $('<tr/>');
		tr.append("<td>" + form[i].firstName + "</td>");
		tr.append("<td>" + form[i].lastName + "</td>");
		tr.append("<td>" + form[i].date + "</td>");
		tr.append("<td>" + form[i].status + "</td>");
		tr.append("<td>" + form[i].gradeFormat + "</td>");
		tr.append("<tr/>")
		
		
		$('#myappstable').append(tr);
		}
	});
	
	$.post("AllFormsServlet", function (data) {
		forms = JSON.parse(data);
		
		for(var i = 0; i < forms.length ; i++){
		var tr;
		tr = $('<tr/>');
		tr.append("<td>" + forms[i].firstName + "</td>");
		tr.append("<td>" + forms[i].lastName + "</td>");
		tr.append("<td>" + forms[i].date + "</td>");
		tr.append("<td>" + forms[i].status + "</td>");
		tr.append("<td>" + forms[i].gradeFormat + "</td>");
		tr.append("<tr/>")
		
		
		$('#allappstable').append(tr);
		}
	});
	
});