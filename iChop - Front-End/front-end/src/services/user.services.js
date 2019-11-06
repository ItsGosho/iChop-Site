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

    async logout() {

    }

};

export default UserServices;