<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko" class="is-pc">
<head>

<meta charset="utf-8">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>회원정보수정</title>
<meta http-equiv="content-language" content="kr">

</head>
<script type="text/javascript">
   $(document)
         .ready(
               function() {

                  let cus_pw = $('#cus_id').val();

                  $("#delete")
                        .on(
                              "click",
                              function() {

                                 if ($("#cus_pw").val() == "") {
                                    alert("비밀번호를 입력해주세요");
                                    $("#cus_pw").focus();
                                    return false
                                 }

                                 if ($('input:checkbox[name=agree]')
                                       .is(":checked") == false) {

                                    alert("탈퇴약관을 동의해주세요");
                                    return false
                                 }
                                 
                                 $
                                       .ajax({
                                          url : "${pageContext.request.contextPath}/customer/passChk",
                                          type : "POST",
                                          dataType : "json",
                                          data : $("#deleteForm")
                                                .serializeArray(),
                                          success : function(data) {

                                             if (data == 0) {
                                                alert("비밀번호를 확인해주세요.");
                                                return;
                                             } else {
                                                if (confirm("탈퇴하시겠습니까?")) {
                                                   $(
                                                         "#deleteForm")
                                                         .submit();
                                                   //location.href = "${pageContext.request.contextPath}/customer/deleteCustomer";

                                                }

                                             }
                                          },
                                          error : function(
                                                request,
                                                status, error) {

                                             alert("status : "
                                                   + request.status
                                                   + ", message : "
                                                   + request.responseText
                                                   + ", error : "
                                                   + error);
                                             alert("요청실패");
                                          }
                                       })
                              });
               });
</script>
<body class="responsive is-round">

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
   <div id="nt_sticky_wrap"></div>


   <div id="nt_header">
      <div id="nt_body" class="nt-body">
         <div class="nt-container my-3 my-sm-4 px-0 px-sm-4 px-xl-0">
            <div class="register m-auto f-de">


               <form action="deleteCustomer" method="post" id="deleteForm"
                  name="deleteForm">

                  <input type="hidden" id="cus_id" name="cus_id" value="${loginid}">
                  <ul class="list-group mb-4">
                     <li></li>
                     <li></li>
                     <li>ㅤ</li>
                     <li>ㅤ</li>
                     <li><h5>회원탈퇴</h5></li>
                     <li class="list-group-item pt-3 pt-sm-4">
                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label" for="reg_mb_password">비밀번호<strong
                              class="sr-only">필수</strong></label>
                           <div class="col-sm-4">
                              <input type="password" name="cus_pw" id="cus_pw">
                           </div>
                        
                        </div> <!--  약관동의서 시작-->


                        <div data-bs-spy="scroll" data-bs-target="#navbar-example2"
                           data-bs-root-margin="300px 300px -40%"
                           data-bs-smooth-scroll="true"
                           class="scrollspy-example bg-light p-3 rounded-2" tabindex="0">

                           <p>[회원탈퇴 약관] 회원탈퇴 신청 전 안내 사항을 확인 해 주세요. 회원탈퇴를 신청하시면 현재 로그인
                              된 아이디는 사용하실 수 없습니다. 회원탈퇴를 하더라도, 서비스 약관 및 개인정보 취급방침 동의하에 따라 일정
                              기간동안 회원 개인정보를 보관합니다. - 회원 정보 - 상품 구입 및 대금 결제에 관한 기록 - 상품 배송에
                              관한 기록 - 소비자 불만 또는 처리 과정에 관한 기록 - 게시판 작성 및 사용후기에 관한 기록 ※ 상세한
                              내용은 사이트 내 개인정보 취급방침을 참고 해 주세요.</p>
                        </div> 
                        
                        <!--  약관동의서 끝 -->

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label">회원탈퇴동의서<strong
                              class="sr-only">필수</strong></label>
                           <div class="col-sm-4">

                              <input type="checkbox" name="agree" value="동의">동의
                              &nbsp


                           </div>
                        </div>
                     </li>
                  </ul>
                  <div class="px-3 px-sm-0 mb-4">
                     <div class="row mx-n2">
                        <div class="col-6 order-2 px-2">
                           <button type="button" id="delete" name="delete"
                              class="btn btn-primary">회원탈퇴@@</button>
                        </div>



                        <div class="col-6 order-1 px-2">
                           <a href="${path}/" class="btn btn-basic btn-lg btn-block en">취소</a>
                        </div>
                     </div>
                  </div>

               </form>

            </div>
         </div>
      </div>
      <!-- .nt-container -->
   </div>
   <!-- .nt-body -->
</body>
</html>
<%@ include file="../include/footer.jsp"%>