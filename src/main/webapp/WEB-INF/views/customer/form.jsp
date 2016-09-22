<%-- 
    Document   : form
    Created on : Sep 22, 2016, 11:03:01 AM
    Author     : laboratoriointerface
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:choose>
    <c:when test="${param.type=='NEW'}">
        <c:url var="action" value="/customer/create"/>
    </c:when>
    <c:otherwise>
        <c:url var="action" value="/customer/update"/>        
    </c:otherwise>
</c:choose>
<form:form modelAttribute="customer" method="post" action="${action}" class="form-horizontal" data-parsley-validate="">
    <!--Errors-->
    <form:errors path="*">
        <c:forEach items="${messages}" var="message">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <span class="glyphicon glyphicon-alert"></span> <strong>${message}</strong>
            </div>
        </c:forEach>                        
    </form:errors>
    <!--Fields-->
    <fieldset>
        <c:choose>
            <c:when test="${param.type=='NEW'}">
            </c:when>
            <c:otherwise>
                <form:hidden path="id" />
                <form:hidden path="version" />
                <form:hidden path="dateCreated" />
                <form:hidden path="lastUpdated" />
                <form:hidden path="status" />
            </c:otherwise>
        </c:choose>
        <s:bind path="customer.name">
            <div class="form-group">
                <s:message code="user.name.label" var="nameLabel"/>
                <label for="name" class="control-label col-xs-2">${nameLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="name" class="form-control" placeholder="${nameLabel}"/>
                </div>                                
            </div>
        </s:bind> 
    </fieldset>    

    <!--Buttons-->
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
                    <a class="btn btn-danger" href="<c:url value="/customer/delete/${customer.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                    <a class="btn btn-default" href="<c:url value="/"/>"><s:message code="cancel.label"/> <span class="glyphicon glyphicon-erase"></span></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</form:form>
