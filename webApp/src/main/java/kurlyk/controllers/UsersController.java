package kurlyk.controllers;

import kurlyk.services.user.UsersService;
import kurlyk.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return usersService.findAll();
    }

    @GetMapping("/users/{user-id}")
    public UserDto getUser(@PathVariable("user-id") Long userId) {
        return usersService.findOne(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        usersService.signUp(userDto);
        return ResponseEntity.ok().build();
    }
}
