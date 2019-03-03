package ichop.repository.threads.reaction;

import ichop.domain.entities.threads.reaction.BaseReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReactionRepository<T extends BaseReaction> extends JpaRepository<T,String> {

}
