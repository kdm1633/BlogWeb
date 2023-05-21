let commentObject = {
	init() {
		$('#btn-comment').click(() => {
			this.submitComment();
		});
	},
	submitComment() {
		let num = $('#postNum').val();
		let comment = {
			content: $('#comment-content').val()
		};
		
		$.ajax({
			method: "post",
			url: `/post/${num}/comment`,
			data: JSON.stringify(comment),
			contentType: "application/json; charset=UTF-8"
		}).done((response) => {
			alert(response["data"]);
			location = `/post/${num}`;
		}).fail((error) => {
			alert(`Error: ${error["data"]}`);
		});
	},
	deleteComment(postNum, commentNum) {
		$.ajax({
			method: "delete",
			url: `/comment/${commentNum}`
		}).done((response) => {
			alert(response["data"]);
			location = `/post/${postNum}`;
		});
	}
};

commentObject.init();