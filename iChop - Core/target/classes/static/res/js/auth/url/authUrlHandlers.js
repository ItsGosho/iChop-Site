function runAuthUrlHandlers() {
    let url = document.URL;
    let buttonMinimized = $(`#button-minimized-navbar`);
    let buttonSignIn = $(`#button-signIn-navBar`);
    let buttonGoToRegister = $(`#button-goToRegister-loginForm`);

    if (url.endsWith(PARAMETER_LOGIN_REQUIRE)) {
        buttonMinimized.click();
        buttonSignIn.click();
    } else if (url.endsWith(PARAMETER_LOGIN_ERROR)) {
        $(`#error-invalidCredentials-loginForm`).css(`display`, `inline-block`);
        $(`#input-usernameOrEmail-loginForm`).addClass("border border-danger");
        $(`#input-password-loginForm`).addClass("border border-danger");
        buttonMinimized.click();
        buttonSignIn.click();
    } else if (url.endsWith(PARAMETER_REGISTER_REQUIRE)) {
        buttonMinimized.click();
        buttonSignIn.click();
        buttonGoToRegister.click();
    }
}