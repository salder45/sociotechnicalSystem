<%-- 
    Document   : closeWorkOrder
    Created on : Oct 10, 2016, 11:26:48 AM
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
        <title><s:message code="close.label"/> <s:message code="workorder.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <c:url var="action" value="/workOrder/close"/>
                <form:form modelAttribute="workOrder" method="post" action="${action}" class="form-horizontal" data-parsley-validate="">
                    <form:hidden path="id" />
                    <form:errors path="*">
                        <c:forEach items="${messages}" var="message">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <span class="glyphicon glyphicon-alert"></span> <strong>${message}</strong>
                            </div>
                        </c:forEach>                        
                    </form:errors>
                    <fieldset>
                        <s:bind path="workOrder.badPieces">
                            <div class="form-group">
                                <s:message code="scrap.label" var="scrapLabel"/>
                                <label for="estimatedReleaseDate" class="control-label col-xs-2">${scrapLabel}</label>    
                                <div class="col-xs-5">
                                    <form:input path="badPieces" class="form-control" placeholder="${scrapLabel}"/>
                                </div>
                            </div>
                        </s:bind>  
                    </fieldset>

                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="saveBtn" name="saveBtn" class="btn btn-primary btn-large" value="send">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="send.label"/>
                            </form:button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div><!--/.container-->
    </body>
</html>
