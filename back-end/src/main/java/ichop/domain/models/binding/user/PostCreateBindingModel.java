package ichop.domain.models.binding.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostCreateBindingModel {

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 150)
    private String content;

}
