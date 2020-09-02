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

	<jstl:if test="${command != 'create'}">
		<jstl:set var="workProgrammeId" value="${id}" />
		<acme:form-submit method="get" code="entrepreneur.work-programme.form.button.activity"
			action="/entrepreneur/activity/create?workProgrammeId=${workProgrammeId}" />


	</jstl:if>
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.work-programme.form.button.delete"
		action="/entrepreneur/work-programme/delete" />
	<acme:form-submit test="${command == 'delete'}" code="entrepreneur.work-programme.form.button.delete"
		action="/entrepreneur/work-programme/delete " />

	<jstl:if test="${command == 'create'}">
		<acme:form-submit code="entrepreneur.work-programme.form.button.create" action="/entrepreneur/work-programme/create?investmentRoundId=${investmentRoundId}" />
	</jstl:if>

	<acme:form-return code="entrepreneur.work-programme.form.button.return" />
</acme:form>