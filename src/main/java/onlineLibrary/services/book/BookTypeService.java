package onlineLibrary.services.book;

import onlineLibrary.models.book.BookType;

public interface BookTypeService {
    Iterable<BookType> findAll();
    BookType findById(Long id);
    void save( BookType bookType);
    void remove (Long id);
}
