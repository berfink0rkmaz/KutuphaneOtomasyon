package org.example.kutuphaneotomasyon.Service;

import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

import java.util.List;

public interface UserService {
     List<User> allUsers();
    /*GenericResponse<?>saveUser(User user);
    GenericResponse<?>updateUser(User user);
    GenericResponse<?>deleteUser(Integer id);
    GenericResponse<?>getAllUsers();
    GenericResponse<?> findById(Integer id);
   // GenericResponse<?> updatePassword(String email);*/

}
