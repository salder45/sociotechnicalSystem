<%-- 
    Document   : show
    Created on : Sep 8, 2016, 12:07:22 PM
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
        <title><s:message code="area.show.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="area.show.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <c:url var="action" value="/area/edit/${area.id}"/>
                <form:form modelAttribute="area" method="post" action="${action}" class="form-horizontal">
                    <form:hidden path="id" />
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="area.code.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${area.code}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.name.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${area.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="area.description.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${area.description}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="editBtn" name="editBtn" class="btn btn-primary btn-large" value="edit">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="edit.label"/>
                            </form:button>
                            <a class="btn btn-danger" href="<c:url value="/area/delete/${area.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                        </div>
                    </div>

                </form:form>
            </div><!--.row -->
        </div><!-- /.container-->  
    </body>
</html>
