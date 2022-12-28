<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<link href="${path}/resources/css/read.css" rel="stylesheet">
<link href="${path}/resources/css/gun_custom.css" rel="stylesheet">
<title>자유게시판 게시물 읽기</title>
<!-- Custom styles for this template -->
</head>
<script>
$(function(){
	$('#delete').on('click', deleteBoard);
	$('#insertReply').on('click', insertReply);
});
function deleteBoard(){
	if(confirm('게시물을 삭제하시겠습니까?')){
			$.ajax({
				method : "post",
				url : "delete",
				data : {
					"board_num" : ${free.board_num}
				},
				success : function(result) {
					if(result==1){ 
					alert('게시물이 성공적으로 삭제되었습니다.');
					history.go(0);
					}
				}
			});}
}

function insertReply(){
	$.ajax({
		method:"post",
		url:"insertReply",
		data:{
			"board_num":${free.board_num},
			"reply_content":$("#reply_content").val(),
			"page": ${page}
		},
		success:function(board_num){
				history.go(0);
		}
	});
}
function deleteReply(reply_num){
		$.ajax({
			method:"post",
			url:"deleteReply",
			data:{
				"reply_num":reply_num,
				"board_num":${free.board_num}
			},
			success:function(board_num){
				alert('삭제완료!');
				history.go(0);
			}
		});
}
</script>
<body>
	<div id="nt_body" class="nt-body">
		<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
			<div class="ArticleTopBtns">
				<div class="right_area">
				
					<!-- 작성자 본인이라면 수정, 삭제  -->
					<!-- 상위 수정, 삭제, 목록 버튼 -->
					<!-- 자유게시판 수정 버튼은 사라질 수도 있음 -->
					<c:if test="${loginid==free.board_id}">
						<a href="#" role="button"
							class="BaseButton BaseButton--skinGray size_default"> <input
							type="button" value="수정" id="update" name="update"
							onclick="updateBoard('${free.board_num}')"
							class="btn btn-default">
						</a>

						<a href="#" role="button"
							class="BaseButton BaseButton--skinGray size_default"> <input
							type="button" value="삭제" id="delete" name="delete"
							class="btn btn-default">
						</a>
					</c:if>

					<a href="${pageContext.request.contextPath}/boards_free" role="button"
						class="BaseButton BaseButton--skinGray size_default"> <input
						type="button" value="목록" class="btn btn-default">
					</a>
				</div>
				<div class="right_area"></div>
			</div>
			<!-- 상위 수정, 삭제, 목록 버튼 끝 -->
			<div class="ArticleContentBox">
				<div class="article_header">
					<div class="ArticleTitle">
						<a
							href="https://cafe.naver.com/ArticleList.nhn?search.clubid=10050146&amp;search.menuid=396"
							class="link_board"> 자유게시판 <svg
								class="svg-icon ico-post-arrow-03-c-75-a">
								<use xlink:href="#ico-post-arrow-03-c-75-a" aria-hidden="true"></use></svg></a>
						<div class="title_area">
							<!--일단 네이버 중고나라 카페로 가는 카테고리/카테고리-->
							<!---->
							<!--제목 시작 단-->
							<h3 class="title_text">${free.board_title}</h3>
						</div>
					</div>
					<div class="WriterInfo">
						<a href="/ca-fe/cafes/10050146/members/prkQguvG11zjC0KAkeAh_A"
							class="thumb"><img
							src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77"
							alt="프로필 사진" width="36" height="36"></a>
						<div class="profile_area">
							<div class="profile_info">
								<div class="nick_box">
									<button id="writerInfodeancho0618" class="nickname">
										${free.board_nickname }</button>
									<!---->
									</div>
									<em class="nick_level"> 열심회원 <i data-v-d34938e2=""
									class="LevelIcon icon_level"
									style="background-image: url(&quot;https://ca-fe.pstatic.net/web-pc/static/img/sprite_levelicon_9dbde2.svg#1_1-usage&quot;);"></i></em><a
									href="#" class="link_talk">구매문의</a>
							</div>
							<div class="article_info">
							
							<span class="date"> <fmt:formatDate
										value="${free.board_date }" pattern="yyyy-MM-dd HH:mm:ss" />

								</span> <span class="count">조회 ${free.board_hits}</span>
							</div>
						</div>
					</div>
					<div class="ArticleTool">
						<a href="#" role="button" class="button_comment"><svg
								class="svg-icon ico-post-comment-323232">
								<use xlink:href="#ico-post-comment-323232" aria-hidden="true"></use></svg>댓글
							<strong class="num">${free.board_replies }</strong></a><a href="#" role="button"
							class="button_url">URL 복사</a>
						<div class="toast_url_copy_successful" style="display: none;">
							URL이 복사되었습니다. 원하는 곳에 붙여 넣으세요.</div>
						<div class="more_area">
							<a id="articleTool" href="#" role="button" title="더보기"
								class="button_more"><svg aria-label="더보기"
									class="svg-icon ico-post-more-979797">
									<use xlink:href="#ico-post-more-979797" aria-hidden="false"></use></svg></a>
							<!---->
							<!---->


						</div>
					</div>
				</div>
				<div class="article_container">
					<!---->
					<!---->
					<!---->
					<!---->
					<div class="article_viewer">
						<!---->
						<div class="SaleInfo">
							<div class="WarningNotice">
								<!---->
								<!---->
							</div>
							<!-- 사진이 비어있다면 안나오게 -->
							<!-- 나의 게시판 (거래글)에만 있으면 될 거 같지만 일단 긁어옴 -->
							 <c:if test="${!empty free.board_fileid}">
								<div class="product_section">
									<div class="ProductImage sold">
										<div class="product_thumb">

											<img
												src="${pageContext.request.contextPath}/boards_free/download?board_num=${free.board_num}"
												height="380" width="380">
											<!---->
										</div>
										<!---->
									</div>
								</div>
							</c:if>
							<!-- 사진이 없으면 안나옴  -->
						</div> 
						
						<div>
							<div class="content CafeViewer">
								<div class="se-viewer se-theme-default" lang="ko-KR">
									<!-- SE_DOC_HEADER_START -->
									<!--@CONTENTS_HEADER-->
									<!-- SE_DOC_HEADER_END -->
									<div class="se-main-container">
										<div class="se-component se-image se-l-default"
											id="SE-C09D94B9-6074-4DF0-A951-123014C3091C">
											<div class="se-component-content se-component-content-fit">
												<div
													class="se-section se-section-image se-l-default se-section-align-left">
													<div class="se-module se-module-image" style="">
														<a class="se-module-image-link __se_image_link __se_link"
															style="" onclick="return false;" data-linktype="img"
															data-linkdata="{&quot;id&quot; : &quot;SE-C09D94B9-6074-4DF0-A951-123014C3091C&quot;, &quot;src&quot; : &quot;https://cafeptthumb-phinf.pstatic.net/MjAyMjExMTRfMTI0/MDAxNjY4NDA3OTM3MDE2.SosinWClQVH07yKpVQUaJCo2UiTEJWKzNGJWko8Wq5sg.LXfaM7sUT5INnXbLoU1hjWcFvJgdz_hzcXkIprMdeXsg.JPEG/output_841148409.jpg&quot;, &quot;originalWidth&quot; : &quot;4032&quot;, &quot;originalHeight&quot; : &quot;3024&quot;, &quot;linkUse&quot; : &quot;false&quot;, &quot;link&quot; : &quot;&quot;}"
															area-hidden="true"> <img
															src="https://cafeptthumb-phinf.pstatic.net/MjAyMjExMTRfMTI0/MDAxNjY4NDA3OTM3MDE2.SosinWClQVH07yKpVQUaJCo2UiTEJWKzNGJWko8Wq5sg.LXfaM7sUT5INnXbLoU1hjWcFvJgdz_hzcXkIprMdeXsg.JPEG/output_841148409.jpg?type=w1600"
															alt="" class="se-image-resource">
														</a>
													</div>
												</div>
											</div>
										</div>
										<div class="se-component se-text se-l-default"
											id="SE-9ad5f8b3-5990-11ed-bd34-8945e708a675">
											<div class="se-component-content">
												<div class="se-section se-section-text se-l-default">
													<div class="se-module se-module-text">
														<!-- SE-TEXT { -->
														<p class="se-text-paragraph se-text-paragraph-align-left "
															style="" id="SE-74AE51C9-ED94-4357-AE68-882C49789E29">
															<span style="" class="se-fs- se-ff-">${free.board_content }</span>
														</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!---->
							<div class="AttachFileIssueLayer" style="display: none;">
								<!---->
							</div>
						</div>
						<!---->
					</div>
					<!---->
					<!---->
					<!---->
					<div class="article_writer">
						<div class="ArticleWriterProfile">
							<a href="/ca-fe/cafes/10050146/members/prkQguvG11zjC0KAkeAh_A"
								class="more_area"><span class="thumb"><img
									src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77"
									alt="프로필 사진" width="36" height="36"
									onerror="this.onerror='';this.src='https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png';"></span><span
								class="box"><strong class="user">${free.board_nickname }</strong>님의
									게시글 더보기 <svg class="svg-icon ico-post-arrow-323232">
										<use xlink:href="#ico-post-arrow-323232" aria-hidden="true"></use></svg></span></a>
						</div>
						<!---->
					</div>
					<div class="ReplyBox">
						<div class="box_left">
							<div class="like_article">
								<!---->
								<!---->
							</div>
						</div>
						<div class="box_right"></div>
					</div>
					<div class="CommentBox">
						<div class="comment_option">
							<h3 class="comment_title">댓글</h3>
							<div class="comment_tab">
								<ul class="comment_tab_list">
									<li class="comment_tab_item"><a href="#" role="button"
										aria-selected="true" class="comment_tab_button"> 등록순 </a></li>
									<li class="comment_tab_item"><a href="#" role="button"
										aria-selected="false" class="comment_tab_button"> 최신순 </a></li>
								</ul>
								<button type="button" class="comment_refresh_button">
									<span class="blind">새로고침</span>
								</button>
							</div>
							<div class="comment_alarm">
								<div class="SubscribeButton">
									<a href="#" class="button_report">신고</a>
									<div class="ToggleSwitch ToggleSwitch--skinGray">
										<input id="2" type="checkbox" class="switch_input blind"><label
											for="2" class="switch_slider"><span class="blind">등록</span></label>
									</div>
								</div>
							</div>
						</div>
						<!--댓글 리스트  -->
						<c:forEach var="reply" items="${reply}">
							<ul class="comment_list">

								<li class="CommentItem"><div class="comment_area">
										<a href="/ca-fe/cafes/10050146/members/prkQguvG11zjC0KAkeAh_A"
											class="comment_thumb"><img
											src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77"
											alt="프로필 사진" width="36" height="36"></a>
										<div class="comment_box">
											<div data-v-cb8347f8="" class="comment_nick_box">
												<div class="comment_nick_info">
													<c:if test="${!empty reply.rreply_id }">
														<a href="#" aria-haspopup="true" aria-expanded="false"
															class="comment_nickname"> @${reply.reply_nickname} </a>
														<a href="#" aria-haspopup="true" aria-expanded="false"
															class="comment_nickname"> ${reply.rreply_nickname} </a>
													</c:if>

													<c:if test="${empty reply.rreply_id}">
														<a href="#" aria-haspopup="true" aria-expanded="false"
															class="comment_nickname"> ${reply.reply_nickname} </a>
													</c:if>

													<!---->
												</div>
												<i class="LevelIcon icon_level"
													style="background-image: url(&quot;https://ca-fe.pstatic.net/web-pc/static/img/sprite_levelicon_9dbde2.svg#1_1-usage&quot;);"></i><em
													class="comment_badge_writer"><svg data-v-cb8347f8=""
														aria-label="작성자" class="svg-icon ico-post-writer">
													<use xlink:href="#ico-post-writer" aria-hidden="false"></use></svg></em>
											</div>

											<div class="comment_text_box">
												<p class="comment_text_view">
													<!---->
													<span class="text_comment">${reply.reply_content}</span>
												</p>
												<!---->
											</div>


											<div class="comment_info_box">
												<span class="comment_info_date">${reply.reply_date}</span>
												<!-- 대댓글 버튼 : 자기 자신이 아닐 때만 나타나도록 -->


											</div>


											<div data-v-5213e8a5="" class="comment_tool">
												<span class="sv"> 
												<c:if
														test="${loginid==reply.reply_id&&empty reply.rreply_id||loginid==reply.rreply_id }">
														<input type="button" id="deleteReply" name="deleteReply" value="댓글 삭제" onClick="deleteReply('${reply.reply_num}')" class="btn btn-info">
													</c:if> 
												</span>
												<!---->
											</div>
										</div>
									</div></li>
								<!---->
							</ul>
						</c:forEach><!--댓글 쓰는 칸-->
						<c:if test="${!empty loginid}">
							<div class="CommentWriter">
								<div class="comment_inbox">
									<strong class="blind">댓글을 입력하세요</strong><em
										class="comment_inbox_name">${loginid }</em>
									<textarea id="reply_content" name="reply_content"
										placeholder="댓글을 남겨보세요" rows="1" class="comment_inbox_text"
										style="height: 17px;"required></textarea>
									<!---->
									<!---->
								</div>
								<div class="comment_attach">
									<div data-v-3b426d7d="" class="register_box">
										<!---->
										<a href="#" role="button" id="insertReply" name="insertReply"
											class="button btn_register">등록</a>
									</div>
								</div>
							</div>
						</c:if>
						<!--여기까지 댓글쓰는 란-->
					</div>
					<!---->
				</div>
			</div>
		</div>
	</div>
	<!-- .nt-body -->
	<h3>ㅤ</h3>
</body>
</html>
<%@ include file="../include/footer.jsp" %>