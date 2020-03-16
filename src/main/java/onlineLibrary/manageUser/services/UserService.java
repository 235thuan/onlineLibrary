package onlineLibrary.manageUser.services;

import onlineLibrary.manageUser.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Iterable<User>findAlls();
    Page<User>findAll(Pageable pageable);
    Page<User> findAllByName(String name, Pageable pageable);
    User findById(Long id);
    void save(User user);
    void remove(Long id);

}
