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
        <link rel="stylesheet" href="<s:url value='/css/dataTables.bootstrap.css' />">
        <link rel="stylesheet" href="<s:url value='/css/fileinput.min.css' />">
        <link rel="stylesheet" href="<s:url value='/css/bootstrap-datetimepicker.min.css' />">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<s:url value='/'/>"><s:message code="title.label"/></a>
                </div><!--/.navbar-header-->
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><s:message code="menu.admin.label"/> <span class="caret"></span></a>
                                <ul class="dropdown-menu multi-level">
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="#"><s:message code="user.label"/></a>
                                        <ul class="dropdown-menu">
                                            <li><a tabindex="-1" href="<c:url value="/user/new"/>"><s:message code="user.new.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/user/profile"/>"><s:message code="profile.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/user/list"/>"><s:message code="user.list.label"/></a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="#"><s:message code="area.label"/></a>
                                        <ul class="dropdown-menu">
                                            <li><a tabindex="-1" href="<c:url value="/area/new"/>"><s:message code="area.new.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/area/list"/>"><s:message code="area.list.label"/></a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="#"><s:message code="machine.label"/></a>
                                        <ul class="dropdown-menu">
                                            <li><a tabindex="-1" href="<c:url value="/machine/new"/>"><s:message code="machine.new.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/machine/list"/>"><s:message code="machine.list.label"/></a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="#"><s:message code="seller.label"/></a>
                                        <ul class="dropdown-menu">
                                            <li><a tabindex="-1" href="<c:url value="/seller/new"/>"><s:message code="seller.new.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/seller/list"/>"><s:message code="seller.list.label"/></a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="#"><s:message code="customer.label"/></a>
                                        <ul class="dropdown-menu">
                                            <li><a tabindex="-1" href="<c:url value="/customer/new"/>"><s:message code="customer.new.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/customer/list"/>"><s:message code="customer.list.label"/></a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="#"><s:message code="workorder.label"/></a>
                                        <ul class="dropdown-menu">
                                            <li><a tabindex="-1" href="<c:url value="/workOrder/new"/>"><s:message code="workorder.new.label"/></a></li>
                                            <li><a tabindex="-1" href="<c:url value="/workOrder/list"/>"><s:message code="workorder.list.label"/></a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>                           
                        </sec:authorize>
                        <!--In nav code must be <li></li>-->
                        <sitemesh:write property="nav"/>                        
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <c:url var="actionProfile" value='/user/profile'/>
                            <form:form id="logout-form" class="navbar-form" action="${actionProfile}" method="post">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-user"></span> <s:message code="profile.label"/>
                                </button>
                            </form:form>                            
                        </li>
                        <li>
                            <c:url var="action" value='/logout'/>
                            <form:form id="logout-form" class="navbar-form" action="${action}" method="post">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-log-out"></span> <s:message code="logout.label"/>
                                </button>
                            </form:form>                            
                        </li>
                    </ul>               

                </div><!--/.navbar-collapse-->
            </div><!--/.container-->            
        </nav>

        <div class="container">
            <sitemesh:write property='body'/>
        </div> <!-- /container -->
        <script src="<c:url value='/js/jquery-1.9.1.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>
        <script src="<c:url value='/js/parsley/parsley.js' />"></script>
        <script src="<c:url value='/js/parsley/i18n/en.js' />"></script>
        <script src="<c:url value='/js/parsley/i18n/es.js' />"></script>
        <script src="<c:url value='/js/app.js' />"></script>
        <script src="<c:url value='/js/jquery.dataTables.js' />"></script>
        <script src="<c:url value='/js/dataTables.bootstrap.js' />"></script>
        <script src="<c:url value='/js/fileinput.min.js' />"></script>
        <script src="<c:url value='/js/scannerPlugin/jquery.scannerdetection.js' />"></script>
        <script src="<c:url value='/js/moment.js' />"></script>
        <script src="<c:url value='/js/bootstrap-datetimepicker.min.js' />"></script>
        <script src="<c:url value='/js/jquery-ui-1.9.2.custom.min.js' />"></script>
    <sitemesh:write property="content"/>

</body>
</html>
