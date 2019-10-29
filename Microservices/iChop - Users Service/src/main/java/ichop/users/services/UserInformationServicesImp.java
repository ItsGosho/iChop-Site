package ichop.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.service.AbstractBaseService;
import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserInformation;
import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.repositories.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class UserInformationServicesImp
        extends AbstractBaseService<UserInformation, UserInformationServiceModel, UserInformationRepository>
        implements UserInformationServices {

    @Autowired
    public UserInformationServicesImp(ObjectMapper objectMapper, UserInformationRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public UserInformationServiceModel update(UserUpdateProfileInformationBindingModel bindingModel) {

        User user = this.modelMapper.map(bindingModel.getUser(), User.class);
        UserInformationServiceModel userInformationServiceModel = this.modelMapper.map(bindingModel, UserInformationServiceModel.class);
        userInformationServiceModel.setId(this.repository.findByUser(user).getId());
        try {
            userInformationServiceModel.setBirthDate(LocalDate.parse(bindingModel.getBirthDate()));
        } catch (DateTimeParseException ex) {
        }

        return this.save(userInformationServiceModel, UserInformationServiceModel.class);
    }

    @Override
    public UserInformationServiceModel getByUser(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user, User.class);
        UserInformation userInformation = this.repository.findByUser(entityUser);
        return userInformation != null ? this.modelMapper.map(userInformation, UserInformationServiceModel.class) : null;
    }

    @Override
    public void createFirstTime(UserServiceModel user) {

        if (!this.hasInformation(user)) {
            UserInformationServiceModel userInformation = new UserInformationServiceModel();
            userInformation.setUser(user);
            userInformation.setStatusMessage("");
            userInformation.setAboutYou("");
            userInformation.setAvatarPath("");

            this.save(userInformation, UserInformationServiceModel.class);
        }

    }

    @Override
    public boolean hasInformation(UserServiceModel user) {
        User entityUser = super.objectMapper.convertValue(user, User.class);
        return super.repository.existsByUser(entityUser);
    }


}
