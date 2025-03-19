package com.example.payyoupayme.repository;

import com.example.payyoupayme.model.TransactExterne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactExterneRepository extends JpaRepository<TransactExterne, Integer> {
}
