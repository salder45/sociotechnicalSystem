<%-- 
    Document   : edit
    Created on : Jul 20, 2016, 8:46:23 AM
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
        <title><s:message code="user.edit.label" /></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <!-- internal menu -->
                <!--Page title -->
                <legend><s:message code="user.edit.label" /></legend>
                <!-- form-->
                <c:url var="action" value="/user/update"/>
                <form:form modelAttribute="user" method="post" action="${action}" class="form-horizontal">
                    <!-- errors-->
                    <form:errors path="*">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <span class="glyphicon glyphicon-alert"></span>
                            <strong>
                                <c:forEach items="${messages}" var="message">
                                    <p>${message}</p>
                                </c:forEach>
                            </strong>
                        </div>
                    </form:errors>
                    <!--fields-->
                    <form:hidden path="id" />
                    <form:hidden path="version" />
                    <fieldset>
                        <s:bind path="user.name">
                            <div class="form-group">
                                <label for="name" class="control-label col-xs-2"><s:message code="user.name.label"/></label>    
                                <div class="col-xs-5">
                                    <form:input path="name" class="form-control"/>
                                </div>                                
                            </div>
                        </s:bind>
                    </fieldset>                        
                </form:form>
            </div><!--/.row-->
        </div><!--/.container-->


        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <small><i></i>Add alerts if form ok... success, else error.</i></small>
                    <div class="alert alert-success"><strong><span class="glyphicon glyphicon-send"></span> Success! Message sent. (If form ok!)</strong></div>	  
                    <div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Error! Please check the inputs. (If form error!)</strong></div>
                </div>
                <form role="form" action="" method="post" >
                    <div class="col-lg-6">
                        <div class="well well-sm"><strong><i class="glyphicon glyphicon-ok form-control-feedback"></i> Required Field</strong></div>
                        <div class="form-group">
                            <label for="InputName">Your Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="InputName" id="InputName" placeholder="Enter Name" required>
                                <span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span></div>
                        </div>
                        <div class="form-group">
                            <label for="InputEmail">Your Email</label>
                            <div class="input-group">
                                <input type="email" class="form-control" id="InputEmail" name="InputEmail" placeholder="Enter Email" required  >
                                <span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span></div>
                        </div>
                        <div class="form-group">
                            <label for="InputMessage">Message</label>
                            <div class="input-group"
                                 >
                                <textarea name="InputMessage" id="InputMessage" class="form-control" rows="5" required></textarea>
                                <span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span></div>
                        </div>
                        <div class="form-group">
                            <label for="InputReal">What is 4+3? (Simple Spam Checker)</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="InputReal" id="InputReal" required>
                                <span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span></div>
                        </div>
                        <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
                    </div>
                </form>
                <hr class="featurette-divider hidden-lg">
                <div class="col-lg-5 col-md-push-1">
                    <address>
                        <h3>Office Location</h3>
                        <p class="lead"><a href="https://www.google.com/maps/preview?ie=UTF-8&q=The+Pentagon&fb=1&gl=us&hq=1400+Defense+Pentagon+Washington,+DC+20301-1400&cid=12647181945379443503&ei=qmYfU4H8LoL2oATa0IHIBg&ved=0CKwBEPwSMAo&safe=on">The Pentagon<br>
                                Washington, DC 20301</a><br>
                            Phone: XXX-XXX-XXXX<br>
                            Fax: XXX-XXX-YYYY</p>
                    </address>
                </div>
            </div>

        </div>
        <div class="container">
            <div class="row">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="inputEmail" class="control-label col-xs-2">First Name</label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="control-label col-xs-2">Last Name</label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" id="inputPassword" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="control-label col-xs-2">Username</label>
                        <div class="col-xs-5">
                            <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="control-label col-xs-2">Password</label>
                        <div class="col-xs-5">
                            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="control-label col-xs-2">Confirm Password</label>
                        <div class="col-xs-5">
                            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <button type="submit" class="btn btn-primary">Add Admin</button>
                        </div>
                    </div>
            </div>
        </div>
    </form>
</body>
</html>
