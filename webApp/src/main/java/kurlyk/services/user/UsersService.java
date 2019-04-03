package kurlyk.services.user;

import kurlyk.models.User;

import java.util.List;


public interface UsersService {
    void signUp(User user);

    List<User> findAll();

    User findOne(Long userId);
}
