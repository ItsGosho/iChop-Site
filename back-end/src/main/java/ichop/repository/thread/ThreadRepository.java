package ichop.repository.thread;

import ichop.domain.entities.threads.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,String> {

    List<Thread> findAllByOrderByCreatedOnAsc();
}
