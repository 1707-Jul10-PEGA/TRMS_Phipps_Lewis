$(document).ready(function() {
	
	$.post("GetFormServlet", function (data) {
		
		var splitter = "";
		splitter = data.split(";");

		var tr;
		tr = $('<tr/>');
		for(var x = 0; x < (splitter.length / 5); x++) {
			//tr.append("<tr/>");
			for(var i = 0; i < splitter.length; i++){
				tr.append("<td>" + splitter[i] + "</td>");
			}
			tr.append("<tr/>");
		}

		
		$('#myappstable').append(tr);
		
		
	
	});
	
	$.post("AllFormsServlet", function (data) {
		
		var splitter = "";
		splitter = data.split(";");

		var tr;
		tr = $('<tr/>');
		//or(var x = 0; x < (splitter.length / 5); x++) {
			//tr.append("<tr/>");
			//for(var i = 0; i < splitter.length; i++){
				tr.append("<tr><td>" + splitter[0] + "</td><td>" + splitter[1] + "</td><td>" + splitter[2] + "</td><td>" + splitter[3] + "</td><td>" + splitter[4] + "</td>");
				tr.append("<tr/>");
			//}
			tr.append("<tr/>");
		//}
		$('#allappstable').append(tr);
	
	});
	
	
});