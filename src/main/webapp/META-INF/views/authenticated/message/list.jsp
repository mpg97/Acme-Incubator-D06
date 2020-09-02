
<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="authenticated.message.list.label.title" path="title" width="20%" />
	<acme:list-column code="authenticated.message.list.label.creationMoment" path="creationMoment" width="20%" />
	<acme:list-column code="authenticated.message.list.label.authorName" path="authorName" width="20%" />
	<acme:list-column code="authenticated.message.list.label.tags" path="tags" width="40%" />
</acme:list>

<acme:form-return code="authenticated.message.list.button.return" action="/authenticated/discussion-forum/list-involved"/>


