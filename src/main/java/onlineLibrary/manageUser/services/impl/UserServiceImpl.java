package onlineLibrary.manageUser.services.impl;

import onlineLibrary.manageUser.models.User;
import onlineLibrary.manageUser.respositories.UserRepository;
import onlineLibrary.manageUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Page<User> findAllByName(String name, Pageable pageable) {
        return userRepository.findAllByName(name, pageable);
    }
    @Override
    public Page<User> findAllByPassword(String password, Pageable pageable) {
        return userRepository.findAllByPassword(password, pageable);
    }
    @Override
    public Page<User> findAllByRole(String role, Pageable pageable) {
        return userRepository.findAllByRole(role, pageable);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }
}
