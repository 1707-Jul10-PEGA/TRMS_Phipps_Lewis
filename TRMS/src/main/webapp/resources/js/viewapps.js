$(document).ready(function() {
	
	$.post("GetFormServlet", function (data) {
		var tr;
		for(var i = 0; i < data.length; i++){
			tr = $('<tr/>');
			tr.append("<td>" + data[i].firstname + "</td>");
			tr.append("<td>" + data[i].lastname + "</td>");
			tr.append("<td>" + data[i].date_made + "</td>");
			tr.append("<td>" + data[i].status + "</td>");
			tr.append("<td>" + data[i].grade_score + "</td>");
			$('myappstable').append(tr);
			
		 	
		}
		
		var splitter = data.split(";");
		splitter
		var tr;
		tr = $('<tr/>');
		for(var i = 0; i < splitter.length; i++){
			tr.append("<td>" + splitter[i + 1]+ "</td>");
		}
		
		$('#myappstable').append(tr);
		window.alert(data);

		window.alert(data.split(";")[1]);
	});
	
	
	
	
});