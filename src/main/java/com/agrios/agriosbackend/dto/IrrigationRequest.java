package com.agrios.agriosbackend.dto;

public class IrrigationRequest {

    private String irrigationDate;
    private double waterQuantity;
    private String method;
    private String notes;
    private Long farmId;

    public String getIrrigationDate() {
        return irrigationDate;
    }

    public void setIrrigationDate(String irrigationDate) {
        this.irrigationDate = irrigationDate;
    }

    public double getWaterQuantity() {
        return waterQuantity;
    }

    public void setWaterQuantity(double waterQuantity) {
        this.waterQuantity = waterQuantity;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }
}