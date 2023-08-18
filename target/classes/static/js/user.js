let userObject = {
	init() {
		$('#btn-signup').click(() => {
			this.signup();
		});
		$('#btn-update').click(() => {
			this.update();
		});
	},
	
	signup() {
		let user = {
			username: $('#username').val(),
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
			alert("error: " + error["data"]);
		});
	},
	
	update() {
		let user = {
			num: $('#num').val(),
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		
		$.ajax({
			method: "PUT",
			url: "/user",
			data: JSON.stringify(user),
			contentType: "application/json; charset=UTF-8"
		}).done(function() {
			location = "/";
		}).fail(function(error) {
			alert("error: " + error["data"]);
		});
	},
};

userObject.init();