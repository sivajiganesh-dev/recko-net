package io.recko.network.post.service;

import io.recko.network.friend.service.FriendService;
import io.recko.network.post.model.Post;
import io.recko.network.post.repository.PostRepository;
import io.recko.network.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FriendService friendService;


    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAllByUser(User user) {
        List<User> friends = friendService.findAllByUser(user);

        if (friends.size() == 0) {
            return postRepository.findPostsByUserIdIn(Collections.singletonList(user.getId()), Sort.by(Sort.Direction.DESC, "created"));
        } else {
            friends.add(user);
            List<Long> allRalatedUserIds = friends.stream().map(User::getId).collect(Collectors.toList());
            return postRepository.findPostsByUserIdIn(allRalatedUserIds, Sort.by(Sort.Direction.DESC, "created"));
        }
    }

    public void deleteById(Long post_id) {
        postRepository.deleteById(post_id);
    }
}
