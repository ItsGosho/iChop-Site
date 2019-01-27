package itsgosho.repository.thread;

import itsgosho.domain.entities.threads.Comment;
import itsgosho.domain.entities.threads.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,String> {

    List<Thread> findAllByOrderByCreatedOnAsc();
}
