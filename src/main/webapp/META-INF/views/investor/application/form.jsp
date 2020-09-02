<%-- - form.jsp - - Copyright (c) 2019 Rafael Corchuelo. - - In keeping with the traditional purpose of furthering education and
research, it is - the policy of the copyright owner to permit non-commercial use and redistribution of - this software. It has been
tested carefully, but it is not guaranteed for any particular - purposes. The copyright owner does not offer any warranties or
representations, nor do - they accept any liabilities with respect to them. --%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
	<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
	<acme:form-submit test="${command == 'apply'}" code="administrator.tool.form.button.create"
		action="/investor/application/apply?investmentRoundId=${param.investmentRoundId}" />
	<acme:form-return code="investor.application.form.button.return" />
</acme:form>
