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
		}).done((response) => {
			let status = response["status"];

			if (status == 200) {
				let message = response["data"];
				alert(message);
				location = "/post/list";
			}
			else {
				let errors = response["data"];
				alert(errors);
			}
		}).fail((error) => {
			let errorMessage = error["data"];
			alert("Error: " + errorMessage);
		});
	},
	
	edit() {
		alert("Post-editing requested");
		
		let post = {
			num: $('#num').val(),
			title: $('#title').val(),
			content: $('#content').val()
		}
		
		$.ajax({
			method: "put",
			url: "/post/edit",
			data: JSON.stringify(post),
			contentType: "application/json; charset=UTF-8"
		}).done((response) => {
			let message = response["data"];
			alert(message);
			location = "/post/list";
		}).fail((error) => {
			let errorMessage = error["data"];
			alert("Error: " + errorMessage);
		});
	},
	
	delete() {
		alert("Post-deleting requested");
		
		let postNum = $('#postNum').val();
		
		$.ajax({
			method: "delete",
			url: `/post/${postNum}`,
			contentType: "application/json; charset=UTF-8"
		}).done((response) => {
			let message = response["data"];
			alert(message);
			location = "/post/list";
		}).fail((error) => {
			let errorMessage = error["data"];
			alert("Error: " + errorMessage);
		});
	}
};

postObject.init();