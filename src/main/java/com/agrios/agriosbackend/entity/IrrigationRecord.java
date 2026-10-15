package com.agrios.agriosbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "irrigation_records")
public class IrrigationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String irrigationDate;
    private double waterQuantity;
    private String method;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    public IrrigationRecord() {
    }

    public IrrigationRecord(String irrigationDate, double waterQuantity,
                            String method, String notes, Farm farm) {
        this.irrigationDate = irrigationDate;
        this.waterQuantity = waterQuantity;
        this.method = method;
        this.notes = notes;
        this.farm = farm;
    }

    public Long getId() {
        return id;
    }

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

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
