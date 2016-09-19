<%-- 
    Document   : list
    Created on : Sep 15, 2016, 3:43:43 PM
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
        <title><s:message code="machine.list.label"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <legend><s:message code="machine.list.label"/></legend>
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>
                            <span class="glyphicon glyphicon-send"></span> <s:message code="${message}" arguments="${messageAttrs}" />
                        </strong>
                    </div>
                </c:if>
                <table id="table-machines" class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th><s:message code="user.id.label"/></th>
                            <th><s:message code="area.code.label"/></th>
                            <th><s:message code="user.name.label"/></th>
                            <th><s:message code="area.description.label"/></th>
                            <th><s:message code="machine.localization.label"/></th>
                            <th><s:message code="dateCreated.label"/></th>
                            <th><s:message code="lastUpdated.label"/></th>
                            <th><s:message code="status.label"/></th>
                            <th>
                                <s:message code="edit.label"/> <s:message code="machine.label"/>
                            </th>
                            <th>
                                <s:message code="delete.label"/> <s:message code="machine.label"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${machineList}" var="machine">
                            <tr>
                                <th>${machine.id}</th>                                
                                <th>${machine.code}</th>                                
                                <th>${machine.name}</th>                                
                                <th>${machine.description}</th>                                
                                <th>${machine.localization}</th>                                
                                <th>${machine.dateCreated}</th>                                
                                <th>${machine.lastUpdated}</th>                                
                                <th>${machine.status}</th>
                                <td><a class="btn btn-default" href="<c:url value="/machine/edit/${machine.id}"/>"><s:message code="edit.label"/> <span class="glyphicon glyphicon-edit"></span></a></td>                                
                                <td><a class="btn btn-default" href="<c:url value="/machine/delete/${machine.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div><!--/.row-->
        </div><!--/.container -->
    </body>
    <content>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#table-machines').DataTable();
            });
        </script>
    </content>
</html>
