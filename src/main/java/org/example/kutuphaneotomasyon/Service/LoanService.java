package org.example.kutuphaneotomasyon.Service;
import org.example.kutuphaneotomasyon.Entity.Loan;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

public interface LoanService {
    GenericResponse<?> BorrowBook();
    /*Kitap Ödünç Ver (POST) -> Kullanıcıya kitap ödünç verilir
 Kitap İade Et (PUT) -> Kullanıcı kitabı iade eder
 Ödünç İşlemlerini Listele (GET) -> Kullanıcının ödünç aldığı tüm kitapları listeler
 Kitap Ödünç Durumunu Getir (GET) -> Belirtilen kitabın ödünç durumunu döndürür
*/
}
