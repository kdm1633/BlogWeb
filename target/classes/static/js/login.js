
$(document).ready(function() {
	$('#btn-login').click(function(event) {
		login();
	});
	$('#password').keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			login();
		}
	});
});

/*jQuery(function($) {
	$('#btn-login').click(function(event) {
		login();
	});
});
*/
function login() {
	let loginInfo = {
		id: $('#id').val(),
		password: $('#password').val(),
	}
	
	$.ajax({
		method: "POST",
		url: "/login",
		data: JSON.stringify(loginInfo),
		contentType: "application/json; charset=UTF-8"
	}).done(function(response) {
		let responseMessage = response["data"];
		alert(responseMessage);
		location = "/post/list";
	}).fail(function(error) {
		let errorMessage = error["data"];
		alert("Error: " + errorMessage)
	});
}
