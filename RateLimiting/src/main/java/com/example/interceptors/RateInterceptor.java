/**
 * 
 */
package com.example.interceptors;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.exceptions.LimitExceededException;
import com.example.services.RateService;
import com.example.utilitys.Utility;



/**
 * @author vivek
 *
 */
@Component
public class RateInterceptor implements HandlerInterceptor {
	
	@Autowired
	Utility utility;
	
	@Autowired
	RateService reateservice;
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException, LimitExceededException {
		
		String tenant = utility.getTenant(request.getQueryString());
		
		System.out.println("Request from " +tenant);
		
		if(reateservice.checkLimit(tenant)==0) {
			CronExpression cronTrigger = CronExpression.parse("0 0 0/1 1/1 * ?");
	        LocalDateTime next = cronTrigger.next(LocalDateTime.now());
			throw new LimitExceededException("you have reached to hourly api call limit will be renewed at :" + next);
		}
		
		return true;
	}


}
