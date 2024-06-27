package com.spring.demo.DTO;

public class APIResponse <T>{
    Integer recordCount;
    T response;

    public APIResponse(Integer recordCount, T response) {
        this.recordCount = recordCount;
        this.response = response;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
