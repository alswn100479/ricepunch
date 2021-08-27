<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	String url = request.getParameter("url");
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String query = "SELECT 1 FROM DUAL";
	
	java.sql.ResultSet rs = null;
	if (null!= url && null != id && passwd != null && null != query) 
	{
		try
		{
			java.sql.Connection conn = java.sql.DriverManager.getConnection(url, id, passwd);
			java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
		}
		catch (java.sql.SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			request.setAttribute("rs", rs);
			request.setAttribute("result", true);
		}
		
		request.setAttribute("url", url);
		request.setAttribute("id", id);
		request.setAttribute("passwd", passwd);
		request.setAttribute("query", query);
	}
%>
<script>
$(document).ready(function(){
	if (${result}) {
		$('#showHideBtn').click();
	}
});
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
	var queryString = $("form[name=dbForm]").serialize() ;
	moveContentPage('/pub/querySelect.jsp?' + queryString);
}
</script>
	<section class="section">
		<div class="section-header">
			<h1><spring:message code="util.001"/></h1>
		</div>
		<div class="section-body">
			<h2 class="section-title">info..</h2>
            <p class="section-lead">
              <spring:message code="util.003"/></a>
            </p>
            
            <%-- DB Info --%>
            <form name="dbForm" onsubmit="return false">
	            <div class="card">
					<div class="card-header">
						<h4>DB Info</h4>
	                    <div class="card-header-action">
	                      <c:if test="${result}">
			    			<img style="margin:0 3px 3px 3px;" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-check-mark-48.png" width="20" height="20"/>
							</c:if>
	                      <a id="showHideBtn" data-collapse="#mycard-collapse" class="btn btn-icon btn-info" href="javascript:void(0);"><i class="fas fa-minus"></i></a>
	                    </div>
					</div>
					<div class="collapse show" id="mycard-collapse" style="" >
						 <div class="card-body">
							<div class="section-title mt-0"><spring:message code="util.002"/></div>
							<div class="form-group">
								<label>URL</label>
									<input id="url" name="url" value="${url}" type="text" placeholder="jdbc:mariadb://ip:port/scheme" class="form-control form-control-sm" >
							</div>
							<div class="form-group">
								<label>NAME</label>
								<input id="id" name="id" value="${id}" type="text" class="form-control form-control-sm" placeholder="ricepunch">
							</div>
							<div class="form-group">
								<label>PASSWD</label>
								<input id="passwd" name="passwd" type="text" value="${passwd}" class="form-control form-control-sm" placeholder="ricepunch123">
							</div>
							<div class="card-footer text-right"><!-- onclick="connectTest();"  -->
								<button class="btn btn-primary mr-1" onclick="connectTest()">Test</button>
							</div>
						</div>
					</div>
				</div>
	            <%-- Query Box --%>
				<div class="form-group">
					<div class="input-group mb-3">
						<input name="query" id="query" type="text" class="form-control" placeholder="SELECT 1 FROM DUAL" aria-label="">
							<div class="input-group-append">
								<button class="<c:choose><c:when test="${result}">btn btn-primary</c:when><c:otherwise>btn disabled btn-primary</c:otherwise></c:choose>" type="button" data-toggle="tooltip" 
								data-placement="left" title="" data-original-title="<spring:message code="util.004"/>">Select</button>
	                        </div>
	                 </div>
				</div>
			</form>
            
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
