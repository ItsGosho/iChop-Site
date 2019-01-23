package itsgosho.repository.thread;

import itsgosho.domain.entities.threads.Comment;
import itsgosho.domain.entities.threads.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactRepository extends JpaRepository<React,String> {
}
