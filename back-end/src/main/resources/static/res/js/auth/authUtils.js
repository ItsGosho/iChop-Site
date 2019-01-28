function getAuthUtil() {

    //Username validators:

    function validateUsernameLength(usernameField) {

        let val = usernameField.val();
        if (val.length >= USERNAME_MIN_LENGTH && val.length <= USERNAME_MAX_LENGTH) {
            return true;
        }
        return false;
    }


    function isUsernameAvailable(usernameField) {
        let username = usernameField.val();
        let returnResult = false;

        $.ajax({
            method: 'GET',
            url: URL_API_USERNAME_AVAILABILITY + username,
            async: false,
            success: function (result) {
                returnResult = !result;
            }
        });
        return returnResult;
    }

    //Password validators:

    function validatePasswordEquals(passwordField, confirmPasswordField) {

        if (!this.validatePasswordMatchPattern(passwordField)) {
            return false;
        }

        let passwordValue = passwordField.val();
        let confirmPasswordValue = confirmPasswordField.val();

        if (passwordValue === confirmPasswordValue) {
            return true;
        } else {
            return false;
        }
    }

    function validatePasswordMatchPattern(passwordField) {

        let val = passwordField.val();
        let matcher = new RegExp(PASSWORD_PATTERN);

        if (matcher.test(val)) {
            return true;
        } else {
            return false;
        }

    }

    //Email validators:

    function validateEmailMatch(emailField) {

        let val = emailField.val();
        let matcher = new RegExp(EMAIL_PATTERN);

        if (matcher.test(val)) {
            return true;
        } else {
            return false;
        }

    }

    function isEmailAvailable(emailField) {

        let email = emailField.val();
        let returnResult = false;

        $.ajax({
            method: 'GET',
            url: URL_API_EMAIL_AVAILABILITY + email,
            async: false,
            success: function (result) {
                returnResult = !result;
            }
        });


        return returnResult;
    }

    function onSuccessPass(field, errorField) {
        field.removeClass("border border-danger");
        field.addClass("border border-success");
        errorField.hide();
    }

    function onSuccessWithoutErrorField(field) {
        field.removeClass("border border-danger");
        field.addClass("border border-success");
    }

    function onFailurePass(field, errorField) {
        errorField.show();
        field.addClass("border border-danger");
    }

    function onFailurePassWithoutErrorField(field) {
        field.addClass("border border-danger");
    }

    //Configs:

    function runShowPasswordButton(showPasswordsButton, passwordField, confirmPasswordField) {
        showPasswordsButton.on(`click`, showOrHidePassword);

        function showOrHidePassword() {
            if (passwordField.attr(`type`) === 'password') {
                passwordField.attr(`type`, `text`);
                confirmPasswordField.attr(`type`, `text`);

                showPasswordsButton.text(`Hide`);
            } else {
                passwordField.attr(`type`, `password`);
                confirmPasswordField.attr(`type`, `password`);
                showPasswordsButton.text(`Show`);
            }
        }
    }

    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });

    return {
        validateUsernameLength, isUsernameAvailable,
        validatePasswordMatchPattern, validatePasswordEquals, runShowPasswordButton
        , validateEmailMatch, isEmailAvailable
        , onSuccessPass, onFailurePass, onSuccessWithoutErrorField, onFailurePassWithoutErrorField
    };
}