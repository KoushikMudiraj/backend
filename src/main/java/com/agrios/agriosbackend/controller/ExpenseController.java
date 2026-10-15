package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.ExpenseRequest;
import com.agrios.agriosbackend.entity.Expense;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5173")
public class ExpenseController {

    private final ExpenseService service;
    private final FarmRepository farmRepository;

    public ExpenseController(ExpenseService service,
                             FarmRepository farmRepository) {
        this.service = service;
        this.farmRepository = farmRepository;
    }

    @PostMapping
    public Expense add(@RequestBody ExpenseRequest request) {

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        Expense expense = new Expense(
                request.getCategory(),
                request.getAmount(),
                request.getExpenseDate(),
                request.getDescription(),
                farm
        );

        return service.add(expense);
    }

    @GetMapping
    public List<Expense> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Expense getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id,
                          @RequestBody ExpenseRequest request) {

        Expense expense = new Expense();

        expense.setCategory(request.getCategory());
        expense.setAmount(request.getAmount());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setDescription(request.getDescription());

        return service.update(id, expense);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Expense deleted successfully";
    }
}
