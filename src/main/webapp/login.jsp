<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <h1 class="text-center login-title"><s:message code="login.header.label"/></h1>
                    <div class="account-wall">
                        <img class="profile-img" src="<s:url value="/img/avatar_2x.png"/>" alt="">
                        <form class="form-signin">
                            <input type="text" class="form-control" placeholder="<s:message code="user.username.label"/>" required autofocus>
                            <input type="password" class="form-control" placeholder="<s:message code="user.password.label"/>" required>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">
                                <s:message code="login.signin.label"/>
                            </button>
                            <label class="checkbox pull-left">
                                <input type="checkbox" value="D14537">
                                <s:message code="login.rememberme.label"/>
                            </label>

                        </form>
                    </div>
                </div>
            </div>
        </div><!--/.container-->
    </body>
</html>
