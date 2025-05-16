<%--
- form.jsp
-
- Copyright (C) 2012-2025 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="administrator.banned-passenger.form.label.name" path="name"/>
	<acme:input-moment code="administrator.banned-passenger.form.label.birthDate" path="birthDate"/>
	<acme:input-textbox code="administrator.banned-passenger.form.label.passport" path="passport"/>
	<acme:input-textbox code="administrator.banned-passenger.form.label.nationality" path="nationality"/>
	<acme:input-textarea code="administrator.banned-passenger.form.label.reason" path="reason"/>
	<acme:input-moment code="administrator.banned-passenger.form.label.banDate" path="banDate"/>
	<acme:input-moment code="administrator.banned-passenger.form.label.liftDate" path="liftDate"/>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update')}">
		
			<acme:input-checkbox code="administrator.banned-passenger.form.label.confirmation" path="confirmation"/>	
			<acme:submit code="administrator.banned-passenger.form.button.update" action="/administrator/banned-passenger/update"/>
			
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:input-checkbox code="administrator.banned-passenger.form.label.confirmation-create" path="confirmationCreate"/>	
			<acme:submit code="administrator.banned-passenger.form.button.create" action="/administrator/banned-passenger/create"/>
		</jstl:when>		
	</jstl:choose>	
	
</acme:form>
