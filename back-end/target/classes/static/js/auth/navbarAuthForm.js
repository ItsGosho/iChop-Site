function runAuthForm() {
    $('body').on("click", ".dropdown-menu", function (e) {
        $(this).parent().is(".show") && e.stopPropagation();
    });

    $(`#buttonGoToRegister`).on(`click`, function () {
        $(`#loginPage`).css('display', 'none');
        $(`#registerPage`).css('display', 'inline');

    });

    $(`#buttonGoToLogin`).on(`click`, back);
    $(`#buttonGoToLogin2`).on(`click`, back);

    $(`#buttonGoToForgottenPassword`).on(`click`, function () {
        $(`#loginPage`).css('display', 'none');
        $(`#forgottenPasswordPage`).css('display', 'inline');

    });

    function back() {
        $(`#registerPage`).css('display', 'none');
        $(`#forgottenPasswordPage`).css('display', 'none');
        $(`#loginPage`).css('display', 'inline');
    }

}