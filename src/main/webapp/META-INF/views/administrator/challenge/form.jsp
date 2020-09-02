<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title" />
	<acme:form-moment code="administrator.challenge.form.label.deadline" path="deadline" />
	<acme:form-textbox code="administrator.challenge.form.label.description" path="description" />
	<acme:form-textbox code="administrator.challenge.form.label.rookieGoal" path="rookie.goal" />
	<acme:form-money code="administrator.challenge.form.label.rookieReward" path="rookie.reward" />
	<acme:form-textbox code="administrator.challenge.form.label.averageGoal" path="average.goal" />
	<acme:form-money code="administrator.challenge.form.label.averageReward" path="average.reward" />
	<acme:form-textbox code="administrator.challenge.form.label.expertGoal" path="expert.goal" />
	<acme:form-money code="administrator.challenge.form.label.expertReward" path="expert.reward" />
	
	<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.update"
		action="/administrator/challenge/update" />
	<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.delete"
		action="/administrator/challenge/delete" />
	<acme:form-submit test="${command == 'create'}" code="administrator.challenge.form.button.create"
		action="/administrator/challenge/create" />
	<acme:form-submit test="${command == 'update'}" code="administrator.challenge.form.button.update"
		action="/administrator/challenge/update" />
	<acme:form-submit test="${command == 'delete'}" code="administrator.challenge.form.button.delete"
		action="/administrator/challenge/delete " />
	<acme:form-return code="administrator.challenge.form.button.return" />
</acme:form>
