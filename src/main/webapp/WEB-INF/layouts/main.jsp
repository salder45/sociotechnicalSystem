<%-- 
    Document   : main
    Created on : Jun 29, 2016, 10:02:27 AM
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
        <title><s:message code="title.label" /> - <sitemesh:write property="title"/></title>
        <link rel="stylesheet" href="<s:url value='/css/app.css' />">
        <link rel="stylesheet" href="<s:url value='/css/bootstrap.css' />">
    </head>
    <body>
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<s:url value='/'/>"><s:message code="title.label"/></a>
                    <div class="nav-collapse">
                        <sitemesh:write property="nav"/>

                        <c:url var="action" value='/logout'/>
                        <form:form id="logout-form" action="${action}" method="post">
                            <button class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-log-out"></span> <s:message code="logout.label"/>
                            </button>
                        </form:form>                        
                    </div>
                </div>
                <!-- header stuff -->
            </div><!-- /.container-->
        </div><!-- /.navbar -->


        <div class="container">
            <sitemesh:write property='body'/>
        </div> <!-- /container -->

    </body>
</html>
