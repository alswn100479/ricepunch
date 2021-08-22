<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=lb07yh2rsh&submodules=geocoder"></script>
<%-----------------------------------------------------------
	
	공중화장실 메인화면
	ㅇ 맵 생성

------------------------------------------------------------%>
<style>
	.locationDiv {padding:10px 0 20px 0; font-weight:inherit; color:#000;}
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
			document.cookie = MAP_COOKIE + '=true';
		} else if($("#mycard-collapse").hasClass("show") === true) {
			$('#mycard-collapse').removeClass('show').addClass('hide');
			$(this).find('.fas').removeClass('fa-minus').addClass('fa-plus');
			document.cookie = MAP_COOKIE + '=false';
		}
	});
});
var map;
var isMapShow = true;
var latitude, longitude;
var MAP_COOKIE = 'rstr.map.show';
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
	latitude = pos.coords.latitude || 37.5782709;
    longitude = pos.coords.longitude || 126.9770043;
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
    }
    searchCoordinateToAddress(location);
    
    <%-- 목륵 load --%>
    $("#table").load("<%=request.getContextPath()%>/rstr/list.table.do?latitude="+latitude+"&longitude="+longitude);
}
<%-- getLocation error callback --%>
function error(e) {
	switch(error.code) {
        case error.PERMISSION_DENIED: //사용자가 Geolocation API의 사용 요청을 거부
        	$('#location').text('<spring:message code="rstr.005"/>').css({'color':'red', 'font-weight':'bold'})
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
	$('#showHideBtn').addClass('disabled');
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
		<h1><spring:message code="rstr.001"/></h1>
	</div>
	<div class="section-body">
		<h2 class="section-title">Info</h2>
		<p class="section-lead"><spring:message code="rstr.006"/></p>
	    
	    <%-- 주소 --%>
		<div class="locationDiv">
			<img class="siren" src="<%=request.getContextPath()%>/resources/img/rstr/icons8-refresh-90.png" onclick="getLocation();" width="20" height="20" style="cursor:pointer;"/>
			<span id="location"/>
		</div>
		
		<%-- Map --%>
		<div class="card">
			<div class="card-header">
				<h4><spring:message code="rstr.004"/></h4>
				<div class="card-header-action">
					<a id="showHideBtn" data-collapse="#mycard-collapse" class="btn btn-icon btn-info" href="javascript:void(0);"><i class="fas fa-minus"></i></a>
				</div>
			</div>
			<div class="collapse show" id="mycard-collapse" style="">
				<div class="card-body">
					<div id="map" style="width:100%;height:300px;"></div>
				</div>
			</div>
		</div>
        <%-- Table : ajax로 동적으로 append --%>
        <div>
        	<div id="table"/>
		</div>
	</div>
</section>