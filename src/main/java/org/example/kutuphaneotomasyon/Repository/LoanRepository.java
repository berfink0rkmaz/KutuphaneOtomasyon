package org.example.kutuphaneotomasyon.Repository;

import org.example.kutuphaneotomasyon.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
   Loan findLoanById(Integer id);
}
