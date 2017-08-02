$(document).ready(function() {
    
    $.post("GetFormServlet", function (data) {
        	var tr;
        	var splitter = data.split(";");
        	window.alert(splitter);
            tr = $('<tr/>');
            tr.append("<tr>");
            tr.append("<td>" + splitter[0] + "</td>");
            tr.append("<td>" + splitter[1] + "</td>");
            tr.append("<td>" + splitter[2] + "</td>");
            tr.append("<td>" + splitter[3] + "</td>");
            tr.append("<td>" + splitter[4] + "</td>");
            tr.append("</tr>");
            $('myappstable').append(tr);
            
        
        
       });
    
    
    
    
});