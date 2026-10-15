package com.agrios.agriosbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "crop_problems")
public class CropProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String problemType;
    private String description;
    private String reportedDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    public CropProblem() {
    }

    public CropProblem(String problemType, String description,
                       String reportedDate, String status, Crop crop) {
        this.problemType = problemType;
        this.description = description;
        this.reportedDate = reportedDate;
        this.status = status;
        this.crop = crop;
    }

    public Long getId() {
        return id;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
