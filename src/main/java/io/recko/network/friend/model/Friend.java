package io.recko.network.friend.model;

import io.recko.network.user.model.User;
import org.springframework.lang.NonNull;

import javax.persistence.*;
/**
 * Friend entity stores the identifiers of two users.
 * For each friend request/addition there will be two entries
 * one from the requested to target user and vice-versa
 */
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @Column(name = "friend_id")
    @NonNull
    private Long friendId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Long userId) {
        this.userId = userId;
    }

    @NonNull
    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(@NonNull Long friendId) {
        this.friendId = friendId;
    }
}
