package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void save(User user);

    public User findById(Long id);

    public List<User> findAll();

    public void update(Long id, String username, String email, Integer age);

    public void delete(Long id);
}
