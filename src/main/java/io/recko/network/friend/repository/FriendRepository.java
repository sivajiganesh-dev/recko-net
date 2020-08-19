package io.recko.network.friend.repository;

import io.recko.network.friend.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    public List<Friend> findFriendsByUserId(Long userId);
    public Friend findFriendByUserIdAndFriendId(Long userId, Long friendId);
}
