package ichop.domain.entities.threads.reaction;

import ichop.domain.entities.threads.Thread;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "thread_reactions")
public class ThreadReaction extends BaseReaction {

    @ManyToOne(optional = false)
    private Thread thread;

}
