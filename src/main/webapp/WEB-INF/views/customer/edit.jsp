<%-- 
    Document   : edit
    Created on : Sep 22, 2016, 12:09:42 PM
    Author     : laboratoriointerface
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:message code="customer.edit.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="customer.edit.label"/></legend>
                <jsp:include page="form.jsp" >
                    <jsp:param name="type" value="EDIT" />
                </jsp:include>
            </div><!--/.row-->
        </div><!--/.container-->
    </body>
</html>
