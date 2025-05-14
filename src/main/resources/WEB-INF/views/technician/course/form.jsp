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
	<acme:input-textbox code="technician.course.form.label.courseId" path="courseId"/>
	<acme:input-textbox code="technician.course.form.label.name" path="name"/>
	<acme:input-textbox code="technician.course.form.label.org" path="org"/>
	<acme:input-textarea code="technician.course.form.label.shortDescription" path="shortDescription"/>
	<acme:input-textbox code="technician.course.form.label.start" path="start"/>
	<acme:input-url code="technician.course.form.label.blocksUrl" path="blocksUrl"/>
	
</acme:form>
