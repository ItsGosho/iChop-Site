import ServerRoutingURLs from "../constants/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.USER;

const UserServices = {

    async login(email, password) {
        let response = await Requester.post(Endpoints.LOGIN, {email, password}, true);

        if (response.error) {
            NotificationHelper.showErrorNotification(response.error);
        } else {
            NotificationHelper.showSuccessNotification(response.message);
        }

        return response.data;
    },

    async register(username, password, confirmPassword, email) {
        let response = await Requester.post(Endpoints.REGISTER, {username, password, confirmPassword, email}, true);
        NotificationHelper.showNotificationByResponse(response);

        return response.successful;
    },

    async forgottenPassword(email) {
        let response = await Requester.post(Endpoints.FORGOTTEN_PASSWORD, {email}, true);
        NotificationHelper.showNotificationByResponse(response);
    },

    async changePasswordByToken(token,password,confirmPassword) {
        let response = await Requester.post(Endpoints.CHANGE_PASSWORD_BY_TOKEN, {token,password,confirmPassword}, true);
        NotificationHelper.showNotificationByResponse(response);
    },

    async retrieveUserByToken() {
        let response = await Requester.get(Endpoints.GET_CURRENT_AUTHENTICATED);

        if (!response.error) {
            return response.data;
        }
    },

    async logout() {
        await Requester.post(Endpoints.LOGOUT, {});
    }

};

export default UserServices;