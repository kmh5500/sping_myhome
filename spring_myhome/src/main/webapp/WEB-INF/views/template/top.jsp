
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 
<% request.setCharacterEncoding("utf-8"); %> 
 <%
 
	String id=(String)session.getAttribute("id");
	String grade=(String)session.getAttribute("grade");


%>
<!DOCTYPE html> 
<html> 
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
<head> 
</head> 
<script type="text/javascript">
//Side navigation
function w3_open() {
    var x = document.getElementById("mySidebar");
    x.style.width = "100%";
    x.style.fontSize = "40px";
    x.style.paddingTop = "10%";
    x.style.display = "block";
}
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
}

//Tabs
function openCity(evt, cityName) {
var i;
var x = document.getElementsByClassName("city");
for (i = 0; i < x.length; i++) {
  x[i].style.display = "none";
}
var activebtn = document.getElementsByClassName("testbtn");
for (i = 0; i < x.length; i++) {
   activebtn[i].className = activebtn[i].className.replace(" w3-dark-grey", "");
}
document.getElementById(cityName).style.display = "block";
evt.currentTarget.className += " w3-dark-grey";
}

/* var mybtn = document.getElementsByClassName("testbtn")[0];
mybtn.click();
 */
//Accordions
function myAccFunc(id) {
 var x = document.getElementById(id);
 if (x.className.indexOf("w3-show") == -1) {
     x.className += " w3-show";
 } else { 
     x.className = x.className.replace(" w3-show", "");
 }
}

//Slideshows
var slideIndex = 1;

function plusDivs(n) {
slideIndex = slideIndex + n;
showDivs(slideIndex);
}

function showDivs(n) {
var x = document.getElementsByClassName("mySlides");
if (n > x.length) {slideIndex = 1}    
if (n < 1) {slideIndex = x.length} ;
for (i = 0; i < x.length; i++) {
  x[i].style.display = "none";  
}
/* x[slideIndex-1].style.display = "block";  
 */}

showDivs(1);

//Progress Bars
function move() {
var elem = document.getElementById("myBar");   
var width = 5;
var id = setInterval(frame, 10);
function frame() {
 if (width == 100) {
   clearInterval(id);
 } else {
   width++; 
   elem.style.width = width + '%'; 
   elem.innerHTML = width * 1  + '%';
 }
}
}
</script>
<!-- *********************************************** -->
<body>

<!-- Side Navigation -->
<nav class="w3-sidebar w3-bar-block w3-card w3-animate-left w3-center" style="display:none" id="mySidebar">
  <h1 class="w3-xxxlarge w3-text-theme">Side Navigation</h1>
  <button class="w3-bar-item w3-button" onclick="w3_close()">Close <i class="fa fa-remove"></i></button>
  <a href="${root }/bbs/list" class="w3-bar-item w3-button">업로드 게시판</a>
  <a href="${root }/img/list" class="w3-bar-item w3-button">이미지 게시판</a>
  <a href="#" class="w3-bar-item w3-button">방명록</a>
  <a href="#" class="w3-bar-item w3-button">정보 페이지</a>
</nav>

<!-- Header -->
<header class="w3-container w3-theme w3-padding" id="myHeader">
  <i onclick="w3_open()" class="fa fa-bars w3-xlarge w3-button w3-theme"></i> 
  <div class="w3-center">
  <h4>BEAUTIFUL RESPONSIVE WEB SITES</h4>
  <h1 class="w3-xxxlarge w3-animate-bottom">BUILT WITH W3.CSS</h1>
    <div class="w3-padding-32">
<      <button class="w3-btn w3-xlarge w3-dark-grey w3-hover-light-grey" onclick="document.getElementById('id01').style.display='block'" style="font-weight:900;">LEARN W3.CSS</button>
     </div>
  </div>
</header>

<!-- Modal -->
<div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-top">
      <header class="w3-container w3-theme-l1"> 
        <span onclick="document.getElementById('id01').style.display='none'"
        class="w3-button w3-display-topright">×</span>
        <h4>Oh snap! We just showed you a modal..</h4>
        <h5>Because we can <i class="fa fa-smile-o"></i></h5>
      </header>
      <div class="w3-padding">
        <p>Cool huh? Ok, enough teasing around..</p>
        <p>Go to our <a class="w3-btn" href="/w3css/default.asp">W3.CSS Tutorial</a> to learn more!</p>
      </div>
      <footer class="w3-container w3-theme-l1">
        <p>Modal footer</p>
      </footer>
    </div>
</div>
<!-- 테이블 시작 -->
<div class="w3-bar w3-white w3-large">
  <a href="${root}/" class="w3-bar-item w3-button w3-red w3-mobile"><i class="fa fa-bed w3-margin-right"></i>Home</a>
<!--   <a href="#rooms" class="w3-bar-item w3-button w3-mobile">Rooms</a>
  <a href="#about" class="w3-bar-item w3-button w3-mobile">About</a>
  <a href="#contact" class="w3-bar-item w3-button w3-mobile">Contact</a> -->
 
 <c:choose>
 	<c:when test="${empty id }">
 	
 	  <a href="${root}/member/login" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">login</a>
  <a href="${root}/member/agree" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">회원 가입</a>
  
   
	</c:when>
	<c:otherwise>
		<c:if test="${grade!='A' }">
			<a href="${root}/member/read" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Myinfo</a>
  <a href="${root}/member/logout" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Logout</a>
		
		<a href="${root}/member/delete" 
   class="w3-bar-item w3-button w3-mobile">회원 탈퇴</a>
		</c:if>
	</c:otherwise>
 </c:choose>
 
 <c:if test="${not empty id&&grade=='A' }">
    	
  <a id="admin" href="${root}/admin/list" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">회원 목록</a>  
    	<a href="${root}/member/read" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Myinfo</a>
  <a href="${root}/member/logout" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Logout</a>
    </c:if>
 
 
</div>
</body>
<!-- *********************************************** -->
</html> 