package com.example.lld.lldpractice.apicounttracker.interceptor;

import com.example.lld.lldpractice.apicounttracker.models.ApiMetaData;
import com.example.lld.lldpractice.apicounttracker.service.ApiMetaDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ApiCountTrackerInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiMetaDataService apiMetaDataService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ApiMetaData metaData = getApiMetaData(request);

        log.info("Api meta data information {}", metaData);

        return true;
    }

    private ApiMetaData getApiMetaData(HttpServletRequest request) {
        ApiMetaData metaData = apiMetaDataService.getApiMetaData(request.getRequestURI());
        if(metaData == null) {
            metaData = apiMetaDataService.setApiMetaData(new ApiMetaData(request.getRequestURI(), 1L));
        } else {
            metaData.setCount(metaData.getCount() + 1);
            metaData = apiMetaDataService.setApiMetaData(metaData);
        }

        return metaData;
    }
}
