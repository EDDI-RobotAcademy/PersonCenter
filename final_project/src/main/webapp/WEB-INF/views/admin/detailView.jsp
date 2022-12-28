<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko" class="is-pc">
<head>

<meta charset="utf-8">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>회원상세정보</title>
<meta http-equiv="content-language" content="kr">

</head>
<body class="responsive is-round">
   <div id="nt_sticky_wrap"></div>


   <div id="nt_header">
      <div id="nt_body" class="nt-body">
         <div class="nt-container my-3 my-sm-4 px-0 px-sm-4 px-xl-0">
            <div class="register m-auto f-de">

               <form action="vertifyUpdate" method="post"
                  onsubmit="return checkForm();" enctype="multipart/form-data">
                  <ul class="list-group mb-4">
                     <li></li>
                     <li></li>
                     <li>ㅤ</li>
                     <li>ㅤ</li>
                     <li><h5>회원상세정보</h5></li>
                     <li class="list-group-item pt-3 pt-sm-4">

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label" for="reg_mb_id">프로필사진</label>

                           <img src="${pageContext.request.contextPath}/admin/download?customer_id=${customer.cus_id}"
                              class="rounded-circle" style="width: 60px; height: 60px;">

                        </div>



                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label" for="reg_mb_id">아이디<strong
                              class="sr-only">필수</strong></label>
                           <div class="col-sm-4">
                              <input type="text" name="cus_id" value="${customer.cus_id}"
                                 id="cus_id" required class="form-control required"
                                 readonly="readonly">
                           </div>
                        
                        </div>

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label" for="reg_mb_password">비밀번호<strong
                              class="sr-only">필수</strong></label>
                           <div class="col-sm-4">
                              <input type="text" name="cus_pw" id="cus_pw" value="${customer.cus_pw}"
                                 class="form-control required" readonly="readonly">
                           </div>
                         
                        </div>

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label" for="reg_mb_nick">이름<strong
                              class="sr-only">필수</strong></label>
                           <div class="col-sm-4">
                              <input type="text" name="cus_nickname"
                                 value="${customer.cus_nickname}" id="cus_nickname"
                                 class="form-control nospace required" readonly="readonly">
                           </div>
                         
                           <div class="col-sm-10 offset-sm-2">
                              <div id="msg_mb_nick"></div>
                           </div>
                        </div>

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label">질문<strong
                              class="sr-only">필수</strong></label>
                              
                           <c:if test="${customer.cus_question==1}">
                           <div class="col-sm-4">
                           	
                             	<input type="text" name="cus_question" id="cus_question" value="가장 좋아하는 장소는?"
                                 class="form-control required" readonly="readonly">
             
                           </div>
                          </c:if>
                          
                            <c:if test="${customer.cus_question==2}">
                           <div class="col-sm-4">
                           	
                             	<input type="text" name="cus_question" id="cus_question" value="가장 좋아하는 동물은?"
                                 class="form-control required" readonly="readonly">
             
                           </div>
                          </c:if>
                          <c:if test="${customer.cus_question==3}">
                             <div class="col-sm-4">
                           	
                             	<input type="text" name="cus_question" id="cus_question" value="가장 좋아하는 음식은?"
                                 class="form-control required" readonly="readonly">
             
                           </div>
                          
                          </c:if>
                          
                        </div>

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label">답변<strong
                              class="sr-only">필수</strong></label>
                           <div class="col-sm-4">
                              <input type="text" class="form-control" 
                                 id="cus_answer" name="cus_answer" value="${customer.cus_answer}" readonly="readonly">
                           </div>
                        </div>

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label">은행<strong
                              class="sr-only">필수</strong></label>
                               <div class="col-sm-4">
                               <input type="text" class="form-control" 
                                 id="cus_bank" name="cus_bank" value="${customer.cus_bank}" readonly="readonly">
								</div>
                        </div>
                        
                           <div class="form-group row">
                           <label class="col-sm-2 col-form-label">계좌번호<strong
                              class="sr-only">필수</strong></label>
                              <div class="col-sm-4">
                               <input type="text" class="form-control" 
                                 id="cus_account" name="cus_account" value="${fn:substring(customer.cus_account,0,3)}-${fn:substring(customer.cus_account,3,5)}-${fn:substring(customer.cus_account,5,9)}-${fn:substring(customer.cus_account,9,15)}" readonly="readonly">
								</div>
                        </div>

                        <div class="form-group row">
                           <label class="col-sm-2 col-form-label">우편번호<strong
                              class="sr-only">필수</strong></label>
                              <div class="col-sm-4">
                               <input type="text" class="form-control" 
                                 id="cus_zip_code" name="cus_zip_code" value="${customer.cus_zip_code}" readonly="readonly">
								</div>
                        </div>

                           <div class="form-group row">
                           <label class="col-sm-2 col-form-label">주소<strong
                              class="sr-only">필수</strong></label>
                              <div class="col-sm-4">
                               <input type="text" class="form-control" 
                                 id="cus_addr" name="cus_addr" value="${customer.cus_addr}" readonly="readonly">
								</div>
                        </div>

                    
                           <div class="form-group row">
                           <label class="col-sm-2 col-form-label">핸드폰번호<strong
                              class="sr-only">필수</strong></label>
                              <div class="col-sm-4">
                               <input type="text" class="form-control" 
                                 id="cus_tel" name="cus_tel" value="${fn:substring(customer.cus_tel,0,3)}-${fn:substring(customer.cus_tel,3,7)}-${fn:substring(customer.cus_tel,7,11)}" readonly="readonly">
								</div>
                        </div>
                     </li>
                  </ul>
                  <div class="px-3 px-sm-0 mb-4">
                     <div class="row mx-n2">
                        <div class="col-6 order-2 px-2">
                           <button type="submit" value="Join Us" id="btn_submit"
                              class="btn btn-primary btn-lg btn-block en">회원정보수정</button>
                        </div>
                        <div class="col-6 order-1 px-2">
                           <a href="${path}/admin/adminView" class="btn btn-basic btn-lg btn-block en">회원목록보기</a>
                        </div>
                     </div>
                  </div>

                  <input type="hidden" name="cus_answer"
                     value="${customer.cus_answer}" id="cus_answer" required
                     class="form-control required"> <input type="hidden"
                     name="cus_question" value="${customer.cus_question}" id="cus_id"
                     required class="form-control required">

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