package org.example.kutuphaneotomasyon.Controller;

import org.example.kutuphaneotomasyon.Dto.UserDto;
import org.example.kutuphaneotomasyon.Dto.UserDtoIU;
import org.example.kutuphaneotomasyon.Entity.User;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        UserDto dto = new UserDto(
                currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getEmail(),
                currentUser.getRole(),
                currentUser.isEnabled()
        );
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> allUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public GenericResponse<?> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDtoIU dto) {
        return userService.updateUser(id, dto);
    }

    @GetMapping("/find-By-Id")
    public GenericResponse<?> findById(@RequestParam Integer id) {
        return userService.findById(id);
    }
}