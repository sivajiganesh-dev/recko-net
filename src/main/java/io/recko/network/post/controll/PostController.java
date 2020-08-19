package io.recko.network.post.controll;

import io.recko.network.post.model.Post;
import io.recko.network.post.service.PostService;
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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    /**
     * Get post by post id
     * @param post_id
     * @return
     */
    @GetMapping("/{post_id}")
    private ResponseEntity<Post> getPost(@PathVariable Long post_id) {
        User user = getCurrentUser();
        Post post = postService.findById(post_id).orElse(null);

        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post);
        }
    }


    /**
     * Get post feed for users own and the list of friends posts
     * @return
     */
    @GetMapping("/feed")
    private ResponseEntity<List<Post>> getPosts() {
        User user = getCurrentUser();
        logger.info("Not null " + (user == null));
        List<Post> posts = postService.findAllByUser(user);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Create post by providing subject and the content for the post
     * @param post
     * @return Post
     */
    @PostMapping("/")
    private ResponseEntity<Post> createPost(@RequestBody Post post) {
        User user = getCurrentUser();
        post.setCreated(new Date());
        post.setUserId(user.getId());
        post = postService.add(post);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * Upate post by passing the post object
     * @param post
     * @return
     */
    @PutMapping("/")
    private ResponseEntity<Post> updatePost(@RequestBody Post post) {
        User user = getCurrentUser();
        Post oldPost = postService.findById(post.getId()).orElse(null);

        if (oldPost == null && !user.getId().equals(post.getId()))
            return ResponseEntity.notFound().build();

        if (oldPost != null) {
            oldPost.setCreated(new Date());
            oldPost.setSubject(post.getSubject());
            oldPost.setContent(post.getContent());

            postService.add(oldPost);
            return new ResponseEntity<>(oldPost, HttpStatus.OK);
        } else
            return ResponseEntity.badRequest().build();
    }

    /**
     * Delete post using post id
     * @param post_id
     * @return
     */
    @DeleteMapping("/{post_id}")
    private ResponseEntity<Post> deletePost(@PathVariable Long post_id) {
        postService.deleteById(post_id);
        return ResponseEntity.ok().build();
    }


    /**
     * Get the authorized principle object
     * @return User
     */
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }
}
