<%-- 
    Document   : new
    Created on : Sep 14, 2016, 11:40:20 AM
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
        <title><s:message code="machine.new.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="machine.new.label"/></legend>
                <jsp:include page="form.jsp">
                    <jsp:param name="type" value="NEW"/>
                </jsp:include>
            </div><!-- /.row --->
        </div><!-- /.container -->
    </body>
</html>
