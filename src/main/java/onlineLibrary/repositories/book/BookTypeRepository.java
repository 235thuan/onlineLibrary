package onlineLibrary.repositories.book;

import onlineLibrary.models.book.BookType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookTypeRepository extends PagingAndSortingRepository<BookType, Long> {
}
