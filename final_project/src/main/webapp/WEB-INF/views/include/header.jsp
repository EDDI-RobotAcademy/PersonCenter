<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link href="${path}/resources/css/NEXON-Gothic-14px.css"
	rel="stylesheet">
<link href="${path}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/resources/css/carousel.css" rel="stylesheet">
<link href="${path}/resources/css/nariya.css" rel="stylesheet">
<link href="${path}/resources/css/theme.css" rel="stylesheet">
<link href="${path}/resources/css/Blue.css" rel="stylesheet">
<link href="${path}/resources/css/widget1.css" rel="stylesheet">
<link href="${path}/resources/css/widget2.css" rel="stylesheet">
<link href="${path}/resources/css/widget3.css" rel="stylesheet">

<style>
.nt-container {
	max-width: 1100px;
}

.nt-container-wide {
	max-width: 1980px;
}

.boxed.wrapper, .boxed.wrapper #nt_menu_wrap.me-sticky nav {
	max-width: 1100px;
}

.no-responsive .wrapper {
	min-width: 1100px;
}
</style>

<style>
#carousel_wmjsrnletughxfoqikvp .img-wrap {
	padding-bottom: 27%;
}

@media ( max-width :1199px) {
	.responsive #carousel_wmjsrnletughxfoqikvp .img-wrap {
		padding-bottom: 27% !important;
	}
}

@media ( max-width :991px) {
	.responsive #carousel_wmjsrnletughxfoqikvp .img-wrap {
		padding-bottom: 35% !important;
	}
}

@media ( max-width :767px) {
	.responsive #carousel_wmjsrnletughxfoqikvp .img-wrap {
		padding-bottom: 45% !important;
	}
}

@media ( max-width :575px) {
	.responsive #carousel_wmjsrnletughxfoqikvp .img-wrap {
		padding-bottom: 56.25% !important;
	}
}
</style>

<script>
	
</script>
<script src="http://gukbbong.com/nariya/js/jquery-3.5.1.min.js"></script>
<script src="http://gukbbong.com/nariya/js/common.js?ver=210618"></script>
<script src="http://gukbbong.com/js/wrest.js?ver=210618"></script>
<script src="http://gukbbong.com/js/placeholders.min.js"></script>
<script
	src="http://gukbbong.com/nariya/app/bs4/js/bootstrap.bundle.min.js"></script>
<script src="http://gukbbong.com/nariya/js/nariya.js?ver=210618"></script>
<script src="http://gukbbong.com/theme/BS4-Basic/js/theme.js"></script>
<script async
	src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-8696806389739546"
	crossorigin="anonymous"></script>

<script src="http://gukbbong.com/nariya/js/sly.min.js?ver=210618"></script>
<script
	src="http://gukbbong.com/nariya/app/owlcarousel/owl.carousel.min.js?ver=210618"></script>
<script src="http://gukbbong.com/nariya/js/jquery.fitvids.js?ver=210618"></script>
<script
	src="http://gukbbong.com/nariya/js/jquery.prettyembed.min.js?ver=210618"></script>


<!-- 헤더 -->
<div class="wrapper wided">
	<div id="nt_header">

		<nav id="nt_menu"
			class="nt-menu bg-primary d-none d-md-block font-weight-normal">
			<h3 class="sr-only">메인 메뉴</h3>
			<div class="nt-container">
				<div class="d-flex">
					<div class="flex-grow-1 order-2 me-list">
						<ul class="row m-0 me-ul nav-slide">
							<li class="col p-0 me-li"><a class="d-block bg-primary"
								href="${pageContext.request.contextPath}/boards_free"
								target="_self"> <span class="me-a text-nowrap f-md en px-4">

										자유게시판 </span>
							</a></li>
							<li class="col p-0 me-li"><a class="d-block bg-primary"
								href="${pageContext.request.contextPath}/boards_data"
								target="_self"> <span class="me-a text-nowrap f-md en px-4">

										정보게시판 </span>
							</a></li>

							<li class="col p-0 me-li"><a class="d-block bg-primary"
								href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=0"
								target="_self"> <span class="me-a text-nowrap f-md en px-4">

										거래게시판 </span>
							</a></li>
							
							<li class="col p-0 me-li"><a class="d-block bg-primary"
								href="${pageContext.request.contextPath}/boards_auction"
								target="_self"> <span class="me-a text-nowrap f-md en px-4">

										경매게시판 </span>
							</a></li>

							<li class="col p-0 me-li"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#"> <span
									class="me-a text-nowrap f-md en px-4">친구</span>
							</a>
								<ul class="dropdown-menu">
									<li><a class="nav-link" href="${path}/friend/home">친구찾기</a></li>
									<li><a class="nav-link" href="${path}/friend/management">친구관리
											<span class="w3-badge">${numofFriendRequest}</span>
									</a></li>
									<li><a class="nav-link"
										href="${path}/message/management?category=read">메세지읽기</a></li>
									<li><a class="nav-link"
										href="${path}/message/management?category=sent">메세지보내기 <span
											class="w3-badge">${numofFriendRequest}</span>
									</a></li>
									
								</ul></li>

							<!-- 관리자라면 -->
							<c:if test="${loginid == 'admin'}">
								<li class="col p-0 me-li"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> <span
										class="me-a text-nowrap f-md en px-4">관리자페이지</span>
								</a>
									<ul class="dropdown-menu">
										<li><a class="nav-link" href="${path}/notice">공지사항</a></li>
										<li><a class="nav-link" href="${path}/admin">회원관리</a></li>
									</ul></li>
							</c:if>



							<li class="col p-0 me-li"><a class="d-block bg-primary"
								href="${pageContext.request.contextPath}/boards" target="_self">
									<span class="me-a text-nowrap f-md en px-4"> 나의 거래글 </span>
							</a></li>

						</ul>
					</div>
					<div class="me-icon order-1 me-li off">
						<a href="${path}/" class="me-a f-md" title="메인으로"> <img
							src="${path}/resources/img/white-home-icon-png-21.jpg"
							width="28px">
						</a>
					</div>
				</div>
			</div>
			<script>
				$(document).ready(function() {
					$('#nt_menu .nav-slide').nariya_menu(); // 주메뉴
				});
			</script>
			<script>
				// 컨텐츠 영역 최소 높이
				$(document).ready(function() {
					na_content_height('nt_body', 'nt_header', 'nt_footer');
					$(window).resize(function() {
						na_content_height('nt_body', 'nt_header', 'nt_footer');
					});
				});
			</script>

		</nav>
		<!-- 헤더끝 -->
	</div>
	<!-- 헤더고정끝 -->
</div>
<!-- 헤더고정체크끝 -->