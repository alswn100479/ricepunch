<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%-----------------------------------------------------------
	
	공중화장실 메인화면
	ㅇ 맵 생성

------------------------------------------------------------%>
<style>
.locationDiv {
	padding: 10px 0 20px 0;
	font-weight: inherit;
	color: #000;
}

.card .card-header .card-header-action {
	width: auto !important;
	margin-top: 0px !important;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<section class="section">
	<div class="section-header">
		<h1>
			<spring:message code="dictionary.001" />
		</h1>
	</div>
	<div class="section-body">
		<h2 class="section-title">Info</h2>
		<p class="section-lead">
			<spring:message code="dictionary.002" />
		</p>

	<form action="write.do" method="post">
		<div class="row">
			<div class="col-12 col-md-6 col-lg-9">
				<div class="card">
					<form class="needs-validation" novalidate="">
						<div class="card-header">
							<h4>Form</h4>
						</div>
						<div class="card-body">
							<div class="form-group row">
								<label class="col-sm-2 col-form-label"><spring:message code="form.001" /></label>
								<div class="col">
									<input name="title" type="text" class="form-control" >
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label"><spring:message code="form.004" /></label>
								<div class="col">
									<input type="text" class="form-control" >
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label"><spring:message code="form.003" /></label>
								<div class="col">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group mb-0 row">
								<label class="col-sm-2 col-form-label"><spring:message code="form.002" /></label>
								<div class="col">
									<textarea name="content" class="form-control" rows="3" ></textarea>
								</div>
							</div>
						</div>
						<div class="card-footer text-right">
							<button class="btn btn-primary"><spring:message code="form.005"/></button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</form>
</section>