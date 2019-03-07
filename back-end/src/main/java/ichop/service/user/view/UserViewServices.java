package ichop.service.user.view;

import ichop.domain.models.view.user.profile.UserProfileViewModel;

public interface UserViewServices {

    UserProfileViewModel getByUsername(String id);

}
