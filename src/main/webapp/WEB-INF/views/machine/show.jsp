<%-- 
    Document   : show
    Created on : Sep 19, 2016, 5:40:40 PM
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
        <title><s:message code="machine.show.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="machine.show.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <c:url var="action" value="/machine/edit/${machine.id}"/>
                <form:form modelAttribute="machine" method="post" action="${action}" class="form-horizontal">
                    <form:hidden path="id" />

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="area.code.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${machine.code}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.name.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${machine.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="area.description.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${machine.description}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="machine.localization.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${machine.localization}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="area.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${machine.area.name}</p>
                        </div>
                    </div>



                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="editBtn" name="editBtn" class="btn btn-primary btn-large" value="edit">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="edit.label"/>
                            </form:button>
                            <a class="btn btn-danger" href="<c:url value="/machine/delete/${machine.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                        </div>
                    </div>

                </form:form>
            </div><!--/.row-->
        </div><!--/.container-->

    </body>
</html>
