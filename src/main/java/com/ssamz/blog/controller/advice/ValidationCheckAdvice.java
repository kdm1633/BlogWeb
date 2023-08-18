package com.ssamz.blog.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ssamz.blog.dto.ResponseDto;

@Component
@Aspect
public class ValidationCheckAdvice {
	@Around("execution(* com.ssamz.*.controller.*Controller.*(..)) && (args(*, bindingResult, ..))")
	public Object checkValidation(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			String message = "";
			for (Map.Entry<String, String> entry : errorMap.entrySet()) {
				message += entry.getValue() + "\n";
			}
			
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), message);
		}
		
		return pjp.proceed();
	}
	
//	@Around("execution(* com.ssamz..controller.*Controller.*(..)) && !args(org.springframework.validation.BindingResult, ..)")
//    public Object checkValidation(ProceedingJoinPoint pjp) throws Throwable {
//        return pjp.proceed();
//    }
}
