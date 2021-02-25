package com.example.lld.lldpractice.apicounttracker.service;

import com.example.lld.lldpractice.apicounttracker.models.ApiMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ApiMetaDataService {
    private Map<String, ApiMetaData> apiMetaDataMap;

    public ApiMetaDataService() {
        apiMetaDataMap = new HashMap<>();
    }

    public ApiMetaData setApiMetaData(ApiMetaData apiMetaData) {
        apiMetaDataMap.put(apiMetaData.getApiEndPoint(), apiMetaData);
        return apiMetaDataMap.get(apiMetaData.getApiEndPoint());
    }

    public ApiMetaData getApiMetaData(String endPoint) {
        return apiMetaDataMap.get(endPoint);
    }
}
