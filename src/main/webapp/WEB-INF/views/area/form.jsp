<%-- 
    Document   : form
    Created on : Sep 6, 2016, 6:24:06 PM
    Author     : laboratoriointerface
--%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:choose>
    <c:when test="${param.type=='NEW'}">
        <c:url var="action" value="/area/create"/>
    </c:when>
    <c:otherwise>
        <c:url var="action" value="/area/update"/>        
    </c:otherwise>
</c:choose>
<form:form modelAttribute="area" method="post" action="${action}" class="form-horizontal" data-parsley-validate="">
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
            </c:when>
            <c:otherwise>
                <form:hidden path="id" />
                <form:hidden path="version" />
                <form:hidden path="code" />
                <form:hidden path="dateCreated" />
                <form:hidden path="lastUpdated" />
                <form:hidden path="status" />
            </c:otherwise>
        </c:choose>
        <!--CHECK VALUES-->
        <s:bind path="area.name">
            <div class="form-group">
                <s:message code="user.name.label" var="nameLabel"/>
                <label for="name" class="control-label col-xs-2">${nameLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="name" class="form-control" placeholder="${nameLabel}"/>
                </div>                                
            </div>
        </s:bind>               
        <s:bind path="area.description">
            <div class="form-group">
                <s:message code="area.description.label" var="descriptionLabel"/>
                <label for="name" class="control-label col-xs-2">${descriptionLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="description" class="form-control" placeholder="${descriptionLabel}"/>
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
                    <a class="btn btn-danger" href="<c:url value="/area/delete/${user.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                    <a class="btn btn-default" href="<c:url value="/"/>"><s:message code="cancel.label"/> <span class="glyphicon glyphicon-erase"></span></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</form:form>
