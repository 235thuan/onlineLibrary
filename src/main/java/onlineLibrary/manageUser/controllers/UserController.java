package onlineLibrary.manageUser.controllers;

import onlineLibrary.manageUser.models.User;
import onlineLibrary.manageUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ModelAndView list(@RequestParam("userType") Optional<Long> userType,
                             @RequestParam("s") Optional<String> s,
                             @PageableDefault(size = 5) Pageable pageable) {
        Page<User> users;
        if (s.isPresent()) {
            users = userService.
                    findAllByName
                            (s.get(), pageable);
        } else {
            users = userService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView create(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "New user is created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit-user")
    public ModelAndView edit(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/edit", "user", user);
        modelAndView.addObject("message", "Updated user successfully");
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/delete-user")
    public String delete(@ModelAttribute("user") User user) {
        userService.remove(user.getId());
        return "redirect:/users";
    }

    @GetMapping("/view-user/{id}")
    public ModelAndView view(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
