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
                <c:url var="action" value="/user/update"/>
                <form:form action="${action}" class="form-horizontal">
                    <fieldset>
                        <div id="legend"><legend><s:message code="user.edit.label" /></legend></div>
                        <div class="control-group">
                            <label class="control-label">ALGO</label>
                            <input class="input-lg"/>
                            <p class="help-block"></p>
                        </div>
                    </fieldset>
                </form:form>
            </div><!--/.row-->
        </div><!--/.container-->
    </body>
</html>
