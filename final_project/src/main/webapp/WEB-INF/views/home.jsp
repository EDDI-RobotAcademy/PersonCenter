<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="./include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파이널 홈</title>
</head>
<body class="responsive is-round">
	<script>
		// Page Loader
		$(window).on('load', function() {
			$("#nt_loader").delay(100).fadeOut("slow");
		});
		$(document).ready(function() {
			$('#nt_loader').on('click', function() {
				$('#nt_loader').fadeOut();
			});
		});
	</script>
	<div id="nt_loader">
		<div class="loader">
			<i class="fa fa-spinner fa-spin text-primary"></i> <span
				class="sr-only">Loading...</span>
		</div>
	</div>


	<div id="nt_body" class="nt-body">

		<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
			<div class="row na-row">
				<!-- 상단 광고 배너 -->
				<div class="col-md-9 na-col">


					<div class="mb-3 mb-sm-4">
						<div id="carousel_wmjsrnletughxfoqikvp" class="carousel slide"
							data-ride="carousel" data-interval="5000">
							<div class="carousel-inner">
								<div class="carousel-item active">
									<div class="img-wrap">
										<div class="img-item">
											<a href="https://youtu.be/bDZtSJSU_nU" target="_blank"> <img
												src="https://img.youtube.com/vi/bDZtSJSU_nU/maxresdefault.jpg"
												alt="일본 K-소주에 취하다">
											</a>
										</div>
									</div>
								</div>
								<div class="carousel-item">
									<div class="img-wrap">
										<div class="img-item">
											<a href="https://youtu.be/EepDPC5f1Yk" target="_blank"> <img
												src="https://img.youtube.com/vi/EepDPC5f1Yk/maxresdefault.jpg"
												alt="현대 아이오닉6에 대한 해외반응">
											</a>
										</div>
									</div>
								</div>
								<div class="carousel-item">
									<div class="img-wrap">
										<div class="img-item">
											<a href="https://youtu.be/-yjfbeQzIbA" target="_blank"> <img
												src="https://img.youtube.com/vi/-yjfbeQzIbA/maxresdefault.jpg"
												alt="김하성의 허슬플레이">
											</a>
										</div>
									</div>
								</div>
								<div class="carousel-item">
									<div class="img-wrap">
										<div class="img-item">
											<a href="https://youtu.be/W6z1L1RmPK0" target="_blank"> <img
												src="https://img.youtube.com/vi/W6z1L1RmPK0/maxresdefault.jpg"
												alt="미국 시골에서 백종원의 홍콩반점을 차리면 생기는일">
											</a>
										</div>
									</div>
								</div>
								<div class="carousel-item">
									<div class="img-wrap">
										<div class="img-item">
											<a href="https://youtu.be/FzOhSEuSpow" target="_blank"> <img
												src="https://img.youtube.com/vi/FzOhSEuSpow/maxresdefault.jpg"
												alt="현대 N 비전 74에 해외 반응">
											</a>
										</div>
									</div>
								</div>

							</div>
							<!-- 배너 컨트롤 -->
							<a class="carousel-control-prev"
								href="#carousel_wmjsrnletughxfoqikvp" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">이전</span>
							</a> <a class="carousel-control-next"
								href="#carousel_wmjsrnletughxfoqikvp" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">다음</span>
							</a>
							<!-- 배너 하단 표시 -->
							<ol class="carousel-indicators">
								<li data-target="#carousel_wmjsrnletughxfoqikvp"
									data-slide-to="0" class="active"></li>
								<li data-target="#carousel_wmjsrnletughxfoqikvp"
									data-slide-to="1"></li>
								<li data-target="#carousel_wmjsrnletughxfoqikvp"
									data-slide-to="2"></li>
								<li data-target="#carousel_wmjsrnletughxfoqikvp"
									data-slide-to="3"></li>
								<li data-target="#carousel_wmjsrnletughxfoqikvp"
									data-slide-to="4"></li>
							</ol>
						</div>
					</div>
					<!-- 배너 끝 -->

				<div class="row na-row">
                  <!-- 자유게시판 -->
                  <div class="col-md-4 na-col">

                     <!-- 위젯 시작 { -->
                     <h3 class="h3 f-lg en">
                        <a href="${pageContext.request.contextPath}/boards_free"> <span
                           class="float-right more-plus"></span> 자유게시판
                        </a>
                     </h3>
                     <hr class="hr" />
                     <div class="mt-3 mb-4">
                        <ul class="na-list mt-n1">
                           <c:forEach var="item" items="${boards_free}" varStatus="status">
                              <li class="px-3 px-sm-0">
                                 <div class="na-title">
                                    <div
                                       class="float-right text-muted f-sm font-weight-normal ml-2">
                                       <span class="sr-only">등록일</span>
                                       <fmt:formatDate pattern="MM.dd" value="${item.board_date}" />

                                    </div>
                                    <div class="na-item">
                                       <a
                                          href="${pageContext.request.contextPath}/boards_free/get?board_num=${item.board_num}&page=${page}"
                                          class="na-subject"> <span class="rank-icon en bg-red">${status.count}</span>
                                          ${item.board_title}
                                       </a>
                                    </div>
                                 </div>
                              </li>
                           </c:forEach>
                        </ul>
                     </div>
                     <!-- } 위젯 끝-->

                  </div>

                  <!-- 정보게시판 -->
                  <div class="col-md-4 na-col">

                     <!-- 위젯 시작 { -->
                     <h3 class="h3 f-lg en">
                        <a href="${pageContext.request.contextPath}/boards_data"> <span
                           class="float-right more-plus"></span> 정보게시판
                        </a>
                     </h3>
                     <hr class="hr" />
                     <div class="mt-3 mb-4">
                        <ul class="na-list mt-n1">
                           <c:forEach var="item" items="${boards_data}" varStatus="status">
                              <li class="px-3 px-sm-0">
                                 <div class="na-title">
                                    <div
                                       class="float-right text-muted f-sm font-weight-normal ml-2">
                                       <span class="sr-only">등록일</span>
                                       <fmt:formatDate pattern="MM.dd" value="${item.board_date}" />

                                    </div>
                                    <div class="na-item">
                                       <a
                                          href="${pageContext.request.contextPath}/boards_data/get?board_num=${item.board_num}&page=${page}"
                                          class="na-subject"> <span class="rank-icon en bg-orange">${status.count}</span>
                                          ${item.board_title}
                                       </a>
                                    </div>
                                 </div>
                              </li>
                           </c:forEach>
                        </ul>
                     </div>
                     <!-- } 위젯 끝-->

                  </div>

						<!-- # -->
						<div class="col-md-4 na-col">

							<!-- 위젯 시작 { -->
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_auction"> <span
                           class="float-right more-plus"></span> 경매게시판
                        </a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<ul class="na-list mt-n1">
									<c:forEach var="item" items="${auction}" varStatus="status">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />

												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards_auction/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> <span class="rank-icon en bg-green">${status.count}</span>
														${item.board_title}
													</a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
							<!-- } 위젯 끝-->

						</div>
					</div>

					<!-- 위젯 시작 { -->
					<h3 class="h3 f-lg en">협찬</h3>
					<hr class="hr" />
					<div class="px-3 px-sm-0 mt-3 mb-4">
						<ul id="banner_rfhicvptelnkqjgdoums"
							class="owl-carousel basic-banner">
							<li class="item">
								<div class="img-wrap" style="padding-bottom: 56.25%;">
									<div class="img-item">
										<a href="https://youtu.be/W6z1L1RmPK0" target="_blank"> <img
											src="https://img.youtube.com/vi/W6z1L1RmPK0/maxresdefault.jpg"
											alt="미국 시골에서 백종원의 홍콩반점을 차리면 생기는일" class="na-round">
										</a>
									</div>
								</div>
							</li>
							<li class="item">
								<div class="img-wrap" style="padding-bottom: 56.25%;">
									<div class="img-item">
										<a href="https://youtu.be/bDZtSJSU_nU" target="_blank"> <img
											src="https://img.youtube.com/vi/bDZtSJSU_nU/maxresdefault.jpg"
											alt="일본 K-소주에 취하다" class="na-round">
										</a>
									</div>
								</div>
							</li>
							<li class="item">
								<div class="img-wrap" style="padding-bottom: 56.25%;">
									<div class="img-item">
										<a href="https://youtu.be/-yjfbeQzIbA" target="_blank"> <img
											src="https://img.youtube.com/vi/-yjfbeQzIbA/maxresdefault.jpg"
											alt="김하성의 허슬플레이" class="na-round">
										</a>
									</div>
								</div>
							</li>
							<li class="item">
								<div class="img-wrap" style="padding-bottom: 56.25%;">
									<div class="img-item">
										<a href="https://youtu.be/EepDPC5f1Yk" target="_blank"> <img
											src="https://img.youtube.com/vi/EepDPC5f1Yk/maxresdefault.jpg"
											alt="현대 아이오닉6에 대한 해외반응" class="na-round">
										</a>
									</div>
								</div>
							</li>
							<li class="item">
								<div class="img-wrap" style="padding-bottom: 56.25%;">
									<div class="img-item">
										<a href="https://youtu.be/FzOhSEuSpow" target="_blank"> <img
											src="https://img.youtube.com/vi/FzOhSEuSpow/maxresdefault.jpg"
											alt="현대 N 비전 74에 해외 반응" class="na-round">
										</a>
									</div>
								</div>
							</li>

						</ul>

						<script>
							$('#banner_rfhicvptelnkqjgdoums').owlCarousel({
								autoplay : true,
								autoplayHoverPause : true,
								loop : true,
								item : 4,
								margin : 12,
								stagePadding : 0,
								nav : true,
								dots : false,
								responsive : {
									0 : {
										items : 2
									},
									575 : {
										items : 3
									},
									767 : {
										items : 4
									},
									991 : {
										items : 4
									},
									1199 : {
										items : 4
									}
								}
							});
						</script>

					</div>
					<!-- } 위젯 끝-->


					<h3 class="h3 f-lg en">ㅤ</h3>
					<div class="row na-row">
						<!-- 여기부터 거래글1  -->
						<div class="col-md-4 na-col">
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=1"> <span
									class="float-right more-plus"></span>게시판 1
								</a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<!-- 거래글 테이블 -->
								<ul class="na-list mt-n1">

									<c:forEach var="item" items="${boards_home_1}">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />
												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> ${item.board_title} </a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!-- 거래글 목록 끝-->
						<!-- 여기부터 거래글1  -->
						<div class="col-md-4 na-col">
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=2"> <span
									class="float-right more-plus"></span>게시판 2
								</a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<!-- 거래글 테이블 -->
								<ul class="na-list mt-n1">

									<c:forEach var="item" items="${boards_home_2}">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />
												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> ${item.board_title} </a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!-- 거래글 목록 끝-->
						<!-- 여기부터 거래글1  -->
						<div class="col-md-4 na-col">
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=3"> <span
									class="float-right more-plus"></span>게시판 3
								</a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<!-- 거래글 테이블 -->
								<ul class="na-list mt-n1">

									<c:forEach var="item" items="${boards_home_3}">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />
												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> ${item.board_title} </a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!-- 거래글 목록 끝-->
					</div>

					<div class="row na-row">
						<!-- 여기부터 거래글1  -->
						<div class="col-md-4 na-col">
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=4"> <span
									class="float-right more-plus"></span>게시판 4
								</a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<!-- 거래글 테이블 -->
								<ul class="na-list mt-n1">

									<c:forEach var="item" items="${boards_home_4}">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />
												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> ${item.board_title} </a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!-- 거래글 목록 끝-->
						<!-- 여기부터 거래글1  -->
						<div class="col-md-4 na-col">
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=5"> <span
									class="float-right more-plus"></span>게시판 5
								</a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<!-- 거래글 테이블 -->
								<ul class="na-list mt-n1">

									<c:forEach var="item" items="${boards_home_5}">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />
												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> ${item.board_title} </a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!-- 거래글 목록 끝-->
						<!-- 여기부터 거래글1  -->
						<div class="col-md-4 na-col">
							<h3 class="h3 f-lg en">
								<a href="${pageContext.request.contextPath}/boards_transaction/transaction?board_category_name=6"> <span
									class="float-right more-plus"></span>게시판 6
								</a>
							</h3>
							<hr class="hr" />
							<div class="mt-3 mb-4">
								<!-- 거래글 테이블 -->
								<ul class="na-list mt-n1">

									<c:forEach var="item" items="${boards_home_6}">
										<li class="px-3 px-sm-0">
											<div class="na-title">
												<div
													class="float-right text-muted f-sm font-weight-normal ml-2">
													<span class="sr-only">등록일</span>
													<fmt:formatDate pattern="MM.dd" value="${item.board_date}" />
												</div>
												<div class="na-item">
													<a
														href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}&page=${page}"
														class="na-subject"> ${item.board_title} </a>
												</div>
											</div>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!-- 거래글 목록 끝-->
					</div>
				</div>
				<!-- 사이드 영역 -->
				<div class="col-md-3 na-col">
					<!-- 로그인안되어 있을때 -->
					<c:if test="${empty loginid }">
						<div class="d-none d-md-block mb-4">
							<div class="f-de font-weight-normal">

								<form action="customer/login" method="post">
									<div class="form-group">
										<label for="outlogin_mb_id" class="sr-only">아이디<strong
											class="sr-only"> 필수</strong></label>
										<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <img
													src="${path}/resources/img/user.png" width="16px">
												</span>
											</div>
											<input type="text" name="cus_id" id="cus_id"
												class="form-control required" placeholder="아이디">
										</div>
									</div>
									<div class="form-group">
										<label for="outlogin_mb_password" class="sr-only">비밀번호<strong
											class="sr-only"> 필수</strong></label>
										<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <img
													src="${path}/resources/img/password.png" width="16px">
												</span>
											</div>
											<input type="password" name="cus_pw" id="cus_pw"
												class="form-control required" placeholder="비밀번호">
										</div>
									</div>
									<div class="form-group">
										<button type="submit"
											class="btn btn-primary btn-block btn-lg en">로그인</button>
									</div>

									<div>
										<div>
											<a href="${pageContext.request.contextPath}/customer/join">
												회원가입 </a> <span class="na-bar"></span> <a
												href="${pageContext.request.contextPath}/customer/findPassword"
												class="win_password_lost"> 비밀번호찾기 </a>
										</div>
									</div>
								</form>


							</div>
						</div>
					</c:if>

					<!-- 로그인 되어 있을떄-->
					<c:if test="${!empty loginid }">
						<div class="d-none d-md-block mb-4">

							<div class="f-de font-weight-normal">


								<div class="d-flex align-items-center mb-3">
									<div class="pr-3">
										<a> <c:if test="${!empty customer}">
												<img src="${pageContext.request.contextPath}/customer/download"
													class="rounded-circle" style="width: 60px; height: 60px;">
											</c:if> <c:if test="${empty customer}">
												<img
													src="${pageContext.request.contextPath}/img/post-sample-image"
													class="rounded-circle" style="width: 60px; height: 60px;">
											</c:if>
										</a>
									</div>
									<div class="flex-grow-1 pt-2">

										<h5 class="hide-photo mb-2">


											<b style="letter-spacing: -1px;"> <span class="sv_wrap">

													<a>${loginid} 님 <br>환영합니다
												</a>
											</span></b>
										</h5>
									</div>
								</div>
								<div class="btn-group w-100" role="group"
									aria-label="Member Menu">
									<a href="${pageContext.request.contextPath}/customer/vertify"
										class="btn btn-primary text-white" role="button"> 내정보 </a> <a
										href="${pageContext.request.contextPath}/customer/logout"
										class="btn btn-primary text-white" role="button"> 로그아웃 </a> <a
										href="${pageContext.request.contextPath}/customer/withdrawalView"
										class="btn btn-primary text-white" role="button"> 회원탈퇴 </a>
								</div>

							</div>
						</div>

					</c:if>
					<!-- 위젯 시작 { -->
					<h3 class="h3 f-lg en">
						<a href="#"> <span class="float-right more-plus"></span> 공지글
						</a>
					</h3>
					<hr class="hr" />
					<div class="mt-3 mb-4">

						<ul class="na-list mt-n1">
							<li class="px-3 px-sm-0"><c:forEach var="item"
									items="${Notice_boards}">

									<div class="na-title">
										<div
											class="float-right text-muted f-sm font-weight-normal ml-2">
											<span class="sr-only">등록일</span>
											<fmt:formatDate pattern="MM.dd" value="${item.board_no_date}" />
										</div>
										<div class="na-item">
											<a
												href="${pageContext.request.contextPath}/notice/get?board_num=${item.board_num}&page=${page}"
												class="na-subject"> ${item.board_no_title} </a>
										</div>
									</div>

								</c:forEach></li>

						</ul>

					</div>
					<!-- } 위젯 끝-->

					<!-- 위젯 시작 { -->
					<div class="px-3 px-sm-0 mb-4">
						﻿
						<div class="img-wrap na-round">
							<div class="img-item">
								<div id="vid_rgucipokdlbsnehfmtqja"></div>
							</div>
						</div>

						<script>
							$('#vid_rgucipokdlbsnehfmtqja').prettyEmbed({
								videoID : '-yjfbeQzIbA',
								previewSize : 'maxresdefault',
								customPreviewImage : '',
								showInfo : false,
								showControls : true,
								loop : false,
								colorScheme : 'dark',
								showRelated : false,
								useFitVids : true
							});
						</script>
					</div>

					<!-- } 위젯 끝-->

					<!-- 위젯 시작 { -->
					<h3 class="h3 f-lg en mb-1">
						<a href="http://gukbbong.com/bbs/new.php?view=w"> <span
							class="float-right more-plus"></span> 최근글
						</a>
					</h3>
					<hr class="hr" />
					<div class="mt-3 mb-4">

						<ul class="na-list mt-n1">
							<li class="px-3 px-sm-0">
								<div class="na-title">
									<div
										class="float-right text-muted f-sm font-weight-normal ml-2">
										<span class="sr-only">등록일</span> 11.26
									</div>
									<div class="na-item">
										<a href="#" class="na-subject"> 한류의 진화, 일본 MZ세대의 한국 열풍 </a>
									</div>
								</div>
							</li>
							<li class="px-3 px-sm-0">
								<div class="na-title">
									<div
										class="float-right text-muted f-sm font-weight-normal ml-2">
										<span class="sr-only">등록일</span> 11.19
									</div>
									<div class="na-item">
										<a href="#" class="na-subject"> 맥도날드 월드컵광고 feat ITZY </a>
									</div>
								</div>
							</li>
							<li class="px-3 px-sm-0">
								<div class="na-title">
									<div
										class="float-right text-muted f-sm font-weight-normal ml-2">
										<span class="sr-only">등록일</span> 11.15
									</div>
									<div class="na-item">
										<a href="#" class="na-subject"> 심슨가족이 부르는 블랙핑크 </a>
									</div>
								</div>
							</li>
							<li class="px-3 px-sm-0">
								<div class="na-title">
									<div
										class="float-right text-muted f-sm font-weight-normal ml-2">
										<span class="sr-only">등록일</span> 11.13
									</div>
									<div class="na-item">
										<a href="#" class="na-subject"> 코빗에서 작성한 Alameda Research
											/ FTX 유동성 사태 현황 보고서 입니다 </a>
									</div>
								</div>
							</li>
							<li class="px-3 px-sm-0">
								<div class="na-title">
									<div
										class="float-right text-muted f-sm font-weight-normal ml-2">
										<span class="sr-only">등록일</span> 10.28
									</div>
									<div class="na-item">
										<a href="#" class="na-subject"> 너무 잔혹해서, 칼리스토 프로토콜 日 발매 취소
										</a>
									</div>
								</div>
							</li>

						</ul>

					</div>
					<!-- } 위젯 끝-->

				</div>
			</div>
		</div>
	</div>
	<!-- .nt-body -->

</body>
</html>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>