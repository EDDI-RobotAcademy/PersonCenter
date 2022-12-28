<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/write.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/read.css"
	rel="stylesheet">
</head>
<body>

	<script>
		//게시판 선택확인
		$(function() {
			$('#btn').click(function() {
				if ($('#board_category').val() == '') {
					$('#board_category_error').html('게시판을 선택하시오.');
					$('#board_category_error').attr('color', '#dc3545');
				} else {
					$('#board_category_error').html('');
				}
			});
		});
	</script>

	<div id="nt_body" class="nt-body">
		<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">

			<!-- 메인 -->
			<form action="insert" method="post" enctype="multipart/form-data">
			<div class="WritingCommerce">
				<div class="form_box">
					<h4 class="form_label">
						판매 가격<span role="img" aria-label="필수" class="validation">*</span>
					</h4>
					<div class="input_box">
						<div class="FormInputText">
							<input type="text" placeholder="가격을 입력해주세요" title=""
								class="input_text" id="board_cost" name="board_cost" required class="form-control">
						</div>
						<span class="won">원</span>
					</div>
					<p class="message red" style="display: none;">판매 가격을 입력해 주세요.</p>
				</div>
				<div class="form_box">
					<div class="input-group mb-3">
						<span class="note-current-fontname" style="font-family: sans-serif;"> <select
							name="board_category" id="board_category" required class="form-control">
								<option value="">게시판 선택</option>
								<option value="1">게시판 1</option>
								<option value="2">게시판 2</option>
								<option value="3">게시판 3</option>
								<option value="4">게시판 4</option>
								<option value="5">게시판 5</option>
								<option value="6">게시판 6</option>
						</select>
						</span> <input type="text" class="form-control" id="board_title"
							name="board_title" placeholder="제목" required class="form-control"
							aria-label="Text input with dropdown button" style="width: 300px">

					</div>
					<input class="file" id="chooseFile" type="file" name="upload" multiple required class="form-control">	
					<h1>ㅤ</h1>
				</div>
				<!-- 공개설정 -->
				<input type="hidden" name="board_see" value="all"checked>
				<textArea id="board_content" name="board_content" required
					class="form-control" rows="20"></textArea>
				<h1>ㅤ</h1>
				<input type="submit" class="btn btn-default" id="btn" value="글쓰기" style="float: right; background-color: #d7ebc2; border-radius: 10px">
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
<%@ include file="../include/footer.jsp"%>