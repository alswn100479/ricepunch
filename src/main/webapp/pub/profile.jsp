<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	var firstDate = new Date(2017, 11, 20, 9, 0);
	careerCalculateur(firstDate);
	
	setInterval(function(){
		careerCalculateur(firstDate);
	},1000);
});
/**
 * 현재시간까지의 경력을 계산한다.
 */
function careerCalculateur(firstDate) {
	var now = new Date();
	now.setMonth(now.getMonth() + 1);
	
	var yearVal = now.getFullYear() - firstDate.getFullYear();
	
	var monthVal = 0;  
	if (firstDate.getMonth() > now.getMonth()) {
		yearVal--;
		monthVal = (12 - firstDate.getMonth()) + now.getMonth();
	} else {
		monthVal = now.getMonth() - firstDate.getMonth();
	}
	
	var dayVal = 0;
	if (firstDate.getDay() > now.getDay()) {
		monthVal--;
		dayVal = (firstDate.getDate() - firstDate.getDay()) + now.getDay(); 
	} else {
		dayVal = now.getDay() - firstDate.getDay();
	}
	
	var hourVal = 0;
	if (firstDate.getHours() > now.getHours()) {
		dayVal--;
		hourVal = (24 - firstDate.getHours()) + now.getHours();
	} else {
		hourVal = now.getHours() - firstDate.getHours();
	}
	
	var minuteVal = now.getMinutes() - firstDate.getMinutes();
	var secondVal = now.getSeconds() - firstDate.getSeconds();
	
	$('#yearVal').text(yearVal);
	$('#monthVal').text(monthVal);
	$('#dayVal').text(dayVal);
	$('#hourVal').text(hourVal);
	$('#minuteVal').text(minuteVal);
	$('#secondVal').text(secondVal);
}
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
                    	<a href="https://ricepunch-tech.tistory.com/" target="_blank">블로그</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>