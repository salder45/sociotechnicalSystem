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
        <title>404 Not Found></title>
        <!--CSS-->
        <link rel="stylesheet" href="<s:url value='/css/error.css' />">
        <link rel="stylesheet" href="<s:url value='/css/bootstrap.css' />">
        
        
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="error-template">
                    <h1>404 Not Found</h1>
                    <div class="error-details">
                        <h4>Ocurrió un error, la página solicitada no ha sido encontrada.</h4>
                    </div>
                    <div class="error-actions">
                        <a href="<s:url value='/' />" class="btn btn-primary btn-lg">Ir a inicio</a>                            
                    </div>                    
                </div>
            </div>
        </div>
    </body>
</html>
