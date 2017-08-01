function postStuff(){
	
	var head = document.getElementById("head");
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		
		switch (xhr.readyState) {
		
		case 0:
			
			head.innerHTML = "Request not initialized";
			break;
			
		case 1:
			head.innerHTML = "Connection Established";
			break;
			
		case 2:
			head.innerHTML = "Request Recieved";
			break;
			
		case 3:
			head.innerHTML = "Processing Request";
			break;
			
		case 4:
			if(xhr.status == 200){
				
				person = JSON.parse(xhr.responseText);
				document.getElementById("stuff").innerHTML = person.major;
				head.innerHTML = "Success";
				
			}
			
			else{
				
				head.innerHTML = "ERROR with Request, response code: " + xhr.status;
				
			}
			break;
			
		}
		
	}
	
	xhr.open("POST", "GetReimbursement", true);
	xhr.send();
	
}
function useJquery(){
 $.post("GetReimbursement");
}
window.onload = function(){
	
	document.getElementById("balanceGet").addEventListener("click", postStuff, false)
}