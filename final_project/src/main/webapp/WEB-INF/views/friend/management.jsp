<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "path" value = "${pageContext.request.contextPath}" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>친구관리</title>

<meta http-equiv="content-language" content="kr">
<meta name="apple-mobile-web-app-title">
</head>
<script>
	function sendMessage(cus_id) {
		var temp = window.open(
				"${pageContext.request.contextPath}/message/send?cus_id="
						+ cus_id, "sendMessage",
				"top=500, left=600, width=500,height=500");
	}
	function acceptFriend(friend_id) {
		confirm("친구가 되시겠습니까? ")
		{
			$
					.ajax({
						url : "accept",
						method : "post",
						data : {
							"friend_id" : friend_id
						},
						success : function(friend_id) {
							alert(friend_id + "님과 친구가 되었습니다!");
							location.href = "${pageContext.request.contextPath}/friend/management";
						}
					});
		}
	}
	function removeFriend(friend_id) {
		confirm(friend_id + "친구가 되고 싶지 않으십니까?");
		{
			$
					.ajax({
						url : "remove",
						method : "post",
						data : {
							"friend_id" : friend_id
						},
						success : function(friend_id) {
							alert(friend_id
									+ "친구를 삭제했습니다");
							location.href = "${pageContext.request.contextPath}/friend/management";
						}
					});

		}
	}
</script>
<body class="responsive is-round">
<script>
// Page Loader
$(window).on('load', function () {
	$("#nt_loader").delay(100).fadeOut("slow");
});
$(document).ready(function() {
	$('#nt_loader').on('click', function () {
		$('#nt_loader').fadeOut();
	});
});
</script>
<div id="nt_loader">
	<div class="loader">
		<i class="fa fa-spinner fa-spin text-primary"></i>
		<span class="sr-only">Loading...</span>
	</div>
</div>


<style>
.nt-container { max-width:1100px; }
.nt-container-wide { max-width:1980px; }
.boxed.wrapper, .boxed.wrapper #nt_menu_wrap.me-sticky nav { max-width:1100px; }
.no-responsive .wrapper { min-width:1100px; }
</style>

<div id="nt_body" class="nt-body">
	
<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
	<div class="row na-row">
		<!-- 메인 영역 -->
		

<!-- 친구 목록 시작 { -->
<div id="bo_list_wrap" class="mb-4">
	
		<!-- 친구 목록 시작 { -->
		<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">	
				</div>
				<hr>
				<h2>친구요청</h2>
				<table cellpadding="10">
					<tr>
						<td>아이디</td>
						<td>관리</td>
						<td>메세지</td>
					</tr>
					<c:forEach var="items" items="${request}">
						<tr>
							<td><a href="${pageContext.request.contextPath}/boards?friend_id=${items}">${items}</a></td>
							<td><input type="button" onClick="acceptFriend('${items}')"
								value="수락하기" class="btn btn-default"></td>
							<td><input type="button" value="메세지"
								class="btn btn-default" onclick="sendMessage('${items}');"></td>
						</tr>
					</c:forEach>
				</table>
				<hr>
				<h4>친구리스트</h4>
				<table cellpadding="10">
					<tr>
						<td>아이디</td>
						<td>관리</td>
						<td>메세지</td>
					</tr>
					<c:forEach var="items" items="${friends}">
						<tr>
							<td><a
								href="${pageContext.request.contextPath}/boards?friend_id=${items}">${items }</a></td>
							<td><input type="button" value="삭제"
								onClick="removeFriend('${items }')" class="btn btn-default"></td>
							<td><input type="button" value="메세지 보내기"
								class="btn btn-default" onclick="sendMessage('${items}');"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
					</div>	
						
   			</div>
   			
		</div>
		
		<div class="font-weight-normal px-3 px-sm-0">
		<ul class="pagination justify-content-center en mb-0">
		<li class="page-item"><a href="${pageContext.request.contextPath}/friend/home?page=${page-1}&friend_id=${friend_id}">&laquo;</a></li>
		<c:forEach var="page" begin="${page}" end="${endPage }">
		<li><a href="${pageContext.request.contextPath}/friend/home?page=${page}&friend_id=${friend_id}">${page}</a></li>
		</c:forEach>
		<li class="page-item"><a href="${pageContext.request.contextPath}/friend/home?page=${page+1}&friend_id=${friend_id}">&raquo;</a></li>
		
		</ul>
		</div>
		</div>

</body>
</html>
<%@ include file="../include/footer.jsp" %>
