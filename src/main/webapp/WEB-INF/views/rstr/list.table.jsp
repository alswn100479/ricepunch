<%@ include file="/WEB-INF/views/include/content.taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="card">
			<div class="card-header">
				<h4><fmt:message key="ricepunch.001"/></h4>
			</div>
			<div class="card-body p-0">
				<table class="table">
					<thead>
						<tr>
							<th><fmt:message key="rstr.003"/></th>
						</tr>
					</thead>
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