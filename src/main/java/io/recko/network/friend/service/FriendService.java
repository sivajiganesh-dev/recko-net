package io.recko.network.friend.service;

import io.recko.network.friend.model.Friend;
import io.recko.network.friend.repository.FriendRepository;
import io.recko.network.user.model.User;
import io.recko.network.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {
    private final Logger logger = LoggerFactory.getLogger(FriendService.class);

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserService userService;

    /**
     * Find all friends and return list of users who are friends for the provided user
     * @param user
     * @return List of User
     */
    public List<User> findAllByUser(User user) {
        List<Friend> friends = friendRepository.findFriendsByUserId(user.getId());
        List<Long> userIds = friends.stream().map(Friend::getFriendId).collect(Collectors.toList());
        return userService.findAllByIds(userIds);
    }

    /**
     * Check if the user has friend with specific user id
     * @param user
     * @param friendId
     * @return
     */
    public boolean isExist(User user, Long friendId) {
        return friendRepository.findFriendByUserIdAndFriendId(user.getId(), friendId) != null;
    }

    public Friend create(Friend newFriend) {
        return friendRepository.save(newFriend);
    }

    /**
     * Delete friend entry for both users using both userId and friendId
     * @param userId
     * @param friendId
     */
    public void delete(Long userId, Long friendId) {
        Friend friend = friendRepository.findFriendByUserIdAndFriendId(userId, friendId);
        Friend friendBy = findFriend(friend.getFriendId(), friend.getUserId());

        friendRepository.delete(friend);
        friendRepository.delete(friendBy);
    }

    /**
     * Find friend using both userId and friendId
     * @param userId
     * @param friendId
     * @return
     */
    public Friend findFriend(Long userId, Long friendId) {
        return friendRepository.findFriendByUserIdAndFriendId(userId, friendId);
    }

    /**
     * Add friend to a user and this operation will do the two-way relation of two users
     * @param user
     * @param friendId
     * @return
     */
    public Friend addFriend(User user, Long friendId) {

        if (isExist(user, friendId))
            return friendRepository.findFriendByUserIdAndFriendId(user.getId(), friendId);

        logger.info("is not exist");

        User friend = userService.findById(friendId).orElse(null);

        logger.info("friend email: " + friend.getEmail());

        Friend friendTo = new Friend();
        friendTo.setUserId(user.getId());
        friendTo.setFriendId(friend.getId());
        friendTo = create(friendTo);

        Friend friendBy = new Friend();
        friendBy.setUserId(friend.getId());
        friendBy.setFriendId(user.getId());
        create(friendBy);

        return friendTo;
    }
}
