function runForgottenPasswordValidationsAndUtils() {
    let usernameOrEmail = $(`#input-usernameOrEmail-forgottenPasswordForm`);
    let errorUsernameOrEmailDoesntExists = $(`#error-usernameOrEmailDoesntExists-forgottenPasswordForm`);

    $(`#button-proceedEmailSend-forgottenPasswordForm`).on(`click`,function () {

        let resultUsernameAvailability = getAuthUtil().isUsernameAvailable(usernameOrEmail);
        let resultEmailAvailability = getAuthUtil().isEmailAvailable(usernameOrEmail);

        if (!resultUsernameAvailability ||
            !resultEmailAvailability) {

            return true;
        }
        getAuthUtil().onFailurePass(usernameOrEmail,errorUsernameOrEmailDoesntExists);
        return false;
    });
}