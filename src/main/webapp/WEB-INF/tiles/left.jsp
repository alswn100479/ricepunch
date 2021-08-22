<div class="main-sidebar">
	<aside id="sidebar-wrapper">
		<div class="sidebar-brand">
			<a href="<%=request.getContextPath()%>/index.do">Minju</a>
		</div>
		<div class="sidebar-brand sidebar-brand-sm">
			<a href="index.html">St</a>
		</div>
		<ul class="sidebar-menu">
			<li class="menu-header">Starter</li>
				<li><a class="nav-link" href="<%=request.getContextPath() %>/rstr/index.do"><i class="far fa-square"></i><span>RestRoom</span></a></li>
				<li class="menu-header">Util</li>
				<li class="nav-item dropdown">
					<a href="#" class="nav-link has-dropdown"><i class="fas fa-th-large"></i> <span>Jsp Util</span></a>
					<ul class="dropdown-menu">
						<li><a class="nav-link" href="javascript:moveContentPage('/pub/querySelect.jsp')">Query Select</a></li>
						<li><a class="nav-link" href="javascript:moveContentPage('/pub/tableSpcfc.jsp')">Table Specification</a></li>
					</ul>
				</li>
		</ul>
	</aside>
</div>