function runRegisterFormValidation() {

    const EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    const PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    const API_USERNAME_AVAILABILITY_URL = '/api/users/exists?username=';
    const API_EMAIL_AVAILABILITY_URL = '/api/users/exists?email=';

    //Validate Username;

    let usernameField = $(`#input-username-registerForm`);
    let errorUsernameLength = $(`#error-usernameIsShort-registerForm`);
    let errorUsernameAvailability = $(`#error-usernameAlreadyExists-registerForm`);

    usernameField.on(`keyup`, validateUsernameLength);

    function validateUsernameLength() {
        let val = usernameField.val();
        if (val.length >= 3) {
            usernameField.removeClass("border border-danger");
            usernameField.addClass("border border-success");
            errorUsernameLength.css(`display`, `none`);
            return true;
        } else {
            errorUsernameLength.css(`display`, `inline-block`);
            usernameField.addClass("border border-danger");
            return false;
        }
    }

    function validateUsernameAvailability(){
        let username = usernameField.val();
        let returnResult = false;

        if(!validateUsernameLength()){
            return false;
        }

        $.ajax({
            method: 'GET',
            url: API_USERNAME_AVAILABILITY_URL+username,
            async: false,
            success: function (result) {
                if(!result){
                    usernameField.removeClass("border border-danger");
                    usernameField.addClass("border border-success");
                    errorUsernameAvailability.css(`display`, `none`);
                    returnResult=true;
                }else{
                    errorUsernameAvailability.css(`display`, `inline-block`);
                    usernameField.addClass("border border-danger");
                    returnResult=false;
                }
            }
        });
        return returnResult;
    }

    //Validate Passwords:
    let showPasswordsButton = $(`#button-showPassword-registerForm`);
    let passwordField = $(`#input-password-registerForm`);
    let confirmPasswordField = $(`#input-confirmPassword-registerForm`);

    passwordField.on(`keyup`, validatePasswordMatchPattern);
    confirmPasswordField.on(`keyup`, validatePasswordEquals);


    function validatePasswordEquals() {

        if (!validatePasswordMatchPattern()) {
            confirmPasswordField.addClass("border border-danger");
            return false;
        }

        let passwordValue = passwordField.val();
        let confirmPasswordValue = confirmPasswordField.val();

        if (passwordValue === confirmPasswordValue) {

            confirmPasswordField.removeClass("border border-danger");
            confirmPasswordField.addClass("border border-success");
            return true;
        } else {
            confirmPasswordField.addClass("border border-danger");
            return false;
        }
    }

    function validatePasswordMatchPattern() {
        let val = passwordField.val();
        let matcher = new RegExp(PASSWORD_PATTERN);

        if (matcher.test(val)) {
            passwordField.removeClass("border border-danger");
            passwordField.addClass("border border-success");
            return true;
        } else {
            passwordField.addClass("border border-danger");
            return false;
        }
    }

    //Validate Email:
    let emailField = $(`#input-email-registerForm`);
    let errorEmailExist = $(`#error-emailAlreadyExists-registerForm`);
    emailField.on(`keyup`, validateEmail);

    function validateEmail() {
        let val = emailField.val();
        let matcher = new RegExp(EMAIL_PATTERN);

        if (matcher.test(val)) {
            emailField.removeClass("border border-danger");
            emailField.addClass("border border-success");
            return true;
        } else {
            emailField.addClass("border border-danger");
            return false;
        }

    }

    function validateEmailAvailability(){
        let email = emailField.val();
        let returnResult = false;

        if(!validateEmail()){
            return false;
        }

        $.ajax({
            method: 'GET',
            url: API_EMAIL_AVAILABILITY_URL+email,
            async: false,
            success: function (result) {
                if(!result){
                    emailField.removeClass("border border-danger");
                    emailField.addClass("border border-success");
                    errorEmailExist.css(`display`, `none`);
                    returnResult=true;
                }else{
                    errorEmailExist.css(`display`, `inline-block`);
                    emailField.addClass("border border-danger");
                    returnResult=false;
                }
            }
        });
        return returnResult;
    }

    let proceedRegisterButton = $(`#button-proceedRegister-registerForm`);

    proceedRegisterButton.on(`click`, function () {
        //It is like that ,because if the methods are in the if method
        //it will stop at the first `true` match and the other input fields
        //will not be painted with the corresponding color :)

        let resultUsernameLength = validateUsernameLength();
        let resultUsernameAvailability = validateUsernameAvailability();
        let resultEmailAvailability = validateEmailAvailability();
        let resultPasswordEquals = validatePasswordEquals();
        let resultPasswordMatchPattern = validatePasswordMatchPattern();
        let resultEmail = validateEmail();

        if (resultUsernameLength &&
            resultPasswordEquals &&
            resultPasswordMatchPattern &&
            resultEmail &&
            resultUsernameAvailability &&
            resultEmailAvailability) {
            return true;
        }
        return false;
    });

    //Show/Hide Passwords:
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



    //Password popover

    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });

}