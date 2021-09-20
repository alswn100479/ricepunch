<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
System.out.println(request.getAttribute("locale"));
%>
<script type="text/javascript">
	var firstDate = new Date(2017, 11, 20, 9, 0);
	
	setInterval(function(){
		var now = new Date();
		var interval = now - firstDate;
		
		var second = 1000;                  //1초 후
		var minute = 1000 * 60;             //1분 후
		var hour = 1000 * 60 * 60;        //1시간 후
		var day = 1000*60*60*24; 
		var month = day*30; 
		var year = month*12; 
		
		var yearVal = parseInt(interval/year);
		var minus = parseInt(interval % year);
		
		var monthVal = parseInt(minus/month);
		minus = parseInt(minus % month)
		
		var dayVal = parseInt(minus/day);
		minus = parseInt(minus % day);
		
		var hourVal = parseInt(minus / hour);
		minus = parseInt(minus % hour);
		
		var minuteVal = parseInt(minus / minute);
		minus = parseInt(minus % minute);
		
		var secondVal = parseInt(minus / second);
		
		$('#yearVal').text(yearVal);
		$('#monthVal').text(monthVal);
		$('#dayVal').text(dayVal);
		$('#hourVal').text(hourVal);
		$('#minuteVal').text(minuteVal);
		$('#secondVal').text(secondVal);
		
	},1000);

</script>
<section class="section">
          <div class="section-header">
            <h1>Profile</h1>
          </div>
          <div class="section-body">
            <h2 class="section-title">Developer</h2>
            <p class="section-lead">
            	<!-- info -->
            </p>

            <div class="row mt-sm-4">
              <div class="col-12 col-md-12 col-lg-5">
                <div class="card profile-widget">
                  <div class="profile-widget-header">
                    <img alt="image" src="<%=request.getContextPath()%>/resources/stisla/assets/img/avatar/avatar-1.png" class="rounded-circle profile-widget-picture">
                    <!--
	                    <div class="profile-widget-items">
	                      <div class="profile-widget-item">
	                        <div class="profile-widget-item-label">Posts</div>
	                        <div class="profile-widget-item-value">187</div>
	                      </div>
	                      <div class="profile-widget-item">
	                        <div class="profile-widget-item-label">Followers</div>
	                        <div class="profile-widget-item-value">6,8K</div>
	                      </div>
	                      <div class="profile-widget-item">
	                        <div class="profile-widget-item-label">Following</div>
	                        <div class="profile-widget-item-value">2,1K</div>
	                      </div>
	                    </div>
                     -->
                  </div>
                  <div class="profile-widget-description">
                    <div class="profile-widget-name">최민주 <div class="text-muted d-inline font-weight-normal"><div class="slash"></div> Web Developer</div></div>
                    <div id="career">
                    <span><spring:message code="site.007"/> : </span>
                    <span id="yearVal"></span><spring:message code="site.001"/>
                    <span id="monthVal"></span><spring:message code="site.002"/>
                    <span id="dayVal"></span><spring:message code="site.003"/>
                    <span id="hourVal"></span><spring:message code="site.004"/>
                    <span id="minuteVal"></span><spring:message code="site.005"/>
                    <span id="secondVal"></span><spring:message code="site.006"/>
                    <span id="milliSecondVal">
                    </div>
                    	<!-- info -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>