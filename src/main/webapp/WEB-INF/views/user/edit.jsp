<%-- 
    Document   : edit
    Created on : Jul 20, 2016, 8:46:23 AM
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
        <title><s:message code="user.edit.label" /></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="user.edit.label" /></legend>
                <jsp:include page="form.jsp" >
                    <jsp:param name="type" value="EDIT" />
                </jsp:include>
            </div><!--/.row-->
        </div><!--/.container-->        
    </body>
</html>
