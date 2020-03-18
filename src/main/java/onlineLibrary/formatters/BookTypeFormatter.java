package onlineLibrary.formatters;

import onlineLibrary.models.book.BookType;
import onlineLibrary.services.book.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class BookTypeFormatter implements Formatter<BookType> {
    private BookTypeService bookTypeService;

    @Autowired
    public BookTypeFormatter(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    @Override
    public BookType parse(String text, Locale locale) throws ParseException {
        return bookTypeService.findById(Long.parseLong(text));
    }

    @Override
    public String print(BookType object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}
