package com.deutschebahn.rest.utility;


import com.deutschebahn.rest.service.OperationOfficeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Aspect
@Component
public class TrackExecutionTimeAspect {

    Logger logger= LoggerFactory.getLogger(OperationOfficeService.class);

    @Around("@annotation(com.deutschebahn.rest.utility.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable{
        String className=point.getSignature().getDeclaringTypeName();
        String methodName=point.getSignature().getName();

        LocalDateTime startTime=LocalDateTime.now();
        logger.info("TS Class: "+className+". Method: "+methodName+" hat begonnen, @ {} ",startTime);
        Object object=point.proceed();
        LocalDateTime endTime= LocalDateTime.now();
        logger.info("TS Class: "+className+". Method: "+methodName+" has beendet, @ {} ",endTime);

        logger.info("TS Class: "+className+". Method: "+methodName+" hat gedauert, @ {} milliseconds ", ChronoUnit.MILLIS.between(startTime,endTime));

        return object;
    }
}
