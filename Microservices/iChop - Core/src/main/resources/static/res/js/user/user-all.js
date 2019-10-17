function runSortingUrlScript() {
    let inputSearchByUsername = $(`#input-searchByUsername-usersAll`);
    let buttonSearchByUsername = $(`#button-searchByUsernameSend-usersAll`);

    let iconSortUsername = $(`#icon-sortByUsername-usersAll`);
    let iconSortEmail = $(`#icon-sortByEmail-usersAll`);
    let iconSortRegistrationDate = $(`#icon-sortByRegistrationDate-usersAll`);

    let sortParam = "sort";
    let pageParam = "page";
    let usernameParam = "isUsernameLike";
    let hasRoleParam = "hasRole";

    let usernameSortValue = "username,asc";
    let emailSortValue = "email,asc";
    let registrationDateSortValue = "registrationDate,asc";
    let adminRoleValue = 'ADMIN';
    let moderatorRoleValue = 'MODERATOR';
    let userRoleValue = 'USER';

    let url = new URL(window.location.href);

    if(url.searchParams.get(pageParam) === null){
        url.searchParams.set(pageParam,0);
        window.location.href = url.href;
    }

    if(url.searchParams.get(usernameParam) !== null){
        inputSearchByUsername.val(url.searchParams.get(usernameParam));
    }

    colorSortSelectors();

    buttonSearchByUsername.on(`click`, function (event) {
        let url = new URL(window.location.href);
        url.searchParams.set(usernameParam,inputSearchByUsername.val());
        window.location.href = url.href;
    });

    $(`#a-showOnlyAdministrators-usersAll`).on(`click`, function (event) {
        event.preventDefault();
        window.location.href = prepareUrl(hasRoleParam,adminRoleValue);
    });

    $(`#a-showOnlyModerators-usersAll`).on(`click`, function (event) {
        event.preventDefault();
        window.location.href = prepareUrl(hasRoleParam,moderatorRoleValue);
    });

    $(`#a-showOnlyUsers-usersAll`).on(`click`, function (event) {
        event.preventDefault();
        window.location.href = prepareUrl(hasRoleParam,userRoleValue);
    });

    $(`#a-sortByUsername-usersAll`).on(`click`, function (event) {
        window.location.href = prepareUrl(sortParam,usernameSortValue);
    });

    $(`#a-sortByEmail-usersAll`).on(`click`, function (event) {
        window.location.href = prepareUrl(sortParam,emailSortValue);
    });

    $(`#a-sortByRegistrationDate-usersAll`).on(`click`, function (event) {
        window.location.href = prepareUrl(sortParam,registrationDateSortValue);
    });

    function prepareUrl(param,value) {
        let url = new URL(window.location.href);

        url.searchParams.set(param,value);

        return url.href;
    }

    function colorSortSelectors() {
        let url = new URL(window.location.href);

        switch (url.searchParams.get(sortParam)) {
            case usernameSortValue:
                iconSortUsername.css('color','green');
                break;
            case emailSortValue:
                iconSortEmail.css('color','green');
                break;
            case registrationDateSortValue:
                iconSortRegistrationDate.css('color','green');
                break;
        }
    }
}