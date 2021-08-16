<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=lb07yh2rsh&submodules=geocoder"></script>
<div id="map" style="width:100%;height:400px;"></div>
<script>
	var latitude = 37.5782709;
	var longitude = 126.9770043;
		
	var map = new naver.maps.Map('map', {
	    center: new naver.maps.LatLng(latitude, longitude),
	    zoom: 14
	});
	
	var infoWindow = new naver.maps.InfoWindow({
		  anchorSkew: true
	});
	infoWindow.close();
		
	var marker = new naver.maps.Marker({
	    position: new naver.maps.LatLng(latitude, longitude),
	    map: map
	});
	
	<%-- infoWindow
	infoWindow.close();
	infoWindow.setContent([
	    '<div style="padding:10px;min-width:200px;line-height:150%;">',
	    '<h4 style="margin-top:5px;">Search coordinates</h4><br />',
	    htmlAddresses.join('<br />'),
	    '</div>'
  	].join('\n'));
	infoWindow.open(map, latlng);--%>
</script>