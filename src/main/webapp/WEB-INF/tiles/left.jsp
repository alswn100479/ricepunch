<div class="main-sidebar">
	<aside id="sidebar-wrapper">
		<div class="sidebar-brand">
			<a href="<%=request.getContextPath()%>/">Minju</a>
		</div>
		<div class="sidebar-brand sidebar-brand-sm">
			<a href="index.html">St</a>
		</div>
		<ul class="sidebar-menu">
			<!-- API -->
			<li class="menu-header">API</li>
			<li><a class="nav-link" href="<%=request.getContextPath() %>/rstr/"><i class="far fa-square"></i><span>RestRoom</span></a></li>
			<li><a class="nav-link" href="<%=request.getContextPath() %>/dictionary/adminIndex.do"><i class="far fa-square"></i><span>Dictionary</span></a></li>
			
			<!-- Util -->
			<li class="menu-header">Util</li>
			<li class="nav-item dropdown">
				<a href="#" class="nav-link has-dropdown"><i class="fas fa-th-large"></i> <span>JSP Util</span></a>
				<ul class="dropdown-menu">
					<li><a class="nav-link" href="javascript:moveContentPage('/pub/querySelect.jsp')">Query Select</a></li>
					<!-- <li><a class="nav-link" href="javascript:moveContentPage('/pub/tableSpcfc.jsp')">Table Specification</a></li> -->
				</ul>
			</li>
			
			<!-- Developer -->
			<li class="menu-header">Developer</li>
			<li><a class="nav-link" href="javascript:moveContentPage('/pub/profile.jsp')"><i class="far fa-square"></i><span>Profile</span></a></li>
		</ul>
	</aside>
</div>