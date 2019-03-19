function runAuthFormsConfig() {
    $('body').on("click", ".dropdown-menu", function (e) {
        $(this).parent().is(".show") && e.stopPropagation();
    });

    $(`#button-goToRegister-loginForm`).on(`click`, function () {
        $(`#div-login-dropdown`).css('display', 'none');
        $(`#div-register-dropdown`).css('display', 'inline');

    });

    $(`#button-goToLogin-registerForm`).on(`click`, backToLoginForm);
    $(`#button-goToLogin-forgottenPasswordForm`).on(`click`, backToLoginForm);

    $(`#button-goToForgottenPassword-loginForm`).on(`click`, function () {
        $(`#div-login-dropdown`).css('display', 'none');
        $(`#div-forgotten_password-dropdown`).css('display', 'inline');

    });

    function backToLoginForm() {
        $(`#div-register-dropdown`).css('display', 'none');
        $(`#div-forgotten_password-dropdown`).css('display', 'none');
        $(`#div-login-dropdown`).css('display', 'inline');
    }
}