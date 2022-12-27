<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메세지 읽기</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

<!-- Custom styles for this template -->
</head>
<script>
function deleteMessage(){
	if(confirm('메세지를 삭제하시겠습니까?')){
			$.ajax({
				method : "post",
				url : "delete",
				data : {
					"message_num" : ${message.message_num},
					"category":"${category}"
				},
				success : function(result) {
					if(result==1){
					alert('메세지가 삭제되었습니다.');
					location.href = "${pageContext.request.contextPath}/message/management?category=${category}";
					}
				}
			});}
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
						<c:if test="category=='read'">
							<h1>Read Message</h1>
							<span class="subheading">Read your Message </span>
						</c:if>
						<c:if test="category=='read'">
							<h1>Read Message</h1>
							<span class="subheading">Read message you sent </span>
						</c:if>

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
					<c:if test="category=='read'">
						<h2>메세지 읽기</h2>
					</c:if>
					<c:if test="category=='sent'">
						<h2>받은 메세지 읽기</h2>
					</c:if>
					<table cellpadding="10">
						<tr>
							<td>제목</td>
							<td>${message.message_title }</td>
						</tr>
						<tr>
							<td>보낸이</td>
							<td>${message.friend_id }</td>
						</tr>
						<tr>
							<td>날짜</td>
							<td>${message.message_date }</td>
						</tr>
						<tr>
							<td>메세지</td>
							<td>${message.message }</td>
						</tr>
						<tr>
							<td><input type="button" value="Delete"
								onClick="deleteMessage();" class="btn btn-default"> <input
								type="button" value="to List"
								onclick="location.href='${pageContext.request.contextPath}/message/management?category=${category}&page=${page}'"
								class="btn btn-default"></td>
					</table>
					<hr>
					<br>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
				</div>
			</div>
		</div>
	</footer>
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