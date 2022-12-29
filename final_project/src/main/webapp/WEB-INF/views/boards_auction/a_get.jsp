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
<!-- 결과값이 있으면 -->

<c:if test="${!empty requestResult }">
	<script>
			alert('${requestResult}');
		</script>
</c:if>

<script>
function acceptFriend(friend_id) {
	confirm("거래신청을 하시겠습니까? ")
	{
		$
				.ajax({
					url : "accept",
					method : "post",
					data : {
						"friend_id" : friend_id,
						"board_num":$("#board_num").val()
					},
					success : function(friend_id) {
						alert(friend_id + "거래신청을 하였습니다!");
						history.go(0);
					}
				});
	}
}

$(function(){
	$('#delete').on('click', deleteBoard);
});


function deleteBoard(){
	if(confirm('게시물을 삭제하시겠습니까?')){
			$.ajax({
				method : "post",
				url : "delete",
				data : {
					"board_num" : ${auction.board_num}
				},
				success : function(result) {
					if(result==1){ 
					alert('게시물이 성공적으로 삭제되었습니다.');
					history.go(0);
					}
				}
			});}
}
function updateBoard(board_num){
	location.href = "${pageContext.request.contextPath}/boards_auction/update?board_num="+board_num+"&page=${page}&friend_id=${friend_id}";
}

</script>

<body>
	<div id="nt_body" class="nt-body">
		<div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
			<div class="ArticleTopBtns">
				<div class="right_area">
				
					<!-- 작성자 본인이라면 수정, 삭제  -->
					<c:if test="${loginid==auction.board_id }">
						<a href="#" role="button"
							class="BaseButton BaseButton--skinGray size_default"> 
							<input type="button" value="수정" id="update" name="update"
							onclick="updateBoard('${auction.board_num}')"
							class="btn btn-default">
						</a>

						<a href="#" role="button"
							class="BaseButton BaseButton--skinGray size_default"> 
							<input type="button" value="삭제" id="delete" name="delete"
							class="btn btn-default">
						</a>
					</c:if>

						<a href="${pageContext.request.contextPath}/boards_auction" role="button"
						class="BaseButton BaseButton--skinGray size_default"> 
						<input type="button" value="목록" class="btn btn-default">
					</a>
				</div>
				<div class="right_area"></div>
			</div>

			<div class="ArticleContentBox">
				<div class="article_header">
					<div class="ArticleTitle">
						<a class="link_board"> 현재 접속 아이디 : ${loginid }</a>
						<div class="title_area">
							<!---->
							<!---->
							<!---->
							<h3 class="title_text">${auction.board_title }</h3>
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
										${auction.board_nickname }</button>
									<!---->
								</div>
								<em class="nick_level"> 열심회원 <i data-v-d34938e2=""
									class="LevelIcon icon_level"
									style="background-image: url(&quot;https://ca-fe.pstatic.net/web-pc/static/img/sprite_levelicon_9dbde2.svg#1_1-usage&quot;);"></i></em><a
									href="#" class="link_talk">구매문의</a>
							</div>
							<div class="article_info">

								<span class="date"> <fmt:formatDate
										value="${auction.board_date }" pattern="yyyy-MM-dd HH:mm:ss" />

								</span> <span class="count">조회 ${auction.board_hits }</span>
							</div>
						</div>
					</div>
					<div class="ArticleTool">
						<a href="#" role="button" class="button_comment"><svg
								class="svg-icon ico-post-comment-323232">
								<use xlink:href="#ico-post-comment-323232" aria-hidden="true"></use></svg>댓글
							<strong class="num">${auction.board_replies }</strong></a><a href="#" role="button"
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
						<div class="SaleInfo">
							<div class="WarningNotice">
							</div>
						<!-- 사진이 비어있다면 안나오게 -->
							<c:if test="${!empty auction.board_fileid }">
								<div class="product_section">
									<div class="ProductImage sold">
										<div class="product_thumb">

											<img
												src="${pageContext.request.contextPath}/boards_auction/download?board_num=${auction.board_num}"
												height="380" width="380">
										</div>
									</div>
									<div class="product_area">
										<div class="product_detail">
											<div class="product_detail_box">
												<div class="ProductCategory">카테고리 / ${auction.board_category }</div>
												<p class="ProductName">
													<em class="SaleLabel sold"> [판매] </em> ${auction.board_title }
												</p>
												<div class="ProductPrice sold">
													<strong class="cost">${auction.board_cost }원</strong>
													<!---->
												</div>
											</div>
											<div class="CommercialDetail">
												<!---->
												<div class="section">
													<!---->
													<dl class="detail_list">
														<dt class="list_title">결제 방법</dt>
														<dd class="list_detail">
															<!---->
															직접거래 [판매자 수락시 계좌정보 전송]
															<button type="button" class="btn_help">
																<svg aria-label="도움말" class="svg-icon ico-write-info">
																<use xlink:href="#ico-write-info" aria-hidden="false"></use></svg>
															</button>


														</dd>
													</dl>
													<!---->
													<!---->
													<!---->
												</div>
												<div class="CommercialDetailSellerInfo section"></div>
												<div class="form-group">
												
											 		<!-- 다른사람이 쓴 글 보기 -->
													<!-- 만약 friend가 아니라면 -->
													<c:if test="${agree =='failed'}">

														<!-- 본인이 쓴 게시물이 아니라면 -->
														<c:if test="${loginid ne auction.board_id }">	
														<!-- 다른사람과 거래중이라면 -->
														<c:if test="${status_seller eq null && board_status eq null }">	
																<!-- 로그인아이디  -->
																<input type="hidden" id="friend_id" name="friend_id" value="${loginid}">
															<form action="friendRequest" method="post" class="friend">
																<!-- 게시글작성자아이디  -->
																<input type="hidden" id="friend_id" name="friend_id" value="${auction.board_id }">
																<input type="hidden" id="board_num" name="board_num" value="${auction.board_num }">
																<button type="submit" class="btn btn-primary btn-block btn-lg en">거래신청하기</button>
															</form>
															</c:if>
															
															<c:if test="${status_seller eq 'friend' || status_buyer eq 'friend'|| board_status eq 'friend'}">	
																<!-- 거래완료버튼  -->
																<button type="submit" class="btn btn-primary btn-block btn-lg en">거래완료</button>
															</c:if>
															
															<c:if test="${status_seller eq 'request'|| board_status eq 'receive'}">	
																<!-- 거래완료버튼  -->
																<button type="submit" class="btn btn-primary btn-block btn-lg en">거래중</button>
															</c:if>
															
														</c:if>
														
														
														<!-- 본인이 쓴 게시물이라면 -->
														<c:if test="${loginid eq auction.board_id }">
															<!-- 거래요청을 보낸상태라면  -->
															<c:if test="${!empty request}">
																<table cellpadding="10">
																	<tr>
																		<td>아이디</td>
																		<td>관리</td>
																	</tr>
																	<c:forEach var="items" items="${request}">
																		<tr>
																			<td><a
																				href="${pageContext.request.contextPath}/boards_auction?friend_id=${items}">${items}</a></td>
																			<td>
																			<input type="hidden" id="board_num" name="board_num" value="${auction.board_num }">
																			<input type="button" onClick="acceptFriend('${items}')" value="수락하기" class="btn btn-default"></td>
																		</tr>
																	</c:forEach>
																</table>
												
															  	 <input type="hidden" id="friend_id" name="friend_id" value="${auction.board_id }"><br>	
																 <input type="hidden" id="friend_id" name="friend_id" value="${request}">
															</c:if>
														</c:if>
														
													</c:if>
													<!-- 여기까지 다른사람이 쓴글 -->
													
													 <!-- 만약 판매자와 구매자가 상태가 친구라면 성공으로 표시하고 계좌정보 불러오기 -->
													<c:if test="${agree=='successed'}">
													[판매자 정보]<br>
													계좌정보 : ${seller_info.cus_bank} / ${seller_info.cus_account} <br><br>
													</c:if>
												
													<!-- 내가 쓴 글 보기  -->
													<!-- 만약 friend가 아니라면 -->
													<c:if test="${agree2 =='failed'}">
														<!-- 본인이 쓴 게시물이라면 -->
														<c:if test="${loginid eq board.board_id }">

														
														</c:if>
													</c:if>
													
													 <!-- 만약 판매자와 구매자가 상태가 친구라면 성공으로 표시하고 주소정보 불러오기 -->
													<c:if test="${agree2=='successed'}">
													[구매자 정보]<br>
													주소 : ${buyer_info.cus_addr} <br> 
													우편번호 : ${buyer_info.cus_zip_code} <br> 
													연락처 : ${buyer_info.cus_tel}
													
													</c:if>
												

												</div>
												<!---->
											</div>
											<!---->
											<div class="BottomNotice">

												<p class="text_info">※거래신청 수락시 구매자의 연락처 및 배송지 정보가 보입니다.※</p>
												<br>
												<p class="text_info">등록된 판매 물품과 내용은 개별 판매자가 등록한 것으로서, 위
													사이트는 등록을 위한 시스템만 제공하며 내용에 대하여 일체의 책임을 지지 않습니다.</p>


											</div>
										</div>
									</div>
							</c:if>
							<!-- 사진이 없으면 안나옴  -->
						</div>
						</div >
						<!---->
					</div>
					<!---->
					<!--글 내용-->
					${board.board_content }
					<!---->
					<div class="article_writer">
						<div class="ArticleWriterProfile">
							<a href="/ca-fe/cafes/10050146/members/prkQguvG11zjC0KAkeAh_A"
								class="more_area"><span class="thumb"><img
									src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77"
									alt="프로필 사진" width="36" height="36"
									onerror="this.onerror='';this.src='https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png';"></span><span
								class="box"><strong class="user">${auction.board_nickname }</strong>님의
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
						<c:forEach var="reply" items="${reply }">
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
															class="comment_nickname"> @${reply.reply_nickname } </a>
														<a href="#" aria-haspopup="true" aria-expanded="false"
															class="comment_nickname"> ${reply.rreply_nickname } </a>
													</c:if>

													<c:if test="${empty reply.rreply_id }">
														<a href="#" aria-haspopup="true" aria-expanded="false"
															class="comment_nickname"> ${reply.reply_nickname } </a>
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
													<span class="text_comment">${reply.reply_content }</span>
												</p>
												<!---->
											</div>


											<div class="comment_info_box">
												<span class="comment_info_date">${reply.reply_date }</span>
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
						</c:forEach>

						<!--댓글 쓰는 칸-->
						<c:if test="${!empty loginid}">
							<div class="CommentWriter">
								<div class="comment_inbox">
									<strong class="blind">댓글을 입력하세요</strong><em
										class="comment_inbox_name">${loginid }</em>
									<textarea id="reply_content" name="reply_content"
										placeholder="댓글을 남겨보세요" rows="1" class="comment_inbox_text"
										style="height: 17px;"required></textarea>
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
<%@ include file="../include/footer.jsp"%>