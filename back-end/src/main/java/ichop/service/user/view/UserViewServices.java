package ichop.service.user.view;

import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.domain.models.view.user.UserProfileViewModel;
import ichop.domain.models.view.user.UsersAllViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserViewServices {

    UserProfileViewModel getByUsername(String username);

    Page<UsersAllViewModel> listAllByPage(Pageable pageable);

}
