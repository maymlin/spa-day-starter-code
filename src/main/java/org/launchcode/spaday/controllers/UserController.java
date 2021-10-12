package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            return "user/index";
        } else {
            model.addAttribute("error", "Passowrds do not match");
        }
        return "user/add";
    }

    @GetMapping
    public String displayAllUsers(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping("userdetail/{userId}")
    public String displayUserDetails(Model model, @PathVariable int userId) {
        User userToDisplay = UserData.getById(userId);
        model.addAttribute("user", userToDisplay);
        String title = "Welcome, " + userToDisplay.getUsername() + "!";
        model.addAttribute("title", title);
        return "user/userdetail";
    }

}
