<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	if(request.getAttribute("language") == null) {
		request.setAttribute("language", "ko");
	}
%>
