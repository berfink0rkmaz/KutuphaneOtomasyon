package org.example.kutuphaneotomasyon.Service.Impl;

import jakarta.transaction.Transactional;
import org.example.kutuphaneotomasyon.Dto.LoanDtoIU;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Durum;
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
    @Transactional
    @Override
    public GenericResponse<?> saveLoan(LoanDtoIU loanDtoIU) { // issue
        User user = userRepository.findById(loanDtoIU.getUserId()).orElse(null);
        Book book = bookRepository.findBookWithRawSql(String.valueOf(loanDtoIU.getBookId()));
        System.out.println(loanDtoIU.getBookId());

        if (user == null) {
            return GenericResponse.error(Constants.EMPTY_USER);
        }
        if (book == null) {
            return GenericResponse.error(Constants.EMPTY_BOOK);
        }
        if (book.getDurum() != Durum.MUSAIT) {
            return GenericResponse.error("Kitap şu an uygun değil: " + book.getDurum().name());
        }
        // Kitabı ödünç ver -> durumunu güncelle
        book.setDurum(Durum.ODUNC_VERILDI);
        try {
            // COMMAND INJECTION — CRITICAL severity
            Runtime.getRuntime().exec("echo Kitap ödünç verildi: " + book.getAd());
        } catch (Exception e) {
            e.printStackTrace();
        }

        bookRepository.save(book);


        Loan loan = loanMapper.dtoToLoan(loanDtoIU, user, book);
        Loan saved = loanRepository.save(loan);
        return GenericResponse.success(loanMapper.loanToDto(saved));
    }

        @Override
        public GenericResponse<?> getAllLoans() {
            List<Loan> loans = loanRepository.findAll();
            return GenericResponse.success(
                    loans.stream().map(loanMapper::loanToDto).toList()
            );
        }

        @Override
        public GenericResponse<?> getLoanById(Integer id) {
            return loanRepository.findById(id)
                    .map(loan -> GenericResponse.success(loanMapper.loanToDto(loan)))
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
    public GenericResponse<?> updateLoan(Integer id, LoanDtoIU updatedLoan) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isEmpty()) {
            return GenericResponse.error("Loan not found!");
        }

        User user = userRepository.findById(updatedLoan.getUserId()).orElse(null);
        Book book = bookRepository.findById(updatedLoan.getBookId()).orElse(null);

        if (user == null) return GenericResponse.error("User not found!");
        if (book == null) return GenericResponse.error("Book not found!");

        Loan loan = optionalLoan.get();
        loan.setBorrowDate(updatedLoan.getBorrowDate());
        loan.setReturnDate(updatedLoan.getReturnDate());
        loan.setReturned(updatedLoan.isReturned());
        loan.setUser(user);
        loan.setBook(book);

        if (updatedLoan.isReturned()) {
            book.setDurum(Durum.MUSAIT);
            bookRepository.save(book);
        }

        Loan updated = loanRepository.save(loan);
        return GenericResponse.success(loanMapper.loanToDto(updated));
    }
    }