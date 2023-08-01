<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		AOP(관점 지향 프로그래밍)		
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				Advice 테스트	
			</div>
			<div class="card-body">
				<div><a href="before" class="btn-12 btn-sm"><span>@Before 테스트</span><span>눌러주세용ㅇ0ㅇ</span></a></div>
				<div><a href="after" class="btn-12 btn-sm"><span>@After 테스트</span><span>눌러바ㅎ</span></a></div>
				<div><a href="afterReturning" class="btn-12 btn-sm"><span>@AfterReturning 테스트</span><span>끌릭끌릭</span></a></div>
				<div><a href="afterThrowing" class="btn-12 btn-sm"><span>@AfterThrowing 테스트</span><span>쫜~득!</span></a></div>
				<div><a href="around" class="btn-12 btn-sm"><span>@Around 테스트</span><span>yummy!</span></a></div>
			</div>
			
			
		
			
			
		</div>
		
		<div class="card">
			<div class="card-header">
				AOP 예제1
			</div>
			<div class="card-body">
				<p><a href="runtimeCheck" class="btn btn-info btn-sm">요청 처리 시간 측정</a></p>
				<div>실행시간: ${methodName} - ${howLong} ns</div>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				AOP 예제2
			</div>
			<div class="card-body">
				<p><a href="loginCheck" class="btn btn-info btn-sm">인증 여부 확인</a></p>
			</div>

		</div>		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>