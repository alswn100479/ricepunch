<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-----------------------------------------------------------
	
	목록 include 페이지
	ㅇ 목록 그림 / 마커 생성

------------------------------------------------------------%>
<style>
	.card .card-header .card-header-form .input-group {max-width:300px; float:right;}
	.card .card-body {padding:10px 15px;}
	.card .card-body .list-group-item {border:none; cursor:pointer;}
	.card .card-body .list-group-item .desc {display:none;}
</style>
<script>
$(document).ready(function(){
	var markers = [], infoWindows = [];
	var contextPath = '<%=request.getContextPath()%>';
	
	<%-- 마커 생성 --%>
	<c:forEach var="item" items="${list}">
		var rMarker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(${item.latitude}, ${item.longitude}),
		    title : '${item.name}',
		    id : ${item.id},
		    icon : {url: contextPath + '/resources/img/rstr/icons8-marker-40.png'},
		    map: map
		});
		
		var infoWindow = new naver.maps.InfoWindow({
	        content: '<div style="width:150px;text-align:center;padding:10px;">${item.name}</div>'
	    });
		markers.push(rMarker);
	    infoWindows.push(infoWindow);
	</c:forEach>
	
	naver.maps.Event.addListener(map, 'idle', function() {
	    updateMarkers(map, markers);
	});

	function updateMarkers(map, markers) {
	    var mapBounds = map.getBounds();
	    var marker, position;

	    for (var i = 0; i < markers.length; i++) {
	        marker = markers[i]
	        position = marker.getPosition();
	        if (mapBounds.hasLatLng(position)) {
	            showMarker(map, marker);
	        } else {
	            hideMarker(map, marker);
	        }
	    }
	}
	function showMarker(map, marker) {
	    if (marker.setMap()) return;
	    marker.setMap(map);
	}
	function hideMarker(map, marker) {
	    if (!marker.setMap()) return;
	    marker.setMap(null);
	}
	function getClickHandler(seq) {
	    return function(e) {
	        var marker = markers[seq],
	            infoWindow = infoWindows[seq];

	        if (infoWindow.getMap()) {
	            infoWindow.close();
	        } else {
	            infoWindow.open(map, marker);
	        }
	    }
	}

	for (var i=0, ii=markers.length; i<ii; i++) {
	    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
	}
	
	$('#improved .head').click(function(e){
		var id = $(this).attr('id');
		for (var i = 0; i < markers.length; i++) {
			markers[i].setIcon({
			    url: ''
			});
			if (markers[i].id == id) {
				markers[i].setIcon({
				    url: contextPath + '/resources/img/rstr/icons8-region-64.png'
				});
				infoWindows[i].open(map, markers[i]);
			} else {
				markers[i].setIcon({
				    url: contextPath + '/resources/img/rstr/icons8-marker-40.png'
				});
			}
		}
		$('.desc').not($(this).closest('li').find('.desc')).hide();
	  	$(this).closest('li').find('.desc').not(':animated').slideToggle();
	    e.preventDefault();
	});
});
<%-- search --%>
function formSubmit() {
	var queryString = $("form[name=searchForm]").serialize() ;
	queryString += '&latitude='+latitude+'&longitude='+longitude;
	$("#table").load("<%=request.getContextPath()%>/rstr/list.do?" + queryString);
}
<%-- 아코디언 --%>
$(function() {
    $( "#accordion" ).accordion({
        collapsible: true,
        animate:200,
        active: false
    });
});
</script>
<div class="card">
	<div class="card-header">
		<h4><spring:message code="ricepunch.001"/></h4>
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
	<div class="card-body">
		<ul id="improved" class="list-group list-group-flush">
			<c:forEach var="item" items="${list}">
			    <li class="list-group-item">
			    	<a class="head" aria-selected="false" id=${item.id}>
			    		<div>
			    			${item.name}
			    			<c:if test="${!empty item.emgBell}">
			    				<img style="margin:0 3px 3px 3px;" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-siren-96.png" width="13" height="13"/>
							</c:if>
							<c:if test="${item.ladiesHandicapBowlNum > 0 || item.menHandicapBowlNum > 0 || menHandicapUrinalNum > 0}">
								<img style="margin:0 0 3px 0" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-assistive-technology-96.png" width="15" height="15"/>
							</c:if> 
							<c:if test="${!empty item.dipersExchgPosi}">
								<img style="margin:0 0 3px 0" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-mother-room-96.png" width="15" height="15"/>
			    			</c:if>
			    		</div>
			    	</a>
				   <div class="desc">
				   		<ul>
				   			<div style="float:left;">
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
				   			<c:if test="${!empty item.dipersExchgPosi}">
					   			<li>
					   				기저귀갈이대 ${item.dipersExchgPosi}
					   			</li>
				   			</c:if>
				   			<li>
				   				<c:choose>
				   					<c:when test="${empty item.rdnmAdr}">${item.lnmAdr}</c:when>
				   					<c:otherwise>${item.rdnmAdr}</c:otherwise>
				   				</c:choose>
				   			</li>
				   			</div>
				   			<div style="float:right;">
				   				<%-- <a onclick="window.scrollTo(0,0);"><img src="<%=request.getContextPath()%>/resources/img/rstr/icons8-chevron-up-24.png" width="15" height="15"/></a> --%>
				   			</div>
				   		</ul>
				    </div>
			    </li>
			 </c:forEach>
		</ul>
	</div>
</div>