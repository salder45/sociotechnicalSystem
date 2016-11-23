<%-- 
    Document   : closedOrders
    Created on : Nov 23, 2016, 4:03:10 PM
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
        <title><s:message code="workorder.closed.list.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="workorder.closed.list.label"/></legend>
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${workOrderList}" var="workOrder">
                            <tr>
                                <th>${workOrder.code}</th>
                                <th>${workOrder.estimatedReleaseDate}</th>
                                <th>${workOrder.piecesNumber}</th>
                                <th>${workOrder.seller.name}</th>
                                <th>${workOrder.customer.name}</th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div><!--/.row-->
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
