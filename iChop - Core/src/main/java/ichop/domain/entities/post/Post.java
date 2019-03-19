package ichop.domain.entities.post;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "Post")
@Table(name = "posts")
public class Post extends BaseEntity {

    @ManyToOne(optional = false, targetEntity = User.class)
    private User user;

    @ManyToOne(optional = false, targetEntity = User.class)
    private User creator;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_on", nullable = false,updatable = false)
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = PostReport.class)
    private List<PostReport> reports;

}
