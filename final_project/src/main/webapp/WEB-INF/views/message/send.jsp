<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<title>메세지보내기</title>
</head>
<script>
	$(function(){
		$('#send').on('click', message);
	});
	function message(){
		$.ajax({
			url:"send",
			method:"post",
			data:{
				"cus_id":$("#loginid").val(),
				"friend_id":$("#cus_id").val(),
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
	<table class="table">
		<tr>
			<td>받는이</td>
			<td>${cus_id} <input type="hidden" id="cus_id" class="cus_id" value=${cus_id } required></td> <!-- ajax cus_id 변수설정 -->
		</tr>
		<tr>
			<td>보낸이</td>
			<td>${loginid} <input type="hidden" id="loginid" class="loginid" value=${loginid } required></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" id="message_title" class="form-control"
				required></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textArea id="message" name="message" class="form-control"
					rows="10" required></textArea></td>
		</tr>
	</table>
	<div class="center">
		<input type="button" id="send" value="메세지보내기"
			class="btn btn-default">
	</div>
</body>
</html>