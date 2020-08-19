package io.recko.network.friend.controller;

import io.recko.network.friend.service.FriendService;
import io.recko.network.user.domain.UserDTO;
import io.recko.network.user.model.User;
import io.recko.network.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FriendController handled the friend request management
 * and it provided functionality for Get all friends, add new friend, delete existing friend for current user
 *
 */
@RestController
@RequestMapping("/friends")
public class FriendController {
    private final Logger logger = LoggerFactory.getLogger(FriendController.class);

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;


    /**
     * Get all friend available for the authorized/current user
     * @return List<UserDTO>
     */
    @GetMapping("/")
    private ResponseEntity<List<UserDTO>> getFriends() {
        User user = getCurrentUser();
        List<User> friends = friendService.findAllByUser(user);

        return new ResponseEntity<>(userService.getClonedUserDTOs(friends), HttpStatus.OK);
    }

    /**
     * Add a friend to the current user by providing user id of the added user
     * @param friend_user_id
     * @return
     */
    @PostMapping("/{friend_user_id}")
    private ResponseEntity<UserDTO> addFriend(@PathVariable Long friend_user_id) {
        User user = getCurrentUser();
        User friend = userService.findById(friend_user_id).orElse(null);

        friendService.addFriend(user, friend_user_id);
        return new ResponseEntity<>(friend.clone(), HttpStatus.OK);
    }

    /**
     * Delete friend by provided user id of the added friend
     * @param friend_user_id
     * @return
     */
    @DeleteMapping("/{friend_user_id}")
    private ResponseEntity<UserDTO> deleteFriend(@PathVariable Long friend_user_id) {
        User user = getCurrentUser();
        User friend = userService.findById(friend_user_id).orElse(null);

        if (friend == null)
            return ResponseEntity.noContent().build();

        friendService.delete(user.getId(), friend.getId());
        return new ResponseEntity<>(friend.clone(), HttpStatus.OK);
    }

    /**
     * Fetch the current authorized principle
     * @return User
     */
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }
}
