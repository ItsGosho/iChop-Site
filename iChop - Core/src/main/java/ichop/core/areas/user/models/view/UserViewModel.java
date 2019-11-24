package ichop.core.areas.user.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserViewModel {

    private String id;
    private String username;
    private String email;
    private String authority;
    private Set<UserAuthorityViewModel> authorities;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;

}
