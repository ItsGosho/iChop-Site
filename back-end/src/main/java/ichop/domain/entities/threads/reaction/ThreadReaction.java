package ichop.domain.entities.threads.reaction;

import ichop.domain.entities.threads.Thread;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thread_reactions")
public class ThreadReaction extends ReactionBase {

    @ManyToOne(optional = false)
    private Thread thread;


    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
