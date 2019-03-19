function runRegisterValidationsAndUtils() {
    let buttonShowPassword = $(`#button-showPassword-registerForm`);
    let buttonProcedRegister = $(`#button-proceedRegister-registerForm`);

    let usernameField = $(`#input-username-registerForm`);
    let passwordField = $(`#input-password-registerForm`);
    let confirmPasswordField = $(`#input-confirmPassword-registerForm`);
    let emailField = $(`#input-email-registerForm`);

    let errorUsernameIsShort = $(`#error-usernameIsShort-registerForm`);
    let errorUsernameAlreadyExists = $(`#error-usernameAlreadyExists-registerForm`);
    let errorEmailAlreadyExists = $(`#error-emailAlreadyExists-registerForm`);
    let errorPasswordsDoesntMatch = $(`#error-passwordsDoesntMatch-registerForm`);

    usernameField.on(`keyup`, validateUsername);

    passwordField.on(`keyup`, validatePassword);

    confirmPasswordField.on(`keyup`, validateConfirmPassword);

    emailField.on(`keyup`, validateEmail);

    getAuthUtil().runShowPasswordButton(buttonShowPassword, passwordField, confirmPasswordField);

    buttonProcedRegister.on(`click`, function () {

        let isValid = false;

        let resultUsername = validateUsername();
        let resultPassword = validatePassword();
        let resultConfirmPassword = validateConfirmPassword();
        let resultEmail = validateEmail();


        if (resultUsername && resultPassword && resultConfirmPassword && resultEmail) {

            let resultUsernameAvailability = validateUsernameAvailability();
            let resultEmailAvailability = validateEmailAvailability();

            if (resultUsernameAvailability &&
                resultEmailAvailability) {
                isValid = true;
            }
        }
        return isValid;
    });

    function validateUsername() {
        let result = getAuthUtil().validateUsernameLength(usernameField);
        if (result) {
            getAuthUtil().onSuccessPass(usernameField, errorUsernameIsShort);
        } else {
            getAuthUtil().onFailurePass(usernameField, errorUsernameIsShort);
        }
        return result;
    }

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

    function validateEmail() {
        let result = getAuthUtil().validateEmailMatch(emailField);
        if (result) {
            getAuthUtil().onSuccessWithoutErrorField(emailField);
        } else {
            getAuthUtil().onFailurePassWithoutErrorField(emailField);
        }
        return result;
    }

    function validateUsernameAvailability() {
        if(!validateEmail()){
            return false;
        }

        let result = getAuthUtil().isUsernameAvailable(usernameField);
        if (result) {
            getAuthUtil().onSuccessPass(usernameField, errorUsernameAlreadyExists);
        } else {
            getAuthUtil().onFailurePass(usernameField, errorUsernameAlreadyExists);
        }
        return result;
    }

    function validateEmailAvailability() {
        if(!validateEmail()){
           return false;
        }

        let result = getAuthUtil().isEmailAvailable(emailField);
        if (result) {
            getAuthUtil().onSuccessPass(emailField, errorEmailAlreadyExists);
        } else {
            getAuthUtil().onFailurePass(emailField, errorEmailAlreadyExists);
        }
        return result;
    }
}