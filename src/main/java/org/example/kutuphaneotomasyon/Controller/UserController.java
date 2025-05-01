package org.example.kutuphaneotomasyon.Controller;

import org.example.kutuphaneotomasyon.Dto.LoanDto;
import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.Impl.UserServiceImpl;
import org.example.kutuphaneotomasyon.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    public UserController(UserService userService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    } //bir aa bak postmande sorun var

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userServiceImpl.allUsers();
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
    @PutMapping("/update")
    public GenericResponse<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @GetMapping("/find-By-Id")
    public GenericResponse<?> findById(@RequestParam Integer id) {
        return userService.findById(id);

    }
}