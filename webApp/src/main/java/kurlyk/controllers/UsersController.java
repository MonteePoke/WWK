package kurlyk.controllers;

import kurlyk.models.User;
import kurlyk.models.UserProgress;
import kurlyk.services.user.UsersService;
import kurlyk.services.userProgress.UserProgressService;
import kurlyk.transfer.UserProgressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/user/progress/{id}")
    public UserProgress getOneUserProgress(@PathVariable("id") Long id) {
        return userProgressService.getOneUserProgress(id);
    }

    @GetMapping("/user/progress")
    public List<UserProgressDto> getUserProgress(
            @RequestParam("userId") Long userId,
            @RequestParam(name = "labWorkId", required = false) Long labWorkId,
            @RequestParam(name = "taskId", required = false) Long taskId,
            @RequestParam(name = "questionId", required = false) Long questionId
    ) {
        return userProgressService.getUserProgress(
                UserProgressDto
                        .builder()
                        .userId(userId)
                        .labWorkId(labWorkId)
                        .taskId(taskId)
                        .questionId(questionId)
                        .build()
        );
    }

    @GetMapping("/user/full-progress")
    public List<UserProgress> getFullUserProgress(
            @RequestParam("userId") Long userId,
            @RequestParam(name = "labWorkId", required = false) Long labWorkId,
            @RequestParam(name = "taskId", required = false) Long taskId,
            @RequestParam(name = "questionId", required = false) Long questionId
    ) {
        return userProgressService.getFullUserProgress(
                UserProgressDto
                        .builder()
                        .userId(userId)
                        .labWorkId(labWorkId)
                        .taskId(taskId)
                        .questionId(questionId)
                        .build()
        );
    }

    @PostMapping("/user/progress")
    public ResponseEntity<Object> postUserProgress(@RequestBody UserProgress userProgress) {
        userProgressService.saveUserProgress(userProgress);
        return ResponseEntity.ok().build();
    }
}
