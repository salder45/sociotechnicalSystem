<%-- 
    Document   : index
    Created on : Jul 13, 2016, 3:45:16 PM
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
        <title><s:message code="init.title.label" /></title>
    </head>
    <body>
        <h1><s:message code="init.title.label"/></h1>
        <c:url var="action" value="/inicializa" />
        <form:form action="${action}" method="post">
            <fieldset>
                <div class="control-group">
                    <input name="username" type="text" class="form-control" placeholder="<s:message code="user.username.label"/>" required autofocus/>
                    <input name="password" type="password" class="form-control" placeholder="<s:message code="user.password.label"/>" required/>

                    <button class="btn btn-lg btn-primary">
                        <s:message code="save.label"/>
                    </button>
                </div>
            </fieldset>
        </form:form>        
    </body>
</html>
