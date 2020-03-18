package onlineLibrary.services.impl.book;

import onlineLibrary.models.book.Book;
import onlineLibrary.models.book.BookType;
import onlineLibrary.repositories.book.BookRepository;
import onlineLibrary.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByBookType(BookType bookType, Pageable pageable) {
        return bookRepository.findAllByBookType(bookType, pageable);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Page<Book> findAllByTitleContainingOrAuthorContaining(String title, String author, Pageable pageable) {
        return bookRepository.findAllByTitleContainingOrAuthorContaining(title, author, pageable);
    }
}
