package com.wanari.graphql.service.error;

public class ErrorDescriptionParamDto {
    public String paramKey;
    public String paramValue;

    public ErrorDescriptionParamDto(String paramKey, String paramValue) {
        this.paramKey = paramKey;
        this.paramValue = paramValue;
    }
}
