package ichop.repository.thread.reaction;

import ichop.domain.entities.threads.reaction.ReactionBase;
import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface ReactionRepository<T extends ReactionBase> extends JpaRepository<T,String> {

}
