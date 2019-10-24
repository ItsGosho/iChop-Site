package ichop.core.areas.user.web.controllers;

import ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import ichop.core.areas.player.services.PlayerLinkJmsServices;
import ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByUser;
import ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import ichop.core.areas.user.helpers.view.user_options.UserProfileOptionsInformationViewHelper;
import ichop.core.areas.user.services.UserInformationServices;
import ichop.core.areas.user.services.UserServices;
import ichop.core.areas.user.services.UserWebStorageJmsServices;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserProfileOptionsController extends BaseController {

    private final UserServices userServices;
    private final UserInformationServices userInformationServices;
    private final UserProfileOptionsInformationViewHelper userProfileOptionsInformationViewHelper;
    private final PlayerLinkJmsServices playerLinkJmsServices;
    private final UserWebStorageJmsServices userWebStorageJmsServices;

    public UserProfileOptionsController(UserServices userServices, UserInformationServices userInformationServices, UserProfileOptionsInformationViewHelper userProfileOptionsInformationViewHelper, PlayerLinkJmsServices playerLinkJmsServices, UserWebStorageJmsServices userWebStorageJmsServices) {
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
        this.userProfileOptionsInformationViewHelper = userProfileOptionsInformationViewHelper;
        this.playerLinkJmsServices = playerLinkJmsServices;
        this.userWebStorageJmsServices = userWebStorageJmsServices;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET)
    public ModelAndView getProfileInformationOptions(Principal principal, ModelAndView modelAndView) {
        this.userInformationServices.createFirstTime(this.userServices.findUserByUsername(principal.getName()));
        UserProfileOptionsInformationViewModel userInfo = this.userProfileOptionsInformationViewHelper.create(principal.getName());

        modelAndView.addObject("optionsPage", "user/options/user-options-profile-information");
        modelAndView.addObject("userInfo", userInfo);

        return super.page("user/options/user-options-base", "Options", modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_POST)
    public String proceedProfileInformationUpdate(@Valid @ModelAttribute UserUpdateProfileInformationBindingModel bindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
        }

        this.userInformationServices.createFirstTime(bindingModel.getUser());
        this.userInformationServices.update(bindingModel);
        this.userWebStorageJmsServices.sendUpdateAvatarRequest(bindingModel.getUser().getUsername(), bindingModel.getAvatarBinary());

        return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_CHANGE_PASSWORD_GET)
    public ModelAndView getChangePasswordPage(ModelAndView modelAndView) {

        modelAndView.addObject("optionsPage", "user/options/user-options-profile-change_password");

        return super.page("user/options/user-options-base", "Change Password", modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_CHANGE_PASSWORD_POST)
    public ModelAndView proceedChangePassword(@Valid @ModelAttribute UserResetPasswordBindingModelByUser bindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.viewWithMessage("notification/info", "Whoops", "Error occured with the data that you have entered!");
        }

        this.userServices.resetPassword(bindingModel);

        return super.viewWithMessage("notification/info", "Successful Change", "You have successful changed your password!");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_MINECRAFT_GET)
    public ModelAndView getMinecraftPage(Principal principal, ModelAndView modelAndView) {

        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkJmsServices.getPlayerDataBySiteUser(user.getUsername());

        modelAndView.addObject("playerData", playerData);
        modelAndView.addObject("optionsPage", "user/options/user-options-profile-minecraft");

        return super.page("user/options/user-options-base", "Minecraft", modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_MINECRAFT_UNLINK_POST)
    public String proceedAccountUnlink(Principal principal) {

        this.playerLinkJmsServices.unlinkPlayerAccount(principal.getName());

        return super.redirect(URLConstants.USER_PROFILE_OPTIONS_MINECRAFT_GET);
    }

}
