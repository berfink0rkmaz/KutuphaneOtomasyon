package org.example.kutuphaneotomasyon.Controller;

import org.example.kutuphaneotomasyon.Dto.LoanDto;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/save")
    public GenericResponse<?> saveLoan(@RequestBody LoanDto dtoLoan) {
        return loanService.saveLoan(dtoLoan);
    }

    @GetMapping("/getAll")
    public GenericResponse<?> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/getLoan/{id}")
    public GenericResponse<?> getLoanById(@PathVariable Integer id) {
        return loanService.getLoanById(id);
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> deleteLoanById(@PathVariable Integer id) {
        return loanService.deleteLoanById(id);
    }

    @PutMapping("/update")
    public GenericResponse<?> updateLoan(@PathVariable Integer id, @RequestBody LoanDto dtoLoan) {
        return loanService.updateLoan(id, dtoLoan);
    }
}