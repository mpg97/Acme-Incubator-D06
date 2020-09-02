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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investment-round.form.label.ticker" path="ticker" />
	<acme:form-textbox code="authenticated.investment-round.form.label.creationDate" path="creationDate" />
	<acme:form-textbox code="authenticated.investment-round.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.investment-round.form.label.description" path="description" />
	<acme:form-textbox code="authenticated.investment-round.form.label.amount" path="amount" />
	<acme:form-textbox code="authenticated.investment-round.form.label.kindRound" path="kindRound" />
	<acme:form-textbox code="authenticated.investment-round.form.label.entrepreneur.startUpName" path="entrepreneur.startUpName" />
	<security:authorize access="hasRole('Investor')">
		<br>
		<jstl:set var="investmentRoundId" value="${id}" />
		<acme:form-submit method="get" code="authenticated.investment-round.form.button.application"
			action="/investor/application/apply?investmentRoundId=${investmentRoundId}" />
	</security:authorize>
	<acme:form-return code="authenticated.investment-round.form.button.return" />
</acme:form>
