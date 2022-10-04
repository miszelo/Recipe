package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void registerAccount(@Valid @RequestBody User user) {
        userService.registerAccount(user);
    }


}
