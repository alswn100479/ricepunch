	<section class="section">
		<div class="section-header">
			<h1><fmt:message key="util.001"/></h1>
            <div class="section-header-breadcrumb">
				<div class="breadcrumb-item active"><a href="#">Dashboard</a></div>
				<div class="breadcrumb-item"><a href="#">Modules</a></div>
				<div class="breadcrumb-item">DataTables</div>
            </div>
		</div>
		<div class="section-body">
			<h2 class="section-title">Info</h2>
            <p class="section-lead">
              <fmt:message key="util.003"/></a>
            </p>
            
            <%-- DB Info --%>
            <div class="card">
				<div class="card-header">
					<h4>DB Info</h4>
                    <div class="card-header-action">
                      <a data-collapse="#mycard-collapse" class="btn btn-icon btn-info" href="#"><i class="fas fa-plus"></i></a>
                    </div>
				</div>
				<div class="collapse hide" id="mycard-collapse" style="">
					 <div class="card-body">
						<div class="section-title mt-0"><fmt:message key="util.002"/></div>
						<div class="form-group">
							<label>URL</label>
								<input type="text" class="form-control form-control-sm">
						</div>
						<div class="form-group">
							<label>NAME</label>
							<input type="text" class="form-control form-control-sm">
						</div>
						<div class="form-group">
							<label>PASSWD</label>
							<input type="text" class="form-control form-control-sm">
						</div>
						<div class="card-footer text-right">
							<button class="btn btn-primary mr-1" type="submit">Connect</button>
						</div>
					</div>
				</div>
			</div>
			
            <%-- Query Box --%>
			<div class="form-group">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="SELECT 1 FROM DUAL" aria-label="">
						<div class="input-group-append">
							<button class="btn disabled btn-primary" type="button" data-toggle="tooltip" 
							data-placement="left" title="" data-original-title="<fmt:message key="util.004"/>">Select</button>
                        </div>
                 </div>
			</div>
            
            <%-- Result --%>        
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4>Result</h4>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table class="table table-striped" id="table-1">
                        <thead>
                          <tr>
                            <th class="text-center">
                              #
                            </th>
                            <th>Task Name</th>
                            <th>Progress</th>
                            <th>Members</th>
                            <th>Due Date</th>
                            <th>Status</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>
                              1
                            </td>
                            <td>Create a mobile app</td>
                            <td class="align-middle">
                              <div class="progress" data-height="4" data-toggle="tooltip" title="100%">
                                <div class="progress-bar bg-success" data-width="100%"></div>
                              </div>
                            </td>
                            <td>
                              <img alt="image" src="<%=request.getContextPath()%>/resources/stisla/assets/img/avatar/avatar-5.png" class="rounded-circle" width="35" data-toggle="tooltip" title="Wildan Ahdian">
                            </td>
                            <td>2018-01-20</td>
                            <td><div class="badge badge-success">Completed</div></td>
                            <td><a href="#" class="btn btn-secondary">Detail</a></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
