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
                  <div class="card-stats-title"><spring:message code="test.common.msg"/>
                    <div class="dropdown d-inline">
                      <a class="font-weight-600 dropdown-toggle" data-toggle="dropdown" href="#" id="orders-month">August</a>
                      <ul class="dropdown-menu dropdown-menu-sm">
                        <li class="dropdown-title">Select Month</li>
                        <li><a href="#" class="dropdown-item">January</a></li>
                        <li><a href="#" class="dropdown-item">February</a></li>
                        <li><a href="#" class="dropdown-item">March</a></li>
                        <li><a href="#" class="dropdown-item">April</a></li>
                        <li><a href="#" class="dropdown-item">May</a></li>
                        <li><a href="#" class="dropdown-item">June</a></li>
                        <li><a href="#" class="dropdown-item">July</a></li>
                        <li><a href="#" class="dropdown-item active">August</a></li>
                        <li><a href="#" class="dropdown-item">September</a></li>
                        <li><a href="#" class="dropdown-item">October</a></li>
                        <li><a href="#" class="dropdown-item">November</a></li>
                        <li><a href="#" class="dropdown-item">December</a></li>
                      </ul>
                    </div>
                  </div>
                  <div class="card-stats-items">
                    <div class="card-stats-item">
                      <div class="card-stats-item-count">24</div>
                      <div class="card-stats-item-label">Pending</div>
                    </div>
                    <div class="card-stats-item">
                      <div class="card-stats-item-count">12</div>
                      <div class="card-stats-item-label">Shipping</div>
                    </div>
                    <div class="card-stats-item">
                      <div class="card-stats-item-count">23</div>
                      <div class="card-stats-item-label">Completed</div>
                    </div>
                  </div>
                </div>
                <div class="card-icon shadow-primary bg-primary">
                  <i class="fas fa-archive"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Total Orders</h4>
                  </div>
                  <div class="card-body">
                    59
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
              <div class="card card-statistic-2">
                <div class="card-chart">
                  <canvas id="balance-chart" height="80"></canvas>
                </div>
                <div class="card-icon shadow-primary bg-primary">
                  <i class="fas fa-dollar-sign"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Balance</h4>
                  </div>
                  <div class="card-body">
                    $187,13
                  </div>
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
                  <div class="card-body">
                    4,732
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
          	<div class="card mt-4">
                  <div class="card-header">
                    <h4>Browser</h4>
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
                        <div><img width="70" src="<%=request.getContextPath()%>/resources/common/icons8-internet-explorer-96.png" alt="product"></div>
                        <div class="mt-2 font-weight-bold">IE</div>
                        <div class="text-small text-muted"><span class="text-primary"></span> ${browser.IE}%</div>
                      </div>
                    </div>
                  </div>
                </div>
          </div>
        </section>