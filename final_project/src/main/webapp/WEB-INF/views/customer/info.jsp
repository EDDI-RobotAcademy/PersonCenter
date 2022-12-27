<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!doctype html>
<html lang="ko" class="is-pc">
<head>
     
<meta charset="utf-8">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>정보확인</title>

<link rel="canonical" href="http://gukbbong.com/bbs/myphoto.php">
<link rel="stylesheet" href="http://gukbbong.com/nariya/app/bs4/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="http://gukbbong.com/nariya/css/nariya.css" type="text/css">
<link rel="stylesheet" href="http://gukbbong.com/theme/BS4-Basic/css/theme.css" type="text/css">
<link rel="stylesheet" href="http://gukbbong.com/theme/BS4-Basic/css/font/NEXON-Gothic-14px.css" type="text/css">
<link rel="stylesheet" href="http://gukbbong.com/theme/BS4-Basic/css/color/Blue.css" type="text/css">
<link rel="stylesheet" href="http://gukbbong.com/js/font-awesome/css/font-awesome.min.css" type="text/css">

<script src="http://gukbbong.com/nariya/js/jquery-3.5.1.min.js"></script>
<script src="http://gukbbong.com/nariya/js/common.js?ver=210618"></script>
<script src="http://gukbbong.com/js/wrest.js?ver=210618"></script>
<script src="http://gukbbong.com/js/placeholders.min.js"></script>
<script src="http://gukbbong.com/nariya/app/bs4/js/bootstrap.bundle.min.js"></script>
<script src="http://gukbbong.com/nariya/js/nariya.js?ver=210618"></script>
<script src="http://gukbbong.com/theme/BS4-Basic/js/theme.js"></script>
<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-8696806389739546"crossorigin="anonymous"></script>


     
</head>
<body class="responsive is-round">
<div class="sr-only"><div id="hd_login_msg">테스트님 로그인 중 <a href="http://gukbbong.com/bbs/logout.php">로그아웃</a></div></div>
<style>
	body { padding:0; margin:0; background:#fff; }
	.myphoto img { width:60px; height:60px; }
</style>

<section class="p-5">
	<form name="fphotoform" class="form" role="form" method="post" enctype="multipart/form-data" autocomplete="off">
		<input type="hidden" name="mode" value="u">
		
		<h3 class="text-center">My Photo</h3>
		
		<p class="myphoto my-4  text-center">
			<img src="http://gukbbong.com/img/no_profile.gif?nocache=1670385795" class="rounded-circle">
		</p>
		<p class="my-3 f-de">
			회원사진은 GIF/JPG/PNG 이미지 파일만 가능하며, 등록시 60x60 사이즈로 자동 리사이즈됩니다.
		</p>
		
		<input type=file name="mb_icon2">

		
		<p class="mt-5 text-center">
			<button type="button" class="btn btn-basic" onclick="window.close();">닫기</button>
			<button type="submit" class="btn btn-primary">확인</button>
		</p>		
	</form>
</section>




</body>
</html>
