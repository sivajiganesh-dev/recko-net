package io.recko.network.user.service;

import io.recko.network.user.domain.UserDTO;
import io.recko.network.user.model.User;
import io.recko.network.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public List<User> findAllByIds(List<Long> userIds) {
        return userRepository.findAllById(userIds);
    }

    public List<UserDTO> getClonedUserDTOs(List<User> users) {
        return users.parallelStream().map(User::clone).collect(Collectors.toList());
    }
}
