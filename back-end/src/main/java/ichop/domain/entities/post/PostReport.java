package ichop.domain.entities.post;

import ichop.domain.entities.report.BaseReport;
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