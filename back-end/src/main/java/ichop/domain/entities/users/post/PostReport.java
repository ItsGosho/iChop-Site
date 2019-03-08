package ichop.domain.entities.users.post;

import ichop.domain.entities.base.BaseReport;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "posts_reports")
public class PostReport extends BaseReport {

    @ManyToOne(optional = false)
    private Post post;

}
