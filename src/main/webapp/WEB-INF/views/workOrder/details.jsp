<%-- 
    Document   : details
    Created on : Oct 5, 2016, 8:59:54 AM
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
        <title><s:message code="workorder.details.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="workorder.details.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <c:url var="action" value="/workOrder/addBatch"/>
                <form:form modelAttribute="batch" method="post" action="${action}" class="form-horizontal">
                    <form:hidden path="workOrder.id" />
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

                    <legend><s:message code="batch.label"/></legend>
                    <s:bind path="batch.description">
                        <div class="form-group">
                            <s:message code="area.description.label" var="descriptionLabel"/>
                            <label for="name" class="control-label col-xs-2">${descriptionLabel}</label>    
                            <div class="col-xs-5">
                                <form:input path="description" class="form-control" placeholder="${descriptionLabel}"/>
                            </div>                                
                        </div>
                    </s:bind>
                    <s:bind path="batch.batchPieces">
                        <div class="form-group">
                            <s:message code="batch.pieces.label" var="piecesBatchLabel"/>
                            <label for="name" class="control-label col-xs-2">${piecesBatchLabel}</label>    
                            <div class="col-xs-5">
                                <form:input path="batchPieces" class="form-control" placeholder="${piecesBatchLabel}"/>
                            </div>                                
                        </div>
                    </s:bind>
                    <s:bind path="batch.existDraw">
                        <div class="form-group">
                            <s:message code="batch.draw.exist.label" var="existDrawLabel"/>
                            <label for="existDraw" class="control-label col-xs-2">${existDrawLabel}</label>    
                            <div class="col-xs-5">
                                <form:checkbox path="existDraw" cssClass="" />
                            </div>                                
                        </div>
                    </s:bind>
                    <s:bind path="batch.sharpening">
                        <div class="form-group">
                            <s:message code="batch.sharpening.label" var="sharpeningLabel"/>
                            <label for="sharpening" class="control-label col-xs-2">${sharpeningLabel}</label>    
                            <div class="col-xs-5">
                                <form:checkbox path="sharpening" cssClass="" />
                            </div>                                
                        </div>
                    </s:bind>
                    <s:bind path="batch.coveringRequired">
                        <div class="form-group">
                            <s:message code="batch.covering.label" var="coveringLabel"/>
                            <label for="existDraw" class="control-label col-xs-2">${coveringLabel}</label>    
                            <div class="col-xs-5">
                                <form:checkbox path="coveringRequired" cssClass="" />
                            </div>                                
                        </div>
                    </s:bind>

                    <div class="form-group">
                        <div class="col-xs-10">
                            <form:button id="editBtn" name="editBtn" class="btn btn-primary btn-large" value="edit">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <s:message code="save.label"/>
                            </form:button>
                        </div>
                    </div>

                    <legend><s:message code="batch.list.label"/></legend>
                    <table id="table-batchs" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th><s:message code="area.description.label"/></th>
                                <th><s:message code="batch.pieces.label"/></th>
                                <th><s:message code="batch.draw.exist.label"/></th>
                                <th><s:message code="batch.sharpening.label"/></th>
                                <th><s:message code="batch.covering.label"/></th>
                                <th>
                                    <s:message code="delete.label"/> <s:message code="batch.label"/>
                                </th>
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
                                    <td><a class="btn btn-default" href="<c:url value="/workOrder/removeBatch/${oneBatch.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form:form>
            </div><!--/.row-->
        </div><!--/.container-->            
    </body>
    <content>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#table-batchs').DataTable();
            });
        </script>
    </content>
</html>
