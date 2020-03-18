package onlineLibrary.repositories.login;

import onlineLibrary.models.login.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository <User, Long> {
    Page<User> findAllByName(String name, Pageable pageable);
}
