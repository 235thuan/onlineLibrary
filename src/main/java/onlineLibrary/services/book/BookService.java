package onlineLibrary.services.book;

import onlineLibrary.models.book.Book;
import onlineLibrary.models.book.BookType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAllByTitleContainingOrAuthorContaining(String title, String author, Pageable pageable);
    Page<Book>findAll(Pageable pageable);
    Page<Book>findAllByBookType(BookType bookType, Pageable pageable);
    Book findById(Long id);
    void save(Book book);
    void remove(Long id);
}
