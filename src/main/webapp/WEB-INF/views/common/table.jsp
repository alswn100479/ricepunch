<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<div class="row">
	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<h4>Result</h4>
				<div class="card-header-form">
					<form>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search">
							<div class="input-group-btn">
								<button class="btn btn-primary"><i class="fas fa-search"></i></button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="card-body p-0">
				<div class="table-responsive">
					<table class="table table-striped">
					<tr>
						<th>
							<spring:message code="rstr.003"/>
						</th>
						<th>
							<spring:message code="rstr.002"/>
						</th>
						<th>
							<spring:message code="rstr.004"/>
						</th>
						<th>
							<spring:message code="rstr.005"/>
						</th>
					</tr>
					<c:forEach var="item" items="${list}">
					<tr>
						<c:forTokens  var="field" items="${fields}" delims=",">
    						<td>
								<c:out value="${item[field]}"/>
							</td>
						</c:forTokens>
					</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>