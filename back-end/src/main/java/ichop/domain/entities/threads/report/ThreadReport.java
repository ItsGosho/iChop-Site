package ichop.domain.entities.threads.report;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "thread_reports")
public class ThreadReport extends BaseReport {

    @ManyToOne(optional = false)
    private Thread thread;

}
