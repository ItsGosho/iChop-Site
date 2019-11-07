package ichop.threads.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCreateRequest extends RequestCandidate {

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 50)
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private String creatorUsername;

}
