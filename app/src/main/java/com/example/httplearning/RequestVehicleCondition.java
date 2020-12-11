package com.example.httplearning;

public class RequestVehicleCondition {
    private String userId,vin,requestId,dataFields="timeStamp,brake,status,speed3d";

    public RequestVehicleCondition() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDataFields() {
        return dataFields;
    }

    public void setDataFields(String dataFields) {
        this.dataFields = dataFields;
    }

    @Override
    public String toString() {
        return "RequestVehicleCondition{" +
                "userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", requestId='" + requestId + '\'' +
                ", dataFields='" + dataFields + '\'' +
                '}';
    }
}
