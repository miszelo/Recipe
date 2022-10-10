package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.exceptions.UserAlreadyExistException;
import recipes.model.User;
import recipes.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public void registerAccount(User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new UserAlreadyExistException();
        }
        System.out.println("email " + user.getEmail());
        System.out.println("haslo " + user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("haslo po kodowaniu " + user.getPassword());
        userRepository.save(user);
    }
}
