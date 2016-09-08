<%-- 
    Document   : list
    Created on : Sep 7, 2016, 4:37:37 PM
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
        <title><s:message code="area.list.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="area.list.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <table id="table-areas" class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th><s:message code="user.id.label"/></th>
                            <th><s:message code="area.code.label"/></th>
                            <th><s:message code="user.name.label"/></th>
                            <th><s:message code="area.description.label"/></th>
                            <th><s:message code="dateCreated.label"/></th>
                            <th><s:message code="lastUpdated.label"/></th>
                            <th><s:message code="status.label"/></th>
                            <th>
                                <s:message code="edit.label"/> <s:message code="area.label"/>
                            </th>
                            <th>
                                <s:message code="delete.label"/> <s:message code="area.label"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${areaList}" var="area">
                            <tr>
                                <th><a href="<c:url value="/area/show/${area.id}"/>">${area.id}</a></th>
                                <th>${area.code}</th>
                                <th>${area.name}</th>
                                <th>${area.description}</th>
                                <th>${area.dateCreated}</th>
                                <th>${area.lastUpdated}</th>
                                <th>${area.status}</th>
                                <td><a class="btn btn-default" href="<c:url value="/area/edit/${area.id}"/>"><s:message code="edit.label"/> <span class="glyphicon glyphicon-edit"></span></a></td>                                
                                <td><a class="btn btn-default" href="<c:url value="/area/delete/${area.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a></td>                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <content>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#table-areas').DataTable();
            });
        </script>
    </content>
</html>
