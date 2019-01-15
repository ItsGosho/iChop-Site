function runAuthFormsConfig() {
    $('body').on("click", ".dropdown-menu", function (e) {
        $(this).parent().is(".show") && e.stopPropagation();
    });

    $(`#button-goToRegister-loginForm`).on(`click`, function () {
        $(`#div-loginForm`).css('display', 'none');
        $(`#div-registerForm`).css('display', 'inline');

    });

    $(`#button-goToLogin-registerForm`).on(`click`, backToLoginForm);
    $(`#button-goToLogin-forgottenPasswordForm`).on(`click`, backToLoginForm);

    $(`#button-goToForgottenPassword-loginForm`).on(`click`, function () {
        $(`#div-loginForm`).css('display', 'none');
        $(`#div-forgottenPasswordForm`).css('display', 'inline');

    });

    function backToLoginForm() {
        $(`#div-registerForm`).css('display', 'none');
        $(`#div-forgottenPasswordForm`).css('display', 'none');
        $(`#div-loginForm`).css('display', 'inline');
    }
}