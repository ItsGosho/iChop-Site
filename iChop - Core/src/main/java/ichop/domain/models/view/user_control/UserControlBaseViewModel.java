package ichop.domain.models.view.user_control;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserControlBaseViewModel {

    private String id;
    private String username;
    private String role;
}
