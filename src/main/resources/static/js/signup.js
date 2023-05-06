jQuery(function($) {
	$('#btn-signup').click(function() {
		signup();
	});
});

function signup() {
	let user = {
		id: $('#id').val(),
		password: $('#password').val(),
		email: $('#email').val()
	};
	
	$.ajax({
		method: "post",
		url: "/signup",
		data: JSON.stringify(user),
		contentType: "application/json; charset=UTF-8"
	}).done(function(response) {
		console.log(response);
//		location = "/";
	}).fail(function(error) {
		alert("error: " + error);
	});
}