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
	<acme:form-textbox code="authenticated.discussion-forum.form.label.ticker" path="investmentRound.ticker" />
	<acme:form-textbox code="authenticated.discussion-forum.form.label.title" path="investmentRound.title" />
	<acme:form-textbox code="authenticated.discussion-forum.form.label.kindRound" path="investmentRound.kindRound" />
	<acme:form-textbox code="authenticated.discussion-forum.form.label.entrepreneur.startUpName" path="investmentRound.entrepreneur.startUpName" />

	<jstl:if test="${hasMessages}">
		<jstl:set var="discussionId" value="${id}" />
		<acme:form-submit method="get" code="authenticated.discussion-forum.form.button.messages" action="/authenticated/message/list-by-discussion?discussionId=${discussionId}" />
	</jstl:if>
	
	<security:authorize access="hasRole('Entrepreneur')">
		
		<jstl:set var="discussionId" value="${id}" />
		<acme:form-submit method="get" code="authenticated.message.list.button.create.message"
			action="/entrepreneur/message/post?discussionId=${discussionId}" />
	</security:authorize>

	<security:authorize access="hasRole('Investor')">
		
		<jstl:set var="discussionForumId" value="${id}" />
		<acme:form-submit method="get" code="authenticated.message.list.button.create.message"
			action="/investor/message/post?discussionId=${discussionId}" />
	</security:authorize>


	<acme:form-return code="authenticated.discussion-forum.form.button.return" />
</acme:form>
