<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

</script>
<section class="section">
          <div class="section-header">
            <h1>User Info</h1>
          </div>
          <div class="section-body">
            <h2 class="section-title">User Info</h2>
            <p class="section-lead">
            	<!-- info -->
            </p>

            <div class="row mt-sm-4">
            <!-- 사용자 정보 -->
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
                    <div class="profile-widget-name">${user.name}</div>
                    ${user.userDesc}
                  </div>
                </div>
              </div>
              <!-- 사용자 정보 수정 -->
              <div class="col-12 col-md-12 col-lg-5">
                <div class="card profile-widget">
                  <div class="profile-widget-header">
                    <img alt="image" src="<%=request.getContextPath()%>/resources/stisla/assets/img/avatar/avatar-1.png" class="rounded-circle profile-widget-picture">
                  </div>
                  <form action="modifyUserInfo.do" method="post">
	                  <div class="profile-widget-description">
	                    <div class="profile-widget-name">User <div class="text-muted d-inline font-weight-normal"><div class="slash"></div> Info</div>
	                    	<div class="form-group row">
								<label class="col-sm-2 col-form-label"><spring:message code="user.002" /></label>
								<div class="col">
									<input name="name" value="${user.name}" type="text" class="form-control" >
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label"><spring:message code="user.003" /></label>
								<div class="col">
									<textarea name="userDesc" class="form-control" rows="3">${user.userDesc}</textarea>
								</div>
							</div>
							<div class="form-group row">
		                    	<label class="col-sm-2 col-form-label"></label>
		                    	<div class="col">
		                    		<button class="btn btn-primary">Save</button>
		                    	</div>
		                    </div>
		                    <input type="hidden" name="id" value="${user.id}">
	                    </div>
	                  </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </section>