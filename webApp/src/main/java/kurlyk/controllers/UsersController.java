package kurlyk.controllers;

import kurlyk.models.User;
import kurlyk.models.UserLabWorkAccess;
import kurlyk.models.UserProgressLabWork;
import kurlyk.services.user.UsersService;
import kurlyk.services.userProgress.UserProgressService;
import kurlyk.transfer.UserLabWorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UserProgressService userProgressService;

    /*
        User
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return usersService.findAll();
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return usersService.findOne(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        usersService.signUp(user);
        return ResponseEntity.ok().build();
    }

    /*
        UserProgress
     */
    @GetMapping("/user/progress")
    public List<UserProgressLabWork> getUserProgress(
            @RequestParam("userId") Long userId,
            @RequestParam("labWorkId") Long labWorkId
    ) {
        return userProgressService.getUserProgress(
                UserLabWorkDto
                        .builder()
                        .userId(userId)
                        .labWorkId(labWorkId)
                        .build()
        );
    }

    @PostMapping("/user/progress")
    public Long saveUserProgress(@RequestBody UserProgressLabWork userProgressLabWork) {
        return userProgressService.saveUserProgress(userProgressLabWork);
    }

    @DeleteMapping("/user/progress/{id}")
    public ResponseEntity<Object> deleteUserProgress(@PathVariable("id") Long id) {
        try {
            userProgressService.deleteUserProgress(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


    /*
        UserLabWorkAccess
    */
    @GetMapping("/user/access")
    public Optional<UserLabWorkAccess> getUserLabWorkAccess(
            @RequestParam("userId") Long userId,
            @RequestParam("labWorkId") Long labWorkId
    ) {
        return userProgressService.getUserLabWorkAccess(
                UserLabWorkDto
                        .builder()
                        .userId(userId)
                        .labWorkId(labWorkId)
                        .build()
        );
    }

    @PostMapping("/user/access")
    public Long saveUserLabWorkAccess(@RequestBody UserLabWorkAccess userLabWorkAccess) {
        return userProgressService.saveUserLabWorkAccess(userLabWorkAccess);
    }

    @DeleteMapping("/user/access/{id}")
    public ResponseEntity<Object> deleteUserLabWorkAccess(@PathVariable("id") Long id) {
        try {
            userProgressService.deleteUserLabWorkAccess(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
