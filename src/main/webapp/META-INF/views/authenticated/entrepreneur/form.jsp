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
	<acme:form-textbox code="authenticated.entrepreneur.entrepreneur.form.label.startUpName" path="startUpName"/>
	<acme:form-select code="administrator.technology.form.label.sector" path="sector.sector">
		<jstl:forEach items="${sectorsAvailable}" var="name">
			<jstl:if test="${sectorName == name}">
				<acme:form-option code="${name}" selected="true" value="${name}"/>
			</jstl:if>
			<jstl:if test="${sectorName != name}">
				<acme:form-option code="${name}" selected="false" value="${name}"/>
			</jstl:if>
		</jstl:forEach>
	</acme:form-select>	<acme:form-textbox code="authenticated.entrepreneur.entrepreneur.form.label.qualification" path="qualificationRecord"/>
	<acme:form-textbox code="authenticated.entrepreneur.entrepreneur.form.label.skillRecord" path="skillRecord"/>
		
	<acme:form-submit test="${command == 'create'}" code="authenticated.entrepreneur.entrepreneur.form.button.create" action="/authenticated/entrepreneur/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.entrepreneur.entrepreneur.form.button.update" action="/authenticated/entrepreneur/update"/>
	<acme:form-return code="authenticated.entrepreneur.entrepreneur.form.button.return"/>
</acme:form>
