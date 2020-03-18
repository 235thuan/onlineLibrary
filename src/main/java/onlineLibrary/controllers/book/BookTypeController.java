package onlineLibrary.controllers.book;

import onlineLibrary.models.book.BookType;
import onlineLibrary.services.book.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping("/bookTypes")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("library/bookType/list");
        modelAndView.addObject("bookTypes", bookTypeService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-bookType")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("library/bookType/create");
        modelAndView.addObject("bookType", new BookType());
        return modelAndView;
    }

    //
    @PostMapping("/create-bookType")
    public ModelAndView create(@ModelAttribute("bookType") BookType bookType) {
        bookTypeService.save(bookType);
        ModelAndView modelAndView = new ModelAndView("library/bookType/create");
        modelAndView.addObject("bookType", new BookType());
        modelAndView.addObject("message", "New category is created successfully");
        return modelAndView;
    }

    //
    @GetMapping("/edit-bookType/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id) {
        BookType bookType = bookTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("library/bookType/edit");
        modelAndView.addObject("bookType", bookType);
        return modelAndView;
    }

    //
    @PostMapping("/edit-bookType")
    public ModelAndView edit(@ModelAttribute("bookType") BookType bookType) {
        bookTypeService.save(bookType);
        ModelAndView modelAndView = new ModelAndView("library/bookType/edit");
        modelAndView.addObject("message", "Updated category successfully");
        return modelAndView;
    }

    //
    @GetMapping("/delete-bookType/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id) {
        BookType bookType = bookTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("library/bookType/delete");
        modelAndView.addObject("bookType", bookType);
        return modelAndView;
    }

    //
    @PostMapping("/delete-bookType")
    public String delete(@ModelAttribute("bookType") BookType bookType) {
        bookTypeService.remove(bookType.getId());
        return "redirect:/bookTypes";
    }
}
