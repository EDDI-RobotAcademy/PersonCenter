<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보게시판 글쓰기</title>

<!-- Custom styles for this template -->
<link
   href="${pageContext.request.contextPath}/resources/css/clean-blog.min.css"
   rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/write.css"
   rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/read.css"
   rel="stylesheet">
</head>
<script>
	/*$(function() {
	 $('#insert').on('click', insertBoard);
	 });
	 function insertBoard() {
	 $
	 .ajax({
	 method : "post",
	 url : "insert",
	 data : {
	 "board_title" : $("#board_title").val(),
	 "board_content" : $("#board_content").val(),
	 "board_see" : $(
	 "input[type=radio][name=board_see]:checked")
	 .val()
	 },
	 success : function(result) {
	 if (result == 1) {
	 alert('You posted successfully');
	 location.href = "${pageContext.request.contextPath}/boards";
	 }
	 }
	 });
	 }
	 */
</script>
<body>
	<div id="nt_body" class="nt-body">
      <div class="nt-container px-0 px-sm-4 px-xl-0 pt-0 pt-sm-4">
         <!-- 메인 -->
         <form action="insert" method="post" enctype="multipart/form-data">
         <div class="WritingCommerce">
           
            <div class="form_box">
               <div class="input-group mb-3">
                   <input type="text" class="form-control" id="board_title"
                     name="board_title" placeholder="제목" required class="form-control"
                     aria-label="Text input with dropdown button" style="width: 300px">

               </div>
               <input class="file" id="chooseFile" type="file" name="upload" multiple required class="form-control">   
               <h1>ㅤ</h1>
            </div>
            <!-- 공개설정 -->
            <input type="hidden" name="board_see" value="all"checked>
            <textArea id="board_content" name="board_content" required
               class="form-control" rows="20"></textArea>
            <h1>ㅤ</h1>
            <input type="submit" class="btn btn-default" id="btn" value="글쓰기" style="float: right; background-color: #d7ebc2; border-radius: 10px">
            <h1>ㅤ</h1>
         </div>
         </form>
      </div>
   </div>

	<!-- Bootstrap core JavaScript -->
	<script
      src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
   <script
      src="${pageContext.request.contextPath}/resources/vendor/popper/popper.min.js"></script>
   <script
      src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
   <script
      src="${pageContext.request.contextPath}/resources/js/clean-blog.min.js"></script>

</body>
</html>
<%@ include file="../include/footer.jsp" %>