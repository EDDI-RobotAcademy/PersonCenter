<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<title>게시물 수정</title>

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css"
	rel="stylesheet">
</head>

<script>
	$(function() {
		makeCheck();
		$('#update').on('click', updateBoard);
	});
	function makeCheck(){
		$("#${board_free.board_see}").prop("checked", true);			
	}
	function updateBoards_free() {
		$
				.ajax({
					method : "post",
					url : "update",
					data : {
						"board_num":${board.board_num},
						"board_title" :  $("#board_title").val(),
						"board_content" : $("#board_content").val(),
						"board_see" : $("input[type=radio][name=board_see]:checked").val(),
						"page":${page},
						"friend_id":${friend_id}
					},
					success : function(board_num) {
							alert('Your post is updated successfully');
							location.href = "${pageContext.request.contextPath}/boards_free/get?board_num="+board_num+"&page=${page}&friend_id=${friend_id}";
					}
				});
	}

</script>
<body>
	<!-- Page Header -->
	<header class="masthead"
		style="background-image: url('${pageContext.request.contextPath}/resources/img/home-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Write Diary</h1>
						<span class="subheading"> Write and share your daily life
							with your friends! </span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<h2>Update diary</h2>
					<hr>
					<form action="update" method="post" enctype="multipart/form-data">
						<input type="hidden" id="board_num" name="board_num"
							value="${board.board_num }"> <input type="hidden"
							id="page" name="page" value="${page }">
							<input type="hidden"
							id="friend_id" name="friend_id" value="${friend_id }">
						<table class="table">
							<tr>
								<td>Title</td>
								<td><input type="text" id="board_title" name="board_title"
									class="form-control" value="${board.board_title }"></td>
							</tr>
							<tr>
								<td>Privacy bounds</td>
								<td><input type="radio" id="all" name="board_see"
									value="all">all<br> <input type="radio"
									id="friend" name="board_see" value="friend">to only
									friends<br> <input type="radio" id="secret"
									name="board_see" value="secret">private<br></td>
							</tr>
							<tr>
								<td>Picture</td>
								<td><input type="file" name="upload"></td>
							</tr>
							<tr>
								<td>Content</td>
								<td><textArea id="board_content" name="board_content"
										class="form-control" rows="20">${board.board_content}</textArea></td>
							</tr>
						</table>
						<input type="submit" value="Update" class="btn btn-default">
						<input type="button" value="Back"
							onclick="location.href='${pageContext.request.contextPath}/'"
							class="btn btn-default">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>