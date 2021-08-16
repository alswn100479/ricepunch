 <%@ include file="/WEB-INF/views/include/page/header.jsp" %>
</head>
<body>
	<section class="section">
		<div class="section-header">
			<h1><fmt:message key="util.005"/></h1>
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
		</div>
            
			<%-- File --%>
		<div class="custom-file">
			<input type="file" class="custom-file-input" id="customFile">
				<label class="custom-file-label" for="customFile">Choose file</label>
		</div>
			
			
			<%-- Query Box --%>
			<div class="card" style="margin-top:30px">
                  <div class="card-header">
                    <h4>Result</h4>
                  </div>
                  <div class="card-body">
                    ${query}
                  </div>
                  <div class="card-footer text-right">
                    <button class="btn btn-primary">Copy</button>
                  </div>
                </div>
              </div>
          </div>
        </section>
</body>
</html>
