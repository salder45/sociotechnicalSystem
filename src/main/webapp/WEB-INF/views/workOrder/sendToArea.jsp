<%-- 
    Document   : show
    Created on : Oct 4, 2016, 12:26:01 PM
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
        <title><s:message code="send.label"/> - <s:message code="workorder.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="send.label"/> - <s:message code="workorder.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <c:url var="action" value="/workOrder/sendWorkOrder"/>
                <form:form modelAttribute="workOrder" method="post" action="${action}" class="form-horizontal" data-parsley-validate="">
                    <form:errors path="*">
                        <c:forEach items="${messages}" var="message">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <span class="glyphicon glyphicon-alert"></span> <strong>${message}</strong>
                            </div>
                        </c:forEach>                        
                    </form:errors>
                    <form:hidden path="id" />
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
                    <s:bind path="workOrder.badPieces">
                        <div class="form-group">
                            <s:message code="scrap.label" var="scrapLabel"/>
                            <label for="scrap" class="control-label col-xs-2">${scrapLabel}</label>    
                            <div class="col-xs-5">
                                <form:input path="badPieces" class="form-control" max="${workOrder.piecesNumber}"/>
                            </div>                                
                        </div>
                    </s:bind>
                            
                    <s:bind path="workOrder.areaActual.id">
                        <div class="form-group">
                            <s:message code="area.label" var="areaLabel"/>
                            <label for="name" class="control-label col-xs-2">${areaLabel}</label>    
                            <div class="col-xs-5">
                                <form:select path="areaActual.id" items="${areaList}" itemValue="id" itemLabel="name" class="form-control"/>
                            </div>                                
                        </div>
                    </s:bind>

                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="saveBtn" name="saveBtn" class="btn btn-primary btn-large" value="send">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="send.label"/>
                            </form:button>
                        </div>
                    </div>
                </form:form>
                <!--Show WorkOrderData-->
            </div><!--/.row-->
        </div><!--/.container-->
    </body>
    <content>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#table-workOrders').DataTable();
                 $('#workOrder').parsley();
            });
        </script>
    </content>
</html>
