<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:message code="title.label" /> - <s:message code="login.title.label"/></title>
        <!--CSS-->
        <link rel="stylesheet" href="<s:url value='/css/login.css' />">
        <link rel="stylesheet" href="<s:url value='/css/bootstrap.css' />">
    </head>
    <body>
        <div class="container">

            <h1 class="text-center login-title">DIASET</h1>    

            <c:url var="action" value='/authenticate'/>
            <form:form id="login-form" action="${action}" method="post" class="form-signin" autocomplete="off">
                <img class="profile-img" src="<s:url value="/img/avatar_2x.png"/>" alt="">
                <h1 class="text-center login-title"><s:message code="login.header.label"/></h1>    
                <input name="username" type="text" class="form-control" placeholder="<s:message code="user.username.label"/>" required autofocus>
                <input name="password" type="password" class="form-control" placeholder="<s:message code="user.password.label"/>" required>

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <s:message code="login.signin.label"/>
                </button>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="D14537">
                        <s:message code="login.rememberme.label"/>
                    </label>
                </div>
            </form:form>


            <!--Alerts-->
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger">
                    <a class="close" onclick="$('.alert').hide()">×</a>  
                    <strong><s:message code="error.label"/> : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.</strong>
                </div>
            </c:if>        
        </div><!--/.container-->
    </body>
</html>
