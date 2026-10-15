package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Expense;
import com.agrios.agriosbackend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense add(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> getAll() {
        return repository.findAll();
    }

    public Expense getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public Expense update(Long id, Expense updated) {
        Expense expense = getById(id);

        expense.setCategory(updated.getCategory());
        expense.setAmount(updated.getAmount());
        expense.setExpenseDate(updated.getExpenseDate());
        expense.setDescription(updated.getDescription());

        return repository.save(expense);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
