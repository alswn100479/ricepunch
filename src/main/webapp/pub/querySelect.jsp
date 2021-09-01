<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
	String url = request.getParameter("url");
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String query = request.getParameter("query");
	
	ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
	ArrayList<String> columnNames = new ArrayList<String>();
	
	java.sql.ResultSet rs = null;
	if (null!= url && null != id && passwd != null && null != query) 
	{
		boolean isSuccess = true;
		java.sql.Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try
		{
			conn = java.sql.DriverManager.getConnection(url, id, passwd);
			pstmt = conn.prepareStatement(query);
			pstmt.setQueryTimeout(10);
			rs = pstmt.executeQuery();
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next())
			{
				if (rs.isFirst())
				{
					for (int i = 0; i < rsmd.getColumnCount(); i++)
					{
						columnNames.add(rsmd.getColumnName(i+1));
					}
				}

				ArrayList<String> rowData = new ArrayList<String>();
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
					rowData.add(rs.getString(i+1));
				}
				data.add(rowData);
			}
		}
		catch (java.sql.SQLException e)
		{
			isSuccess = false;
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage().toString());
			if (conn != null) {
				conn.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
		finally{
			if (conn != null) {
				conn.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
		
		request.setAttribute("isSuccess", isSuccess);
		request.setAttribute("result", data);
		request.setAttribute("columnNames", columnNames);
		request.setAttribute("url", url);
		request.setAttribute("id", id);
		request.setAttribute("passwd", passwd);
		request.setAttribute("query", query);
	}
%>
<script>
$(document).ready(function(){
	<c:if test="${isSuccess}">
	setCookie('querySelector_url', '${url}');
	setCookie('querySelector_id', '${id}');
	setCookie('querySelector_passwd', '${passwd}');
	</c:if>
	
	if (!$('#url').val()) {
		$('#url').val(getCookie('querySelector_url'));
	}
	if (!$('#id').val()) {
		$('#id').val(getCookie('querySelector_id'));
	}
	if (!$('#passwd').val()) {
		$('#passwd').val(getCookie('querySelector_passwd'));
	}
});
function connect() {
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
							<c:if test="${isSuccess}">
								<img style="margin:0 3px 3px 3px;" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-check-mark-48.png" width="20" height="20"/>
							</c:if>
						</div>
					</div>
					<div class="collapse show">
						<div class="card-body">
							<div class="form-group">
								<label>URL</label>
									<input id="url" name="url" value="${url}" type="text" class="form-control form-control-sm" >
							</div>
							<div class="form-group">
								<label>NAME</label>
								<input id="id" name="id" value="${id}" type="text" class="form-control form-control-sm">
							</div>
							<div class="form-group">
								<label>PASSWD</label>
								<input id="passwd" name="passwd" type="text" value="${passwd}" class="form-control form-control-sm">
							</div>
							<div class="form-group">
								<label>QUERY</label>
								<input id="query" name="query" type="text" class="form-control form-control-sm" 
									value="<c:choose><c:when test="${query != null}">${query}</c:when><c:otherwise>Select 1 From Dual</c:otherwise></c:choose>">
							</div>
						</div>
						<div class="card-footer text-right">
							<button class="btn btn-primary mr-1" onclick="connect()">Select</button>
						</div>
					</div>
				</div>
			</form>
            
            <%-- Result --%>        
			<div class="row">
				<div class="col-12">
                <div class="card" style="font-size:13px">
					<div class="card-header">
						<h4><c:choose><c:when test="${errorMsg != null}">Error</c:when><c:otherwise>Result</c:otherwise></c:choose></h4>
					</div>
					<c:choose>
						<%-- error --%>
						<c:when test="${errorMsg != null}">
							<div class="card-body">
								<div class="table-responsive">${errorMsg}</div>
							</div>
						</c:when>
						<%-- success --%>
						<c:otherwise>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-striped" id="table-1">
										<thead>
											<tr>
												<th class="text-center">#</th>
												<c:forEach var="columnName" items="${columnNames}">
													<th class="text-center">${columnName}</th>
												</c:forEach>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="resultRow" items="${result}" varStatus="status">
												<tr>
													<td class="text-center">${status.count}</td>
													<c:forEach var="resultColumn" items="${resultRow}">
														<td class="text-center">${resultColumn}</td>
													</c:forEach>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</section>
