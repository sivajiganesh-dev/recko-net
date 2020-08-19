package io.recko.network.post.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Post entity for storing user posts which contains subject, content, timestamp, and user id
 */
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @Column(name = "subject")
    @NonNull
    private String subject;

    @Column(name = "content")
    @NonNull
    private String content;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
