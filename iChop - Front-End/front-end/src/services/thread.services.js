import ServerRoutingURLs from "../constants/routing/server.routing.urls";
import Requester from "../requesters/requester";
import NotificationHelper from "../helpers/notification.helper";

const Endpoints = ServerRoutingURLs.CORE.THREAD;

const ThreadServices = {

    async create(title,content) {
        let response = await Requester.post(Endpoints.CREATE,{title,content});

        NotificationHelper.showNotificationByResponse(response);
    },

};

export default ThreadServices;