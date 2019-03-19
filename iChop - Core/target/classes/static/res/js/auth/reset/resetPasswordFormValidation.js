function runResetPasswordFormValidation() {
    let buttonShowPassword = $(`#button-showPassword-registerForm`);
    let buttonProceedReset = $(`#button-proceedResetPassword-resetPasswordForm`);

    let passwordField = $(`#input-password-resetPasswordForm`);
    let confirmPasswordField = $(`#input-confirmPassword-resetPasswordForm`);

    let errorPasswordsDoesntMatch = $(`#error-passwordsDoesntMatch-resetPasswordForm`);

    passwordField.on(`keyup`, validatePassword);

    confirmPasswordField.on(`keyup`, validateConfirmPassword);

    getAuthUtil().runShowPasswordButton(buttonShowPassword,passwordField,confirmPasswordField);

    buttonProceedReset.on(`click`,function () {

        let resultPassword = validatePassword();
        let resultConfirmPassword = validateConfirmPassword();

        if (resultPassword && resultConfirmPassword) {
            return true;
        }
        return false;
    });


    function validatePassword() {
        let result = getAuthUtil().validatePasswordMatchPattern(passwordField);
        if (result) {
            getAuthUtil().onSuccessWithoutErrorField(passwordField);
        } else {
            getAuthUtil().onFailurePassWithoutErrorField(passwordField);
        }
        return result;
    }

    function validateConfirmPassword() {
        let result = getAuthUtil().validatePasswordEquals(passwordField, confirmPasswordField);
        if (result) {
            getAuthUtil().onSuccessPass(confirmPasswordField, errorPasswordsDoesntMatch);
        } else {
            getAuthUtil().onFailurePass(confirmPasswordField, errorPasswordsDoesntMatch);
        }
        return result;
    }
}