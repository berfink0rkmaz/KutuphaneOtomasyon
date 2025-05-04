package org.example.kutuphaneotomasyon.Service.Impl;

import jakarta.transaction.Transactional;
import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.Repository.UserRepository;
import org.example.kutuphaneotomasyon.ResponseMessage.Constants;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    @Override
    public GenericResponse<?> deleteUser(Integer id){
        System.out.println("delete user called");
        User userExists=userRepository.findUserById(id);
        if(userExists == null){
            return GenericResponse.error(Constants.EMPTY_ID);
        }
        else {
            userExists.setDeleted(true);
            userRepository.save(userExists);
            return GenericResponse.success(userExists);
        }
    }
    @Override
    public GenericResponse<?> updateUser(User user){
        System.out.println("update academician called...");
        User userExists = userRepository.findUserById(user.getId());
        if (userExists == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        } else {
            User updatedAcademician = userRepository.save(user);
            return GenericResponse.success(updatedAcademician);
        }
    }
    @Override
    public GenericResponse<?> findById(Integer id) {
        System.out.println("getUserById called");
        User user = userRepository.findUserById(id);
        if(user==null) {return GenericResponse.error(Constants.EMPTY_ID);}
        else{return GenericResponse.success(user);}
    }
    @Override
    public GenericResponse<?> searchByUserName(String keyword){
        System.out.println("searchByUsername called");
        List<User> foundUsers = userRepository.searchByUsername(keyword);
        return GenericResponse.success(foundUsers);

    }

}
