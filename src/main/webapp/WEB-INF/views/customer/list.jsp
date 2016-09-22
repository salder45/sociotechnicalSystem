<%-- 
    Document   : list
    Created on : Sep 22, 2016, 12:09:53 PM
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
        <title><s:message code="customer.list.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="customer.list.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <table id="table-customers" class="table table-striped table-hover">
                     <thead>
                        <tr>
                            <th><s:message code="user.id.label"/></th>
                            <th><s:message code="user.name.label"/></th>
                            <th><s:message code="dateCreated.label"/></th>
                            <th><s:message code="lastUpdated.label"/></th>
                            <th><s:message code="status.label"/></th>
                            <th>
                                <s:message code="edit.label"/> <s:message code="customer.label"/>
                            </th>
                            <th>
                                <s:message code="delete.label"/> <s:message code="customer.label"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${customerList}" var="customer">
                            <tr>
                                <th>${customer.id}</th> 
                                <th>${customer.name}</th> 
                                <th>${customer.dateCreated}</th> 
                                <th>${customer.lastUpdated}</th> 
                                <th>${customer.status}</th> 
                                <td><a class="btn btn-default" href="<c:url value="/customer/edit/${customer.id}"/>"><s:message code="edit.label"/> <span class="glyphicon glyphicon-edit"></span></a></td>                                
                                <td><a class="btn btn-default" href="<c:url value="/customer/delete/${customer.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a></td>
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
                $('#table-customers').DataTable();
            });
        </script>
    </content>
</html>
