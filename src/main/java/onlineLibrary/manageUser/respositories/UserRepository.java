package onlineLibrary.manageUser.respositories;

import onlineLibrary.manageUser.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository <User, Long> {
    Page<User> findAllByName(String name, Pageable pageable);
    Page<User> findAllByPassword(String name, Pageable pageable);
    Page<User> findAllByRole(String name, Pageable pageable);

}
