package ichop.domain.entities.threads.report;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thread_reports")
public class ThreadReport extends BaseReport {

    @ManyToOne(optional = false)
    private Thread thread;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
