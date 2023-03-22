package aop2;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		//	핵심업무에 사용 Method
			String signatureStr = joinpoint.getSignature().toShortString();
			System.out.println(signatureStr + " is start...");
		//	Return the current time in milliseconds
			long startTime = System.currentTimeMillis();
			
			Object obj;
			
			try {
//				핵심관심사 Method 수행 --> aop2.buz.Student.getStudentInfo()
				obj = joinpoint.proceed(); 
				return obj;
			} finally {
				long endTime = System.currentTimeMillis();
				System.out.println(signatureStr + " is finished");
				System.out.println(signatureStr + " 경과시간 : " + (endTime - startTime));
			}
			
		}
	
	
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}
	public void afterReturningAdvice() {
		System.out.println("afterReturningAdvice()");
	}
	public void afterThrowingAdvice() {
		System.out.println("afterThrowingAdvice()");
	}
	public void afterAdvice() {
		System.out.println("afterAdvice())");
	}
	
	
	
}
