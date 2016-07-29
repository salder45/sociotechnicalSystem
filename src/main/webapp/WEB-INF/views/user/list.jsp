<%-- 
    Document   : list
    Created on : Jul 27, 2016, 6:22:58 PM
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
        <title><s:message code="user.list.label"/></title>
    </head>
    <body>
        <div class="container">   
            <div class="row">
                <legend><s:message code="user.list.label"/></legend>
                <table id="table-users" class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <s:message code="user.id.label"/>
                            </th>
                            <th>
                                <s:message code="user.username.label"/>
                            </th>
                            <th>
                                <s:message code="user.name.label"/>
                            </th>
                            <th>
                                <s:message code="user.lastname.label"/>
                            </th>
                            <th>
                                <s:message code="user.mothersmaidenname.label"/>
                            </th>
                            <th>
                                <s:message code="email.label"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${usersList}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.name}</td>
                                <td>${user.lastName}</td>
                                <td>${user.mothersMaidenName}</td>
                                <td>${user.email}</td>
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
                $('#table-users').DataTable();
            });
        </script>
    </content>
</html>
