<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	System.out.println("url = " + request.getParameter("url"));
%>
<script>
$('#showHideBtn').click(function(e) {
	if($("#mycard-collapse").hasClass("hide") === true) {
		$('#mycard-collapse').removeClass('hide').addClass('show');
		$(this).find('.fas').removeClass('fa-plus').addClass('fa-minus');
	} else if($("#mycard-collapse").hasClass("show") === true) {
		$('#mycard-collapse').removeClass('show').addClass('hide');
		$(this).find('.fas').removeClass('fa-minus').addClass('fa-plus');
	}
});

function connectTest() {
	console.log($('#url').val());
	console.log($('#name').val());
	console.log($('#passwd').val());
}
</script>
	<section class="section">
		<div class="section-header">
			<h1><spring:message code="util.001"/></h1>
		</div>
		<div class="section-body">
			<h2 class="section-title">Info</h2>
            <p class="section-lead">
              <spring:message code="util.003"/></a>
            </p>
            
            <%-- DB Info --%>
            <form>
            <div class="card">
				<div class="card-header">
					<h4>DB Info</h4>
                    <div class="card-header-action">
                      <a id="showHideBtn" data-collapse="#mycard-collapse" class="btn btn-icon btn-info" href="javascript:void(0);"><i class="fas fa-plus"></i></a>
                    </div>
				</div>
				<div class="collapse hide" id="mycard-collapse" style="" >
					 <div class="card-body">
						<div class="section-title mt-0"><spring:message code="util.002"/></div>
						<div class="form-group">
							<label>URL</label>
								<input id="url" name="url" type="text" class="form-control form-control-sm" placeholder="jdbc:mariadb://ip:port/scheme">
						</div>
						<div class="form-group">
							<label>NAME</label>
							<input id="name" type="text" class="form-control form-control-sm" placeholder="ricepunch">
						</div>
						<div class="form-group">
							<label>PASSWD</label>
							<input id="passwd" type="text" class="form-control form-control-sm" placeholder="ricepunch123">
						</div>
						<div class="card-footer text-right"><!-- onclick="connectTest();"  -->
							<button class="btn btn-primary mr-1">Test</button>
						</div>
					</div>
				</div>
			</div>
			</form>
			
            <%-- Query Box --%>
			<div class="form-group">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="SELECT 1 FROM DUAL" aria-label="">
						<div class="input-group-append">
							<button class="btn disabled btn-primary" type="button" data-toggle="tooltip" 
							data-placement="left" title="" data-original-title="<spring:message code="util.004"/>">Select</button>
                        </div>
                 </div>
			</div>
            
            <%-- Result --%>        
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4>Result</h4>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table class="table table-striped" id="table-1">
                        <thead>
                          <tr>
                            <th class="text-center">
                              #
                            </th>
                            <th>Task Name</th>
                            <th>Progress</th>
                            <th>Members</th>
                            <th>Due Date</th>
                            <th>Status</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>
                              1
                            </td>
                            <td>Create a mobile app</td>
                            <td class="align-middle">
                              <div class="progress" data-height="4" data-toggle="tooltip" title="100%">
                                <div class="progress-bar bg-success" data-width="100%"></div>
                              </div>
                            </td>
                            <td>
                              <img alt="image" src="<%=request.getContextPath()%>/resources/stisla/assets/img/avatar/avatar-5.png" class="rounded-circle" width="35" data-toggle="tooltip" title="Wildan Ahdian">
                            </td>
                            <td>2018-01-20</td>
                            <td><div class="badge badge-success">Completed</div></td>
                            <td><a href="#" class="btn btn-secondary">Detail</a></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
