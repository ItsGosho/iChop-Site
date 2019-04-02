package com.ichop.core.areas.user.helpers.view.user_all;

import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_all.UsersAllViewModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersAllViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;

    @Autowired
    public UsersAllViewHelper(ModelMapper modelMapper, UserServices userServices, UserRoleServices userRoleServices) {
        super(modelMapper);
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
    }

    public Page<UsersAllViewModel> create(Pageable pageable){
        Page<UserServiceModel> users = this.userServices.findAll(pageable);
        return users.map(x-> super.modelMapper.map(x, UsersAllViewModel.class));
    }

    public Page<UsersAllViewModel> createWhereUsernameContains(String containingWord,Pageable pageable){
        Page<UserServiceModel> users = this.userServices.findUsersByUsernameContains(containingWord,pageable);
        return users.map(x-> super.modelMapper.map(x, UsersAllViewModel.class));
    }

    public Page<UsersAllViewModel> createWhereRoleIsPresent(String role, Pageable pageable){
        Page<UserServiceModel> users = this.userServices.findUsersWhomHasRole(role, pageable);

        List<UsersAllViewModel> usersAllViewModels = users.stream()
                .filter(x -> this.userRoleServices.findHighestOfUser(x).getAuthority().toUpperCase().equals(UserRoles.valueOf(role).name().toUpperCase()))
                .map(x -> this.modelMapper.map(x, UsersAllViewModel.class))
                .collect(Collectors.toList());

        return new PageImpl<>(usersAllViewModels);
    }

}
