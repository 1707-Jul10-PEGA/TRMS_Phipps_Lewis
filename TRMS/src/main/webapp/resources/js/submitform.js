$(document).ready(function() {

$('#submission').click(function(){
		$.post("SubmitForm", {cost: $('#cost').val() ,grade: $('#sel1').val()})
	
});

});