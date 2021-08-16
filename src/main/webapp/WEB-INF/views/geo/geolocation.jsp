<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
if('geolocation' in navigator) {  <%-- 브라우저에 서 지원하는지 체크 --%>
	
	<%-- 현재위치 위도,경도 불러오기 성공 시 실행 --%>
	function success(pos) {
		var latitude = pos.coords.latitude;
	    var longitude = pos.coords.longitude;
	    var accuracy = pos.coords.accuracy;
	    var altitude = pos.coords.altitude;
	    var altitudeAccuracy = pos.coords.altitudeAccuracy;
	    var timestamp = pos.coords.timestamp;
	    var speed = pos.coords.speed;
	    /* console.log("위도:" + latitude + ' / 경도 :' + longitude + ' / 위도,경도의 정확도 :' + accuracy);
	    console.log("평균 해수면을 기준으로 하는 고도값 :" + altitude +' / 고도값의 정확도 : '+ altitudeAccuracy);
	    console.log("초당 이동한 미터 수 : " + speed); */
	    
	   var location = new naver.maps.LatLng(latitude, longitude);
	    map.setCenter(location);
	    marker.setPosition(location);
        marker.setMap(map);
	}
	
	<%-- 현재위치 위도,경도 불러오기 실패 시 실행 --%>
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
	
	<%-- 옵션
		enableHighAccuracy : 정확도 설정 여부. 배터리 등 소모가 있지만 더 정확히 측정할 수 있다.
		maximumAge : 캐시로 사용할 밀리초단위. 해당 값의 시간만큼 캐시로 남겨두고 시간이 지나면 다시 받아온다. 0 설정시 매번 새로 받아옴
		timeout : 밀리초 단위의 응답유효시간. 값이 없으면 계속 대기한다.
	 --%>
	var positionOptions = {enableHighAccuracy : true, maximumAge : 0, timeout : 3000 };

	<%-- 낮은 정밀도로 현재 위치 가져오기 --%>
	navigator.geolocation.getCurrentPosition(success, error, positionOptions);
	
	<%-- 사용자의 위치를 탐지하여 현재 위치 가져오기 --%>
	//var watchId = navigator.geolocation.watchPosition(success, error, positionOptions);
	//navigator.geolocation.clearWatch(watchId);
	
} else {
	console.log('브라우저에서 지원하지 않음');
}
</script>
