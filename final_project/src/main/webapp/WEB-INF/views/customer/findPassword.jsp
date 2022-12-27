<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<script>
	$(function() {
		$('#findPassword').on('click', findPassword);
	});
	function findPassword() {
		$
				.ajax({
					url : "findPassword",
					method : "post",
					data : {
						"cus_id" : $("#cus_id").val(),
						"cus_question" : $("#cus_question").val(),
						"cus_answer" : $("#cus_answer").val()
					},
					success : function(password) {
						if (password != 'false') {

							alert("비밀번호는  " + password
									+ "  입니다. 다시 로그인 해주세요");
							window.close();
						} else if (password == 'false') {
							alert("비밀번호와 답변을 다시 확인해주세요");
						}
					}
				});
	}
</script>
<body>
	<!-- 메인 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<table cellpadding="10">
						<tr>
							<td>아이디</td>
							<td><input type="text" id="cus_id" name="cus_id" required></td>
						</tr>
						<tr>
							<td>질문</td>
							<td><select id="cus_question" name="cus_question">
									<option value="1">가장 좋아하는 장소는?</option>
									<option value="2">가장 좋아하는 동물은?</option>
									<option value="3">가장 좋아하는 음식은?</option>
							</select></td>
						</tr>
						<tr>
							<td>답변</td>
							<td><input type="text" id="cus_answer" name="cus_answer" required></td>
						</tr>
					</table>
					<br> <input type="button" id="findPassword"
						value="비밀번호 찾기" class="btn btn-default">
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