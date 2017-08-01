function handleEvent(){

	var head = document.getElementById("head");
	
//	var xhr = new XMLHttpRequest();
//	
//	xhr.onreadystatechange = function(){
//		
//		switch (xhr.readyState) {
//		
//		case 0:
//			head.innerHTML = "Request not initialized";
//			break;
//			
//		case 1:
//			head.innerHTML = "Connection Established";
//			break;
//			
//		case 2:
//			head.innerHTML = "Request Recieved";
//			break;
//			
//		case 3:
//			head.innerHTML = "Processing Request";
//			break;
//			
//			
//		case 4:
//			if(xhr.status == 200){
//				
//				var test = xhr.responseText;
//				document.getElementById("stuff").innerHTML = test;
//				head.innerHTML = "Success";
//				
//			}
//			
//			else{
//				
//				head.innerHTML = "ERROR with Request, response code: " + xhr.status;
//				document.getElementById("stuff").innerHTML = " Hi " + xhr.status;
//				
//			}
//			break;
//			
//		}
//		
//	}
//	
//	xhr.open("GET", "VerifyLogin", true);
//	xhr.send();
	
	$.ajax(function(){
		url:"VerifyLogin", type: "GET"
	})
	  .done(function(data){
		JSON.parse(dATA);
	});
	
	
}
function postIt(){
$.post("/VerifyLogin");

	
}

window.onload = function(){
	
	document.getElementById("login").addEventListener("click", handleEvent, false);
	//document.getElementById("testPost").addEventListener("click", postIt, false);
}

