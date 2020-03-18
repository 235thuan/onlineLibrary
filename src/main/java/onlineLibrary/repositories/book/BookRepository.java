package onlineLibrary.repositories.book;

import onlineLibrary.models.book.Book;
import onlineLibrary.models.book.BookType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findAllByTitleContainingOrAuthorContaining(String title, String author, Pageable pageable);
    Page<Book> findAllByBookType(BookType bookType, Pageable pageable);
}
