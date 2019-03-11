package ichop.service.user.view;

import ichop.domain.models.view.user.UserProfileViewModel;

public interface UserViewServices {

    UserProfileViewModel getByUsername(String username);

}
