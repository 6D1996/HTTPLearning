package com.example.httplearning;

public class VehicleCondition {
    private String dataResults,userId,requestId,code,message;

    public VehicleCondition() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataResults() {
        return dataResults;
    }

    public void setDataResults(String dataResults) {
        this.dataResults = dataResults;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "VehicleCondition{" +
                "dataResults='" + dataResults + '\'' +
                ", userId='" + userId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
