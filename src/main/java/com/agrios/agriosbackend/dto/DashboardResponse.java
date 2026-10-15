package com.agrios.agriosbackend.dto;

public class DashboardResponse {

    private long totalFarms;
    private long totalCrops;
    private double totalExpenses;
    private long totalHarvests;
    private double totalRevenue;

    public DashboardResponse(long totalFarms,
                             long totalCrops,
                             double totalExpenses,
                             long totalHarvests,
                             double totalRevenue) {

        this.totalFarms = totalFarms;
        this.totalCrops = totalCrops;
        this.totalExpenses = totalExpenses;
        this.totalHarvests = totalHarvests;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalFarms() {
        return totalFarms;
    }

    public long getTotalCrops() {
        return totalCrops;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public long getTotalHarvests() {
        return totalHarvests;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}