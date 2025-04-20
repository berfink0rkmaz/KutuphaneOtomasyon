package org.example.kutuphaneotomasyon.Service.Impl;

import jakarta.transaction.Transactional;
import org.example.kutuphaneotomasyon.Dto.LoanDto;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Loan;
import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.Mapper.LoanMapper;
import org.example.kutuphaneotomasyon.Repository.BookRepository;
import org.example.kutuphaneotomasyon.Repository.LoanRepository;
import org.example.kutuphaneotomasyon.Repository.UserRepository;
import org.example.kutuphaneotomasyon.ResponseMessage.Constants;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanMapper loanMapper;

    @Override
    public GenericResponse<?> saveLoan(LoanDto loanDto) {

        User user = userRepository.findById(loanDto.getUserId()).orElse(null);
        Book book = bookRepository.findById(loanDto.getBookId()).orElse(null);
        if(user==null){
            return GenericResponse.error(Constants.EMPTY_USER);
        }
        else if (book==null) {
            return GenericResponse.error(Constants.EMPTY_BOOK);
        }
        else{
        Loan loan = loanMapper.dtoToLoan(loanDto, user, book);
        loan.setUser(user);
        loan.setBook(book);
        return GenericResponse.success(loanRepository.save(loan));
        }
    }

    @Override
    public GenericResponse<?> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return GenericResponse.success(loans.stream().map(loanMapper::loanToDto).toList());
    }

    @Override
    public GenericResponse<?> getLoanById(Integer id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.map(value -> GenericResponse.success(loanMapper.loanToDto(value)))
                .orElseGet(() -> GenericResponse.error("Loan not found"));
    }

    @Override
    public GenericResponse<?> deleteLoanById(Integer id) {
        if (!loanRepository.existsById(id)) {
            return GenericResponse.error("Loan not found!");
        }
        loanRepository.deleteById(id);
        return GenericResponse.success("Deleted successfully");
    }

    @Override
    public GenericResponse<?> updateLoan(Integer id, LoanDto updatedLoan) {
        Optional<Loan> existing = loanRepository.findById(id);
        if (existing.isEmpty()) {
            return GenericResponse.error("Loan not found!");
        }

        Loan loan = existing.get();
        loan.setBorrowDate(updatedLoan.getBorrowDate());
        loan.setReturnDate(updatedLoan.getReturnDate());
        loan.setReturned(updatedLoan.isReturned());

        return GenericResponse.success(loanRepository.save(loan));
    }
}