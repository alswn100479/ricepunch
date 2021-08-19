<%@ include file="/WEB-INF/views/include/content.taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	function formSubmit() {
		var queryString = $("form[name=searchForm]").serialize() ;
		queryString += '&latitude='+latitude+'&longitude='+longitude;
 		$("#table").load("<%=request.getContextPath()%>/rstr/list.table.do?" + queryString);
	}
</script>
<div class="card">
	<div class="card-header">
		<h4><fmt:message key="ricepunch.001"/></h4>
		<form id="searchForm" name="searchForm" class="card-header-form" onsubmit="return false">
			<!-- <span class="form-check">
				<input class="form-check-input" type="checkbox" id="defaultCheck1">
					<label class="form-check-label" for="defaultCheck1">Checkbox 1</label>
			</span>
			<span class="form-check">
				<input class="form-check-input" type="checkbox" id="defaultCheck3">
					<label class="form-check-label" for="defaultCheck3">Checkbox 2</label>
			</span> -->
			<div class="input-group">
				<input type="text" name="search" class="form-control" placeholder="Search">
				<div class="input-group-btn">
					<button class="btn btn-primary btn-icon" onclick="formSubmit();"><i class="fas fa-search"></i></button>
				</div>
			</div>
		</form>
	</div>
	<div class="card-body p-0">
		<table class="table">
			<tbody>
				<c:forEach var="item" items="${list}">
					<tr>
						<td>
							<div>
								${item.name}
								<img style="margin:0 3px 3px 3px;" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-siren-96.png" width="13" height="13"/>
									<c:if test="${item.ladiesHandicapBowlNum > 0 || item.menHandicapBowlNum > 0 || menHandicapUrinalNum > 0}">
									</c:if> 
									<img style="margin:0 0 3px 0" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-assistive-technology-96.png" width="15" height="15"/>
									<img style="margin:0 0 3px 0" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-mother-room-96.png" width="15" height="15"/>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>