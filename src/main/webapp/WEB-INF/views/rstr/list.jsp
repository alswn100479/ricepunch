<%@ include file="/WEB-INF/views/include/content.taglib.jsp" %>
<%-----------------------------------------------------------
	
	공중화장실 목록 화면

------------------------------------------------------------%>
<style>
	.locationDiv {padding:0 0 10px 0;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	console.log('list.jsp');
	getLocation();
	$('#showHideBtn').click(function(e) {
		$('#mycard-collapse').removeClass('hide').addClass('show');
		var fas = $(this).find('fas').
		e.preventDefault();
	});
	/* $('a').click(function(e) {
		  e.preventDefault();
		}); */
});
<%-- 현재위치 위도,경도 불러오기 --%>
function getLocation() {
	if('geolocation' in navigator) {
		navigator.geolocation.getCurrentPosition(success, error, {enableHighAccuracy : true, maximumAge : 0, timeout : 3000 });
	} else {
		console.log('브라우저에서 지원하지 않음');
	}
}
<%-- getLocation success callback --%>
function success(pos) {
	var latitude = pos.coords.latitude;
    var longitude = pos.coords.longitude;
    var accuracy = pos.coords.accuracy;
    var altitude = pos.coords.altitude;
    var altitudeAccuracy = pos.coords.altitudeAccuracy;
    var timestamp = pos.coords.timestamp;
    var speed = pos.coords.speed;
    
   var location = new naver.maps.LatLng(latitude, longitude);
    map.setCenter(location);
    marker.setPosition(location);
    marker.setMap(map);
    
    searchCoordinateToAddress(location);
}
<%-- getLocation error callback --%>
function error(e) {
	switch(error.code) {
        case error.PERMISSION_DENIED:
        	console.log("사용자가 Geolocation API의 사용 요청을 거부");
        break;
        case error.POSITION_UNAVAILABLE:
        	console.log("가져온 위치 정보를 사용할 수 없음");
        break;
        case error.TIMEOUT:
        	console.log("요청이 허용 시간 초과");
        break;
        case error.UNKNOWN_ERROR:
        	console.log("알 수 없는 오류가 발생");
        break;
	}
}
<%-- 위도경도로 주소 표기 --%>
function searchCoordinateToAddress(latlng) {
	var tm128 = naver.maps.TransCoord.fromLatLngToTM128(latlng);

	naver.maps.Service.reverseGeocode({
		location: tm128,
	    coordType: naver.maps.Service.CoordType.TM128
	}, function(status, response) {
		if (status === naver.maps.Service.Status.ERROR) {
		if (!latlng) {
	        return alert('ReverseGeocode Error, Please check latlng');
		}
		if (latlng.toString) {
			return alert('ReverseGeocode Error, latlng:' + latlng.toString());
		}
		if (latlng.x && latlng.y) {
			return alert('ReverseGeocode Error, x:' + latlng.x + ', y:' + latlng.y);
		}
	      return alert('ReverseGeocode Error, Please check latlng');
		}

		var items = response.result.items,
	      htmlAddresses = [];

		if (items != null && items.length > 0) {
			if (items.length >1 && items[1].address) {
				$('#location').text(items[1].address);
			} else {
				$('#location').text(items[0].address);
			}
		}
	  });
}
</script>
<section class="section">
	<div class="section-header">
		<h1><fmt:message key="rstr.001"/></h1>
	</div>
	<div class="section-body">
		<h2 class="section-title">Table</h2>
		<p class="section-lead">Example of some Bootstrap table components.</p>
	    
		<jsp:include page="/WEB-INF/views/geo/map.jsp"/>
	    
		<%-- DB Info --%>
		<div class="card">
			<div class="card-header">
				<h4>Map</h4>
				<div class="card-header-action">
					<a id="showHideBtn" class="btn btn-icon btn-info" href=""><i class="fas fa-plus"></i></a>
				</div>
			</div>
			<div class="collapse hide" id="mycard-collapse" style="">
				<div class="card-body">
					<div class="section-title mt-0"><fmt:message key="util.002"/></div>
					<div class="form-group">
						<label>URL</label>
							<input type="text" class="form-control form-control-sm">
					</div>
					<div class="form-group">
						<label>NAME</label>
						<input type="text" class="form-control form-control-sm">
					</div>
					<div class="form-group">
						<label>PASSWD</label>
						<input type="text" class="form-control form-control-sm">
					</div>
					<div class="card-footer text-right">
						<button class="btn btn-primary mr-1" type="submit">Connect</button>
					</div>
				</div>
			</div>
		</div>
	
		<%-- 주소 --%>
		<div class="locationDiv">
			<img src="<%=request.getContextPath()%>/resources/img/precision.png" onclick="getLocation();" width="30" height="30" style="cursor:pointer;"/>
			<span id="location"/>
		</div>
			
        <%-- Search --%>
        <div class="card">
			<div class="card-header">
				<h4>Search</h4>
			</div>
			<div class="card-body">
				<div class="form-group">
					<label class="d-block">Checkbox</label>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="defaultCheck1">
							<label class="form-check-label" for="defaultCheck1">Checkbox 1</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="defaultCheck3">
							<label class="form-check-label" for="defaultCheck3">Checkbox 2</label>
					</div>
				</div>
			</div>
			<div class="card-footer text-right">
				<button class="btn btn-primary">Submit</button>
			</div>
		</div>
                
         <%-- Result --%>
         <jsp:include page="/rstr/list.do">
			<jsp:param name="fields" value="name,unisexYn,ladiesBowlNum,ladiesHandicapBowlNum" />
		</jsp:include>
	  </div>
	</section>