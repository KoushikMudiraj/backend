package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
