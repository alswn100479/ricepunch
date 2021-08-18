<%@ include file="/WEB-INF/views/include/content.taglib.jsp" %>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=lb07yh2rsh&submodules=geocoder"></script>
<%-----------------------------------------------------------
	
	공중화장실 목록 화면

------------------------------------------------------------%>
<style>
	.locationDiv {padding:10px 0 20px 0;}
	.card .card-header .card-header-action {width:auto !important; margin-top:0px !important;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	getLocation();
	
	$('#showHideBtn').click(function(e) {
		if($("#mycard-collapse").hasClass("hide") === true) {
			$('#mycard-collapse').removeClass('hide').addClass('show');
			$(this).find('.fas').removeClass('fa-plus').addClass('fa-minus');
			isMapShow = true;
			getLocation();
		} else if($("#mycard-collapse").hasClass("show") === true) {
			$('#mycard-collapse').removeClass('show').addClass('hide');
			$(this).find('.fas').removeClass('fa-minus').addClass('fa-plus');
		}
	});
});
var map, marker, isMapShow;
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
	var latitude = pos.coords.latitude || 37.5782709;
    var longitude = pos.coords.longitude || 126.9770043;
    var accuracy = pos.coords.accuracy;
    var altitude = pos.coords.altitude;
    var altitudeAccuracy = pos.coords.altitudeAccuracy;
    var timestamp = pos.coords.timestamp;
    var speed = pos.coords.speed;
    
    var location = new naver.maps.LatLng(latitude, longitude);
    
    if (isMapShow) {
    	if (!map) {
    		map = new naver.maps.Map('map', {
    		    center: location,
    		    zoom: 14
    		});
        } else {
        	 map.setCenter(location);
        }
        
        if (!marker) {
        	marker = new naver.maps.Marker({
    		    position: new naver.maps.LatLng(latitude, longitude),
    		    map: map
    		});
        } else {
        	 marker.setPosition(location);
        	 marker.setMap(map);
        }
    }
    
    searchCoordinateToAddress(location);
    
    <%-- getLocation success callback --%>
    $.ajax({
        url: "<%=request.getContextPath()%>/rstr/list.table.do", // 클라이언트가 요청을 보낼 서버의 URL 주소
        data: { latitude: latitude, longitude: longitude },                // HTTP 요청과 함께 서버로 보낼 데이터
        type: "GET",                             // HTTP 요청 방식(GET, POST)
        contentType : "text/html; charset=utf8",
        dataType: "html"  ,                       // 서버에서 보내줄 데이터의 타입
		success : function(res){
		    $('#table').empty();
		    $('#table').append(res);
		 },
		 error : function(xhr, status, error){
		 }
    })
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
	    
	    <%-- 주소 --%>
		<div class="locationDiv">
			<img class="siren" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-refresh-90.png" onclick="getLocation();" width="20" height="20" style="cursor:pointer;"/>
			<span id="location"/>
		</div>
		
		<%-- Map --%>
		<div class="card">
			<div class="card-header">
				<h4><fmt:message key="rstr.004"/></h4>
				<div class="card-header-action">
					<a id="showHideBtn" data-collapse="#mycard-collapse" class="btn btn-icon btn-info" href="javascript:void(0);"><i class="fas fa-plus"></i></a>
				</div>
			</div>
			<div class="collapse hide" id="mycard-collapse" style="">
				<div class="card-body">
					<div id="map" style="width:100%;height:400px;"></div>
				</div>
			</div>
		</div>
	
		<%-- Search --%>
		<!-- <span class="form-check">
						<input class="form-check-input" type="checkbox" id="defaultCheck1">
							<label class="form-check-label" for="defaultCheck1">Checkbox 1</label>
					</span>
					<span class="form-check">
						<input class="form-check-input" type="checkbox" id="defaultCheck3">
							<label class="form-check-label" for="defaultCheck3">Checkbox 2</label>
					</span> -->
        
        <%-- Table : ajax로 동적으로 append --%>
        <div id="table"/>        
	</div>
</section>