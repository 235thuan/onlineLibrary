package onlineLibrary.services.impl.book;

import onlineLibrary.models.book.BookType;
import onlineLibrary.repositories.book.BookTypeRepository;
import onlineLibrary.services.book.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public Iterable<BookType> findAll() {
        return bookTypeRepository.findAll();
    }

    @Override
    public BookType findById(Long id) {
        return bookTypeRepository.findOne(id);
    }

    @Override
    public void save(BookType bookType) {
        bookTypeRepository.save(bookType);
    }

    @Override
    public void remove(Long id) {
        bookTypeRepository.delete(id);
    }
}

