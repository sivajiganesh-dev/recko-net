package io.recko.network.user.controller;

import io.recko.network.user.domain.UserDTO;
import io.recko.network.user.model.User;
import io.recko.network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.findByUsername(user.getUsername()) == null) {
            user = userService.create(user);
            return new ResponseEntity<>(user.clone(), HttpStatus.OK);
        } else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.findById(id).orElse(null);

        if(user == null)
            return  ResponseEntity.noContent().build();
        else
            return new ResponseEntity<>(user.clone(),HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(userService.getClonedUserDTOs(users), HttpStatus.OK);
    }
}
