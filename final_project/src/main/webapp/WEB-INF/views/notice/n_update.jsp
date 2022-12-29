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
<link href="${pageContext.request.contextPath}/resources/css/write.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/read.css"
	rel="stylesheet">
</head>

<script>
	$(function() {
	
		$('#update').on('click', updateBoard);
	});
	
	function updateBoard() {
		$
				.ajax({
					method : "post",
					url : "update",
					data : {
						"board_num":${board_notice.board_num},
						"board_no_title" :  $("#board_no_title").val(),
						"board_no_content" : $("#board_no_content").val(),
						"page":${page},
						"friend_id":${friend_id}
					},
					success : function(board_num) {
							alert('공지 수정 완료');
							location.href = "${pageContext.request.contextPath}/notice/get?board_num="+board_num+"&page=${page}&friend_id=${friend_id}";
					}
				});
	}

</script>
<body>

	<div id="nt_body" class="nt-body">
		<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">

			<!-- 메인 -->
			<form action="update" method="post" enctype="multipart/form-data">
				<input type="hidden" id="board_num" name="board_num"
					value="${board.board_num }"> 
				<input type="hidden" id="page" name="page" value="${page }">

				<div class="WritingCommerce">
					<div class="form_box">
						<h4 class="form_label">
							공지 사항 수정<span role="img" aria-label="필수" class="validation">*</span>
						</h4>

					</div>
					<div class="form_box">
						<div class="input-group mb-3">
							<input type="text" class="form-control" id="board_no_title"
								name="board_no_title" placeholder="제목" required
								class="form-control"
								aria-label="Text input with dropdown button"
								style="width: 300px">
							</div>
						<br>
					</div>
					<!-- 공개설정 -->
					<input type="hidden" name="board_no_see" value="all" checked>
					<div class="form_box">
						<h4 class="form_label">
							내용 입력<span role="img" aria-label="필수" class="validation">*</span>
						</h4>
					</div>
					<h1>ㅤ</h1>
						<textArea id="board_no_content" name="board_no_content" required
							class="form-control" rows="20"></textArea>
						<h1>ㅤ</h1>
						<input type="submit" class="btn btn-default" id="btn" value="글수정"
							style="float: right; background-color: #d7ebc2; border-radius: 10px">
						<h1>ㅤ</h1>
					</div>
			</form>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/popper/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/clean-blog.min.js"></script>

</body>
</html>