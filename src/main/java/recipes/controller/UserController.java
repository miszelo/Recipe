package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.User;
import recipes.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void registerAccount(@Valid @RequestBody User user) {
        System.out.println("test");
        userService.registerAccount(user);
        System.out.println("test po");
    }


}
