<%@ include file="common/Header.jspf" %>

	<body>
		<%@ include file="common/navigation.jspf" %>
		<div class="container">
	
			<h1><i>Enter Todo Details</i></h1>
			<form:form method="post" modelAttribute="todo">
				<fieldset class="mb-3">
					<form:label path="Description">Description</form:label>
					<form:input type="text"  path="description" required="required"/>
					<form:errors path="description" cssClass="text-warning"/>
				</fieldset>
				<fieldset class="mb-3">
					<form:label path="targetDate">TargetDate</form:label>
					<form:input type="text"  path="targetDate" required="required"/>
					<form:errors path="targetDate" cssClass="text-warning"/>
				</fieldset>
			
			<form:input type="hidden" path="id" />
			<form:input type="text" path="done"/>
			<input type="submit" class="btn btn-success">
			</form:form>
			
		</div>
	
<%@ include file="common/footer.jspf" %>
		<script type="text/javascript">
		$('#targetDate').datepicker({
		    format: 'yyyy-mm-dd',
		});
		</script>
		</body>
</html>

