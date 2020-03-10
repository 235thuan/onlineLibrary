package onlineLibrary.manageUser.services;

import onlineLibrary.manageUser.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User>findAll(Pageable pageable);
    Page<User> findAllByName(String name, Pageable pageable);
    Page<User> findAllByPassword(String password, Pageable pageable);
    Page<User> findAllByRole(String role, Pageable pageable);
    User findById(Long id);
    void save(User user);
    void remove(Long id);
}
