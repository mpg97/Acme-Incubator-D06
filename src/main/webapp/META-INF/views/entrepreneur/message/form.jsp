<%-- - form.jsp - - Copyright (c) 2019 Rafael Corchuelo. - - In keeping with the traditional purpose of furthering education and
research, it is - the policy of the copyright owner to permit non-commercial use and redistribution of - this software. It has been
tested carefully, but it is not guaranteed for any particular - purposes. The copyright owner does not offer any warranties or
representations, nor do - they accept any liabilities with respect to them. --%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.message.form.label.title" path="title" />
	<acme:form-moment code="entrepreneur.message.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="entrepreneur.message.form.label.tags" path="tags" placeholder="tag 1, tag2, tag 3..."/>
	<acme:form-textarea code="entrepreneur.message.form.label.body" path="body"/>
	<acme:form-textbox code="entrepreneur.message.form.label.authorName" path="authorName" readonly="true"/>
	
	<acme:form-checkbox code="entrepreneur.message.form.label.confirm" path="confirm" />
	
	<acme:form-submit test="${command == 'post'}" code="entrepreneur.message.form.button.create"
		action="/entrepreneur/message/post?discussionId=${discussionId}" />
	<acme:form-return code="entrepreneur.message.form.button.return" action="/authenticated/message/list-by-discussion?discussionId=${discussionId}"/>
	
</acme:form>
