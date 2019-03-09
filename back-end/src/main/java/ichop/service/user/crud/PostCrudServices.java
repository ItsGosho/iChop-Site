package ichop.service.user.crud;

import ichop.domain.models.service.user.PostServiceModel;

public interface PostCrudServices {

    PostServiceModel save(PostServiceModel postServiceModel);
    void delete(PostServiceModel postServiceModel);

}
