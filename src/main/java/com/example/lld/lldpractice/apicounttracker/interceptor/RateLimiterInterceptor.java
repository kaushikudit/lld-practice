package com.example.lld.lldpractice.apicounttracker.interceptor;

import com.example.lld.lldpractice.Exception.CustomException;
import com.example.lld.lldpractice.apicounttracker.service.RateLimiterService;
import com.example.lld.lldpractice.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RateLimiterInterceptor implements HandlerInterceptor {

    @Autowired
    private RateLimiterService rateLimiterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getHeader(AppConstants.HEADER_IP) == null || request.getHeader(AppConstants.HEADER_IP).trim().length() == 0) {
            log.error("{} is missing.", AppConstants.HEADER_IP);
            throw new CustomException(HttpStatus.BAD_REQUEST, "Header ip is missing");
        }
        if(!rateLimiterService.isValidRequest(request.getRequestURI(), request.getHeader(AppConstants.HEADER_IP))) {
            log.error("User has tried too many times.");
            throw new CustomException(HttpStatus.TOO_MANY_REQUESTS, "You have raised too many requests, please wait for sometime.");
        }
        log.info("{} is eligible for the request.", request.getHeader("ip"));
        return true;
    }
}
