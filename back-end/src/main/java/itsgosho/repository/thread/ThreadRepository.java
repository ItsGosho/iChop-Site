package itsgosho.repository.thread;

import itsgosho.domain.entities.threads.Comment;
import itsgosho.domain.entities.threads.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,String> {
}
