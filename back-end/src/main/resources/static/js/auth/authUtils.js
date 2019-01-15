function getAuthUtil() {
    const EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    const PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    const USERNAME_MIN_LENGTH = 3;

    const API_USERNAME_AVAILABILITY_URL = '/api/users/exists?username=';
    const API_EMAIL_AVAILABILITY_URL = '/api/users/exists?email=';


    //Username validators:

    function validateUsernameLength(usernameField) {

        let val = usernameField.val();
        if (val.length >= USERNAME_MIN_LENGTH) {
            return true;
        }
        return false;
    }


    function isUsernameAvailable(usernameField) {
        let username = usernameField.val();
        let returnResult = false;

        $.ajax({
            method: 'GET',
            url: API_USERNAME_AVAILABILITY_URL + username,
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
            url: API_EMAIL_AVAILABILITY_URL + email,
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
        errorField.css(`display`, `none`);
    }

    function onSuccessWithoutErrorField(field) {
        field.removeClass("border border-danger");
        field.addClass("border border-success");
    }

    function onFailurePass(field, errorField) {
        errorField.css(`display`, `inline-block`);
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
        , validateEmailMatch, isEmailAvailable, isEmailAvailable
        , onSuccessPass, onFailurePass, onSuccessWithoutErrorField, onFailurePassWithoutErrorField
    };
}