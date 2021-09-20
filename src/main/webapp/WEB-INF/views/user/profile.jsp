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

            <div class="row">
            <!-- 사용자 정보 -->
              <div class="col">
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
              </div>
              <!-- 사용자 정보 수정 -->
              <div class="row">
              <div class="col">
                <div class="card">
                  <div class="card-header">
                    <h4>Update User Info</h4>
                  </div>
                  <div class="card-body">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                      <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">UserInfo</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
                      </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                      <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                  	<form action="modifyUserInfo.do" method="post">
		                  <div class="profile-widget-description">
		                    <div class="profile-widget-name">
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
								<div class="form-group row" style="float:right">
			                    	<div class="col">
			                    		<button class="btn btn-primary">Save</button>
			                    	</div>
			                    </div>
			                    <input type="hidden" name="id" value="${user.id}">
		                    </div>
		                  </div>
                  		</form>
                      </div>
                      <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        profile..
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>