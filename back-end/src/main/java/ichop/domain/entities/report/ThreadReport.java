package ichop.domain.entities.report;

import ichop.domain.entities.threads.Thread;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "ThreadReport")
@Table(name = "threads_reports")
public class ThreadReport extends BaseReport {

    @ManyToOne(optional = false,targetEntity = Thread.class)
    private Thread thread;

}
