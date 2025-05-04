package org.example.kutuphaneotomasyon.Service;

import org.example.kutuphaneotomasyon.Dto.UserDto;
import org.example.kutuphaneotomasyon.Dto.UserDtoIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

import java.util.List;

public interface UserService {
     List<UserDto> allUsers();
     GenericResponse<?> deleteUser(Integer id);
     GenericResponse<?> updateUser(Integer id, UserDtoIU dto);
     GenericResponse<?> findById(Integer id);
     GenericResponse<?> searchByUserName(String keyword);
}