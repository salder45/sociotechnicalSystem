<%-- 
    Document   : show
    Created on : Jul 22, 2016, 1:39:45 PM
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
        <title><s:message code="user.show.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="user.show.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <c:url var="action" value="/user/edit/${user.id}"/>
                <form:form modelAttribute="user" method="post" action="${action}" class="form-horizontal">
                    <form:hidden path="id" />
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.username.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.username}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.name.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.lastname.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.lastName}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.mothersmaidenname.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.mothersMaidenName}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="email.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.email}</p>
                        </div>
                    </div>

                    <s:message var="active" code="active.label"/>
                    <s:message var="inactive" code="inactive.label"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.enabled.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.enabled==true?active:inactive}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.account.expired.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.isAccountNonExpired()==true?active:inactive}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.account.locked.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.isAccountNonLocked()==true?active:inactive}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.credentials.expired.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.isCredentialsNonExpired()==true?active:inactive}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="user.roles.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${user.roles}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="editBtn" name="editBtn" class="btn btn-primary btn-large" value="edit">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="edit.label"/>
                            </form:button>
                            <a class="btn btn-danger" href="<c:url value="/user/delete/${user.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                        </div>
                    </div>
                </form:form>
            </div><!--.row -->
        </div><!-- /.container-->
    </body>
</html>
