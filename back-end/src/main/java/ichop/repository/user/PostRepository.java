package ichop.repository.user;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findAllByUser(User user);

}
