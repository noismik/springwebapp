<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
   <div class="card-header">
      DTO 객체의 필드값을 checkbox로 세팅
   </div>
   <div class="card-body">
      <%--
      <form method="get" action="form3">
         <div>
            <c:forEach var="language" items="${languageList}" varStatus="status">
               <span>
                  <input type="checkbox" id="lang${status.count}" name="mlanguage" value="${language}"
                        <c:forEach var="temp" items="${member.mlanguage}">
                           <c:if test="${temp == language}">checked</c:if>
                        </c:forEach>/>
                     <label for="lang${status.count}">${language}</label>
                  </span>
              </c:forEach>
         </div>
         <button class="btn btn-info btn-sm">제출</button>
      </form>
      --%>
      
      <form:form modelAttribute="member" class="mt-3">
         <div>
            <form:checkboxes items="${languageList}" path="mlanguage" 
                             cssClass="mr-2" delimiter="&nbsp; | &nbsp;"/>
         </div>
         <button class="btn btn-info btn-sm">제출</button>
      </form:form>
      
      <form:form modelAttribute="member" class="mt-3">
         <div>
            <form:checkboxes items="${skillList}" path="mskill" 
                         itemValue="code" itemLabel="label" 
                         cssClass="mr-2" delimiter="&nbsp; | &nbsp;"/>
         </div>
         <button class="btn btn-info btn-sm">제출</button>
      </form:form>
   </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>