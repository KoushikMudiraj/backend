package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.dto.DashboardResponse;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.repository.CropRepository;
import com.agrios.agriosbackend.repository.ExpenseRepository;
import com.agrios.agriosbackend.repository.HarvestRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final ExpenseRepository expenseRepository;
    private final HarvestRepository harvestRepository;

    public DashboardService(FarmRepository farmRepository,
                            CropRepository cropRepository,
                            ExpenseRepository expenseRepository,
                            HarvestRepository harvestRepository) {

        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
        this.expenseRepository = expenseRepository;
        this.harvestRepository = harvestRepository;
    }

    public DashboardResponse getDashboardData() {

        long totalFarms = farmRepository.count();

        long totalCrops = cropRepository.count();

        double totalExpenses = expenseRepository.findAll()
                .stream()
                .mapToDouble(expense -> expense.getAmount())
                .sum();

        long totalHarvests = harvestRepository.count();

        double totalRevenue = harvestRepository.findAll()
                .stream()
                .mapToDouble(harvest -> harvest.getRevenue())
                .sum();

        return new DashboardResponse(
                totalFarms,
                totalCrops,
                totalExpenses,
                totalHarvests,
                totalRevenue
        );
    }
}