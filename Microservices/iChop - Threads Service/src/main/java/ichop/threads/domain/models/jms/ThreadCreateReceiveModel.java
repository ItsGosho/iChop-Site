package ichop.threads.domain.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCreateReceiveModel extends BaseReceiveModel {

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 50)
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private String userId;

}
