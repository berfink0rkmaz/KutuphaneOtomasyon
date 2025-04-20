package org.example.kutuphaneotomasyon.Service;
import org.example.kutuphaneotomasyon.Dto.LoanDto;
import org.example.kutuphaneotomasyon.Entity.Loan;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

public interface LoanService {
    GenericResponse<?> saveLoan(LoanDto loanDto);
    GenericResponse<?> getAllLoans();
    GenericResponse<?> getLoanById(Integer id);
    GenericResponse<?> deleteLoanById(Integer id);
    GenericResponse<?> updateLoan(Integer id, LoanDto updatedLoan);
}
