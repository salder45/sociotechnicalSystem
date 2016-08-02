<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:choose>
    <c:when test="${param.type=='NEW'}">
        <c:url var="action" value="/user/create"/>
    </c:when>
    <c:otherwise>
        <c:url var="action" value="/user/update"/>        
    </c:otherwise>
</c:choose>
<form:form modelAttribute="user" method="post" action="${action}" class="form-horizontal" data-parsley-validate="">
    <form:errors path="*">
        <c:forEach items="${messages}" var="message">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <span class="glyphicon glyphicon-alert"></span> <strong>${message}</strong>
            </div>
        </c:forEach>                        
    </form:errors>
    <!--Check id,version,username-->
    <fieldset>
        <c:choose>
            <c:when test="${param.type=='NEW'}">
                <!--SHOW FIELD-->
                <s:bind path="user.username">
                    <div class="form-group">
                        <s:message code="user.username.label" var="usernameLabel"/>
                        <label for="username" class="control-label col-xs-2">${usernameLabel}</label>    
                        <div class="col-xs-5">
                            <form:input path="username" class="form-control" placeholder="${usernameLabel}"/>
                        </div>                                
                    </div>
                </s:bind>
                <form:hidden path="enabled" />
            </c:when>
            <c:otherwise>
                <!--HIDDEN FIELD-->
                <form:hidden path="id" />
                <form:hidden path="version" />
                <form:hidden path="username" />            
            </c:otherwise>
        </c:choose>

        <s:bind path="user.name">
            <div class="form-group">
                <s:message code="user.name.label" var="nameLabel"/>
                <label for="name" class="control-label col-xs-2">${nameLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="name" class="form-control" placeholder="${nameLabel}"/>
                </div>                                
            </div>
        </s:bind>
        <s:bind path="user.lastName">
            <div class="form-group">
                <s:message code="user.lastname.label" var="lastNameLabel"/>
                <label for="lastName" class="control-label col-xs-2">${lastNameLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="lastName" class="form-control" placeholder="${lastNameLabel}"/>
                </div>                                
            </div>
        </s:bind>
        <s:bind path="user.mothersMaidenName">
            <div class="form-group">
                <s:message code="user.mothersmaidenname.label" var="maidenNameLabel"/>
                <label for="mothersMaidenName" class="control-label col-xs-2">${maidenNameLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="mothersMaidenName" class="form-control" placeholder="${maidenNameLabel}"/>
                </div>                                
            </div>
        </s:bind>
        <s:bind path="user.email">
            <div class="form-group">
                <s:message code="email.label" var="emailLabel"/>
                <label for="email" class="control-label col-xs-2">${emailLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="email" class="form-control" placeholder="${emailLabel}"/>                                    
                </div>                                
            </div>
        </s:bind>
        <!--PASSWORD -->
        <s:bind path="user.password">
            <div class="form-group">
                <s:message code="user.password.label" var="passwordLabel"/>
                <label for="password" class="control-label col-xs-2">${passwordLabel}</label>    
                <div class="col-xs-5">
                    <form:password path="password" showPassword="true" class="form-control" placeholder="${passwordLabel}" data-parsley-equalto="#confirmPassword"/>
                </div>
            </div>
        </s:bind>
        <s:bind path="user.confirmPassword">
            <div class="form-group">
                <s:message code="user.password.confirm.label" var="confirmPasswordLabel"/>
                <label for="confirmPassword" class="control-label col-xs-2">${confirmPasswordLabel}</label>    
                <div class="col-xs-5">
                    <form:password path="confirmPassword" showPassword="true" class="form-control" placeholder="${confirmPasswordLabel}" data-parsley-equalto="#password"/>
                </div>
            </div>
        </s:bind>
        <s:bind path="user.roles">
            <div class="form-group">
                <s:message code="user.roles.label" var="rolesLabel"/>
                <label for="roles" class="control-label col-xs-2">${rolesLabel}</label>
                <div class="col-xs-5">
                    <form:checkboxes path="roles" items="${rolesList}" itemValue="authority" itemLabel="authority" cssClass="" element="div"/>
                </div>
            </div>
        </s:bind>
    </fieldset>

    <c:choose>
        <c:when test="${param.type=='NEW'}">
            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <form:button id="saveBtn" name="saveBtn" class="btn btn-primary btn-large" value="save">
                        <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> <s:message code="save.label"/>
                    </form:button>
                    <a class="btn btn-default" href="<c:url value="/"/>"><s:message code="cancel.label"/> <span class="glyphicon glyphicon-erase"></span></a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <form:button id="updateBtn" name="updateBtn" class="btn btn-primary btn-large" value="update">
                        <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> <s:message code="update.label"/>
                    </form:button>
                    <a class="btn btn-danger" href="<c:url value="/user/delete/${user.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                    <a class="btn btn-default" href="<c:url value="/"/>"><s:message code="cancel.label"/> <span class="glyphicon glyphicon-erase"></span></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>


</form:form>

<content>
    <script type="text/javascript">
        $("#user").parsley();
    </script>
</content>

