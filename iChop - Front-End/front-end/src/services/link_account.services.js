import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.LINK_ACCOUNT;

const LinkAccountServices = {

    async isKeyValid(key) {
        let response = await Requester.get(Endpoints.IS_KEY_VALID(key));

        return response.data ? response.data.result : false;
    },

    async retrieveKey(key) {
        let response = await Requester.get(Endpoints.KEY_RETRIEVE(key));
        return response.data;
    },

    async retrieveLink(username) {
        let response = await Requester.get(Endpoints.LINK_RETRIEVE(username));

        return response.data ? response.data : {};
    },

    async link(key) {
        let response = await Requester.post(Endpoints.LINK_CREATE, {linkKey: key});
        NotificationHelper.showNotificationByResponse(response);
        return response;
    },

    async unlink(username) {
        let response = await Requester.post(Endpoints.LINK_REMOVE, {username});
        NotificationHelper.showNotificationByResponse(response);
        return response;
    },

};

export default LinkAccountServices;