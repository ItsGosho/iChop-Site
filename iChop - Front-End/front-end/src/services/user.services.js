import ServerRoutingURLs from "../constants/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.USER;

const UserServices = {

    async login(email, password) {
        let response = await Requester.post(Endpoints.LOGIN, {email, password});

        if (response.error) {
            NotificationHelper.showErrorNotification(response.error);
        } else {
            NotificationHelper.showSuccessNotification(response.message);
        }
    },

    async register(username, password, confirmPassword, email) {
        let response = await Requester.post(Endpoints.REGISTER, {username, password, confirmPassword, email});
        console.log(response);
        /* if (response.error) {
             NotificationHelper.showErrorNotification(response.error);
         } else {
             NotificationHelper.showSuccessNotification(response.message);
         }*/
    },

    async logout() {
        let response = await Requester.post(Endpoints.LOGOUT, {});
        console.log(response);
    }

};

export default UserServices;