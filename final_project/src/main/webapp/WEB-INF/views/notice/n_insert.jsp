<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css"
	rel="stylesheet">
</head>
<script>
	/*$(function() {
	 $('#insert').on('click', insertBoard);
	 });
	 function insertBoard() {
	 $
	 .ajax({
	 method : "post",
	 url : "insert",
	 data : {
	 "board_title" : $("#board_title").val(),
	 "board_content" : $("#board_content").val(),
	 "board_see" : $(
	 "input[type=radio][name=board_see]:checked")
	 .val()
	 },
	 success : function(result) {
	 if (result == 1) {
	 alert('You posted successfully');
	 location.href = "${pageContext.request.contextPath}/boards";
	 }
	 }
	 });
	 }
	 */
</script>
<body>
	<!-- Page Header -->
	<header class="masthead"
		style="background-image: url('${pageContext.request.contextPath}/resources/img/home-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>게시물 작성</h1>
						<span class="subheading"> 게시물을 올려보아요</span>
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
					<h2>게시물 작성</h2>
					<hr>
					<form action="insert" method="post" enctype="multipart/form-data">
						<table class="table" cellpadding="10">
							<tr>
								<td>제목</td>
								<td><input type="text" id="board_no_title" name="board_no_title"
									class="form-control"></td>
							</tr>
							<tr>
								<td>공개설정</td>
								<td><input type="hidden" name="board_no_see" value="all"
									checked>모두에게</td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textArea id="board_no_content" name="board_no_content"
										class="form-control" rows="20"></textArea></td>
							</tr>
							<tr>
								<td><input type="submit" class="btn btn-default" value="글쓰기"></td>
								<td><input type="button" value="뒤로가기"
									onclick="location.href='${pageContext.request.contextPath}/'"
									class="btn btn-default"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/popper/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Custom scripts for this template -->
	<script
		src="${pageContext.request.contextPath}/resources/js/clean-blog.min.js"></script>

</body>
</html>
<%@ include file="../include/footer.jsp" %>