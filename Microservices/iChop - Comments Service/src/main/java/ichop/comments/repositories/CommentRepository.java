package ichop.comments.repositories;

import ichop.comments.domain.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommentRepository<E extends Comment> extends MongoRepository<E,String> {
}
