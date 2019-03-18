package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserInformation;
import ichop.domain.models.binding.user.UserUpdateProfileInformationBindingModel;
import ichop.domain.models.service.user.UserInformationServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.repository.user.UserInformationRepository;
import ichop.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserInformationServicesImp extends BaseService<UserInformation, UserInformationRepository> implements UserInformationServices {

    private final UserServices userServices;

    @Autowired
    public UserInformationServicesImp(ModelMapper modelMapper, UserInformationRepository repository, UserServices userServices) {
        super(modelMapper, repository);
        this.userServices = userServices;
    }

    @Override
    public UserInformationServiceModel update(UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel, UserServiceModel user) {
        boolean isUserExists = this.userServices.isUserExistsByUsername(user.getUsername());

        if (!isUserExists) {
            throw new UserNotFoundException();
        }

        if(this.isUserInformationExistsByUser(user)){
            UserInformationServiceModel userInformationServiceModel = super.modelMapper.map(super.repository.findByUser(super.modelMapper.map(user, User.class)),UserInformationServiceModel.class);
            userInformationServiceModel.setAboutYou(userUpdateProfileInformationBindingModel.getAboutYou());
            userInformationServiceModel.setAvatarPath(userUpdateProfileInformationBindingModel.getAvatarPath());
            userInformationServiceModel.setBirthDate(LocalDate.parse(userUpdateProfileInformationBindingModel.getBirthDate()));
            userInformationServiceModel.setStatusMessage(userUpdateProfileInformationBindingModel.getStatusMessage());
            return super.save(userInformationServiceModel,UserInformationServiceModel.class);
        }

        UserInformationServiceModel userInformationServiceModel = super.modelMapper.map(userUpdateProfileInformationBindingModel,UserInformationServiceModel.class);
        userInformationServiceModel.setBirthDate(LocalDate.parse(userUpdateProfileInformationBindingModel.getBirthDate()));
        userInformationServiceModel.setUser(user);

        return super.save(userInformationServiceModel,UserInformationServiceModel.class);
    }

    @Override
    public boolean isUserInformationExistsByUser(UserServiceModel user) {
        User entityUser = super.modelMapper.map(user,User.class);
        return super.repository.findByUser(entityUser) != null;
    }


}
