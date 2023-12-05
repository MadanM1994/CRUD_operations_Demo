//package com.madan.crud_operations_demo.aspect;
//
//import com.madan.crud_operations_demo.exception.EmployeeNotFoundException;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//@Component
//@Aspect
//public class MyExceptionThrowingAspect {
//
//    @Around("execution(* com.madan.crud_operations_demo..*.*(..))")
//    public Object handleControllerException(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            return joinPoint.proceed();
//        } catch (EmployeeNotFoundException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (MethodArgumentTypeMismatchException ex) {
//            return new ResponseEntity<>("Your client side request is incorrect. Please correct it and retry.", HttpStatus.BAD_REQUEST);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Something went wrong. Please contact support.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
