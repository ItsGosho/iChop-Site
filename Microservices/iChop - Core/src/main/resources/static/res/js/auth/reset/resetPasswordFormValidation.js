function runResetPasswordFormValidation(buttonShowPassword,buttonProceedResetPassword,passwordField,confirmPasswordField,errorPasswordsDoesntMatchField) {
    passwordField.on(`keyup`, validatePassword);

    confirmPasswordField.on(`keyup`, validateConfirmPassword);

    getAuthUtil().runShowPasswordButton(buttonShowPassword,passwordField,confirmPasswordField);

    buttonProceedResetPassword.on(`click`,function () {

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
            getAuthUtil().onSuccessPass(confirmPasswordField, errorPasswordsDoesntMatchField);
        } else {
            getAuthUtil().onFailurePass(confirmPasswordField, errorPasswordsDoesntMatchField);
        }
        return result;
    }
}