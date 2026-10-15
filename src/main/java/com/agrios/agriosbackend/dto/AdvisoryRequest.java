package com.agrios.agriosbackend.dto;

public class AdvisoryRequest {

    private String recommendation;
    private String advisoryDate;
    private String officerName;
    private Long problemId;

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getAdvisoryDate() {
        return advisoryDate;
    }

    public void setAdvisoryDate(String advisoryDate) {
        this.advisoryDate = advisoryDate;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
}