<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@page import="com.mim.login.LoginController"%>
<%
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies) {
	if (LoginController.KAKAO_TOKEN_NAME.equals(cookie.getName())) {
		request.setAttribute("isLogined", true);
	}
}
%>
<style>
.dropdown-menu .dropdown-title {text-transform:none;}
</style>
<script>
<%-- 언어를 변경한다. --%>
function changeLanguage(value) {
	$.ajax({
        url : '<%=request.getContextPath()%>/changeLanguage.do?language='+value,
        type : 'get',
        success : function(data){
           location.reload();
        }
    })
}
</script>
<div class="navbar-bg"></div>
<nav class="navbar navbar-expand-lg main-navbar">
	<form class="form-inline mr-auto">
		<ul class="navbar-nav mr-3">
			<li><a href="#" data-toggle="sidebar" class="nav-link nav-link-lg"><i class="fas fa-bars"></i></a></li>
			<li><a href="#" data-toggle="search" class="nav-link nav-link-lg d-sm-none"><i class="fas fa-search"></i></a></li>
		</ul>
		<div class="search-element">
			<input class="form-control" type="search" placeholder="Search" aria-label="Search" data-width="250" style="width: 250px;">
			<button class="btn" type="submit"><i class="fas fa-search"></i></button>
			<div class="search-backdrop"></div>
			<div class="search-result">
				<div class="search-header">
					Histories
				</div>
				<div class="search-item">
					<a href="#">How to hack NASA using CSS</a>
					<a href="#" class="search-close"><i class="fas fa-times"></i></a>
				</div>
				<div class="search-item">
					<a href="#">Kodinger.com</a>
					<a href="#" class="search-close"><i class="fas fa-times"></i></a>
				</div>
				<div class="search-item">
				 	 <a href="#">#Stisla</a>
					  <a href="#" class="search-close"><i class="fas fa-times"></i></a>
				</div>
				<div class="search-header">
				  Result
				</div>
				<div class="search-item">
					<a href="#">
						<img class="mr-3 rounded" width="30" src="<%=request.getContextPath()%>/resources/stisla/assets/img/products/product-3-50.png" alt="product">
						oPhone S9 Limited Edition
					</a>
				</div>
				<div class="search-item">
					<a href="#">
						<img class="mr-3 rounded" width="30" src="<%=request.getContextPath()%>/resources/stisla/assets/img/products/product-2-50.png" alt="product">
						Drone X2 New Gen-7
					</a>
				</div>
				<div class="search-item">
					<a href="#">
						<img class="mr-3 rounded" width="30" src="<%=request.getContextPath()%>/resources/stisla/assets/img/products/product-1-50.png" alt="product">
						Headphone Blitz
					</a>
				</div>
				<div class="search-header">
					Projects
				</div>
				<div class="search-item">
					<a href="#">
						<div class="search-icon bg-danger text-white mr-3">
						 <i class="fas fa-code"></i>
						</div>
						Stisla Admin Template
					</a>
				</div>
				<div class="search-item">
					<a href="#">
						<div class="search-icon bg-primary text-white mr-3">
							<i class="fas fa-laptop"></i>
						</div>
						Create a new Homepage Design
					</a>
				</div>
			</div>
		</div>
	</form>
	<ul class="navbar-nav navbar-right">
		<%-- profile --%>
		<li class="dropdown"><a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg nav-link-user">
			<img alt="image" src="<%=request.getContextPath()%>/resources/stisla/assets/img/avatar/avatar-1.png" class="rounded-circle mr-1">
			<div class="d-sm-none d-lg-inline-block">Hi, Guest</div></a>
			<div class="dropdown-menu dropdown-menu-right">
				<div class="dropdown-title">Login Plzzz..</div>
				<div class="dropdown-divider"></div>
				<c:if test="${!isLogined}">
				<a href="${kakao_url}" class="dropdown-item has-icon">
					<img src="<%=request.getContextPath()%>/resources/common/kakao_login.png" width="20px" height="20px" style="margin-right:5px;"/>
					<spring:message code="login.001"/>
				</a>
				</c:if>
				<!-- <div class="dropdown-divider"></div> -->
				<c:if test="${isLogined}">
					<a href="<%=request.getContextPath()%>/logout/kakao.do" class="dropdown-item has-icon text-danger" onclick="kakaoLogout()">
						<i class="fas fa-sign-out-alt"></i> Logout
					</a>
				</c:if>
			</div>
		</li>
	</ul>
</nav>