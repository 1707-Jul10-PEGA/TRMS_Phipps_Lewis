$(document).ready(function() {
	$("#balanceGet").click(function(){
		$.post( "GetReimbursement",{id: $('#empID').val(),stuff: "stuff=myInfo"}, function( data ) {
			  $( "#balanceResult" ).html( data );
			});
	});
});


