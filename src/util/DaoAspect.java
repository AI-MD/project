package util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;



@Aspect
public class DaoAspect {
	
	//필터 개념생각 
	@Pointcut("execution(* controller.CtrlBang.like(..))")
	public void xxyy(){}
	//보안 세션 
	@Around("xxyy()")
	public Object 트랜잭션(final ProceedingJoinPoint jp)
		throws Throwable
	{
		Object o =txtpl.execute(new TransactionCallback<Object>() {

			@Override
			public Object doInTransaction(TransactionStatus status) {
				try{
					Object obj =jp.proceed();
					return obj;
				}catch (Throwable e) {
					status.setRollbackOnly();
					return e;
				}
			}
		});
		
		if(o!=null && o instanceof Throwable ){
			throw(Throwable)o;
		}
		return o;
	}
	

	private TransactionTemplate txtpl=null;
	
	public void setTransactionTemplate(TransactionTemplate l){
		txtpl=l;
	}
	
}
