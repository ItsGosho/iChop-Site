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
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private User creator;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PostReport> reports;

}
