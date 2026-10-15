package com.agrios.agriosbackend.dto;

public class ReportResponse {

    private long totalFarms;
    private long totalCrops;
    private double totalExpenses;
    private long totalHarvests;
    private double totalRevenue;
    private double profit;

    public ReportResponse(long totalFarms,
                          long totalCrops,
                          double totalExpenses,
                          long totalHarvests,
                          double totalRevenue,
                          double profit) {

        this.totalFarms = totalFarms;
        this.totalCrops = totalCrops;
        this.totalExpenses = totalExpenses;
        this.totalHarvests = totalHarvests;
        this.totalRevenue = totalRevenue;
        this.profit = profit;
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

    public double getProfit() {
        return profit;
    }
}