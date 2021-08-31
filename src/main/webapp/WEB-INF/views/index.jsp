<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies) {
	System.out.println("index jsp = " + cookie.getName() + " " + cookie.getValue());
}
System.out.println("================");
%>
<section class="section">
	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-12">
			<div class="card card-statistic-2">
				<div class="card-stats">
					<div class="card-stats-title"><spring:message code="site.008"/></div>
					<div class="card-stats-items">
						<div class="card-stats-item">
							<div class="card-stats-item-count">${accessCnt.yesterday}</div>
							<div class="card-stats-item-label">yesterday</div>
						</div>
						<div class="card-stats-item">
							<div class="card-stats-item-count">${accessCnt.week}</div>
							<div class="card-stats-item-label">week</div>
						</div>
						<div class="card-stats-item">
							<div class="card-stats-item-count">${accessCnt.year}</div>
							<div class="card-stats-item-label">year</div>
						</div>
					</div>
				</div>
				<div class="card-icon shadow-primary bg-primary"><i class="fas fa-archive"></i></div>
				<div class="card-wrap">
					<div class="card-header">
						<h4>Today</h4>
					</div>
					<div class="card-body">${accessCnt.today}</div>
				</div>
			</div>
  		</div>
		<div class="col-lg-4 col-md-4 col-sm-12">
			<div class="card card-statistic-2">
				<div class="card-chart">
					<canvas id="balance-chart" height="80"></canvas>
				</div>
				<div class="card-icon shadow-primary bg-primary"><i class="fas fa-dollar-sign"></i></div>
     		 	<div class="card-wrap">
					<div class="card-header">
						<h4>Balance</h4>
					</div>
					<div class="card-body">$187,13</div>
                </div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-12">
			<div class="card card-statistic-2">
				<div class="card-chart">
					<canvas id="sales-chart" height="80"></canvas>
				</div>
				<div class="card-icon shadow-primary bg-primary">
					<i class="fas fa-shopping-bag"></i>
				</div>
				<div class="card-wrap">
					<div class="card-header">
						<h4>Sales</h4>
					</div>
					<div class="card-body">4,732</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-sm-12 col-lg-6">
			<div class="card">
				<div class="card-header">
					<h4><spring:message code="site.009"/></h4>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-chrome-96.png" alt="product"></div>
							<div class="mt-2 font-weight-bold">Chrome</div>
							<div class="text-small text-muted"><span class="text-primary"></span> ${browser.Chrome}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/firefox.png" style="padding:5px;" alt="product"></div>
							<div class="mt-2 font-weight-bold">Firefox</div>
							<div class="text-small text-muted"><span class="text-primary"></span> ${browser.Firefox}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-adventures-96.png" alt="product"></div>
							<div class="mt-2 font-weight-bold">Safari</div>
							<div class="text-small text-muted"><span class="text-danger"></span> ${browser.Safari}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-microsoft-edge-96.png" alt="product"></div>
							<div class="mt-2 font-weight-bold">Edge</div>
							<div class="text-small text-muted">${browser.Edge}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/internet-explorer.png" style="padding:5px;" alt="product"></div>
							<div class="mt-2 font-weight-bold">IE</div>
							<div class="text-small text-muted"><span class="text-primary"></span> ${browser.IE}%</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12 col-sm-12 col-lg-6">
			<div class="card">
				<div class="card-header">
					<h4><spring:message code="site.009"/></h4>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-chrome-96.png" alt="product"></div>
							<div class="mt-2 font-weight-bold">Chrome</div>
							<div class="text-small text-muted"><span class="text-primary"></span> ${browser.Chrome}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/firefox.png" style="padding:5px;" alt="product"></div>
							<div class="mt-2 font-weight-bold">Firefox</div>
							<div class="text-small text-muted"><span class="text-primary"></span> ${browser.Firefox}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-adventures-96.png" alt="product"></div>
							<div class="mt-2 font-weight-bold">Safari</div>
							<div class="text-small text-muted"><span class="text-danger"></span> ${browser.Safari}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-microsoft-edge-96.png" alt="product"></div>
							<div class="mt-2 font-weight-bold">Edge</div>
							<div class="text-small text-muted">${browser.Edge}%</div>
						</div>
						<div class="col mb-4 mb-lg-0 text-center">
							<div><img width="70" src="<%=request.getContextPath()%>/resources/common/internet-explorer.png" style="padding:5px;" alt="product"></div>
							<div class="mt-2 font-weight-bold">IE</div>
							<div class="text-small text-muted"><span class="text-primary"></span> ${browser.IE}%</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>