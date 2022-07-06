package org.dpaul.template.springboot.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dpaul.template.springboot.exception.ErrorCode;
import org.dpaul.template.springboot.exception.GlobalException;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class CommonAspect {

	/**
	 * 匹配所有Job
	 */
	@Pointcut("execution(* org.dpaul.template.springboot.control..*.*(..))")
	public void CommonAspectPointcut() {
	}

	@Around("CommonAspectPointcut()")
	public Object aroundPrintLog(ProceedingJoinPoint point) throws Throwable {
		Object rtValue;
		final String funName = point.getSignature().getName();
		StopWatch stopWatch = new StopWatch(funName);
		stopWatch.start();
		try {
			Object[] args = point.getArgs();
			log.info("執行方法:{}", funName);
			rtValue = point.proceed(args);
		} catch (Throwable e) {
			log.info("執行方法:{}, 異常:{}", funName, e.getMessage());
			if (e instanceof GlobalException) {
				log.error(e.getLocalizedMessage(), e);
				throw e;
			} else {
				log.warn(e.getLocalizedMessage(), e);
				throw new GlobalException(e, ErrorCode.UNPREDICTABLE);
			}
		} finally {
			stopWatch.stop();
			log.info("執行方法:{}, {}", funName, stopWatch.getLastTaskTimeMillis());
		}
		return rtValue;
	}
}