<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메세지</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<!-- 커스텀 폰트 -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- body 시작  -->
	<div id="nt_body" class="nt-body">
	<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
	<!-- Page Header -->
	<header class="masthead"
		style="background-image: url('${pageContext.request.contextPath}/resources/img/home-bg.jpg')">
		<div class="container">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<c:if test="${category=='read' }">
							<span class="subheading"> 받은 메세지를 확인하세요 </span>
						</c:if>
						<c:if test="${category=='sent' }">
							<span class="subheading"> 보낸 메세지를 확인하세요 </span>
						</c:if>
					</div>
				</div>
		</div>
	</header>
	
	<!-- 메인영역 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<c:if test="${category=='read' }">
						<h2>${loginid }가&nbsp받은 메세지</h2>
					</c:if>
					<c:if test="${category=='sent'}">
						<h2>&nbsp${loginid}가&nbsp보낸 메세지</h2>
					</c:if>

					<table class="table" cellpadding="10">
						<tr>
							<td>제목</td>
							<c:if test="${category=='read' }">
								<td>보낸이</td>
							</c:if>
							<c:if test="${category=='sent' }">
								<td>받는이</td>
							</c:if>
							<td>날짜</td>
							<c:if test="${category=='sent' }">
								<td>확인여부</td>
							</c:if>
						</tr>
						<c:forEach var="item" items="${message }">
							<c:if
								test="${category=='read'&&item.friend_status!='delete'||category=='sent'&&item.cus_status!='delete' }">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/message/get?message_num=${item.message_num}&category=${category}&page=${page}">${item.message_title }
											<c:if
												test="${item.friend_status=='receive'&&category=='read'||item.cus_status=='sent'&&category=='sent'}">
												<span class="w3-badge w3-red">New</span>
											</c:if>
									</a></td>
									<c:if test="${category=='read' }">
										<td>${item.cus_id }</td>
									</c:if>
									<c:if test="${category=='sent' }">
										<td>${item.friend_id }</td>
									</c:if>
									<td>${item.message_date }</td>
									<c:if test="${category=='sent' }">
										<c:if
											test="${item.friend_status=='read'||item.friend_status=='delete' }">
											<td>읽음</td>
										</c:if>
										<c:if test="${item.friend_status=='receive' }">
											<td>안읽음</td>
										</c:if>
									</c:if>
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<hr>
			
					<div style="text-align: center;">
						<br> <a
							href="${pageContext.request.contextPath}/message/management?page=${page-1}&category=${category}">&laquo;</a>
						<c:forEach var="page" begin="${page }" end="${endPage }">
							<a
								href="${pageContext.request.contextPath}/message/management?page=${page}&category=${category}">${page}</a>
						</c:forEach>
						<a
							href="${pageContext.request.contextPath}/message/management?page=${page+1}&category=${category}">&raquo;</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<!-- Custom scripts for this template -->
	<script
		src="${pageContext.request.contextPath}/resources/js/clean-blog.min.js"></script>

</body>
</html>
<%@ include file="../include/footer.jsp" %>