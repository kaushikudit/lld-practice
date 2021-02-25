package com.example.lld.lldpractice.apicounttracker.service;

import com.example.lld.lldpractice.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RateLimiterService {
    Map<String, List<Long>> rateLimiter;

    public RateLimiterService() {
        rateLimiter = new Hashtable<>();
    }

    public boolean isValidRequest(String key) {
        List<Long> time = rateLimiter.get(key);
        Long currTime = (new Date().getTime() / 1000);
        time = removeAllBeforeCurrTime(time, currTime);
        log.info("After removing the old time frame, the list contains {}", time);
        if(time == null) {
            time = new ArrayList<>();
        }
        log.info("Current number of request hit by {} is ", key);
        if(time.size() >= AppConstants.NUMBER_OF_REQUESTS) {
            return false;
        }
        log.info("Added time frame");
        time.add(currTime);
        rateLimiter.put(key, time);

        return true;
    }

    public List<Long> removeAllBeforeCurrTime(List<Long> time, Long currTime) {
        int index = -1;
        if(Objects.nonNull(time)) {
            int start = 0, end = time.size() - 1;
            while(start <= end) {
                int mid = (start + (end - start) / 2);
                if(time.get(mid) <= currTime - 60) {
                    index = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            List<Long> tempList = new ArrayList<>();
            for(int i = index + 1 ; i < time.size() ; i++) {
                tempList.add(time.get(i));
            }

            time = tempList;
        }

        return time;
    }

    public boolean isValidRequest(String endPoint, String userIP) {
//        if()
        return isValidRequest(userIP);
    }
}
