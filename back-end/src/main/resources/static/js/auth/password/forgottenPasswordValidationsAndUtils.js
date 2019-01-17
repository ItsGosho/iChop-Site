function runForgottenPasswordValidationsAndUtils() {
    let usernameOrEmail = $(`#input-usernameOrEmail-forgottenPasswordForm`);
    let errorUsernameOrEmailDoesntExists = $(`#error-noUserExistsWithTheProvidedCredentials-forgottenPasswordForm`);
    let notificationPleaseWait = $(`#notification-pleaseWait-forgottenPasswordForm`);
    let notificationSuccessful = $(`#notification-successful-forgottenPasswordForm`);

    $(`#button-proceedEmailSend-forgottenPasswordForm`).on(`click`,function () {

        let resultUsernameAvailability = getAuthUtil().isUsernameAvailable(usernameOrEmail);
        let resultEmailAvailability = getAuthUtil().isEmailAvailable(usernameOrEmail);

        notificationSuccessful.hide();
        if (!resultUsernameAvailability ||
            !resultEmailAvailability) {

            errorUsernameOrEmailDoesntExists.hide();
            notificationPleaseWait.show();
            usernameOrEmail.removeClass("border border-danger");
            usernameOrEmail.addClass("border border-success");

            $.ajax({
                method: "POST",
                url: "api/users/mail/reset/password",
                data: $(`#form-forgottenPassword`).serialize(),
                success: function (isSuccessful) {
                    if(isSuccessful){
                        notificationPleaseWait.hide();
                        notificationSuccessful.show();
                    }
                }
            });

            return true;
        }
        getAuthUtil().onFailurePass(usernameOrEmail,errorUsernameOrEmailDoesntExists);
        return false;
    });
}