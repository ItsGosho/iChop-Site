package com.ichop.core.helpers.view.user_all;

import com.ichop.core.domain.entities.users.UserRoles;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_all.UsersAllViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.role.UserRoleServices;
import com.ichop.core.service.user.UserServices;
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
                .filter(x -> this.userRoleServices.findHighestRoleOfUser(x).getAuthority().toUpperCase().equals(UserRoles.valueOf(role).name().toUpperCase()))
                .map(x -> this.modelMapper.map(x, UsersAllViewModel.class))
                .collect(Collectors.toList());

        return new PageImpl<>(usersAllViewModels);
    }

}
