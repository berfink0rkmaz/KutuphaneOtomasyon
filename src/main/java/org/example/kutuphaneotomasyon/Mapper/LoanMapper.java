package org.example.kutuphaneotomasyon.Mapper;

import org.example.kutuphaneotomasyon.Dto.LoanDto;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Loan;
import org.example.kutuphaneotomasyon.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class LoanMapper {

    public Loan dtoToLoan(LoanDto dto, User user, Book book) {
        Loan loan = new Loan();
        loan.setId(dto.getId());
        loan.setBorrowDate(dto.getBorrowDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setReturned(dto.isReturned());
        loan.setUser(user);
        loan.setBook(book);
        return loan;
    }

    public LoanDto loanToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBorrowDate(loan.getBorrowDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setReturned(loan.isReturned());

        dto.setUserId(loan.getUser().getId());
        dto.setBookId(loan.getBook().getId());
        dto.setUserName(loan.getUser().getUsername());      // opsiyonel
        dto.setBookTitle(loan.getBook().getAd());       // opsiyonel
        return dto;
    }
}
