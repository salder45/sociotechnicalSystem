<%-- 
    Document   : secureless
    Created on : Jul 13, 2016, 6:30:52 PM
    Author     : laboratoriointerface
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:message code="title.label" /> - <sitemesh:write property="title"/></title>
        <link rel="stylesheet" href="<s:url value='/css/bootstrap.css' />">
    <sitemesh:write property='head'/>
    <link rel="stylesheet" href="<s:url value='/css/app.css' />">
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
            </div>
            <!-- header stuff -->
        </div><!-- /.container-->
    </div><!-- /.navbar -->
    <!--write header with sitemesh-->

    <div class="container">
        <sitemesh:write property='body'/>
    </div> <!-- /container -->
    <script src="<c:url value='/js/jquery-1.9.1.js' />"></script>
    <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/js/app.js' />"></script>
<sitemesh:write property="content"/>

</body>
</html>
