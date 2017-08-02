$(document).ready(function() {
	
	$.post("GetFormServlet", function (data) {
		
		
		var splitter = data.split(";");
		
		var tr;
		tr = $("<tr/>")
		for(var i = 0; i < splitter.length; i++){
			if(i % 5 == 0){
				tr.append("<tr/>")
			}
			tr.append("<td>" + splitter[i]+ "</td>");
		}
		
		$('#myappstable').append(tr);
		
		
	
	});
	
	$.post("AllFormsServlet", function (data) {
		
		
		var splitter = data.split(";");

		var tr;
		tr = $('<tr/>');
		for(var x = 0; x < (spliiter.length / 5); x++) {
			//tr.append("<tr/>");
			for(var i = 0; i < splitter.length; i++){
				tr.append("<td>" + splitter[i] + "</td>");
			}
			tr.append("<tr/>");
		}
		$('#allappstable').append(tr);
	
	});
	
	
});