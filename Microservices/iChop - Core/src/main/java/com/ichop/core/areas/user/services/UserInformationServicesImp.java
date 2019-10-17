package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.entities.UserInformation;
import com.ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.repositories.UserInformationRepository;
import com.ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class UserInformationServicesImp extends BaseService<UserInformation, UserInformationRepository> implements UserInformationServices {

    @Autowired
    public UserInformationServicesImp(ModelMapper modelMapper, UserInformationRepository repository) {
        super(modelMapper, repository);
    }


    @Override
    public UserInformationServiceModel update(UserUpdateProfileInformationBindingModel bindingModel) {

        if (bindingModel.getUser() == null) {
            throw new UserNotFoundException();
        }

        User user = this.modelMapper.map(bindingModel.getUser(),User.class);
        UserInformationServiceModel userInformationServiceModel = this.modelMapper.map(bindingModel, UserInformationServiceModel.class);
        userInformationServiceModel.setId(this.repository.findByUser(user).getId());
        try{
            userInformationServiceModel.setBirthDate(LocalDate.parse(bindingModel.getBirthDate()));
        }catch (DateTimeParseException ex){}

        return this.save(userInformationServiceModel, UserInformationServiceModel.class);
    }

    @Override
    public UserInformationServiceModel getByUser(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);
        UserInformation userInformation = this.repository.findByUser(entityUser);
        return userInformation != null ? this.modelMapper.map(userInformation,UserInformationServiceModel.class) : null;
    }

    @Override
    public void createFirstTime(UserServiceModel user) {

        if (!this.isUserInformationExistsByUser(user)) {
            UserInformationServiceModel userInformation = new UserInformationServiceModel();
            userInformation.setUser(user);
            userInformation.setStatusMessage("");
            userInformation.setAboutYou("");
            userInformation.setAvatarPath("");

            this.save(userInformation,UserInformationServiceModel.class);
        }

    }

    @Override
    public boolean isUserInformationExistsByUser(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user, User.class);
        UserInformation foundedInfo = this.repository.findByUser(entityUser);
        return foundedInfo != null;
    }


}
