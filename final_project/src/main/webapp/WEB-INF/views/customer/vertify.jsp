<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "path" value = "${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko" class="is-pc">
<head>
     
<meta charset="utf-8">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>회원정보수정</title>
<meta http-equiv="content-language" content="kr">

</head>
<body class="responsive is-round">
<div id="nt_sticky_wrap">
</div>


   <div id="nt_header">
   <div id="nt_body" class="nt-body">
         <div class="nt-container my-3 my-sm-4 px-0 px-sm-4 px-xl-0">
<div class="register m-auto f-de">

   <form action="vertifyUpdate" method="post" onsubmit="return checkForm();" enctype="multipart/form-data">
   <ul class="list-group mb-4">
      <li></li>
      <li></li>
      <li>ㅤ</li>
      <li>ㅤ</li>
      <li><h5>회원정보수정</h5></li>
      <li class="list-group-item pt-3 pt-sm-4">
      
       <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="reg_mb_id">프로필사진</label>
            
               <img src="${pageContext.request.contextPath}/customer/download" class="rounded-circle"
                  style="width:60px; height:60px;" >
               
              
            <div class="col-sm-4">
               <input type="file" name="upload">
            </div>
           
         </div>
         
         
         
         <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="reg_mb_id">아이디<strong class="sr-only">필수</strong></label>
            <div class="col-sm-4">
               <input type="text" name="cus_id" value="${customer.cus_id}" id="cus_id" required  class="form-control required" readonly="readonly">
            </div>
            <div class="col-sm-6">
               <p class="form-control-plaintext f-de text-muted pb-0">
                  영문자, 숫자, _ 만 입력 가능. 최소 3자이상 입력 가능
                  <div id="msg_mb_id"></div>
               </p>
            </div>
         </div>

         <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="reg_mb_password">비밀번호<strong class="sr-only">필수</strong></label>
            <div class="col-sm-4">
               <input type="password" name="cus_pw" id="cus_pw" required class="form-control required" minlength="3" maxlength="20">
            </div>
            <label class="col-sm-2 col-form-label" for="reg_mb_password_re">비밀번호 확인<strong class="sr-only">필수</strong></label>
            <div class="col-sm-4">
               <input type="password" name="cus_pwC" id="cus_pwC" required class="form-control required" minlength="3" maxlength="20">
            </div>
         </div>
         
         <div class="form-group row">
               <label class="col-sm-2 col-form-label" for="reg_mb_nick">이름<strong class="sr-only">필수</strong></label>
               <div class="col-sm-4">
                  <input type="text" name="cus_nickname" value="${customer.cus_nickname}"id="cus_nickname" required class="form-control nospace required" maxlength="20">
               </div>
               <div class="col-sm-6">
                  <p class="form-control-plaintext f-de text-muted pb-0">
                     공백없이 한글,영문,숫자만 가능 (한글2자,영문4자 이상 가능) 
                  </p>
               </div>
               <div class="col-sm-10 offset-sm-2">
                     <div id="msg_mb_nick"></div>
                  </div>
                  
                           </div>
         <div class="form-group row">
            <label class="col-sm-2 col-form-label">성별<strong class="sr-only">필수</strong></label>
            <div class="col-sm-4">
                  <input type="radio" name="cus_gender" value="M" checked >남 &nbsp 
                  <input type="radio" name="cus_gender" value="F" >여   
            </div>
         </div>
      </li>
   </ul>
   <div class="px-3 px-sm-0 mb-4">
      <div class="row mx-n2">
         <div class="col-6 order-2 px-2">
            <button type="submit" value="Join Us" id="btn_submit" class="btn btn-primary btn-lg btn-block en">회원정보수정</button>
         </div>
         <div class="col-6 order-1 px-2">
            <a href="${path}/" class="btn btn-basic btn-lg btn-block en">취소</a>
         </div>
      </div>
   </div>
 
   <input type="hidden" name="cus_answer" value="${customer.cus_answer}" id="cus_answer" required  class="form-control required">
   <input type="hidden" name="cus_question" value="${customer.cus_question}" id="cus_id" required  class="form-control required" >
   <input type="hidden" name="cus_hobbies" value="${customer.cus_hobbies}" id="cus_hobbies" required  class="form-control required">
   
   </form>
  
</div>
                     </div>
            </div><!-- .nt-container -->
      </div><!-- .nt-body -->
</body>
</html>
<%@ include file="../include/footer.jsp" %>