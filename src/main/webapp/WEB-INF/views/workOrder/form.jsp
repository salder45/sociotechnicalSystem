<%-- 
    Document   : form
    Created on : Sep 28, 2016, 9:59:46 AM
    Author     : eder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<c:choose>
    <c:when test="${param.type=='NEW'}">
        <c:url var="action" value="/workOrder/create"/>
    </c:when>
    <c:otherwise>
        <c:url var="action" value="/workOrder/update"/>        
    </c:otherwise>
</c:choose>
<form:form modelAttribute="workOrder" method="post" action="${action}" class="form-horizontal" data-parsley-validate="">
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
        <s:bind path="workOrder.estimatedReleaseDate">
            <div class="form-group">
                <s:message code="workorder.estimated.date.label" var="releaseDateLabel"/>
                <label for="estimatedReleaseDate" class="control-label col-xs-2">${releaseDateLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="estimatedReleaseDate" class="form-control" placeholder="${releaseDateLabel}" id="estimatedReleaseDate"/>
                </div>
            </div>
        </s:bind>  
        <s:bind path="workOrder.piecesNumber">
            <div class="form-group">
                <s:message code="workorder.pieces.label" var="piecesLabel"/>
                <label for="estimatedReleaseDate" class="control-label col-xs-2">${piecesLabel}</label>    
                <div class="col-xs-5">
                    <form:input path="piecesNumber" class="form-control" placeholder="${piecesLabel}"/>
                </div>
            </div>
        </s:bind>  
        <s:bind path="workOrder.seller.id">
            <div class="form-group">
                <s:message code="seller.label" var="sellerLabel"/>
                <label for="estimatedReleaseDate" class="control-label col-xs-2">${sellerLabel}</label>    
                <div class="col-xs-5">
                    <div id="seller-autocomplete">
                        <form:input id="sellerId" path="seller.id" class="form-control" placeholder="${sellerLabel}" autocomplete="off"/>
                    </div>
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
                    <a class="btn btn-danger" href="<c:url value="/workOrder/delete/${machine.id}"/>" onclick="return confirm('<s:message code="confirm.delete.message" />');" ><s:message code="delete.label"/> <span class="glyphicon glyphicon-trash"></span></a>
                    <a class="btn btn-default" href="<c:url value="/"/>"><s:message code="cancel.label"/> <span class="glyphicon glyphicon-erase"></span></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</form:form>
<c:url var="autoCompleteSeller" value="/seller/getSellerList"/>
<content>
    <script type="text/javascript">
        $(function () {
            $('#estimatedReleaseDate').datetimepicker();
            $('#sellerId').autocomplete({
                minLength:2,
                source:function(request,response){
                    $.ajax({
                        url:"${autoCompleteSeller}",
                        data:{term: request.term},
                        dataType: "json",
                        success:function(jsonReceived){
                            response ($.map(jsonReceived,function(item){
                                return {
                                    value:item.id,label:item.name
                                };
                            }));
                        }
                    });
                }
            });
        });
    </script>                                
</content>