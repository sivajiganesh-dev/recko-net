package io.recko.network.post.repository;

import io.recko.network.post.model.Post;
import io.recko.network.user.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    public List<Post> findPostsByUserIdIn(List<Long> userIds, Sort created);
}
