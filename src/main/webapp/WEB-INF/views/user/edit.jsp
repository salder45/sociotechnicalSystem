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
                <!-- internal menu -->
                <!--Page title -->
                <legend><s:message code="user.edit.label" /></legend>
                <!-- form-->
                <c:url var="action" value="/user/update"/>
                <form:form modelAttribute="user" method="post" action="${action}" class="form-horizontal">
                    <!-- errors-->
                    <form:errors path="*">
                        <c:forEach items="${messages}" var="message">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <span class="glyphicon glyphicon-alert"></span> <strong>${message}</strong>
                            </div>
                        </c:forEach>                        
                    </form:errors>
                    <!--fields-->
                    <form:hidden path="id" />
                    <form:hidden path="version" />
                    <form:hidden path="username" />
                    <fieldset>
                        <s:bind path="user.name">
                            <div class="form-group">
                                <s:message code="user.name.label" var="nameLabel"/>
                                <label for="name" class="control-label col-xs-2">${nameLabel}</label>    
                                <div class="col-xs-5">
                                    <form:input path="name" class="form-control" placeholder="${nameLabel}"/>
                                </div>                                
                            </div>
                        </s:bind>
                        <s:bind path="user.lastName">
                            <div class="form-group">
                                <s:message code="user.lastname.label" var="lastNameLabel"/>
                                <label for="lastName" class="control-label col-xs-2">${lastNameLabel}</label>    
                                <div class="col-xs-5">
                                    <form:input path="lastName" class="form-control" placeholder="${lastNameLabel}"/>
                                </div>                                
                            </div>
                        </s:bind>
                        <s:bind path="user.mothersMaidenName">
                            <div class="form-group">
                                <s:message code="user.mothersmaidenname.label" var="maidenNameLabel"/>
                                <label for="mothersMaidenName" class="control-label col-xs-2">${maidenNameLabel}</label>    
                                <div class="col-xs-5">
                                    <form:input path="mothersMaidenName" class="form-control" placeholder="${maidenNameLabel}"/>
                                </div>                                
                            </div>
                        </s:bind>
                        <s:bind path="user.email">
                            <div class="form-group">
                                <s:message code="email.label" var="emailLabel"/>
                                <label for="email" class="control-label col-xs-2">${emailLabel}</label>    
                                <div class="col-xs-5">
                                    <form:input path="email" class="form-control" placeholder="${emailLabel}"/>                                    
                                </div>                                
                            </div>
                        </s:bind>
                    </fieldset>  
                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <form:button id="updateBtn" name="updateBtn" class="btn btn-primary btn-large" value="update">
                                <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> <s:message code="update.label"/>
                            </form:button>
                        </form:form>
                    </div>
                </div>
            </div><!--/.row-->
        </div><!--/.container-->        
    </body>
</html>
