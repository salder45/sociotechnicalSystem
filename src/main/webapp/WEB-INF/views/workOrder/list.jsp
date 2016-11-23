<%-- 
    Document   : list
    Created on : Oct 4, 2016, 12:25:50 PM
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
        <title><s:message code="workorder.list.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <c:choose>
                    <c:when test="${area.name=='Ventas'}">
                        <s:message code="area.sales.label" var="areaName"/>
                    </c:when>
                    <c:when test="${area.name=='Planeacion'}">
                        <s:message code="area.planning.label" var="areaName"/>
                    </c:when>
                    <c:when test="${area.name=='Produccion'}">
                        <s:message code="area.production.label" var="areaName"/>
                    </c:when>
                    <c:when test="${area.name=='Calidad'}">
                        <s:message code="area.quality.label" var="areaName"/>
                    </c:when>
                    <c:otherwise>                        
                        <s:message code="error.label" var="areaName"/>
                    </c:otherwise>
                </c:choose>
                <legend>${areaName} - <s:message code="workorder.list.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <table id="table-workOrders" class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th><s:message code="area.code.label"/></th>
                            <th><s:message code="workorder.estimated.date.label"/></th>
                            <th><s:message code="workorder.pieces.label"/></th>
                            <th><s:message code="seller.label"/></th>
                            <th><s:message code="customer.label"/></th>
                            <th><s:message code="send.label"/> <s:message code="workorder.label"/> </th>
                                <sec:authorize access="hasRole('ROLE_PLANNING')">
                                    <c:choose>
                                        <c:when test="${area.name=='Planeacion'}">
                                        <th><s:message code="send.label"/> <s:message code="workorder.label"/> </th>
                                        </c:when>
                                    </c:choose>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_PRODUCTION')">
                                    <c:choose>
                                        <c:when test="${area.name=='Produccion'}">
                                        <th><s:message code="area.production.label"/> <s:message code="workorder.label"/> </th>
                                        </c:when>
                                    </c:choose>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_QUALITY')">
                                    <c:choose>
                                        <c:when test="${area.name=='Calidad'}">
                                        <th><s:message code="area.quality.label"/> <s:message code="workorder.label"/> </th>
                                        <th><s:message code="finish.label"/> <s:message code="workorder.label"/> </th>
                                        </c:when>
                                    </c:choose>
                                </sec:authorize>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${workOrderList}" var="workOrder">
                            <tr>
                                <c:choose>
                                    <c:when test="${area.name=='Ventas'}">
                                        <th>
                                            <a href="<c:url value="/workOrder/addWorkOrderDetails/${workOrder.id}"/>">${workOrder.code}</a>
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>${workOrder.code}</th>                                        
                                        </c:otherwise>
                                    </c:choose>
                                <th>${workOrder.estimatedReleaseDate}</th>
                                <th>${workOrder.piecesNumber}</th>
                                <th>${workOrder.seller.name}</th>
                                <th>${workOrder.customer.name}</th>
                                <th><a class="btn btn-default" href="<c:url value="/workOrder/sendWorkOrderToArea/${workOrder.id}"/>"><s:message code="send.label"/> <span class="glyphicon glyphicon-send"></span></a></th>
                                        <sec:authorize access="hasRole('ROLE_PLANNING')">
                                            <c:choose>
                                                <c:when test="${area.name=='Planeacion'}">
                                            <th><a class="btn btn-default" href="<c:url value="/workOrder/loadEstimatedReleaseDate/${workOrder.id}"/>"><s:message code="workorder.set.estimated.date.label"/> <span class="glyphicon glyphicon-calendar"></span></a></th>
                                                </c:when>
                                            </c:choose>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ROLE_PRODUCTION')">
                                            <c:choose>
                                                <c:when test="${area.name=='Produccion'}">
                                            <!--CHECK IF ORDER IS BEING WORKING ON-->
                                            <c:choose>
                                                <c:when test="${workOrder.status=='W_AT'}">
                                                    <!--CHECK IF ORDER IS BEING WORKING ON-->
                                                    <th><a class="btn btn-default" href="<c:url value="/workOrder/selectMachineToPullOut/${workOrder.id}"/>"><s:message code="processing.label"/> <span class="glyphicon glyphicon-stop"></span></a></th>
                                                        </c:when>
                                                        <c:otherwise>
                                                    <th><a class="btn btn-default" href="<c:url value="/workOrder/selectMachineToPutIn/${workOrder.id}"/>"><s:message code="process.label"/> <span class="glyphicon glyphicon-play"></span></a></th>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                            </c:choose>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ROLE_QUALITY')">
                                            <c:choose>
                                                <c:when test="${area.name=='Calidad'}">
                                            <!--CHECK IF ORDER IS BEING WORKING ON-->
                                            <c:choose>
                                                <c:when test="${workOrder.status=='W_AT'}">
                                                    <!--CHECK IF ORDER IS BEING WORKING ON-->
                                                    <th><a class="btn btn-default" href="<c:url value="/workOrder/selectMachineToPullOut/${workOrder.id}"/>"><s:message code="processing.label"/> <span class="glyphicon glyphicon-stop"></span></a></th>
                                                        </c:when>
                                                        <c:otherwise>
                                                    <th><a class="btn btn-default" href="<c:url value="/workOrder/selectMachineToPutIn/${workOrder.id}"/>"><s:message code="process.label"/> <span class="glyphicon glyphicon-play"></span></a></th>
                                                        </c:otherwise>
                                                    </c:choose>
                                            <th><a class="btn btn-default" href="<c:url value="/workOrder/loadClose/${workOrder.id}"/>"><s:message code="finish.label"/> <span class="glyphicon glyphicon-lock"></span></a></th>
                                                </c:when>
                                            </c:choose>
                                        </sec:authorize>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div><!--/.container-->
    </body>
    <content>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#table-workOrders').DataTable();
            });
        </script>
    </content>
</html>
