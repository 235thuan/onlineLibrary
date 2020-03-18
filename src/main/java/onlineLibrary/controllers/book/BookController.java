package onlineLibrary.controllers.book;

import onlineLibrary.models.book.Book;
import onlineLibrary.models.book.BookType;
import onlineLibrary.services.book.BookService;
import onlineLibrary.services.book.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookTypeService bookTypeService;

    @ModelAttribute("bookTypes")
    public Iterable<BookType> bookTypes() {
        Iterable<BookType> bookTypes = bookTypeService.findAll();
        return bookTypeService.findAll();
    }


    @GetMapping("/book")
    public ModelAndView list(@RequestParam("bookType") Optional<Long> bookType,
                             @RequestParam("s") Optional<String> s,
                             @PageableDefault(size = 5) Pageable pageable) {
        Page<Book> books;
        if (s.isPresent()) {
            books = bookService.
                    findAllByTitleContainingOrAuthorContaining
                            (s.get(), s.get(), pageable);
        }
        else if (bookType.isPresent()) {
            BookType bookType1 = bookTypeService.findById(bookType.get());
            books = bookService.findAllByBookType(bookType1, pageable);
        }
        else {
            books = bookService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("library/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create-book")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("library/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create-book")
    public ModelAndView create(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("library/book/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "New book is created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-book/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("library/book/edit");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/edit-book")
    public ModelAndView edit(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("library/book/edit", "book", book);
        modelAndView.addObject("message", "Updated book successfully");
        return modelAndView;
    }

    @GetMapping("/delete-book/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("library/book/delete");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/delete-book")
    public String delete(@ModelAttribute("book") Book book) {
        bookService.remove(book.getId());
        return "redirect:/books";
    }

    @GetMapping("/view-book/{id}")
    public ModelAndView view(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("library/book/view");
        modelAndView.addObject("book", book);
        return modelAndView;
    }
}

