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
	<acme:form-textbox code="anonymous.paton-bulletin.form.label.author" path="author"/>
	<acme:form-textbox code="anonymous.paton-bulletin.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.paton-bulletin.form.label.description" path="description"/>
	
	<acme:form-submit test="${command == 'create'}" code="anonymous.paton-bulletin.button.create" action="/anonymous/paton-bulletin/create"/>
  	<acme:form-return code="anonymous.paton-bulletin.button.return"/>
</acme:form>
