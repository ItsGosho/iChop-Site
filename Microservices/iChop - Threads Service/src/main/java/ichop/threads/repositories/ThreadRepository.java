package ichop.threads.repositories;

import ichop.threads.domain.entities.Thread;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends MongoRepository<Thread,String> {

}
