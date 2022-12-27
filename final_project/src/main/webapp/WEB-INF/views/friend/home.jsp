<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "path" value = "${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>친구</title>
<meta http-equiv="content-language" content="kr">
<link href="${path}/resources/css/gun_custom.css" rel="stylesheet">
</head>
<body class="responsive is-round">	
<script>
	function sendMessage(cus_id) {
		var temp = window.open(
				"${pageContext.request.contextPath}/message/send?cus_id="
						+ cus_id, "sendMessage",
				"top=500, left=600, width=500,height=500");
	}
</script>
<body class="responsive is-round">
	<c:if test="${!empty requestResult }">
		<script>
			alert('${requestResult}');
		</script>
	</c:if>
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

<!-- body 시작  -->
<div id="nt_body" class="nt-body">
<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
	<div class="row na-row">
		<!-- 메인 영역 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<h2>친구찾기</h2>
					<div>
						<form action="findFriends" method="post">
							<select id="searchType" name="searchType">
								<option value="">전체</option>
								<option value="cus_id">아아디</option>
								<option value="cus_nickname">닉네임</option>
								<option value="cus_hobbies">취미</option>
							</select> <input type="text" id="searchContent" name="searchContent"
								style="width: 40%"> <input type="submit" value="검색"
								class="btn btn-default">
						</form>
					</div>
					<table width="700" cellpadding="10">
						<tr>
							<td>아이디</td>
							<td>성별</td>
							<td>닉네임</td>
							<td>취미</td>
							<td>친구요청</td>
							<td>메세지</td>
						</tr>
						<c:forEach var="items" items="${friends }">
							<tr>
								<c:if test="${items.cus_id!=loginid }">
									<td><a
										href="${pageContext.request.contextPath}/boards?friend_id=${items.cus_id}">${items.cus_id }</a></td>
									<td>${items.cus_gender }</td>
									<td>${items.cus_nickname }</td>
									<td>
										<form action="friendRequest" method="post" class ="friend">
											<input type="hidden" id="searchType" name="searchType" value="${searchType }"> 
											<input type="hidden" id="searchContent" name="searchContent" value="${searchContent }"> 
											<input type="hidden" id="friend_id" name="friend_id" value="${items.cus_id }">	
											<input type="submit" value="친구추가">
										</form>
										
									</td>
									<td>
									<button type="button" class ="button"value="메세지" onclick="sendMessage('${items.cus_id }');"></button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>

		</div>
		
		<div style="text-align: center;">
		<br> <a
			href="${pageContext.request.contextPath}/boards?page=${page-1}&friend_id=${friend_id}">&laquo;</a>
				<c:forEach var="page" begin="${page }" end="${endPage }">
					<a
					href="${pageContext.request.contextPath}/boards?page=${page}&friend_id=${friend_id}">${page}</a>
					</c:forEach>
					<a
					href="${pageContext.request.contextPath}/boards?page=${page+1}&friend_id=${friend_id}">&raquo;</a>
		</div>
		</div>

</body>
</html>
<%@ include file="../include/footer.jsp" %>
