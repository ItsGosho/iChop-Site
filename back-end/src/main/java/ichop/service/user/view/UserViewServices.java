package ichop.service.user.view;

import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.domain.models.view.user.UserProfileViewModel;
import ichop.domain.models.view.user.UsersAllViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserViewServices {

    UserProfileViewModel getByUsername(String username);

    Page<UsersAllViewModel> listAllByPage(Pageable pageable);

    Page<UsersAllViewModel> findUsersByUsernameContains(String containingWord,Pageable pageable);

    Page<UsersAllViewModel> findUsersByRole(String role, Pageable pageable);
}
