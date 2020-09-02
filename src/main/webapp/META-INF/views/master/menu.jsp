<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.gonzalez-bulletin.create" action="/anonymous/gonzalez-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.gonzalez-bulletin.list" action="/anonymous/gonzalez-bulletin/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.paton-bulletin.create" action="/anonymous/paton-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.paton-bulletin.list" action="/anonymous/paton-bulletin/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.notice.actives" action="/anonymous/notice/list-active" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.technology" action="/anonymous/technology/list" />
			<acme:menu-suboption code="master.menu.anonymous.technology.bySector" action="/anonymous/technology/list-by-sector" />
			<acme:menu-suboption code="master.menu.anonymous.technology.byStars" action="/anonymous/technology/list-by-stars" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.tool" action="/anonymous/tool/list" />
			<acme:menu-suboption code="master.menu.anonymous.tool.bySector" action="/anonymous/tool/list-by-sector" />
			<acme:menu-suboption code="master.menu.anonymous.tool.byStars" action="/anonymous/tool/list-by-stars" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.notice" action="/administrator/notice/list" />
			<acme:menu-suboption code="master.menu.administrator.notice.create" action="/administrator/notice/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.inquiry" action="/administrator/inquiry/list" />
			<acme:menu-suboption code="master.menu.administrator.inquiry.create" action="/administrator/inquiry/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.overture" action="/administrator/overture/list" />
			<acme:menu-suboption code="master.menu.administrator.overture.create" action="/administrator/overture/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.technology" action="/administrator/technology/list" />
			<acme:menu-suboption code="master.menu.administrator.technology.create" action="/administrator/technology/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.tool" action="/administrator/tool/list" />
			<acme:menu-suboption code="master.menu.administrator.tool.create" action="/administrator/tool/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.create" action="/entrepreneur/investment-round/create" />
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round" action="/entrepreneur/investment-round/list-mine" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.entrepreneur.application" action="/entrepreneur/application/list-applied-to-mine" />
			<acme:menu-suboption code="master.menu.entrepreneur.application.group-by-ticker" action="/entrepreneur/application/list-applied-to-mine-grouped-by-ticker" />
			<acme:menu-suboption code="master.menu.entrepreneur.application.group-by-creation-date" action="/entrepreneur/application/list-applied-to-mine-grouped-by-creation-date" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.entrepreneur.work-programme" action="/entrepreneur/work-programme/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.ticker"
				action="/entrepreneur/investment-round/list-by-ticker" />
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.creation"
				action="/entrepreneur/investment-round/list-by-creation-date" />

			<acme:menu-suboption code="master.menu.entrepreneur.activity" action="/entrepreneur/activity/list" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.applications" action="/investor/application/list-mine" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.investment-round.involved" action="/bookkeeper/investment-round/list-involved" />
			<acme:menu-suboption code="master.menu.bookkeeper.investment-round.not-involved"
				action="/bookkeeper/investment-round/list-not-involved" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.notice.actives" action="/authenticated/notice/list-active" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.technology" action="/authenticated/technology/list" />
			<acme:menu-suboption code="master.menu.authenticated.technology.bySector" action="/authenticated/technology/list-by-sector" />
			<acme:menu-suboption code="master.menu.authenticated.technology.byStars" action="/authenticated/technology/list-by-stars" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.tool" action="/authenticated/tool/list" />
			<acme:menu-suboption code="master.menu.authenticated.tool.bySector" action="/authenticated/tool/list-by-sector" />
			<acme:menu-suboption code="master.menu.authenticated.tool.byStars" action="/authenticated/tool/list-by-stars" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.inquiry.actives" action="/authenticated/inquiry/list-active" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.overture.actives" action="/authenticated/overture/list-active" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.challenge.actives" action="/authenticated/challenge/list-active" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.challenge.investment-round"
				action="/authenticated/investment-round/list-active" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.challenge.discussion-forum"
				action="/authenticated/discussion-forum/list-involved" />
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.settings" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.configuration" action="/administrator/configuration/show" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create"
				access="!hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update"
				access="hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create"
				access="!hasRole('Investor')" />
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update"
				access="hasRole('Investor')" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

