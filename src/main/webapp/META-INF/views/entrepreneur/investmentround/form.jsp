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
	<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" />
	<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title" />
	<acme:form-textbox code="entrepreneur.investment-round.form.label.description" path="description" />
	<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount" />
	<acme:form-select code="entrepreneur.investment-round.form.label.kindRound" path="kindRound">
		<jstl:forEach items="${kinds}" var="name">
			<jstl:choose>
				<jstl:when test="${kindRound == name}">
					<acme:form-option code="${name}" selected="true" value="${name}" />
				</jstl:when>
				<jstl:otherwise>
					<acme:form-option code="${name}" selected="false" value="${name}" />
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
	</acme:form-select>

	<acme:form-textbox code="entrepreneur.investment-round.form.label.moreInfo" path="moreInfo" />
	<acme:form-textbox code="entrepreneur.investment-round.form.label.entrepreneur.startUpName" path="entrepreneur.startUpName" readonly="true"/>
	
	<jstl:if test="${command != 'create'}">
	<acme:form-checkbox code="entrepreneur.investment-round.finalMode" path="finalMode" />
	</jstl:if>

	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.update"
		action="/entrepreneur/investment-round/update" />
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.delete"
		action="/entrepreneur/investment-round/delete" />

	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investment-round.form.button.create"
		action="/entrepreneur/investment-round/create" />
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investment-round.form.button.update"
		action="/entrepreneur/investment-round/update" />
	<acme:form-submit test="${command == 'delete'}" code="entrepreneur.investment-round.form.button.delete"
		action="/entrepreneur/investment-round/delete " />


	<%-- <jstl:if test="${command != 'create'}">
		<jstl:set var="investmentRoundId" value="${id}" />
		<acme:form-submit method="get" code="entrepreneur.investment-round.form.button.workProgramme"
			action="/entrepreneur/work-programme/create?investmentRoundId=${investmentRoundId}" />
	</jstl:if> --%>

	<acme:form-return code="entrepreneur.investment-round.form.button.return" />
</acme:form>
