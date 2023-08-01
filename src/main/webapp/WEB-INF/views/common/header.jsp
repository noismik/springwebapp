<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="icon" href="${pageContext.request.contextPath}/resources/image/logo-spring.png">
      <title>안뇽 하이 방가방가</title>
      
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
      <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
      <style>
		.btn-12{
		  position: relative;
		  right: 20px;
		  bottom: 20px;
		  border:none;
		  width: 130px;
		  height: 40px;
		  line-height: 40px;
		  -webkit-perspective: 230px;
		  perspective: 230px;
		}
		.btn-12 span {
		  display: block;
		  position: absolute;
		  width: 130px;
		  height: 40px;
		  border: 2px solid #000;
		  margin:0;
		  text-align: center;
		  -webkit-box-sizing: border-box;
		  -moz-box-sizing: border-box;
		  box-sizing: border-box;
		  -webkit-transition: all .3s;
		  transition: all .3s;
		}
		.btn-12 span:nth-child(1) {
		  box-shadow:
		   -7px -7px 20px 0px #fff9,
		   -4px -4px 5px 0px #fff9,
		   7px 7px 20px 0px #0002,
		   4px 4px 5px 0px #0001;
		  -webkit-transform: rotateX(90deg);
		  -moz-transform: rotateX(90deg);
		  transform: rotateX(90deg);
		  -webkit-transform-origin: 50% 50% -20px;
		  -moz-transform-origin: 50% 50% -20px;
		  transform-origin: 50% 50% -20px;
		}
		.btn-12 span:nth-child(2) {
		  -webkit-transform: rotateX(0deg);
		  -moz-transform: rotateX(0deg);
		  transform: rotateX(0deg);
		  -webkit-transform-origin: 50% 50% -20px;
		  -moz-transform-origin: 50% 50% -20px;
		  transform-origin: 50% 50% -20px;
		}
		.btn-12:hover span:nth-child(1) {
		  -webkit-transform: rotateX(0deg);
		  -moz-transform: rotateX(0deg);
		  transform: rotateX(0deg);
		}
		.btn-12:hover span:nth-child(2) {
		  background: #e0e5ec;
		  color: #e0e5ec;
		  -webkit-transform: rotateX(-90deg);
		  -moz-transform: rotateX(-90deg);
		  transform: rotateX(-90deg);
		}
</style>
   </head>
   
   <body>
         <div class="d-flex flex-column vh-100">
         <nav class="navbar navbar-dark bg-dark text-white font-weight-bold">
            <a class="navbar-brand" href="${pageContext.request.contextPath}"> 
            <!--
               ${pageContext.request.contextPath}는 런타임시에 ContextPath를 리턴
               ContextPath: /servlet_jsp
              -->
                <img src="${pageContext.request.contextPath}/resources/image/logo-spring.png" width="30" height="30" class="d-inline-block align-top">
               		전자정부프레임워크
            </a>
            <div>
            	<%--Ch08 --%>
            	<%--
               <div>
              		 <c:if test="${login == null}">
                    <a href="${pageContext.request.contextPath}/ch08/content" class="btn btn-success btn-sm">로그인</a>
                     </c:if>
                     <c:if test="${login != null}">
                     <img src="${pageContext.request.contextPath}/resources/image/face/${login.mid}.png" width="30" height="30"/>
                    <a href="${pageContext.request.contextPath}/ch08/logout" class="btn btn-success btn-sm">로그아웃</a>
                    </c:if>
                     
               </div>
                --%>
                
                <%--Ch13 --%>
                 <div>
              		 <c:if test="${ch13Login == null}">
                 	    <a href="${pageContext.request.contextPath}/ch13/content" class="btn btn-success btn-sm">로그인</a>
                     </c:if>
                     <c:if test="${ch13Login != null}">
                   	 	<a href="${pageContext.request.contextPath}/ch13/logout" class="btn btn-success btn-sm">로그아웃</a>
                    </c:if>
                     
               </div>
            </div>
         </nav>
   
         <div class="flex-grow-1 container-fluid">
            <div class="row h-100">
               <div class="col-md-4 p-3 bg-dark">
                  <div class="h-100 d-flex flex-column">
                     <div class="flex-grow-1" style="height: 0px; overflow-y: auto; overflow-x: hidden;">
                        <!-- 
                  1) header.jsp의 소스코드를 복사해서 붙여넣기
                  2)  절대 경로 /는 웹애플리케이션의 로컬 루트 (WebContent 폴더)
                   -->
                        <%@ include file="/WEB-INF/views/common/menu.jsp" %>
                     </div>
                  </div>
               </div>
   
               <div class="col-md-8 p-3">
                  <div class=" h-100 d-flex flex-column">
                     <div class="flex-grow-1 overflow-auto pr-3" style="height: 0px">