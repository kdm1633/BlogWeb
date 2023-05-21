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
		let status = response["status"];
		
		if (status == 200) {
			let message = response["data"];
			alert(message);
			location = "/";
		}
		else {
			let errors = response["data"];
			alert(errors);
		}
	}).fail(function(error) {
		alert("error: " + error);
	});
}