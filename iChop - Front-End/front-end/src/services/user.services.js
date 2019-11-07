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
        console.log(response);
        /* if (response.error) {
             NotificationHelper.showErrorNotification(response.error);
         } else {
             NotificationHelper.showSuccessNotification(response.message);
         }*/
    },

    async register(username, password, confirmPassword, email) {
        let response = await Requester.post(Endpoints.REGISTER, {username, password, confirmPassword, email}, true);
        console.log(response);
        /* if (response.error) {
             NotificationHelper.showErrorNotification(response.error);
         } else {
             NotificationHelper.showSuccessNotification(response.message);
         }*/
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