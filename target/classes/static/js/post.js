let postObject = {
	init() {
		$('#btn-postup').click(() => {
			this.postup();
		});
		$('#btn-edit').click(() => {
			this.edit();
		});
		$('#btn-delete').click(() => {
			this.delete();
		});
	},
	postup() {
		alert("Postup requested");
		
		let post = {
			title: $('#title').val(),
			content: $('#content').val()
		}
		
		$.ajax({
			method: "post",
			url: "/post/postup",
			data: JSON.stringify(post),
			contentType: "application/json; charset=UTF-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/post/list";
		}).fail(function(error) {
			let errorMessage = error["data"];
			alert("Error: " + errorMessage);
		});
	},
	edit() {
		alert("Post-editing requested");
		
		let post = {
			id: $('#id').val(),
			title: $('#title').val(),
			content: $('#content').val()
		}
		
		$.ajax({
			method: "put",
			url: "/post/edit",
			data: JSON.stringify(post),
			contentType: "application/json; charset=UTF-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/post/list";
		}).fail(function(error) {
			let errorMessage = error["data"];
			alert("Error: " + errorMessage);
		});
	},
	delete() {
		alert("Post-editing requested");
		
		let id = $('#id').val();
		
		$.ajax({
			method: "delete",
			url: "/post/delete/" + id,
			data: JSON.stringify(post),
			contentType: "application/json; charset=UTF-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/post/list";
		}).fail(function(error) {
			let errorMessage = error["data"];
			alert("Error: " + errorMessage);
		});
	}
};

postObject.init();