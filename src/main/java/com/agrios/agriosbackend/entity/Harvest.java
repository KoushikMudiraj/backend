package com.agrios.agriosbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "harvests")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String harvestDate;
    private double quantity;
    private String unit;
    private double revenue;
    private String quality;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    public Harvest() {
    }

    public Harvest(String harvestDate, double quantity,
                   String unit, double revenue,
                   String quality, Crop crop) {
        this.harvestDate = harvestDate;
        this.quantity = quantity;
        this.unit = unit;
        this.revenue = revenue;
        this.quality = quality;
        this.crop = crop;
    }

    public Long getId() {
        return id;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
