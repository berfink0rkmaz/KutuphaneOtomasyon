package org.example.kutuphaneotomasyon.Mapper;

import org.example.kutuphaneotomasyon.Dto.LoanDto;
import org.example.kutuphaneotomasyon.Dto.LoanDtoIU;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Loan;
import org.example.kutuphaneotomasyon.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan dtoToLoan(LoanDtoIU dto, User user, Book book) {
        if (dto == null || user == null || book == null) return null;

        Loan loan = new Loan();
        loan.setBorrowDate(dto.getBorrowDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setReturned(dto.isReturned());
        loan.setUser(user);
        loan.setBook(book);
        return loan;
    }

    public LoanDto loanToDto(Loan loan) {
        if (loan == null) return null;

        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBorrowDate(loan.getBorrowDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setReturned(loan.isReturned());

        if (loan.getUser() != null) {
            dto.setUserId(loan.getUser().getId());
            dto.setUserName(loan.getUser().getUsername());
        }

        if (loan.getBook() != null) {
            dto.setBookId(loan.getBook().getId());
            dto.setBookTitle(loan.getBook().getAd());
        }

        return dto;
    }
}