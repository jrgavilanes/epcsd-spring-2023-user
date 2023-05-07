package edu.uoc.epcsd.user.controllers;

import edu.uoc.epcsd.user.entities.User;
import edu.uoc.epcsd.user.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        log.trace("getAllUsers");
        return userRepository.findAll();
    }

    // add the code for the missing system operations here

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid User user) {
        userRepository.save(user);
    }

}
