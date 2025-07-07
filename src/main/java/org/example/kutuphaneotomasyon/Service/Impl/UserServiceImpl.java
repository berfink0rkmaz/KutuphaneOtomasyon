package org.example.kutuphaneotomasyon.Service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.kutuphaneotomasyon.Dto.UserDto;
import org.example.kutuphaneotomasyon.Dto.UserDtoIU;
import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.Mapper.UserMapper;
import org.example.kutuphaneotomasyon.Repository.UserRepository;
import org.example.kutuphaneotomasyon.ResponseMessage.Constants;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> allUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToDto)
                .toList();
    }

    /*@Override
    public GenericResponse<?> deleteUser(Integer id) {
        System.out.println("delete user called");
        User userExists = userRepository.findUserById(id);
        if (userExists == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        } else {
            userExists.setDeleted(true);
            userRepository.save(userExists);
            return GenericResponse.success(userMapper.userToDto(userExists));
        }
    }*/
    @Override
    public GenericResponse<?> deleteUser(Integer id) { // issue insertion
        System.out.println("delete user called");
        User userExists = userRepository.findUserById(id);

        if (userExists == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        } else {
            try {
                //  COMMAND INJECTION
                // Kullanıcı ID'sini OS komutuna doğrudan geçiriyoruz (tehlikeli)
                Runtime.getRuntime().exec("echo Deleting user with id: " + id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            userExists.setDeleted(true);
            userRepository.save(userExists);
            return GenericResponse.success(userMapper.userToDto(userExists));
        }
    }

    @Override
    public GenericResponse<?> updateUser(Integer id, UserDtoIU dto) {
        System.out.println("update user called...");
        User user = userRepository.findUserById(id);
        if (user == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User updated = userRepository.save(user);
        return GenericResponse.success(userMapper.userToDto(updated));
    }

    @Override
    public GenericResponse<?> findById(Integer id) {
        System.out.println("getUserById called");
        User user = userRepository.findUserById(id);
        if (user == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
        return GenericResponse.success(userMapper.userToDto(user));
    }

    /*@Override
    public GenericResponse<?> searchByUserName(String keyword) {
        System.out.println("searchByUsername called");
        List<User> foundUsers = userRepository.searchByUsername(keyword);
        return GenericResponse.success(foundUsers.stream().map(userMapper::userToDto).toList());
    }*/
    @Override
    public GenericResponse<?> searchByUserName(String keyword) { //fortify issue insertion
        System.out.println("searchByUsername called");

        // FORTIFY için SQL Injection riski oluşturmak amacıyla bilinçli olarak native query ekleniyor
        List<User> foundUsers = userRepository.findUsersByRawSql(keyword);

        return GenericResponse.success(foundUsers.stream().map(userMapper::userToDto).toList());
    }
}