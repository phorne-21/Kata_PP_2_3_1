package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @GetMapping("/update/{id}")
    public String showUserUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam Integer age) {
        userService.update(id, name, email, age);
        return "redirect:/users/update/" + id;
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
