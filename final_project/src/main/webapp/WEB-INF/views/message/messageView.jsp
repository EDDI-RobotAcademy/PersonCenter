<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메세지 보내기</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<script>
	/* $(function() {
		$('#sendMassage').on('click', sendMassage);
	});
	function sendMassage() {
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
	} */
	
	
	$(function(){
		$('#send').on('click', message);
	});
	function message(){
		$.ajax({
			url:"send",
			method:"post",
			data:{
				"cus_id":${loginid},
				"friend_id":${trader.friend_id},
				"message_title":$("#message_title").val(),
				"message":$("#message").val()
			},
			success:function(friend_id){
				alert(friend_id+"님에게 메세지를 전송하였습니다");
				closeWindow();	
			}
		});
	}
	function closeWindow(){
		this.close();
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
							<td>받는사람</td>
							<td><input type="text" value="${trader.friend_id}" id="cus_id" name="cus_id" required  readonly="readonly"></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" id="message_title" name="message_title" required></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textArea rows="10" id="message" name="message" required ></textArea></td>
						</tr>
					</table>
					<br> <input type="button" id="send"
						value="보내기" class="btn btn-default">
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