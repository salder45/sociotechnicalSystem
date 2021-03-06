<%-- 
    Document   : setReleaseDate
    Created on : Oct 10, 2016, 11:25:33 AM
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
        <title><s:message code="workorder.set.estimated.date.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="workorder.set.estimated.date.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <c:url var="action" value="/workOrder/setEstimatedReleaseDate"/>
                <form:form modelAttribute="workOrder" method="post" action="${action}" class="form-horizontal">
                    <form:errors path="*">
                        <c:forEach items="${messages}" var="message">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <span class="glyphicon glyphicon-alert"></span> <strong>${message}</strong>
                            </div>
                        </c:forEach>                        
                    </form:errors>
                    <form:hidden path="id" />
                    <form:hidden path="version" />
                    <form:hidden path="code" />
                    <form:hidden path="dateCreated" />
                    <form:hidden path="lastUpdated" />
                    <form:hidden path="status" />
                    <form:hidden path="releaseDate" />
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="area.code.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${workOrder.code}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="workorder.pieces.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${workOrder.piecesNumber}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="seller.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${workOrder.seller.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="customer.label"/>:</label> 
                        <div class="col-sm-10">
                            <p class="form-control-static">${workOrder.customer.name}</p>
                        </div>
                    </div>
                    <legend><s:message code="batch.list.label"/></legend>
                    <table id="table-workOrders" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th><s:message code="area.description.label"/></th>
                                <th><s:message code="batch.pieces.label"/></th>
                                <th><s:message code="batch.draw.exist.label"/></th>
                                <th><s:message code="batch.sharpening.label"/></th>
                                <th><s:message code="batch.covering.label"/></th>                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${workOrder.batchs}" var="oneBatch">
                                <tr>
                                    <th>${oneBatch.description}</th>
                                    <th>${oneBatch.batchPieces}</th>
                                    <th>${oneBatch.existDraw}</th>
                                    <th>${oneBatch.sharpening}</th>
                                    <th>${oneBatch.coveringRequired}</th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <s:bind path="workOrder.estimatedReleaseDate">
                        <div class="form-group">
                            <s:message code="workorder.estimated.date.label" var="releaseDateLabel"/>
                            <label for="estimatedReleaseDate" class="control-label col-xs-2">${releaseDateLabel}</label>    
                            <div class="col-xs-5">
                                <form:input path="estimatedReleaseDate" class="form-control" placeholder="${releaseDateLabel}" id="estimatedReleaseDate"/>
                            </div>
                        </div>
                    </s:bind>  
                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="saveBtn" name="saveBtn" class="btn btn-primary btn-large" value="send">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="update.label"/>
                            </form:button>
                        </div>
                    </div>
                </form:form>
            </div><!--/.row-->
        </div><!--/.container-->
    </body>
    <content>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#estimatedReleaseDate').datetimepicker({format: "MM/DD/YYYY hh:mm"});
                $('#table-workOrders').DataTable();
            });
        </script>
    </content>
</html>
