package com.example.lld.lldpractice.apicounttracker.enums;

import lombok.Getter;

@Getter
public enum ApiLimitEnum {
    USER_API("/user", 5),
    EMPLOYEE_API("/emp", 10);

    private String endPoint;
    private Integer rateLimit;

    ApiLimitEnum(final String endPoint, final int rateLimit) {
        this.endPoint = endPoint;
        this.rateLimit = rateLimit;
    }

    public ApiLimitEnum findByendPoint(String endPoint) {
        for(ApiLimitEnum apiLimitEnum : ApiLimitEnum.values()) {
            if(apiLimitEnum.getEndPoint().equals(endPoint)) {
                return apiLimitEnum;
            }
        }

        return null;
    }
}
