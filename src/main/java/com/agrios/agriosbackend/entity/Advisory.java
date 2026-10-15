package com.agrios.agriosbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "advisories")
public class Advisory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recommendation;
    private String advisoryDate;
    private String officerName;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private CropProblem problem;

    public Advisory() {
    }

    public Advisory(String recommendation, String advisoryDate,
                     String officerName, CropProblem problem) {
        this.recommendation = recommendation;
        this.advisoryDate = advisoryDate;
        this.officerName = officerName;
        this.problem = problem;
    }

    public Long getId() {
        return id;
    }

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

    public CropProblem getProblem() {
        return problem;
    }

    public void setProblem(CropProblem problem) {
        this.problem = problem;
    }
}
