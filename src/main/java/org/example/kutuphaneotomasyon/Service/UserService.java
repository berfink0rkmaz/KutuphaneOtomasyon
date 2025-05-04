package org.example.kutuphaneotomasyon.Service;

import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

import java.util.List;

public interface UserService {
     List<User> allUsers();
     GenericResponse<?> deleteUser(Integer id);
     GenericResponse<?> updateUser(User user);
     GenericResponse<?> findById(Integer id) ;
     GenericResponse<?> searchByUserName(String keyword);

    /*
    GenericResponse<?>getAllUsers(); güncelle list user ı
   // GenericResponse<?> updatePassword(String email);*/

}
