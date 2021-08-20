<%@ include file="/WEB-INF/views/include/content.taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.card .card-header .card-header-form .input-group {max-width:300px; float:right;}
	.card .card-body .list-group-item {border:none;}
	.card .card-body .list-group-item .desc {display:none;}
</style>
<script>
$(document).ready(function(){
	<c:forEach var="item" items="${list}">
		marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(${item.latitude}, ${item.longitude}),
		    map: map
		});
	</c:forEach>
});
<%-- search --%>
function formSubmit() {
	var queryString = $("form[name=searchForm]").serialize() ;
	queryString += '&latitude='+latitude+'&longitude='+longitude;
	$("#table").load("<%=request.getContextPath()%>/rstr/list.table.do?" + queryString);
}
<%-- 아코디언 --%>
$(function() {
    $( "#accordion" ).accordion({
        collapsible: true,
        animate:200,
        active: false
    });
});
$('#improved .head').click(function(e){
	$('.desc').not($(this).closest('li').find('.desc')).hide();
    e.preventDefault();
   $(this).closest('li').find('.desc').not(':animated').slideToggle();
});
</script>
<div class="card">
	<div class="card-header">
		<h4><fmt:message key="ricepunch.001"/></h4>
		<form id="searchForm" name="searchForm" class="card-header-form" onsubmit="return false">
			<div class="input-group">
				<input type="text" name="search" class="form-control" placeholder="Search">
				<div class="input-group-btn">
					<button class="btn btn-primary btn-icon" onclick="formSubmit();"><i class="fas fa-search"></i></button>
				</div>
			</div>
		</form>
	</div>
	<div class="card-body">
		<ul id="improved" class="list-group list-group-flush">
			<c:forEach var="item" items="${list}">
			    <li class="list-group-item">
			    	<a class="head" aria-selected="false">
			    		<div>
			    			${item.name}
			    			<img style="margin:0 3px 3px 3px;" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-siren-96.png" width="13" height="13"/>
							<c:if test="${item.ladiesHandicapBowlNum > 0 || item.menHandicapBowlNum > 0 || menHandicapUrinalNum > 0}">
								<img style="margin:0 0 3px 0" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-assistive-technology-96.png" width="15" height="15"/>
							</c:if> 
							<img style="margin:0 0 3px 0" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-mother-room-96.png" width="15" height="15"/>
			    		</div>
			    	</a>
				   <div class="desc">
				   		<ul>
				   			<li>${item.type}</li>
				   			<li>
				   				<c:choose>
				   					<c:when test="${item.unisexYn eq '1'}">공용형</c:when>
				   					<c:otherwise>분리형</c:otherwise>
				   				</c:choose>
				   			</li>
				   			<li>
				   				남 ${item.menBowlNum} / ${item.menUrinalNum} / ${item.menHandicapBowlNum} / ${item.menHandicapUrinalNum}
				   			</li>
				   			<li>
				   				여 ${item.ladiesBowlNum} / ${item.ladiesHandicapBowlNum}
				   			</li>
				   			<li>
				   				어린이용 ${menChildrenBowlNum} / ${item.menChildrenUrinalNum} / ${item.ladiesChildToiletBowlNum}
				   			</li>
				   			<li>
				   				기저귀갈이대 ${item.dipersExchgPosi}
				   			</li>
				   			<li>
				   				<c:choose>
				   					<c:when test="${empty item.rdnmAdr}">${item.lnmAdr}</c:when>
				   					<c:otherwise>${item.rdnmAdr}</c:otherwise>
				   				</c:choose>
				   			</li>
				   		</ul>
				    </div>
			    </li>
			 </c:forEach>
		</ul>
	</div>
</div>