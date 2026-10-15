package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.ReportResponse;
import com.agrios.agriosbackend.service.ReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ReportResponse getReport() {
        return reportService.getReport();
    }
}