 <!-- General JS Scripts -->
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>

<!-- Bootstrap JS File -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.nicescroll.min.js"></script>

<!-- DataTable -->
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>

<!-- Template JS File -->
<script src="<%=request.getContextPath()%>/resources/stisla/assets/js/scripts.js"></script>
<script src="<%=request.getContextPath()%>/resources/stisla/assets/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/resources/stisla/assets/js/stisla.js"></script>
<script src="<%=request.getContextPath()%>/resources/stisla/assets/js/tooltip.js"></script>

<!-- Page Specific JS File -->
<script src="<%=request.getContextPath()%>/resources/stisla/assets/js/page/components-table.js"></script>

<script>
var comm = {};
setCookie = function(name, value) {
	document.cookie = name + '=' + value;
}
getCookie = function(name) {
	var cookies = document.cookie.split(';');
	for (var i = 0; i < cookies.length; i++) {
		if (cookies[i].indexOf(name+'=') > -1) {
			return cookies[i].split('=')[1];
		}
	}
}
isNotUndefined = function(value) {
	 if(typeof value == "undefined" || value == null) {
		 return false;
	 } else {
		 value = value.replace(' ', '');
		 if (value == '') {
			 return false;
		 }
	 }

	return true;
}

comm.toString = function(value) {
	return JSON.stringify(value);
}
</script>