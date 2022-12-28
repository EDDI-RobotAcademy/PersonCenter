


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="${path}/resources/css/admin.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/admin.js"></script>


<title>관리자페이지</title>
</head>
<body>
<br>
<h2>  </h2>
<br>
      
<div class="container">
 
    <div class="col-xs-12">
    <a><input type="button" value="선택삭제" class="btn btn-outline-info" onclick="deleteValue();"></a>
    <h2>  </h2>
      <table summary="This table shows how to create responsive tables using Datatables' extended functionality" class="table table-bordered table-hover dt-responsive">
        <caption class="text-center">관리자 페이지 <a href="https://datatables.net/extensions/responsive/" target="_blank"></a></caption>
        <thead>
          <tr>
          	<th scope="col"><input id="allCheck" type="checkbox" name="allCheck"/></th>
            <th>사용자ID</th>
        	 <th>성별</th>
            <th>이름</th>
            <th>은행</th>
            <th>계좌번호</th>
            <th>우편번호</th>
            <th>주소</th>
            <th>번호</th>
            
          </tr>
      

        </thead>
        <tbody>
        
        <c:forEach var="item" items="${customers}">
          
          <tr>
          
         <td><input type="checkbox" name="RowCheck"  value="${item.cus_id}"></td>
         	
            <td><a href="${pageContext.request.contextPath}/admin/detailView?customer_id=${item.cus_id}">${item.cus_id}</a></td>
           
            <td>${item.cus_gender}</td>
            <td>${item.cus_nickname}</td>
            <td>${item.cus_bank}</td>
            <td>${fn:substring(item.cus_account,0,3)}-${fn:substring(item.cus_account,3,5)}-${fn:substring(item.cus_account,5,9)}-${fn:substring(item.cus_account,9,15)}</td>
            <td>${item.cus_zip_code}</td>
            <td>${item.cus_addr}</td>
            <td>${fn:substring(item.cus_tel,0,3)}-${fn:substring(item.cus_tel,3,7)}-${fn:substring(item.cus_tel,7,11)}</td> 

          </tr>
         </c:forEach>

        </tbody>
      </table>

      
      <div class="text-center">
	
	
		
	
	
	</div>
	 
	  <div class="font-weight-normal px-3 px-sm-0">
		<ul class="pagination justify-content-center en mb-0">
		<li class="page-item"><a href="${pageContext.request.contextPath}/admin/adminView?page=${page-1}">&laquo;</a></li>
		<c:forEach var="page" begin="${page}" end="${endPage }">
		<li><a href="${pageContext.request.contextPath}/admin/adminView?page=${page}">${page}</a></li>
		</c:forEach>
		<li class="page-item"><a href="${pageContext.request.contextPath}/admin/adminView?page=${page+1}">&raquo;</a></li>
		
		</ul>
		</div>
		
    </div>
  </div>
<script type="text/javascript">

		
		$(function(){
			var chkObj = document.getElementsByName("RowCheck");
			var rowCnt = chkObj.length;
			
			$("input[name='allCheck']").click(function(){
				var chk_listArr = $("input[name='RowCheck']");
				for (var i=0; i<chk_listArr.length; i++){
					chk_listArr[i].checked = this.checked;
				}
			});
			$("input[name='RowCheck']").click(function(){
				if($("input[name='RowCheck']:checked").length == rowCnt){
					$("input[name='allCheck']")[0].checked = true;
				}
				else{
					$("input[name='allCheck']")[0].checked = false;
				}
			});
		});
		function deleteValue(){
			var url = "${pageContext.request.contextPath}/admin/delete";    // Controller로 보내고자 하는 URL (.dh부분은 자신이 설정한 값으로 변경해야됨)
			var valueArr = new Array();
		    var list = $("input[name='RowCheck']");
		    for(var i = 0; i < list.length; i++){
		        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
		            valueArr.push(list[i].value);
		        }
		    }
		    if (valueArr.length == 0){
		    	alert("선택된 글이 없습니다.");
		    }
		    else{
				var chk = confirm("정말 삭제하시겠습니까?");				 
				$.ajax({
				    url : url,                    // 전송 URL
				    type : 'POST',                // GET or POST 방식
				    traditional : true,
				    data : {
				    	valueArr : valueArr        // 보내고자 하는 data 변수 설정
				    },
	                success: function(jdata){
	                    if(jdata = 1) {
	                        alert("삭제 성공");
	                       // location.replace("${pageContext.request.contextPath}/adminView")
	                        history.go(0);
	                    }
	                    else{
	                        alert("삭제 실패");
	                    }
	                }
				});
			}
		}
	
	</script>

</body>
</html>
<%@ include file="../include/footer.jsp"%>