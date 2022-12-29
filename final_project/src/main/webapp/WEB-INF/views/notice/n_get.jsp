<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<link href="${path}/resources/css/read.css" rel="stylesheet">
<link href="${path}/resources/css/gun_custom.css" rel="stylesheet">
<title>글 읽기</title>
</head>
<script>
$(function(){
	$('#delete').on('click', deleteBoard);
	$('#insertReply').on('click', insertReply);
});

function deleteBoard(){
	if(confirm('Do you want to delete your post?')){
			$.ajax({
				method : "post",
				url : "delete",
				data : {
					"board_num" : ${board.board_num}
				},
				success : function(result) {
					if(result==1){
					alert('Your post is deleted successfully');
					location.href = "${pageContext.request.contextPath}/notice";
					}
				}
			});}
}
function updateBoard(board_num){
	location.href = "${pageContext.request.contextPath}/notice/update?board_num="+board_num+"&page=${page}";
}

</script>

<body>
	<div id="nt_body" class="nt-body">
		<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
			<div class="ArticleTopBtns">
				<div class="right_area">
				
					<!-- 작성자 admin이라면 수정, 삭제  -->
					<c:if test="${loginid=='admin'}">
						<a></a>
						<a href="#" role="button"
							class="BaseButton BaseButton--skinGray size_default">
							<input type="button" value="수정" id="update" name="update"
								onclick="updateBoard('${board.board_num}')"
								class="btn btn-default">
						</a>
						<a href="#" role="button"
							class="BaseButton BaseButton--skinGray size_default">
							
						 <input	type="button" value="삭제" id="delete" name="delete"
							class="btn btn-default">
						</a>
					</c:if>

					<a href="${pageContext.request.contextPath}/notice" role="button"
						class="BaseButton BaseButton--skinGray size_default"> <input
						type="button" value="목록" class="btn btn-default">
					</a>
				</div>
				<div class="right_area"></div>
			</div>

			<div class="ArticleContentBox">
				<div class="article_header">
					<div class="ArticleTitle">
						<a
							href="https://cafe.naver.com/ArticleList.nhn?search.clubid=10050146&amp;search.menuid=396"
							class="link_board"> 공지사항 <svg
								class="svg-icon ico-post-arrow-03-c-75-a">
								<use xlink:href="#ico-post-arrow-03-c-75-a" aria-hidden="true"></use></svg></a>
						<div class="title_area">
							<!---->
							<!---->
							<!---->
							<h3 class="title_text">${board.board_no_title }</h3>
						</div>
					</div>
					<div class="WriterInfo">
						<a href="/ca-fe/cafes/10050146/members/prkQguvG11zjC0KAkeAh_A"
							class="thumb"><img
							src="${pageContext.request.contextPath}/customer/download"
							alt="프로필 사진" width="36" height="36"></a>
						<div class="profile_area">
							<div class="profile_info">
								<div class="nick_box">
									<button id="writerInfodeancho0618" class="nickname">
										${board.board_no_nickname }</button>
									<!---->
								</div>
								<em class="nick_level"> 관리자 <i data-v-d34938e2=""
									class="LevelIcon icon_level"
									style="background-image: url(&quot;https://ca-fe.pstatic.net/web-pc/static/img/sprite_levelicon_9dbde2.svg#1_1-usage&quot;);"></i></em>
							</div>
							<div class="article_info">

								<span class="date"> <fmt:formatDate
										value="${board.board_no_date }" pattern="yyyy-MM-dd HH:mm:ss" />

								</span> <span class="count">조회 ${board.board_no_seq }</span>
							</div>
						</div>
					</div>
					<div class="ArticleTool">
						<a href="#" role="button" class="button_comment"><svg
								class="svg-icon ico-post-comment-323232">
								</svg></a><a href="#" role="button"
							class="button_url">URL 복사</a>
						<div class="toast_url_copy_successful" style="display: none;">
							URL이 복사되었습니다. 원하는 곳에 붙여 넣으세요.</div>
						<div class="more_area">
							<a id="articleTool" href="#" role="button" title="더보기"
								class="button_more"><svg aria-label="더보기"
									class="svg-icon ico-post-more-979797">
									</svg></a>
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
															<span style="" class="se-fs- se-ff-">${board.board_no_content }</span>
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
							<div class="comment_tab">
						
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	
	<!-- .nt-body -->
	<h3>ㅤ</h3>
</body>

</html>
<%@ include file="../include/footer.jsp"%>