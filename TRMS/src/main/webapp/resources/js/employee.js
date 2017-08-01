
$(document).ready(function() {

	$("#balanceGet").click(function(){
		$.post( "GetReimbursement", function( data ) {
			  $( "#balanceResult" ).html( data );
			});
	});
});
	