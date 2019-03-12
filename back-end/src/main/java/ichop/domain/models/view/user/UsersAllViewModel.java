package ichop.domain.models.view.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsersAllViewModel {

    private String id;
    private String username;
    private String email;
    private String location;
    private LocalDateTime lastOnline;
    private LocalDateTime registrationDate;

}